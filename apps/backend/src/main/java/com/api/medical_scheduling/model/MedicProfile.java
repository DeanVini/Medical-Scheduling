package com.api.medical_scheduling.model;


import jakarta.persistence.*;
import lombok.*;

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

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    private String specialty;

    @OneToMany(mappedBy = "medic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ScheduleUnavailability> unavailabilities = new ArrayList<>();

    @OneToMany(mappedBy = "medic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Scheduling> schedules = new ArrayList<>();

    public void addUnavailability(ScheduleUnavailability unavailability) {
        unavailabilities.add(unavailability);
        unavailability.setMedic(this);
    }

    public void addSchedule(Scheduling scheduling) {
        schedules.add(scheduling);
        scheduling.setMedic(this);
    }

}