package com.weststein.pdf;

import com.weststein.repository.*;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PDFFieldMapper {

    public static Map<String, String> mapBusinessProfile(BusinessProfile profile, Map<String, String> map) {

        map.put(PDFFields.COMPANY_URL_1, profile.getUrl());
        map.put(PDFFields.LIVE_IP_ADDRESS_1, profile.getIpAddress());
        StringBuilder countries = new StringBuilder();
        profile.getCountriesOfOperation().forEach(country -> countries.append(country).append(","));
        map.put(PDFFields.OPERATING_COUNTRIES, countries.toString());
        map.put(PDFFields.TARGET_MARKET_DETAILS, profile.getDetailsOfTargetMarket());
        map.put(PDFFields.AFFILIATES, profile.isAffiliatesSellProducts() ? "Yes" : "No");
        map.put(PDFFields.BUSINESS_DESCRIPTION, profile.getDescriptionOfBusiness());
        return map;
    }

    public static Map<String, String> mapCompanyInformation(CompanyInformation information, Map<String, String> map) {

        map.put(PDFFields.CORPORATE_NAME, information.getLegalName());
        map.put(PDFFields.MERCHANT_NAME, information.getTradingName());
        map.put(PDFFields.BUSINESS_LEGAL_STATUS, information.getLegalStatus().getDescription());
        if (CompanyInformation.LegalStatus.Other.equals(information.getLegalStatus())) {
            map.put(PDFFields.OTHER_BUSINESS_LEGAL_STATUS_DETAILS, information.getOtherLegalStatus());
        }
        map.put(PDFFields.BUSINESS_ADDRESS, information.getRegisteredAddress().getLine1() + ", " + information.getRegisteredAddress().getLine2());
        map.put(PDFFields.POSTCODE, information.getRegisteredAddress().getPostalCode());
        map.put(PDFFields.COUNTRY, information.getRegisteredAddress().getCountry());
        map.put(PDFFields.ADDRESS_LINE_1, information.getPrincipalPlaceOfBusiness());
        map.put(PDFFields.BUSINESS_LANDLINE_PHONE, information.getLandLinePhone());
        map.put(PDFFields.BUSINESS_MOBILE_PHONE, information.getMobilePhone());
        map.put(PDFFields.CONTACT_NAME_TITLE, information.getContactTitle().name());
        map.put(PDFFields.CONTACT_NAME, information.getFirstName() + " " + information.getLastName());
        map.put(PDFFields.EMAIL, information.getContactEmail());
        map.put(PDFFields.CONTACT_PERSON_PHONE, information.getContactPhone());
        map.put(PDFFields.INCORPORATION_DATE, information.getDateOfIncorporation());
        map.put(PDFFields.COMPANY_NUM, information.getRegistrationNumber());
        map.put(PDFFields.TAX_NUMBER, information.getTaxNumber());
        map.put(PDFFields.VAT_NUMBER, information.getVatNumber());
        map.put(PDFFields.REGULATED, information.getRegulatedByAuthority() ? "Yes" : "No");
        map.put(PDFFields.AUTHORITY_LICENSE_NUMBER, information.getLicenceNumberGrantedByAuthority());
        if (information.getRegulatedByAuthority()) {
            map.put(PDFFields.AUTHORITY, information.getRegulationAuthorityName());
        }
        return map;
    }

    public static Map<String, String> mapBankAccountDetails(BankAccountDetails bankAccountDetails, Map<String, String> map) {
        map.put(PDFFields.BIC, bankAccountDetails.getBic());
        map.put(PDFFields.BANK_ACCOUNT_CURRENCY, bankAccountDetails.getCurrency().name());
        map.put(PDFFields.IBAN, bankAccountDetails.getIban());
        map.put(PDFFields.SORT_CODE, bankAccountDetails.getSortCode());
        map.put(PDFFields.ACCOUNT_NUMBER, bankAccountDetails.getAccountNumber());
        map.put(PDFFields.ROUTING_NUMBER, bankAccountDetails.getRoutingNumber());
        map.put(PDFFields.BANK_ADDRESS, bankAccountDetails.getBankNameAndAddress());
        map.put(PDFFields.ACCOUNT_HOLDER_NAME, bankAccountDetails.getAccountHolderName());
        return map;
    }

    public static Map<String, String> mapProjectedLoadingFigures(ProjectedLoadingFigures projectedLoadingFigures, Map<String, String> map) {
        map.put(PDFFields.PROJECTED_FIGURE_CURRENCY, projectedLoadingFigures.getCurrency().name());
        map.put(PDFFields.MONTHLY_LOAD, projectedLoadingFigures.getMonthly().toString());
        map.put(PDFFields.YEARLY_LOAD, projectedLoadingFigures.getYearly().toString());
        map.put(PDFFields.QUARTERLYLOAD, projectedLoadingFigures.getQuarterly().toString());
        return map;
    }

    public static Map<String, String> mapCardIban(CardIban cardIban, Map<String, String> map) {
        map.put(PDFFields.CURRENCY, cardIban.getCurrency().name());
        map.put(PDFFields.SOLUTION_PURPOSE, cardIban.getExplanationOfPurpose());
        map.put(PDFFields.ACCOUNT_HOLDER_TYPE, cardIban.getHolders().getType());
        map.put(PDFFields.ACCOUNT_HOLDER_LOCATION, cardIban.getHoldersLocation());
        map.put(PDFFields.CARD_TYPE, cardIban.getUse().getType());
        if (CardUse.Other.equals(cardIban.getUse())) {
            map.put(PDFFields.OTHER_ACCOUNT_DETAILS, cardIban.getOtherCardUse());
        }
        map.put(PDFFields.OTHER_CARD_USE, cardIban.getOtherCardUse());
        map.put(PDFFields.BANK_ACCOUNT_CURRENCY, cardIban.getCurrency().name());
        map.put(PDFFields.NUMBER_OF_CARDS_REQUIRED, cardIban.getNumberOfCards().toString());
        map.put(PDFFields.CARD_PRODUCT_TYPE, cardIban.getType().name());
        map.put(PDFFields.ORIGIN_OF_FUNDS, cardIban.getFundsOrigin());
        map.put(PDFFields.FLOW_OF_FUNDS, cardIban.getFlowOfFunds());
        map.put(PDFFields.EXPECTED_TRANSACTION_VOLUME, cardIban.getNatureOfExpectedTransactions());
        return map;
    }

    public static Map<String, String> mapShareholders(List<ShareHolder> shareHolders, Map<String, String> map) {

        if (shareHolders.size() > 0) {
            ShareHolder holder = shareHolders.get(0);
            map.put(PDFFields.SHAREHOLDER_1_ADDRESS, holder.getAddress().toString());
            map.put(PDFFields.SHAREHOLDER_1_DOB, holder.getDateOfBirth().format(DateTimeFormatter.ofPattern("ddMMyyyy")));
            map.put(PDFFields.SHAREHOLDER_1_EMAIL, holder.getEmail());
            map.put(PDFFields.SHAREHOLDER_1_PHONE, holder.getPhone());
            map.put(PDFFields.SHAREHOLDER_1_NAME, holder.getFirstName() + " " + holder.getLastName());
            map.put(PDFFields.SHAREHOLDER_1_ROLE, holder.getRole().name());
            map.put(PDFFields.SHAREHOLDER_1_PERCENT_OWNED, holder.getPercentageOwned().toString());
        }
        if (shareHolders.size() > 1) {
            ShareHolder holder = shareHolders.get(1);
            map.put(PDFFields.SHAREHOLDER_2_ADDRESS, holder.getAddress().toString());
            map.put(PDFFields.SHAREHOLDER_2_DOB, holder.getDateOfBirth().format(DateTimeFormatter.ofPattern("ddMMyyyy")));
            map.put(PDFFields.SHAREHOLDER_2_EMAIL, holder.getEmail());
            map.put(PDFFields.SHAREHOLDER_2_PHONE, holder.getPhone());
            map.put(PDFFields.SHAREHOLDER_2_NAME, holder.getFirstName() + " " + holder.getLastName());
            map.put(PDFFields.SHAREHOLDER_2_ROLE, holder.getRole().name());
            map.put(PDFFields.SHAREHOLDER_2_PERCENT_OWNED, holder.getPercentageOwned().toString());
        }
        if (shareHolders.size() > 2) {
            ShareHolder holder = shareHolders.get(2);
            map.put(PDFFields.SHAREHOLDER_3_ADDRESS, holder.getAddress().toString());
            map.put(PDFFields.SHAREHOLDER_3_DOB, holder.getDateOfBirth().format(DateTimeFormatter.ofPattern("ddMMyyyy")));
            map.put(PDFFields.SHAREHOLDER_3_EMAIL, holder.getEmail());
            map.put(PDFFields.SHAREHOLDER_3_PHONE, holder.getPhone());
            map.put(PDFFields.SHAREHOLDER_3_NAME, holder.getFirstName() + " " + holder.getLastName());
            map.put(PDFFields.SHAREHOLDER_3_ROLE, holder.getRole().name());
            map.put(PDFFields.SHAREHOLDER_3_PERCENT_OWNED, holder.getPercentageOwned().toString());
        }
        if (shareHolders.size() > 3) {
            ShareHolder holder = shareHolders.get(3);
            map.put(PDFFields.SHAREHOLDER_4_ADDRESS, holder.getAddress().toString());
            map.put(PDFFields.SHAREHOLDER_4_DOB, holder.getDateOfBirth().format(DateTimeFormatter.ofPattern("ddMMyyyy")));
            map.put(PDFFields.SHAREHOLDER_4_EMAIL, holder.getEmail());
            map.put(PDFFields.SHAREHOLDER_4_PHONE, holder.getPhone());
            map.put(PDFFields.SHAREHOLDER_4_NAME, holder.getFirstName() + " " + holder.getLastName());
            map.put(PDFFields.SHAREHOLDER_4_ROLE, holder.getRole().name());
            map.put(PDFFields.SHAREHOLDER_4_PERCENT_OWNED, holder.getPercentageOwned().toString());
        }

        return map;
    }

    public static Map<String, String> mapRequiredDocuments(List<RequiredDocument> requiredDocuments, Map<String, String> map) {

        Arrays.stream(RequiredDocument.Type.values()).forEach(type -> {
            if (type.getPdfMapping() != null) map.put(type.getPdfMapping(), "NO");
        });

        Map<String, String> addedDocuments = requiredDocuments.stream().map(doc -> doc.getType()).collect(Collectors.toMap(value -> value.getPdfMapping(),
                type -> "YES"
        ));
        map.putAll(addedDocuments);
        return map;
    }

}
