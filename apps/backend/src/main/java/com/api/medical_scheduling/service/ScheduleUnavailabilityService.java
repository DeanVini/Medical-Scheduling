package com.api.medical_scheduling.service;

import com.api.medical_scheduling.dto.ScheduleUnavailabilityDTO;
import com.api.medical_scheduling.exception.ResourceNotFoundException;
import com.api.medical_scheduling.model.ScheduleUnavailability;
import com.api.medical_scheduling.repository.ScheduleUnavailabilityRepository;
import com.api.medical_scheduling.utils.CustomBeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleUnavailabilityService {
    private final ScheduleUnavailabilityRepository scheduleUnavailabilityRepository;

    public ScheduleUnavailability getUnavailability(Long medicId) throws ResourceNotFoundException {
        return scheduleUnavailabilityRepository.getScheduleUnavailabilitiesByMedicId(medicId)
                .orElseThrow(() -> new ResourceNotFoundException("Esse médico não possui nenhuma indisponibilidade cadastrada"));
    }
    public ScheduleUnavailability updateUnavailability(Long unavailabilityId, ScheduleUnavailabilityDTO scheduleUnavailabilityDTO) throws ResourceNotFoundException {
        ScheduleUnavailability scheduleUnavailability = scheduleUnavailabilityRepository.findById(unavailabilityId)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi possível encontrar a indisponibilidade informada."));

        CustomBeanUtils.copyNonNullProperties(scheduleUnavailabilityDTO, scheduleUnavailability);

        return scheduleUnavailabilityRepository.save(scheduleUnavailability);
    }

    public ScheduleUnavailability createUnavailability(ScheduleUnavailabilityDTO scheduleUnavailabilityDTO) {
        ScheduleUnavailability scheduleUnavailability = ScheduleUnavailability.builder()
                .medic(scheduleUnavailabilityDTO.getMedic())
                .startTime(scheduleUnavailabilityDTO.getStartTime())
                .endTime(scheduleUnavailabilityDTO.getEndTime())
                .reason(scheduleUnavailabilityDTO.getReason())
                .build();

        return scheduleUnavailabilityRepository.save(scheduleUnavailability);
    }

    public void deleteUnavailability(Long unavailabilityId) throws ResourceNotFoundException {
        ScheduleUnavailability scheduleUnavailability = scheduleUnavailabilityRepository.findById(unavailabilityId)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi possível encontrar a indisponibilidade informada."));

        scheduleUnavailabilityRepository.delete(scheduleUnavailability);
    }

}
