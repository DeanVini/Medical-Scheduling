package com.api.medical_scheduling.model;

import com.api.medical_scheduling.enums.SchedulingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Entity(name = "schedule")
@Table(name = "schedule")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Scheduling {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private ZonedDateTime startTime;

    @Column(nullable = false)
    private ZonedDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "medic_profile_id")
    private MedicProfile medicProfile;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private User patient;

    private SchedulingStatus status;

    private String notes;
}
