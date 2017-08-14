package com.weststein.controller.secured.model.business;

import com.weststein.security.model.entity.Role;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AuthorizedUser {

    private Long roleId;
    private String firstName;
    private String lastName;
    private Role role;

}
