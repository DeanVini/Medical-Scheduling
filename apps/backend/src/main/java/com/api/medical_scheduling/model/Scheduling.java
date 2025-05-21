package com.api.medical_scheduling.model;

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

    private ZonedDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "medic_id")
    private MedicProfile medic;


    private String patient;

    private String status;

    private String notes;
}
