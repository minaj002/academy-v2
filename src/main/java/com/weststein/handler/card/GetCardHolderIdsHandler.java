package com.weststein.handler.card;

import com.weststein.repository.UserRole;
import com.weststein.security.model.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Slf4j
@Component
public class GetCardHolderIdsHandler {

    public List<UserRole> handle() {
        return ((UserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserRoles();
    }
}
