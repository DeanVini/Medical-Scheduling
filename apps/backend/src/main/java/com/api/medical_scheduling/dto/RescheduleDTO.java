package com.api.medical_scheduling.dto;

import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Builder
@Data
public class RescheduleDTO {
    private ZonedDateTime newStartTime;
    private ZonedDateTime newEndTime;
}
