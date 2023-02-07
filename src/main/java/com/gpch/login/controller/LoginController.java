package com.gpch.login.controller;

import com.gpch.login.model.User;
import com.gpch.login.service.UserService;
import com.gpch.login.utils.GenerateEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private GenerateEmail generarEmail;

    @GetMapping(value={"/", "/login"})
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping(value="/registration")
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping(value = "/registration")
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByUserName(user.getUserName());
        if (userExists != null) {
            bindingResult
                    .rejectValue("userName", "error.user",
                            "Ya existe un usuario registrado con ese nombre");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "Usuario registrado corectamente.");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }

    @GetMapping(value="/admin/home")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }

    @GetMapping(value="/recuperar")
    public ModelAndView recuperar(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("recuperar");
        return modelAndView;
    }

    @PostMapping(value = "/recuperar")
    public ModelAndView recuperarContrasenia(@Valid User user, BindingResult bindingResult) throws MessagingException {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findByEmailAndUserName(user.getEmail(),user.getUserName());
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("recuperar");
        }
        if (userExists == null) {
            bindingResult.rejectValue("userName", "error.user", "Usuario No encontrado");
        } else {
            String password = generarEmail.contraseniaAleatoria();
            userExists.setPassword(password);
            userService.updateUser(userExists);

            sendEmail(user,"Notificacion",generarEmail.generarEmail(userExists.getUserName(), password));
            modelAndView.addObject("successMessage", "Contraseña enviada a su email: "+ userExists.getEmail());
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("recuperar");
        }
        return modelAndView;
    }

    public void sendEmail(User user, String subject, String content) throws MessagingException {

            javax.mail.internet.MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            helper.setSubject(subject);

            helper.setText(content, true);
            helper.setTo(user.getEmail());
            mailSender.send(mimeMessage);
    }

}
