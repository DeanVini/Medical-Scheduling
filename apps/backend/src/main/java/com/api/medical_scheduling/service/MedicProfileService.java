package com.api.medical_scheduling.service;

import com.api.medical_scheduling.dto.MedicProfileDTO;
import com.api.medical_scheduling.exception.ResourceNotFoundException;
import com.api.medical_scheduling.model.MedicProfile;
import com.api.medical_scheduling.repository.MedicProfileRepository;
import com.api.medical_scheduling.utils.CustomBeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicProfileService {

    private final MedicProfileRepository medicProfileRepository;

    public MedicProfile updateMedicProfile(Long profileID, MedicProfileDTO medicProfileDTO) throws ResourceNotFoundException {
        MedicProfile medicProfile = medicProfileRepository.findById(profileID)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil Médico nao encontrado"));

        CustomBeanUtils.copyNonNullProperties(medicProfileDTO, medicProfile);
        return medicProfileRepository.save(medicProfile);
    }

    public MedicProfile getMedicProfile(Long profileID) throws ResourceNotFoundException {
        return medicProfileRepository.findById(profileID)
                .orElseThrow(() -> new ResourceNotFoundException("Médico nao encontrado"));
    }

    public void deleteMedicProfile(Long profileID) {
        medicProfileRepository.deleteById(profileID);
    }

}
