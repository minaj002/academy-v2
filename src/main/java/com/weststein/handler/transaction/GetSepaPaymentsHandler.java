package com.weststein.handler.transaction;

import com.weststein.controller.secured.model.SepaTransferModel;
import com.weststein.infrastructure.OrikoObjectMapper;
import com.weststein.repository.CardholderId;
import com.weststein.repository.CardholderIdRepository;
import com.weststein.repository.SepaTransfer;
import com.weststein.repository.SepaTransferRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class GetSepaPaymentsHandler {

    @Autowired
    private OrikoObjectMapper objectMapper;
    @Autowired
    private SepaTransferRepository sepaTransferRepository;
    @Autowired
    private CardholderIdRepository cardholderIdRepository;

    public List<SepaTransferModel> handle(Long cardholder, int size, int page) {
        CardholderId cardholderId = cardholderIdRepository.findOne(cardholder);
        PageRequest pageRequest = new PageRequest(page - 1, size);
        Page<SepaTransfer> sepaTransfers = sepaTransferRepository.findAllByFromAndStatusIsNotOrderByIdDesc(cardholderId, SepaTransfer.Status.DELETED, pageRequest);
        return objectMapper.map(sepaTransfers.getContent(), SepaTransferModel.class);
    }
}
