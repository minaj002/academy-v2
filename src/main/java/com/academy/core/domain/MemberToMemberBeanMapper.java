package com.academy.core.domain;

import com.academy.core.dto.MemberBean;
import com.academy.infrastructure.ObjectMapperConfiguration;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.springframework.stereotype.Component;

@Component
public class MemberToMemberBeanMapper extends ObjectMapperConfiguration<Member, MemberBean> {
    @Override
    public Class<Member> getA() {
        return Member.class;
    }

    @Override
    public Class<MemberBean> getB() {
        return MemberBean.class;
    }

    @Override
    protected void fieldMapping(ClassMapBuilder<Member, MemberBean> builder) {

        builder
                .field("address.street", "street")
                .field("address.city", "city")
                .byDefault();

    }

}
