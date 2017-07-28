package com.weststein.pdf;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.weststein.infrastructure.exceptions.ValidationError;
import com.weststein.infrastructure.exceptions.ValidationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Component
public class PDFServiceItext {

    public ByteArrayOutputStream fill(Map<String, String> applicationValues) {
        ApplicationContext context = new ClassPathXmlApplicationContext();
        Resource templateResource = context.getResource("pfs_application.pdf");

        try {
            PdfReader reader = new PdfReader(templateResource.getInputStream());
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PdfStamper stamper = new PdfStamper(reader, outputStream);
            AcroFields fields = stamper.getAcroFields();

            applicationValues.forEach((a, b) -> {
                try {
                    fields.setField(a, b);
                } catch (IOException e) {
                    List<ValidationError> errors = new ArrayList<>();
                    errors.add(ValidationError.builder().field("pdf").message(e.getMessage()).build());
                    throw new ValidationException(errors, "error while creating PDF");
                } catch (DocumentException e) {
                    List<ValidationError> errors = new ArrayList<>();
                    errors.add(ValidationError.builder().field("pdf").message(e.getMessage()).build());
                    throw new ValidationException(errors, "error while creating PDF");
                }
            });
            stamper.close();

            return outputStream;
        } catch (IOException e) {
            List<ValidationError> errors = new ArrayList<>();
            errors.add(ValidationError.builder().field("pdf").message(e.getMessage()).build());
            throw new ValidationException(errors, "error while creating PDF");
        } catch (DocumentException e) {
            List<ValidationError> errors = new ArrayList<>();
            errors.add(ValidationError.builder().field("pdf").message(e.getMessage()).build());
            throw new ValidationException(errors, "error while creating PDF");
        }
    }

}
