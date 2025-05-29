package com.api.medical_scheduling.repository;

import com.api.medical_scheduling.model.MedicProfile;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface MedicProfileRepository extends JpaSpecificationRepository<MedicProfile, Long> {
    @Override
    @NotNull
    Optional<MedicProfile> findById(@NotNull Long id);
}
