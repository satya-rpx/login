/*Copyright (c) 2019-2020 imaginea.com All Rights Reserved.
 This software is the confidential and proprietary information of imaginea.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with imaginea.com*/
package com.login.emailservice;

import javax.servlet.http.HttpServletRequest;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;
import com.wavemaker.runtime.security.SecurityService;
import com.wavemaker.runtime.service.annotations.ExposeToClient;
import com.wavemaker.runtime.service.annotations.HideFromClient;
import java.security.SecureRandom;
import java.util.*;

//import com.login.emailservice.model.*;

/**
 * This is a singleton class with all its public methods exposed as REST APIs via generated controller class.
 * To avoid exposing an API for a particular public method, annotate it with @HideFromClient.
 *
 * Method names will play a major role in defining the Http Method for the generated APIs. For example, a method name
 * that starts with delete/remove, will make the API exposed as Http Method "DELETE".
 *
 * Method Parameters of type primitives (including java.lang.String) will be exposed as Query Parameters &
 * Complex Types/Objects will become part of the Request body in the generated API.
 *
 * NOTE: We do not recommend using method overloading on client exposed methods.
 */
@ExposeToClient
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
    private Session session;

    private boolean authentication=true;
    private boolean smtpServerTTLSEnabled = true;
    private String host = "smtp.gmail.com";
    private String port = "587";
    private String username="priyaramamoorthy96@gmail.com";
    private String password="Black14111996!";

    @PostConstruct
    public void init() throws Exception {
        Properties props = new Properties();
        props.put("mail.smtp.auth", String.valueOf(authentication));
        props.put("mail.smtp.starttls.enable",smtpServerTTLSEnabled);
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
            }
        });
    }

   



 public void sendEmail(String toEmailAddress,String username) throws Exception {
        
     
     String emailSubject ="Login Credentials! Please Dont Share";
     String ALPHA_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
     String ALPHA = "abcdefghijklmnopqrstuvwxyz";
     String NUMERIC = "0123456789";
     String SPECIAL_CHARS = "!@#$%^&*_=+-/";
     int len = 13;
   SecureRandom random = new SecureRandom();
    System.out.println("Alphanumeric + numeric + special password, length " + len + " chars: ");
    String pwd = "";
    String dic =ALPHA_CAPS + ALPHA + SPECIAL_CHARS + NUMERIC;
    for (int i = 0; i < len; i++) {
        int index = random.nextInt(dic.length());
        pwd += dic.charAt(index);
    }
    
     String template = "Hi "+username+","+"\n\n"+"An account has been created for you at our Bank and you have been issued with a new permanent password." +"\n"+"Your current login credentials are below:"+"\n\n"+
"username: " + toEmailAddress + "\n" +
"password: " + pwd +"\n" + "Thank you!";
        
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            String[] recipientList = toEmailAddress.split(",");
            InternetAddress[] recipientAddresses = new InternetAddress[recipientList.length];
            int counter = 0;
            for (String recipient: recipientList) {
                recipientAddresses[counter] = new InternetAddress(recipient.trim());
                counter++;
                }
            message.setRecipients(Message.RecipientType.TO, recipientAddresses);
            message.setSubject(emailSubject);
            message.setText(template);
            Transport.send(message);
           
            
            
        }
        
}