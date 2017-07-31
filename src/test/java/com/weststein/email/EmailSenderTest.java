package com.weststein.email;

import com.weststein.repository.SentEmail;
import com.weststein.repository.SentEmailRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@Ignore
@RunWith(MockitoJUnitRunner.class)
public class EmailSenderTest {

    @InjectMocks
    private EmailSender emailSender;
    @Mock
    private JavaMailSender javaMailSender;
    @Mock
    private SentEmailRepository sentEmailRepository;
    @Mock
    private EmailTextSource emailTextSource;
    @Mock
    private MimeMessage mimeMessage;

    @Before
    public void setup() {
        when(emailTextSource.getBody(any(),any())).thenReturn("Body");
        when(emailTextSource.getTemplate()).thenReturn("Template  textBody  End");
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);
    }

    @Test
    public void sendWelcomeEmailSuccess() throws Exception {
        emailSender.sendVerifyEmail("mail@mail.com", "token", "lv");
        ArgumentCaptor<SentEmail> sentEmail = ArgumentCaptor.forClass(SentEmail.class);
        verify(sentEmailRepository,times(1)).save(sentEmail.capture());
        assertTrue(sentEmail.getValue().getSuccessfullySent());
        assertEquals("Body", sentEmail.getValue().getText());
    }

    @Test(expected = MailException.class)
    public void sendWelcomeEmailFail() throws Exception {
        doThrow(MailSendException.class).when(javaMailSender).send(any(MimeMessage.class));
        emailSender.sendVerifyEmail("mail@mail.com", "token", "lv");
        ArgumentCaptor<SentEmail> sentEmail = ArgumentCaptor.forClass(SentEmail.class);
        verify(sentEmailRepository,times(1)).save(sentEmail.capture());
        assertFalse(sentEmail.getValue().getSuccessfullySent());
    }

}