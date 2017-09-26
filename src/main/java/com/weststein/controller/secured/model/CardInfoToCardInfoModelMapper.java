package com.weststein.controller.secured.model;

import com.weststein.infrastructure.ObjectMapperConfiguration;
import com.weststein.integration.ViewStatementModelToCardViewStatementResponseMapper;
import com.weststein.integration.response.CardInfoResponse;
import com.weststein.integration.response.CardInquiryResponse;
import com.weststein.repository.UserToBusinessRoleRequest;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperBase;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CardInfoToCardInfoModelMapper extends ObjectMapperConfiguration<CardInfoResponse, CardInfoModel> {
    @Override
    public Class<CardInfoResponse> getA() {
        return CardInfoResponse.class;
    }

    @Override
    public Class<CardInfoModel> getB() {
        return CardInfoModel.class;
    }

    @Override
    protected void fieldMapping(ClassMapBuilder<CardInfoResponse, CardInfoModel> builder) {

        builder
                .customize(new CardInfoMapper())
                .byDefault();

    }

    private class CardInfoMapper extends CustomMapper<CardInfoResponse, CardInfoModel> {

        @Override
        public void mapAtoB(CardInfoResponse a, CardInfoModel b, MappingContext context) {
            b.setAvailBal(a.getAvailBal().divide(BigDecimal.valueOf(100)));
            b.setLedgerBal(a.getLedgerBal().divide(BigDecimal.valueOf(100)));
        }
    }
}
