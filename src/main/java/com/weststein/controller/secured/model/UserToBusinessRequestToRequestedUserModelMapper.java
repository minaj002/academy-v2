package com.weststein.controller.secured.model;

import com.weststein.infrastructure.ObjectMapperConfiguration;
import com.weststein.repository.UserToBusinessRoleRequest;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.springframework.stereotype.Component;

@Component
public class UserToBusinessRequestToRequestedUserModelMapper extends ObjectMapperConfiguration<UserToBusinessRoleRequest, RequestedUserModel> {
    @Override
    public Class<UserToBusinessRoleRequest> getA() {
        return UserToBusinessRoleRequest.class;
    }

    @Override
    public Class<RequestedUserModel> getB() {
        return RequestedUserModel.class;
    }

    @Override
    protected void fieldMapping(ClassMapBuilder<UserToBusinessRoleRequest, RequestedUserModel> builder) {

        builder
                .field("user.email", "email")
                .field("user.userProfile.firstName", "firstName")
                .field("user.userProfile.lastName", "lastName")
                .field("business.enterpriseName", "enterpriseName")
                .field("requester.userProfile.firstName", "requesterFirstName")
                .field("requester.userProfile.lastName", "requesterLastName")
                .byDefault();

    }
}
