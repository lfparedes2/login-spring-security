package com.gpch.login.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "user_name")
    @Length(min = 5, message = "*El nombre de usuario debe contener más de 5 caracteres")
    @NotEmpty(message = "*Por favor ingrese un nombre de usuario")
    private String userName;
    @Column(name = "email")
    @Email(message = "*Por favor ingrese un email válido")
    @NotEmpty(message = "*Por favor ingrese un email")
    private String email;

    @Column(name = "password")
    @Length(min = 5, message = "*Su contraseña debe contener más de 5 caracteres")
    @NotEmpty(message = "*Por favor ingrese una contraseña")
    private String password;

    @Column(name = "name")
    @NotEmpty(message = "*Por favor ingrese sus nombres")
    private String name;

    @Column(name = "last_name")
    @NotEmpty(message = "*Por favor ingrese sus apellidos")
    private String lastName;

    @Column(name = "active")
    private Boolean active;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

}
