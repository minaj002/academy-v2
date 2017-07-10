package com.weststein.email;

import com.weststein.repository.SentEmail;
import com.weststein.repository.SentEmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class EmailSender {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private SentEmailRepository sentEmailRepository;

    public void sendWelcomeEmail(String email, String token) {
        SentEmail sentEmail = SentEmail.builder()
                .email(email)
                .subject("Welcome to Weststein")
                .text("hello, welcome to weststein "+token)
                .sendingTime(LocalDateTime.now())
                .build();
        try {
            javaMailSender.send(sentEmail.toMailMessage());
            sentEmail.setSuccessfullySent(true);
            sentEmailRepository.save(sentEmail);
        } catch (MailException e) {
            sentEmail.setSuccessfullySent(false);
            sentEmail.setFailureReason(e.getLocalizedMessage());
            sentEmailRepository.save(sentEmail);
            throw e;
        }
    }

}
