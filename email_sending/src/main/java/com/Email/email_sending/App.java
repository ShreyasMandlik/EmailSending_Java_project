package com.Email.email_sending;

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

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	System.out.println("preparing to send message ...");
    	Scanner sc= new Scanner(System.in);
        String from="smandlikpict@gmail.com";
        System.out.println("Enter the Subject ");
    	String subject=sc.nextLine();
    	System.out.println("Enter the Message: ");
        String message=sc.nextLine();
    	System.out.println("Enter the number of Participants you want to share the message");
    	int a=sc.nextInt();
    	sc.nextLine();
    	for(int i=0;i<a;i++) {
    		System.out.print("Enter the mail id of Reciptent :");
    		String to=sc.nextLine();
//          For Sending Message
            sendEmail(message,subject,to,from);   
    }

	private static void sendEmail(String message, String subject, String to, String from) {
		String host="smtp.gmail.com";
		
	Properties properties=System.getProperties();
	System.out.println("PROPERTIES "+properties);
	
	//setting important information to properties object
	
	//host set
	properties.put("mail.smtp.host", host);
	properties.put("mail.smtp.port","465");
	properties.put("mail.smtp.ssl.enable","true");
	properties.put("mail.smtp.auth","true");
	Session session=Session.getInstance(properties, new Authenticator() {
		@Override
		protected PasswordAuthentication getPasswordAuthentication() {				
			return new PasswordAuthentication("Your email", "password");
		}
		
		
		
	});
	session.setDebug(true);
	//Step 2 : compose the message [text,multi media]
	MimeMessage m = new MimeMessage(session);
	
	try {
	
	//from email
	m.setFrom(from);
	
	//adding recipient to message
	m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	
	//adding subject to message
	m.setSubject(subject);

	
	//adding text to message
	m.setText(message);
	//send 
	
	//Step 3 : send the message using Transport class
	Transport.send(m);
	
	System.out.println("Sent success...................");
	
	
	}catch (Exception e) {
		e.printStackTrace();
	}
	
	
}
	
}
