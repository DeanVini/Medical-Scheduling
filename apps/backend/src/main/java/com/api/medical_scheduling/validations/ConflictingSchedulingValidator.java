package com.api.medical_scheduling.validations;

import com.api.medical_scheduling.dto.SchedulingDTO;
import com.api.medical_scheduling.exception.InvalidSchedulingException;
import com.api.medical_scheduling.model.Scheduling;
import com.api.medical_scheduling.repository.SchedulingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ConflictingSchedulingValidator implements NewSchedulingValidator {

    private final SchedulingRepository schedulingRepository;

    @Override
    public void validate(SchedulingDTO scheduleRequestDTO) {
        List<Scheduling> conflictingSchedules = schedulingRepository
                .findOverlappingSchedulesByPatient(scheduleRequestDTO.getPatient().getId(),
                                                   scheduleRequestDTO.getStartTime(),
                                                   scheduleRequestDTO.getEndTime());

        if (!conflictingSchedules.isEmpty()) {
            throw new InvalidSchedulingException("Você já possui um agendamento neste horário. Por favor, escolha outro horário.");
        }
    }
}