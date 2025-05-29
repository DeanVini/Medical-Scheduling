package com.api.medical_scheduling.repository;

import com.api.medical_scheduling.model.ScheduleUnavailability;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface ScheduleUnavailabilityRepository extends JpaSpecificationRepository<ScheduleUnavailability, Long> {

    Optional<ScheduleUnavailability> getScheduleUnavailabilitiesByMedicId(@NotNull Long medicId);


}
