package com.api.medical_scheduling.validations;

import com.api.medical_scheduling.dto.UserRequestDTO;

public interface NewUserValidator {
    void validate(UserRequestDTO userRequestDTO);
}
