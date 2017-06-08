package com.weststein.handler.identification;

import com.weststein.infrastructure.EntityUpdater;
import com.weststein.infrastructure.OrikoObjectMapper;
import com.weststein.integration.SolarisIdentification;
import com.weststein.repository.Identification;
import com.weststein.repository.IdentificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SavePersonIdentificationHandler {

    @Autowired
    private IdentificationRepository identificationRepository;
    @Autowired
    private OrikoObjectMapper objectMapper;
    @Autowired
    private EntityUpdater<Identification> entityUpdater;

    public void handle(SolarisIdentification identification) {
        Identification existing = identificationRepository.findBySolarisId(identification.getSolarisId());
        entityUpdater.apply(objectMapper.map(identification, Identification.class), existing);
        identificationRepository.save(objectMapper.map(identification, Identification.class));
    }

}
