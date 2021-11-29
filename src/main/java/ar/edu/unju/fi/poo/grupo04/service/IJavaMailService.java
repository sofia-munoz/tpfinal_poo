package ar.edu.unju.fi.poo.grupo04.service;

import javax.mail.Message;
import javax.mail.Session;


public interface IJavaMailService {
	public void enviarMail(String receptor, String asunto, String mensaje) throws Exception;
	public Message prepararMensaje(Session session, String myAccountEmail, String receptor, String asunto, String mensaje);
}
