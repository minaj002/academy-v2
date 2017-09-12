package com.weststein.handler.administartion;

import com.weststein.repository.CardholderId;
import com.weststein.repository.CardholderIdRepository;
import com.weststein.repository.business.BusinessInformation;
import com.weststein.repository.business.BusinessInformationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AttachCardholderIdHandler {

    @Autowired
    private CardholderIdRepository cardholderIdRepository;
    @Autowired
    private BusinessInformationRepository businessInformationRepository;

    public void handle(Long businessId, Long cardholderId) {

        CardholderId cardholderIdEntity = cardholderIdRepository.findOne(cardholderId);
        BusinessInformation businessInformation = businessInformationRepository.findOne(businessId);
        businessInformation.getCardholderIds().add(cardholderIdEntity);
        businessInformationRepository.save(businessInformation);
    }

}
