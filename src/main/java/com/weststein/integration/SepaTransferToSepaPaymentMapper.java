package com.weststein.integration;

import com.weststein.infrastructure.ObjectMapperConfiguration;
import com.weststein.integration.request.SepaPayment;
import com.weststein.repository.SepaTransfer;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DecimalFormat;

@Component
public class SepaTransferToSepaPaymentMapper extends ObjectMapperConfiguration<SepaTransfer, SepaPayment> {

    public static DecimalFormat AMOUNT_FORMAT = new DecimalFormat("#");

    @Override
    public Class<SepaTransfer> getA() {
        return SepaTransfer.class;
    }

    @Override
    public Class<SepaPayment> getB() {
        return SepaPayment.class;
    }

    @Override
    protected void fieldMapping(ClassMapBuilder<SepaTransfer, SepaPayment> builder) {

        builder
                .customize(new SepaTransferMapper())
                .byDefault();

    }

    private class SepaTransferMapper extends CustomMapper<SepaTransfer, SepaPayment> {

        @Override
        public void mapAtoB(SepaTransfer from, SepaPayment to, MappingContext context) {
            to.setAmount(AMOUNT_FORMAT.format(from.getAmount().multiply(BigDecimal.valueOf(100))));
            to.setCardholderId(from.getFrom().getCardholderId());
            to.setType("1");
        }

    }

}
