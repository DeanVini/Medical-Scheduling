package com.api.medical_scheduling.dto;

import com.api.medical_scheduling.model.MedicProfile;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Builder
public class ScheduleUnavailabilityDTO {
    private ZonedDateTime startTime;
    private ZonedDateTime endTime;
    private String reason;
    private Boolean recurring;
    private MedicProfile medic;
}
