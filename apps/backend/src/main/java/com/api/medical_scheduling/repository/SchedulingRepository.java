package com.api.medical_scheduling.repository;

import com.api.medical_scheduling.model.Scheduling;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.List;

public interface SchedulingRepository extends JpaSpecificationRepository<Scheduling, Long> {

    @Query("SELECT s FROM schedule s WHERE s.patient.id = :patientId " +
            "AND ((s.startTime <= :endTime AND s.endTime >= :startTime))")
    List<Scheduling> findOverlappingSchedulesByPatient(
            @Param("patientId") Long patientId,
            @Param("startTime") ZonedDateTime startTime,
            @Param("endTime") ZonedDateTime endTime);

    List<Scheduling> findByPatientId(Long patientId);

    List<Scheduling> findByMedicProfileId(Long medicId);
}
