package com.retail.util;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class AttachmentEmail{
	
	public static void main(String [] args) {   
		
// Recepients email id
		String to = "prashanthsingh08@gmail.com";
// senders email id
		String from ="ironpatriot005@gmail.com";
// We are sending email from localhost
		String host ="localhost";//http://localhost:8080/
		
		Properties properties = System.getProperties();
		
	// setup email server
		
		properties.setProperty("mail.smtp.host", host);
		
	// get default session object
		Session session = Session.getDefaultInstance(properties);
		
	// create default mimeMessege object
		try{
		MimeMessage message = new MimeMessage(session);
		
		// set from : header field of the header
		message.setFrom(new InternetAddress(from));
		
		// set to " header field of the header
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		
		//Set subject
		message.setSubject("This is the Subject line");
		
		//create message
		BodyPart messageBodyPart = new MimeBodyPart();
		
		 // Fill the message
        messageBodyPart.setText("This is message body");
        
     // Create a multipar message
        Multipart multipart = new MimeMultipart();

     // Set text message part
        multipart.addBodyPart(messageBodyPart);
        
        //attachment
        messageBodyPart = new MimeBodyPart();
        String filename="Sat_Jul_25_11_19_10_BST_2020";
        DataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filename);
        multipart.addBodyPart(messageBodyPart);
        
        message.setContent(multipart );

        Transport.send(message);
        System.out.println("Sent message successfully....");
		
		}catch(MessagingException mex ){
			mex.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
}