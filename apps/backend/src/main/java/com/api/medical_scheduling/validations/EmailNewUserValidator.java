package com.api.medical_scheduling.validations;

import com.api.medical_scheduling.dto.UserRequestDTO;
import com.api.medical_scheduling.exception.InvalidLoginException;
import com.api.medical_scheduling.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class EmailNewUserValidator implements NewUserValidator {
    private final UserRepository userRepository;

    public EmailNewUserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void validate(@NotNull UserRequestDTO userRequestDTO){
        if(Boolean.TRUE.equals(userRepository.existsByEmail(userRequestDTO.getEmail()))){
            throw new InvalidLoginException("O E-mail informado já foi cadastrado!");
        }
    }
}
