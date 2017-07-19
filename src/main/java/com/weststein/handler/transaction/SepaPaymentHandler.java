package com.weststein.handler.transaction;

import com.weststein.controller.secured.model.SepaTransferModel;
import com.weststein.infrastructure.OrikoObjectMapper;
import com.weststein.integration.PPFService;
import com.weststein.integration.request.SepaPayment;
import com.weststein.integration.response.AccountAPIv2SepaPayment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SepaPaymentHandler {

    @Autowired
    private PPFService<SepaPayment, AccountAPIv2SepaPayment> ppfService;
    @Autowired
    private OrikoObjectMapper objectMapper;

    public AccountAPIv2SepaPayment handle(String cardholderId, SepaTransferModel sepa) {

        SepaPayment sepaPayment = objectMapper.map(sepa, SepaPayment.class);
        sepaPayment.setCardholderId(cardholderId);
        return ppfService.get(sepaPayment,
                AccountAPIv2SepaPayment.class);
    }
}
