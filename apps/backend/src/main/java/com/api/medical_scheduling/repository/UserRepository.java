package com.api.medical_scheduling.repository;

import com.api.medical_scheduling.interfaces.JpaSpecificationRepository;
import com.api.medical_scheduling.model.User;

import java.util.Optional;

public interface UserRepository extends JpaSpecificationRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByEmail(String email);
}
