package com.weststein.handler.business;

import com.weststein.repository.business.RequiredDocument;
import com.weststein.repository.business.RequiredDocumentRepository;
import com.weststein.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Component
public class RequiredDocumentsUploadHandler {

    @Autowired
    private Storage storage;
    @Autowired
    private RequiredDocumentRepository requiredDocumentRepository;

    public void handle(Long businessId, MultipartFile file, RequiredDocument.Type type) {

        String bucket = storage.store(businessId, file);
        RequiredDocument document = new RequiredDocument();
        document.setBusinessId(businessId);
        document.setCreated(LocalDateTime.now());
        document.setBucket(bucket);
        document.setName(file.getOriginalFilename());
        document.setType(type);
        requiredDocumentRepository.save(document);

    }

}
