package com.weststein.handler.business;

import com.weststein.controller.secured.model.business.BankAccountDetailsModel;
import com.weststein.infrastructure.OrikoObjectMapper;
import com.weststein.repository.BankAccountDetails;
import com.weststein.repository.BankAccountDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CreateBankAccountDetailsHandler {

    @Autowired
    private BankAccountDetailsRepository bankAccountDetailsRepository;

    @Autowired
    private OrikoObjectMapper objectMapper;

    public void handle(Long businessId, BankAccountDetailsModel bankAccountDetailsModel) {

        BankAccountDetails bankAccountDetails = objectMapper.map(bankAccountDetailsModel, BankAccountDetails.class);
        bankAccountDetails.setBusinessId(businessId);
        bankAccountDetails.setCreated(LocalDateTime.now());

        bankAccountDetailsRepository.save(bankAccountDetails);

    }

}
