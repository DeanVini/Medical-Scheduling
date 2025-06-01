package com.api.medical_scheduling.service;

import com.api.medical_scheduling.dto.RescheduleDTO;
import com.api.medical_scheduling.dto.SchedulingDTO;
import com.api.medical_scheduling.enums.SchedulingStatus;
import com.api.medical_scheduling.model.Scheduling;
import com.api.medical_scheduling.repository.SchedulingRepository;
import com.api.medical_scheduling.validations.NewSchedulingValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SchedulingService {
    private final List<NewSchedulingValidator> newSchedulingValidators;
    private final SchedulingRepository schedulingRepository;


    public Scheduling createScheduling(SchedulingDTO schedulingRequestDTO) {
        validateNewScheduling(schedulingRequestDTO);


        Scheduling scheduling = Scheduling.builder()
                .patient(schedulingRequestDTO.getPatient())
                .medicProfile(schedulingRequestDTO.getMedic())
                .startTime(schedulingRequestDTO.getStartTime())
                .endTime(schedulingRequestDTO.getEndTime())
                .notes(schedulingRequestDTO.getNotes())
                .status(SchedulingStatus.PENDING)
                .build();

        return schedulingRepository.save(scheduling);
    }

    public void confirmAppointment(Long medicId, Long schedulingId) {
        Scheduling scheduling = schedulingRepository.findById(schedulingId)
                .orElseThrow(() -> new IllegalArgumentException("Agendamento não encontrado."));

        if (!Objects.equals(scheduling.getMedicProfile().getId(), medicId)) {
            throw new IllegalArgumentException("O médico não é o responsável por este agendamento.");
        }

        scheduling.setStatus(SchedulingStatus.CONFIRMED);
        schedulingRepository.save(scheduling);
    }

    public void cancelScheduling(Long userId, Long schedulingId) {
        Scheduling scheduling = schedulingRepository.findById(schedulingId)
                .orElseThrow(() -> new IllegalArgumentException("Agendamento não encontrado."));

        if (!scheduling.getPatient().getId().equals(userId) && !scheduling.getMedicProfile().getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("O usuário não é o responsável ou o solicitante deste agendamento.");
        }

        scheduling.setStatus(SchedulingStatus.CANCELED);
        schedulingRepository.save(scheduling);

    }

    public void reschedule(RescheduleDTO rescheduleDTO, Long schedulingId) {
        Scheduling scheduling = schedulingRepository.findById(schedulingId)
                .orElseThrow(() -> new IllegalArgumentException("Agendamento não encontrado."));

        scheduling.setStartTime(rescheduleDTO.getNewStartTime());
        scheduling.setEndTime(rescheduleDTO.getNewEndTime());
        scheduling.setStatus(SchedulingStatus.PENDING);

        schedulingRepository.save(scheduling);
    }

    public List<Scheduling> getMedicSchedulings(Long medicId) {
        return schedulingRepository.findByMedicProfileId(medicId);
    }

    public List<Scheduling> getPatientSchedulings(Long patientId) {
        return schedulingRepository.findByPatientId(patientId);
    }

    public void validateNewScheduling(SchedulingDTO schedulingRequestDTO) {
        newSchedulingValidators.forEach(validator -> validator.validate(schedulingRequestDTO));
    }
}
