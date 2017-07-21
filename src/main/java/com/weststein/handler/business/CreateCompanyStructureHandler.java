package com.weststein.handler.business;

import com.weststein.controller.secured.model.CompanyStructureModel;
import com.weststein.infrastructure.OrikoObjectMapper;
import com.weststein.repository.ShareHolder;
import com.weststein.repository.ShareHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CreateCompanyStructureHandler {

    @Autowired
    private ShareHolderRepository shareHolderRepository;
    @Autowired
    private OrikoObjectMapper objectMapper;

    public void handle(Long businessId, CompanyStructureModel companyStructureModel) {

        List<ShareHolder> shareHolders = objectMapper.map(companyStructureModel.getShareHolders(), ShareHolder.class);
        shareHolders = shareHolders.stream().map(
                shareHolder -> {
                    shareHolder.setCreated(LocalDateTime.now());
                    shareHolder.setBusinessId(businessId);
                    return shareHolder;
                }).collect(Collectors.toList());
        shareHolderRepository.save(shareHolders);

    }

}
