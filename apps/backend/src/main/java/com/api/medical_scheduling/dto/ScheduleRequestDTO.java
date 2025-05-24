package com.api.medical_scheduling.dto;

import com.api.medical_scheduling.model.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScheduleRequestDTO {
    private Long id;
    private String date;
    private String time;
    private String status;
    private User medic;
    private User patient;
}
