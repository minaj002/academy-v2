package com.weststein.integration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.weststein.repository.Booking;
import lombok.Data;

import java.util.Date;

@Data
public class SolarisBooking {
    @JsonProperty("id")
    private String solarisId;
    @JsonProperty("creation_date")
    private Date creationDate;
    @JsonProperty("valuta_date")
    private Date valutaDate;
    @JsonProperty("boking_date")
    private Date bookingDate;
    @JsonProperty("booking_type")
    private Booking.BookingType bookingType;
    private SolarisAmount amount;
    private String description;
    @JsonProperty("recipient_bic")
    private String recipientBic;
    @JsonProperty("recipient_iban")
    private String recipientIban;
    @JsonProperty("recipient_name")
    private String recipientName;
    @JsonProperty("sender_bic")
    private String senderBic;
    @JsonProperty("sender_iban")
    private String senderIban;
    @JsonProperty("sender_name")
    private String senderName;
    @JsonProperty("end_to_end_id")
    private String endToEndId;
    @JsonProperty("creditor_identifier")
    private String creditorIdentifier;
    @JsonProperty("mandate_reference")
    private String mandateReference;
    @JsonProperty("transaction_id")
    private String transactionId;
    private String accountId;

}
