package com.weststein.controller.secured.model;

import com.weststein.repository.UserRole;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Builder
@Data
public class UserRolesModel {

    private List<UserRole> userRoles;

}
