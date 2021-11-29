package ar.edu.unju.fi.poo.grupo04.service.imp;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.poo.grupo04.service.IJavaMailService;

/*
 *Clase JavaMail
 *Finalidad de enviar correos electronicos
 */
@Service("javaMailServiceImp")
public class JavaMailServiceImp implements IJavaMailService{
	static final Logger logger = Logger.getLogger(JavaMailServiceImp.class);
	/*
	 * Metodo enviarMail
	 * @params:parametros para la configuracion del email,DE,Contraseña,SMTP
	 */
	public void enviarMail(String receptor, String asunto, String mensaje) throws Exception {
		logger.info("Preparando para enviar el mensaje...");
		
		Properties properties = new Properties();
		
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		String myAccountEmail = "poo.tpfinal2021@gmail.com";
		String password = "tpfinal1234";
		
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myAccountEmail, password);
			}
		});
		
		Message message = prepararMensaje(session, myAccountEmail, receptor, asunto, mensaje);
		
		Transport.send(message);
		logger.info("Mensaje enviado correctamente...");
	}
	
	/*
	 * Metodo prepararMensaje,
	 * prepara el cuerpo del correo electronico a enviar en formato HTML
	 * Y por ultimo envia el correo
	 */
	public Message prepararMensaje(Session session, String myAccountEmail, String receptor, String asunto, String mensaje) {
		try {						
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(receptor));
			
	        BodyPart texto = new MimeBodyPart();
	        
	        texto.setContent(mensaje, "text/html");
			
	        MimeMultipart partes = new MimeMultipart("related");
	        
	        partes.addBodyPart(texto);
	       	        
	        message.setSubject(asunto);
	        message.setContent(partes);
	        
			return message;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}