package it.milano.project.util;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {

	public static void sendMail(String filename,String subject,String emailText,String emailRecipient) {

		final String username = "USERDOMAIN\\amm-pss";
		final String password = "password1";
		String myEmail = "serena.nicolazzo@sisal.it";
		String giulioEmail = "giulio.castellano@sisal.it";

		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp1.sisal.it");
		props.put("mail.smtp.port", "25");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myEmail));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(emailRecipient));
			message.addRecipient(Message.RecipientType.CC, new InternetAddress(myEmail));
			//message.addRecipient(Message.RecipientType.CC, new InternetAddress(giulioEmail));
			message.setSubject(subject);
			message.setText(emailText);


			DataSource source = new FileDataSource(filename);
			message.setDataHandler(new DataHandler(source));
			message.setFileName(filename);


			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}



}
