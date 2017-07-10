package com.weststein.email;

import com.weststein.repository.SentEmail;
import com.weststein.repository.SentEmailRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmailSenderTest {

    @InjectMocks
    private EmailSender emailSender;
    @Mock
    private JavaMailSender javaMailSender;
    @Mock
    private SentEmailRepository sentEmailRepository;

    @Test
    public void sendWelcomeEmailSuccess() throws Exception {
        emailSender.sendWelcomeEmail("mail@mail.com", "token");
        ArgumentCaptor<SentEmail> sentEmail = ArgumentCaptor.forClass(SentEmail.class);
        verify(sentEmailRepository,times(1)).save(sentEmail.capture());
        assertTrue(sentEmail.getValue().getSuccessfullySent());
    }

    @Test(expected = MailException.class)
    public void sendWelcomeEmailFail() throws Exception {
        doThrow(MailSendException.class).when(javaMailSender).send(any(SimpleMailMessage.class));
        emailSender.sendWelcomeEmail("mail@mail.com", "token");
        ArgumentCaptor<SentEmail> sentEmail = ArgumentCaptor.forClass(SentEmail.class);
        verify(sentEmailRepository,times(1)).save(sentEmail.capture());
        assertFalse(sentEmail.getValue().getSuccessfullySent());
    }

}