package com.weststein.email;

import com.weststein.repository.SentEmail;
import com.weststein.repository.SentEmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.time.LocalDateTime;

@Component
public class EmailSender {

    @Value("${confirmation}")
    private String url;

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private SentEmailRepository sentEmailRepository;
    @Autowired
    private EmailTextSource emailTextSource;



    public void sendResetPasswordEmail(String email,String token, String language) {

        String emailText = String.format(emailTextSource.getBody("reset", language.toLowerCase()), email, url+token);
        sendEmail(email, emailText);
    }

    public void sendVerifyEmail(String email, String token, String language) {

        String emailText = String.format(emailTextSource.getBody("confirm", language.toLowerCase()), url+token);
        sendEmail(email, emailText);
    }

    public void sendCardEmail(String email, String language) {

        String emailText = emailTextSource.getBody("card", language.toLowerCase());
        sendEmail(email, emailText);
    }

    private void sendEmail(String email, String emailText) {
        String emailTemplate = emailTextSource.getTemplate();
        SentEmail sentEmail = SentEmail.builder()
                    .email(email)
                    .subject("Welcome to Weststein")
                    .template(emailTemplate)
                    .text(emailText)
                    .sendingTime(LocalDateTime.now())
                    .build();
        try {
            javaMailSender.send(sentEmail.toMailMessage(javaMailSender.createMimeMessage()));
            sentEmail.setSuccessfullySent(true);
            sentEmailRepository.save(sentEmail);
        } catch (MailException e) {
            sentEmail.setSuccessfullySent(false);
            sentEmail.setFailureReason(e.getLocalizedMessage());
            sentEmailRepository.save(sentEmail);
            throw e;
        } catch (MessagingException e) {
            sentEmail.setSuccessfullySent(false);
            sentEmail.setFailureReason(e.getLocalizedMessage());
            sentEmailRepository.save(sentEmail);
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }

}
