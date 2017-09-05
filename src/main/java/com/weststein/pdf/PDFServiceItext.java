package com.weststein.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.weststein.controller.secured.model.TransactionModel;
import com.weststein.controller.secured.model.ViewStatementModel;
import com.weststein.infrastructure.exceptions.ValidationError;
import com.weststein.infrastructure.exceptions.ValidationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Component
public class PDFServiceItext {

    public static DecimalFormat AMOUNT_FORMAT = new DecimalFormat("#,##0.00");

    public ByteArrayOutputStream fillApplication(Map<String, String> applicationValues) {
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

    public ByteArrayOutputStream createStatement(String name, String iban, ViewStatementModel statement) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4, 36f, 72f, 108f, 180f);
        try {
            PdfWriter.getInstance(document, outputStream);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        document.open();
        try {
            Phrase head = new Phrase("Account statement", FontFactory.getFont(FontFactory.COURIER_BOLD, 20));
            Paragraph headParagraph = new Paragraph(head);
            document.add(headParagraph);

            com.itextpdf.text.List list = new com.itextpdf.text.List(com.itextpdf.text.List.UNORDERED);
            list.setListSymbol(" ");
            list.add(name);
            list.add("IBAN: " + iban);
            list.add("Period: " + statement.getStartDate() + " - " + statement.getEndDate());
            list.add(" ");
            document.add(list);

            PdfPTable table = new PdfPTable(7);
            table.setWidthPercentage(100);
            PdfPCell dateCell = new PdfPCell(new Phrase("Date"));
            PdfPCell typeCell = new PdfPCell(new Phrase("Transaction type"));
            PdfPCell beneficiaryCell = new PdfPCell(new Phrase("Beneficiary"));
            PdfPCell detailsCell = new PdfPCell(new Phrase("Details"));
            PdfPCell amountCell = new PdfPCell(new Phrase("Amount"));
            PdfPCell balanceCell = new PdfPCell(new Phrase("Balance after"));
            PdfPCell statusCell = new PdfPCell(new Phrase("Status"));
            table.addCell(dateCell);
            table.addCell(typeCell);
            table.addCell(beneficiaryCell);
            table.addCell(detailsCell);
            table.addCell(amountCell);
            table.addCell(balanceCell);
            table.addCell(statusCell);


            for (TransactionModel transaction : statement.getTransactions()) {
                PdfPCell cell1 = new PdfPCell();
                PdfPCell cell2 = new PdfPCell();
                PdfPCell cell3 = new PdfPCell();
                PdfPCell cell4 = new PdfPCell();
                PdfPCell cell5 = new PdfPCell();
                PdfPCell cell6 = new PdfPCell();
                PdfPCell cell7 = new PdfPCell();
                cell1.setPhrase(new Phrase(transaction.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))));
                cell2.setPhrase(new Phrase(transaction.getTransactionType().toString()));
                cell3.setPhrase(new Phrase(transaction.getBeneficiary()));
                cell4.setPhrase(new Phrase(transaction.getDetails()));
                cell5.setPhrase(new Phrase(AMOUNT_FORMAT.format(transaction.getAmount())));
                cell6.setPhrase(new Phrase(AMOUNT_FORMAT.format(transaction.getBalanceAfter())));
                cell7.setPhrase(new Phrase(transaction.getStatus()));
                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                table.addCell(cell4);
                table.addCell(cell5);
                table.addCell(cell6);
                table.addCell(cell7);
            }
            document.add(table);

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        document.close();
        return outputStream;

    }

}
