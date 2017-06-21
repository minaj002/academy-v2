package com.weststein.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendWelcomeEmail(String email, String token) {
        SimpleMailMessage simpleMessage = new SimpleMailMessage();
        simpleMessage.setTo(email);
        simpleMessage.setSubject("Welcome to Weststein");
        simpleMessage.setText("hello, welcome to weststein "+token);
        javaMailSender.send(simpleMessage);
    }

}
