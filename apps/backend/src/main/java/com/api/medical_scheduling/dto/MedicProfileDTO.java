package com.api.medical_scheduling.dto;

import com.api.medical_scheduling.model.Scheduling;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MedicProfileDTO {
    private String specialty;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ScheduleUnavailabilityDTO> unavailabilities;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Scheduling> scheduleRequests;


}
