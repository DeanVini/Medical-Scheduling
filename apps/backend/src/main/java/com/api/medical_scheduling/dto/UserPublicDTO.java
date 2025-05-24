package com.api.medical_scheduling.dto;

import com.api.medical_scheduling.model.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserPublicDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;
    private String name;
    private String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MedicProfilePublicDTO medicProfile;

    public static UserPublicDTO fromUser(User user) {
        if (user == null) {
            return null;
        }

        return UserPublicDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    public static UserPublicDTO fromUserWithProfile(User user) {
        if (user == null) {
            return null;
        }

        UserPublicDTO dto = fromUser(user);
        if (user.getMedicProfile() != null) {
            dto.setMedicProfile(MedicProfilePublicDTO.fromMedicProfile(user.getMedicProfile()));
        }

        return dto;
    }
}