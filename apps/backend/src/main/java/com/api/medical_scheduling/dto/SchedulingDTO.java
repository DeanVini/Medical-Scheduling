package com.api.medical_scheduling.dto;

import com.api.medical_scheduling.enums.SchedulingStatus;
import com.api.medical_scheduling.model.MedicProfile;
import com.api.medical_scheduling.model.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Builder
@Data
public class SchedulingDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;
    private ZonedDateTime startTime;
    private ZonedDateTime endTime;
    private SchedulingStatus status;
    private User patient;
    private MedicProfile medic;
    private String specialty;
    private String notes;
}
