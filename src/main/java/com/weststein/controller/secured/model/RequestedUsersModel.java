package com.weststein.controller.secured.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RequestedUsersModel {

    private List<RequestedUserModel> requestedUserModels;

}
