package com.email.service;

import java.io.File;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
	public boolean sendEmailAttach(String subject, String message, String to) {
		String from = "gopal.jfsd@gmail.com";
		boolean f = false;
		// variable for mail
		String host="smtp.gmail.com";
		
		// get the system properties
		
		Properties properties = System.getProperties();
		System.out.println("Properties :" + properties);
		
		// Setting important information to properties object
		
		// set
		properties.put("mail.smtp.host",host);
		properties.put("mail.smtp.prot", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth","true");
		
		// step1: to get the session object..
		
		Session session = Session.getInstance(properties,new Authenticator() {
	
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("gopal.jfsd@gmail.com","cttikwcejsmgjwmh");
			}
		});
		session.setDebug(true);
		
		// step2 : compose the message [text, multi media]
		MimeMessage m= new MimeMessage(session);
		try {
			// from email
			m.setFrom(from);
			
			// adding recipient to message
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			// adding subject to message
			m.setSubject(subject);
			
			// adding text to message and attachment
			//file path
			String path = "D:\\Pooja_Resume.docx";
			
			MimeMultipart mimeMultipart = new MimeMultipart();
			
			// text // file
			MimeBodyPart textMime = new MimeBodyPart();
			
			MimeBodyPart fileMime = new MimeBodyPart();
			
			textMime.setText(message);
			File file = new File(path);
			fileMime.attachFile(file);
			
			mimeMultipart.addBodyPart(textMime);
			mimeMultipart.addBodyPart(fileMime);
			
			m.setContent(mimeMultipart);
			
			// send 
			
			// Step3: send the message using Transport class
			Transport.send(m);
			System.out.println("sent success......");
			f = true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	/*public boolean sendEmail(String subject, String message, String to) {
		
		String from = "gopal.jfsd@gmail.com";
		boolean f = false;
		// variable for mail
		String host="smtp.gmail.com";
		
		// get the system properties
		
		Properties properties = System.getProperties();
		System.out.println("Properties :" + properties);
		
		// Setting important information to properties object
		
		// set
		properties.put("mail.smtp.host",host);
		properties.put("mail.smtp.prot", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth","true");
		
		// step1: to get the session object..
		
		Session session = Session.getInstance(properties,new Authenticator() {
	
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("gopal.jfsd@gmail.com","cttikwcejsmgjwmh");
			}
		});
		session.setDebug(true);
		
		// step2 : compose the message [text, multi media]
		MimeMessage m= new MimeMessage(session);
		try {
			// from email
			m.setFrom(from);
			
			// adding recipient to message
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			// adding subject to message
			m.setSubject(subject);
			
			// adding text to message
			
			m.setText(message);
			
			// send 
			
			// Step3: send the message using Transport class
			Transport.send(m);
			System.out.println("sent success......");
			f = true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return f;
	}*/
}