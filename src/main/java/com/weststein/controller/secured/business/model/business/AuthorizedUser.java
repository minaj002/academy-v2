package com.weststein.controller.secured.business.model.business;

import com.weststein.security.model.entity.Role;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AuthorizedUser {

    private Long userId;
    private Long roleId;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;

}
