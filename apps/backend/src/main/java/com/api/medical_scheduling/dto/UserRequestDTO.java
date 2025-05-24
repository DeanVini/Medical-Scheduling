package com.api.medical_scheduling.dto;

import com.api.medical_scheduling.model.MedicProfile;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequestDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;
    private String name;
    private String email;
    private String username;
    private String password;
    private MedicProfile medicProfile;
}