package com.weststein.controller.secured.model;

import com.weststein.repository.Language;
import com.weststein.security.model.entity.Role;
import lombok.Data;

@Data
public class UserProfileModel {

    private String firstName;
    private String lastName;
    private Language language;
    private String phone;
    private String email;
    private Role role;
}
