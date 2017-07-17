package com.weststein.repository;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
public class SentEmail {

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(strategy =
            "org.hibernate.id.enhanced.SequenceStyleGenerator",
            name = "generator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "hibernate_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1000"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;
    private String email;
    private String subject;
    @Column(columnDefinition="TEXT")
    private String text;
    @Transient
    private String template;
    private LocalDateTime sendingTime;
    private Boolean successfullySent;
    private String failureReason;

    public MimeMessage toMailMessage(MimeMessage mimeMessage) throws MessagingException {

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false);
        helper.setTo(email);
        helper.setSubject(subject);
        mimeMessage.setText(template.replace("textBody", text), "UTF-8", "html");
        return mimeMessage;
    }
}
