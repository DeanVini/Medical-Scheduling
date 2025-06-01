package com.api.medical_scheduling.validations;


import com.api.medical_scheduling.dto.SchedulingDTO;

public interface NewSchedulingValidator {
    void validate(SchedulingDTO scheduleRequestDTO);
}
