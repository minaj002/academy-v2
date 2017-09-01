package com.weststein.handler.card;

import com.weststein.integration.PPFService;
import com.weststein.integration.request.PinReminder;
import com.weststein.integration.response.AccountAPIv2PinReminder;
import com.weststein.repository.CardholderId;
import com.weststein.repository.CardholderIdRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PinReminderHandler {

    @Autowired
    private CardholderIdRepository cardholderIdRepository;
    @Autowired
    private PPFService<PinReminder, AccountAPIv2PinReminder> ppfService;

    public void handle(Long id) {

        CardholderId cardholderId = cardholderIdRepository.findOne(id);
        AccountAPIv2PinReminder res2 = ppfService.get(PinReminder.builder().cardholderId(cardholderId.getCardholderId()).build(),
                AccountAPIv2PinReminder.class);
    }
}
