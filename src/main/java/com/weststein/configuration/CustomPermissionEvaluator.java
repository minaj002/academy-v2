package com.weststein.configuration;

import com.weststein.repository.UserRole;
import com.weststein.security.UserService;
import com.weststein.security.model.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {

    @Autowired
    private UserService userService;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if (authentication == null) {
            return false;
        }

        return hasPrivilege(authentication, (Long) targetDomainObject, permission.toString().toUpperCase());

    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable businessId, String cardholderId, Object permission) {
        if (authentication == null) {
            return false;
        }
        return hasPrivilege(authentication, (Long) businessId, Long.getLong(cardholderId), permission.toString().toUpperCase());
    }

    private boolean hasPrivilege(Authentication auth, Long businessId, Long cardholderId, String roles) {

        for (UserRole role : ((UserContext) auth.getPrincipal()).getUserRoles()) {
            if (role.getEntityId().equals(businessId) && roles.contains(role.getRole().name())) {
                userService.isAuthorizedForBusinessCardHolder(businessId, cardholderId);
                return true;
            }
        }
        return false;
    }

    private boolean hasPrivilege(Authentication auth, Long entityId, String permissionsAsString) {
        for (UserRole role : ((UserContext) auth.getPrincipal()).getUserRoles()) {
            if (role.getEntityId().equals(entityId) && permissionsAsString.contains(role.getRole().name())) {
                return true;
            }
        }
        return false;
    }
}
