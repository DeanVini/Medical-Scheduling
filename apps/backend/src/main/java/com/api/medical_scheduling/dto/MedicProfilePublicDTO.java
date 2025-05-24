package com.api.medical_scheduling.dto;

import com.api.medical_scheduling.model.MedicProfile;
import com.api.medical_scheduling.model.ScheduleUnavailability;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MedicProfilePublicDTO {
    private String specialty;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long medicId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String medicName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ScheduleUnavailability> unavailabilities;

    public static MedicProfilePublicDTO fromMedicProfile(MedicProfile medicProfile) {
        if (medicProfile == null) {
            return null;
        }

        return MedicProfilePublicDTO.builder()
                .specialty(medicProfile.getSpecialty())
                .medicId(medicProfile.getUser().getId())
                .medicName(medicProfile.getUser().getName())
                .unavailabilities(medicProfile.getUnavailabilities())
                .build();
    }
}