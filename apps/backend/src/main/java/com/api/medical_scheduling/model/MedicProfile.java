package com.api.medical_scheduling.model;


import com.fasterxml.jackson.annotation.JsonIdentityReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "medic")
@Table(name = "medic_profiles")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @JsonIdentityReference(alwaysAsId = true)
    @OneToOne(mappedBy = "medicProfile")
    private User user;

    private String specialty;

    @OneToMany(mappedBy = "medic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ScheduleUnavailability> unavailabilities = new ArrayList<>();

    @OneToMany(mappedBy = "medicProfile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Scheduling> schedules = new ArrayList<>();

    public void addUnavailability(ScheduleUnavailability unavailability) {
        unavailabilities.add(unavailability);
        unavailability.setMedic(this);
    }

    public void addSchedule(Scheduling scheduling) {
        schedules.add(scheduling);
        scheduling.setMedicProfile(this);
    }

}