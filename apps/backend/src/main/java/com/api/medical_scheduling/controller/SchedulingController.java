package com.api.medical_scheduling.controller;

import com.api.medical_scheduling.dto.RescheduleDTO;
import com.api.medical_scheduling.dto.SchedulingDTO;
import com.api.medical_scheduling.model.Scheduling;
import com.api.medical_scheduling.service.SchedulingService;
import com.api.medical_scheduling.utils.ResponseConstructorUtils;
import com.api.medical_scheduling.utils.UserSecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/scheduling")
public class SchedulingController {

    private final SchedulingService schedulingService;
    private final UserSecurityUtils userSecurityUtils;

    @PostMapping("/")
    public ResponseEntity<Object> createScheduling(@RequestBody SchedulingDTO schedulingDTO) {
        Scheduling scheduling = schedulingService.createScheduling(schedulingDTO);

        return ResponseConstructorUtils.createdResponse(scheduling);

    }

    @GetMapping("/medic/{medicId}")
    public ResponseEntity<Object> getMedicSchedulings(@PathVariable Long medicId) {
        List<Scheduling> schedules = schedulingService.getMedicSchedulings(medicId);
        return ResponseConstructorUtils.okResponse(schedules);
    }

    @PatchMapping("/{schedulingId}/confirm")
    public ResponseEntity<Object> confirmAppointment(
            @PathVariable Long schedulingId,
            Authentication authentication) {
        Long medicId = userSecurityUtils.getCurrentUserId(authentication);
        schedulingService.confirmAppointment(medicId, schedulingId);
        return ResponseConstructorUtils.successResponse("Agendamento confirmado com sucesso");
    }

    @PatchMapping("/{schedulingId}/cancel")
    public ResponseEntity<Object> cancelScheduling(
            @PathVariable Long schedulingId,
            Authentication authentication) {
        Long userId = userSecurityUtils.getCurrentUserId(authentication);
        schedulingService.cancelScheduling(userId, schedulingId);
        return ResponseConstructorUtils.successResponse("Agendamento cancelado com sucesso");
    }

    @PatchMapping("/{schedulingId}/reschedule")
    public ResponseEntity<Object> rescheduleAppointment(
            @PathVariable Long schedulingId,
            @RequestBody RescheduleDTO rescheduleDTO) {
        schedulingService.reschedule(rescheduleDTO, schedulingId);
        return ResponseConstructorUtils.successResponse("Agendamento remarcado com sucesso");
    }

    @GetMapping("/patient")
    public ResponseEntity<Object> getPatientSchedulings(Authentication authentication) {
        Long patientId = userSecurityUtils.getCurrentUserId(authentication);
        List<Scheduling> schedules = schedulingService.getPatientSchedulings(patientId);
        return ResponseConstructorUtils.okResponse(schedules);
    }
}
