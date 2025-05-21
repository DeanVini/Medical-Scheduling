package com.api.medical_scheduling.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Entity(name = "schedule_unavailability")
@Table(name = "schedule_unavailability")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleUnavailability {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "medic_id")
    private MedicProfile medic;

    @Column(nullable = false)
    private ZonedDateTime startTime;

    @Column(nullable = false)
    private ZonedDateTime endTime;

    @Column(length = 100)
    private String reason;

    private Boolean recurring = false;
}
