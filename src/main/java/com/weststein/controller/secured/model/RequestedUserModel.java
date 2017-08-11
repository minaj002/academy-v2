package com.weststein.controller.secured.model;

import com.weststein.security.model.entity.Role;
import lombok.Data;

@Data
public class RequestedUserModel {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String enterpriseName;
    private Role requestedRole;
    private String requesterFirstName;
    private String requesterLastName;

}
