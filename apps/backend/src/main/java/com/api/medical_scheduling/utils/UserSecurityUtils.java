package com.api.medical_scheduling.utils;

import com.api.medical_scheduling.model.User;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class UserSecurityUtils {

    public boolean isCurrentUserOrAdmin(Authentication authentication, Long userId) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof User user) {
            return user.getId().equals(userId) || Boolean.TRUE.equals(user.getAdmin());
        }

        return false;
    }

    public void validateCurrentUserOrAdmin(Authentication authentication, Long userId) {
        if (!isCurrentUserOrAdmin(authentication, userId)) {
            throw new AccessDeniedException("Acesso não autorizado a dados de outro usuário");
        }
    }
}