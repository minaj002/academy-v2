package com.weststein.repository;

import lombok.Builder;
import lombok.Data;
import org.springframework.mail.SimpleMailMessage;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
public class SentEmail {

    @Id
    private Long id;
    private String email;
    private String subject;
    private String text;
    private LocalDateTime sendingTime;
    private Boolean successfullySent;
    private String failureReason;

    public SimpleMailMessage toMailMessage() {
        SimpleMailMessage simpleMessage = new SimpleMailMessage();
        simpleMessage.setTo(email);
        simpleMessage.setSubject(subject);
        simpleMessage.setText(text);
        return simpleMessage;
    }
}
