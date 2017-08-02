package com.weststein.repository;

import com.weststein.pdf.PDFFields;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class RequiredDocument {

    public enum Type {
        CertificateOfRegistration(PDFFields.REQ_CERT_INCORP), ArticleOfAssociation(PDFFields.REQ_ARTS_ASSOC),
        AnnualReturn(PDFFields.REQ_ANNUAL_RETURN ), ConfirmationOfShareholdingDetails(PDFFields.REQ_SHAREHOLDER_DETAIL),
        ConfirmationOfCompanyDirectors(PDFFields.REQ_DIRECTOR_DETAIL), BankAccountEvidence(PDFFields.REQ_BANK_ACCOUNT),
        ShareholderPhotoId, ShareholderAddressVerification,
        DirectorPhotoId, DirectorAddressVerification, SignedLastPage;

        private String pdfMapping;

        Type(String pdfMapping) {
            this.pdfMapping = pdfMapping;
        }

        Type() {}

        public String getPdfMapping() {
            return pdfMapping;
        }
    }

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(strategy =
            "org.hibernate.id.enhanced.SequenceStyleGenerator",
            name = "generator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "hibernate_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1000"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;
    private Long businessId;
    private LocalDateTime created;
    private Type type;
    private String bucket;
    private String name;
}
