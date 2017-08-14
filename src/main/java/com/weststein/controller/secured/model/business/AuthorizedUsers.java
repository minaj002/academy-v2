package com.weststein.controller.secured.model.business;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class AuthorizedUsers {

    private List<AuthorizedUser> authorizedUsers;

}
