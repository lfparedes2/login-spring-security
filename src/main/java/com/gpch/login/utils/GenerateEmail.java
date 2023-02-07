package com.gpch.login.utils;

import org.springframework.stereotype.Service;

@Service
public class GenerateEmail {

    public String contraseniaAleatoria() {

        char[] chr={'A','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','0','1','3','4','5','6','7','8','9'};
        char[] aleatorio=new char[25];
        String contrasenia ="";
        for(int i=0;i<=12;i++){
            aleatorio[i]=chr[(int)(Math.random()*34)];
            contrasenia += aleatorio[i];
        }
        return contrasenia;
    }

    public String codigoVerificacion() {
        char[] chr={'0','1','2','3','4','5','6','7','8','9'};
        char[] aleatorio=new char[25];
        String condigoVerificacion ="";
        for(int i=0;i<=4;i++){
            aleatorio[i]=chr[(int)(Math.random()*10)];
            condigoVerificacion += aleatorio[i];
        }
        return condigoVerificacion;
    }
    public String generarEmail(String username, String contrasenia){
        String textEmail="<table width=\"100%\" cellpadding=\"0\" cellspacing=\"1\"\r\n" +
                "			style=\"border: solid 1px #000000;\">\r\n" +
                "			<tbody>\r\n" +
                "				<tr>\r\n" +
                "					<td>\r\n" +
                "						<table width=\"100%\" cellpadding=\"0\" cellspacing=\"5\"\r\n" +
                "							style=\"font-size: 10pt; font-family: Verdana, Arial, Helvetica, sans-serif;\">\r\n" +
                "							<tbody>\r\n" +
                "								<tr>\r\n" +
                "									<td STYLE=\"text-align: center;\"><b>SISTEMA DE GESTI&#211;N DE ARCHIVO</td>\r\n" +
                "								</tr>\r\n" +
                "								<tr>\r\n" +
                "									<td>&nbsp;</td>\r\n" +
                "								</tr>\r\n" +
                "								<tr>\r\n" +
                "									<td><b>CREDENCIALES DE ACCESO</td>\r\n" +
                "								</tr>\r\n" +
                "								<tr>\r\n" +
                "									<td>&nbsp;</td>\r\n" +
                "								</tr>\r\n" +
                "								<tr>\r\n" +
                "									<td>\r\n" +
                "										<table>\r\n" +
                "											<tbody>\r\n" +
                "												<tr>\r\n" +
                "													<td style=\"width: 86.4px;\"><strong>Usuario:&nbsp;</strong></td>\r\n" +
                "													<td style=\"width: 236.8px;\">"+ username +"</td>\r\n" +
                "												</tr>\r\n" +
                "												<tr>\r\n" +
                "													<td style=\"width: 86.4px;\"><strong>Contrase&ntilde;a:&nbsp;</strong></td>\r\n" +
                "													<td style=\"width: 35.2px;\">"+ contrasenia +"</td>\r\n" +
                "												</tr>\r\n" +
                "											</tbody>\r\n" +
                "										</table>\r\n" +
                "									</td>\r\n" +
                "								</tr>\r\n" +
                "								<tr>\r\n" +
                "									<td height=\"5px\">&#xA0;</td>\r\n" +
                "								</tr>\r\n" +
                "								<tr>\r\n" +
                "									<td>\r\n" +
                "										<p>\r\n" +
                "											<a href=\"https://fiscalia.uafe.gob.ec/informacion-fge-web/usuario/index.jsf\">Sistema de Gesti&#243;n de Archivo</a> </br>\r\n" +
                "										</p>\r\n" +
                "										<p>\r\n" +
                "											NOTA: Favor no responder este mensaje que ha sido emitido autom&#225;ticamente por el sistema de gesti&#243;n documental de la UAFE."	+
                "										</p>\r\n" +
                "									</td>\r\n" +
                "								</tr>\r\n" +
                "								<tr>\r\n" +
                "									<td>\r\n" +
                "										<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n" +
                "											<tr>\r\n" +
                "												<td valign=\"top\" width=\"17%\"><img width=\"176\"\r\n" +
                "													height=\"95\"\r\n" +
                "													src=\"https://sislaft.uafe.gob.ec/sislaft/resources/uaf/images/Logo_UAF.PNG\"></img>\r\n" +
                "												</td>\r\n" +
                "												<td valign=\"top\" width=\"83%\"><table width=\"100%\"\r\n" +
                "														border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n" +
                "														<tr>\r\n" +
                "															<td height=\"83\"\r\n" +
                "																style=\"font-size: 13px; font-family: 'Myriad Pro Light', Arial, Helvetica, sans-serif; color: #000000; background-color: #FFF;\">\r\n" +
                "																<strong>Unidad de An&#225;lisis Financiero y\r\n" +
                "																	Econ&#243;mico</strong><br /> <strong>Website:\r\n" +
                "																	http://www.uafe.gob.ec/</strong><br /> <strong>Direcci&#243;n:\r\n" +
                "																	Quito Av. Portugal E9-138 y Av. República de El Salvador, Edif. Plaza Real </strong> <br />\r\n" +
                "															<strong>Tel&#233;fonos: PBX (593-2) 3943940. </strong> <br />\r\n" +
                "														</tr>\r\n" +
                "													</table>\r\n" +
                "												</td>\r\n" +
                "											</tr>\r\n" +
                "										</table>\r\n" +
                "									</td>\r\n" +
                "								</tr>\r\n" +
                "							</tbody>\r\n" +
                "						</table>\r\n" +
                "					</td>\r\n" +
                "				</tr>\r\n" +
                "			</tbody>\r\n" +
                "		</table>";

        return textEmail;
    }
}
