package com.api.medical_scheduling.controller;

import com.api.medical_scheduling.dto.MedicProfileDTO;
import com.api.medical_scheduling.dto.MedicProfilePublicDTO;
import com.api.medical_scheduling.exception.ResourceNotFoundException;
import com.api.medical_scheduling.model.MedicProfile;
import com.api.medical_scheduling.service.MedicProfileService;
import com.api.medical_scheduling.utils.ResponseConstructorUtils;
import com.api.medical_scheduling.utils.UserSecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/medic-profile")
public class MedicProfileController {
    private final MedicProfileService medicProfileService;
    private final UserSecurityUtils userSecurityUtils;

    @GetMapping("/{medicProfileId}")
    public ResponseEntity<Object> getMedicProfile(@PathVariable Long medicProfileId, Authentication authentication) throws ResourceNotFoundException {
        MedicProfile medicProfile = medicProfileService.getMedicProfile(medicProfileId);

        if (userSecurityUtils.isCurrentUserOrAdmin(authentication, medicProfile.getUser().getId())) {
            return ResponseConstructorUtils.okResponse(medicProfile);
        }

        return ResponseConstructorUtils.okResponse(MedicProfilePublicDTO.fromMedicProfile(medicProfile));
    }

    @PutMapping("/{medicProfileId}")
    @PreAuthorize("@medicSecurityUtils.isMedic(authentication)")
    public ResponseEntity<Object> updateMedicProfile(@PathVariable Long medicProfileId, @RequestBody MedicProfileDTO medicProfileDTO) throws ResourceNotFoundException {
        MedicProfile updatedMedicProfile = medicProfileService.updateMedicProfile(medicProfileId, medicProfileDTO);
        return ResponseConstructorUtils.okResponse(updatedMedicProfile);
    }

    @DeleteMapping("/{medicProfileId}")
    @PreAuthorize("@userSecurityUtils.isCurrentUserOrAdmin(authentication, #medicProfileId)")
    public ResponseEntity<Object> deleteMedicProfile(@PathVariable Long medicProfileId) {
        medicProfileService.deleteMedicProfile(medicProfileId);
        return ResponseConstructorUtils.successResponse("Medic profile deleted successfully");
    }
}
