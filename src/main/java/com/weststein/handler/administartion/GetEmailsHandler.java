package com.weststein.handler.administartion;

import com.weststein.repository.SentEmail;
import com.weststein.repository.SentEmailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class GetEmailsHandler {

    @Autowired
    private SentEmailRepository sentEmailRepository;

    public List<SentEmail> handle(String email) {
       return sentEmailRepository.findAllByEmailOrderBySendingTimeDesc(email);
    }

}
