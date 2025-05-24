package com.api.medical_scheduling.dto;

import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Builder
@Data
public class ScheduleDTO {
    private Long id;
    private ZonedDateTime dateTime;
    private String date;
    private String time;
    private String status;
    private String notes;
    private UserRequestDTO medic;
    private UserRequestDTO patient;
}
