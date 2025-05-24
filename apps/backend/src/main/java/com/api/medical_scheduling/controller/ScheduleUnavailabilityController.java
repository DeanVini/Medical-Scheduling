package com.api.medical_scheduling.controller;

import com.api.medical_scheduling.dto.ScheduleUnavailabilityDTO;
import com.api.medical_scheduling.exception.ResourceNotFoundException;
import com.api.medical_scheduling.model.ScheduleUnavailability;
import com.api.medical_scheduling.service.ScheduleUnavailabilityService;
import com.api.medical_scheduling.utils.ResponseConstructorUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/unavailability")
public class ScheduleUnavailabilityController {
    private final ScheduleUnavailabilityService scheduleUnavailabilityService;

    @GetMapping("/medic/{medicId}")
    public ResponseEntity<Object> getUnavalabilityByMedicId(@PathVariable Long medicId) throws ResourceNotFoundException {
        ScheduleUnavailability scheduleUnavailability = scheduleUnavailabilityService.getUnavailability(medicId);

        return ResponseConstructorUtils.okResponse(scheduleUnavailability);
    }

    @PostMapping("/")
    public ResponseEntity<Object> createUnavailability( @RequestBody ScheduleUnavailabilityDTO scheduleUnavailabilityDTO){
        ScheduleUnavailability createdUnavailability = scheduleUnavailabilityService.createUnavailability(scheduleUnavailabilityDTO);

        return ResponseConstructorUtils.createdResponse(createdUnavailability);
    }

    @PutMapping("/{unavailabilityId}")
    public ResponseEntity<Object> updateUnavailability(@PathVariable Long unavailabilityId, @RequestBody ScheduleUnavailabilityDTO scheduleUnavailabilityDTO) throws ResourceNotFoundException {
        ScheduleUnavailability updatedUnavailability = scheduleUnavailabilityService.updateUnavailability(unavailabilityId, scheduleUnavailabilityDTO);

        return ResponseConstructorUtils.okResponse(updatedUnavailability);
    }

    @DeleteMapping("/{unavailabilityId}")
    public ResponseEntity<Object> deleteUnavailability(@PathVariable Long unavailabilityId) throws ResourceNotFoundException {
        scheduleUnavailabilityService.deleteUnavailability(unavailabilityId);

        return ResponseConstructorUtils.successResponse("Unavailability deleted successfully");
    }
}
