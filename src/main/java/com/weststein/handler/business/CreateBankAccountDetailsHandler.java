package com.weststein.handler.business;

import com.weststein.controller.secured.business.model.business.BankAccountDetailsModel;
import com.weststein.infrastructure.OrikoObjectMapper;
import com.weststein.repository.business.BankAccountDetails;
import com.weststein.repository.business.BankAccountDetailsRepository;
import org.iban4j.BicUtil;
import org.iban4j.IbanUtil;
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

        IbanUtil.validate(bankAccountDetailsModel.getIban());
        BicUtil.validate(bankAccountDetailsModel.getBic());
        BankAccountDetails bankAccountDetails = objectMapper.map(bankAccountDetailsModel, BankAccountDetails.class);
        bankAccountDetails.setBusinessId(businessId);
        bankAccountDetails.setCreated(LocalDateTime.now());

        bankAccountDetailsRepository.save(bankAccountDetails);

    }

}
