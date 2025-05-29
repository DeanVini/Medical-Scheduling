package com.api.medical_scheduling.utils;

import com.api.medical_scheduling.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


@Component
public class MedicSecurityUtils {
    private static final Logger logger = LoggerFactory.getLogger(MedicSecurityUtils.class);

    public boolean isMedic(Authentication authentication) {
        logger.debug("Verificando permissões de médico: {}", authentication);

        if (authentication == null || !authentication.isAuthenticated()) {
            logger.warn("Autenticação nula ou não autenticada");
            throw new AccessDeniedException("Usuário não autenticado");
        }

        Object principal = authentication.getPrincipal();
        logger.debug("Principal class: {}", principal.getClass().getName());

        if (principal instanceof User user) {
            boolean isMedic = user.getMedicProfile() != null;
            logger.debug("Usuário é médico: {}", isMedic);

            if (!isMedic) {
                throw new AccessDeniedException("Apenas médicos podem acessar este recurso");
            }
            return true;
        }

        logger.warn("Principal não é uma instância de User: {}", principal);
        throw new AccessDeniedException("Tipo de usuário inválido");
    }
}