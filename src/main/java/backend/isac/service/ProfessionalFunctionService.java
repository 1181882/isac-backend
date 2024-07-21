package backend.isac.service;

import backend.isac.dto.ProfessionalFunctionDTO;
import backend.isac.model.ProfessionalFunction;

import java.util.List;

public interface ProfessionalFunctionService {
    ProfessionalFunctionDTO createProfessionalFunction(ProfessionalFunctionDTO professionalFunctionDTO);

    ProfessionalFunctionDTO updateProfessionalFunction(Long id, ProfessionalFunctionDTO professionalFunctionDTO);

    void deleteProfessionalFunction(Long id);

    ProfessionalFunctionDTO getProfessionalFunctionById(Long id);

    List<ProfessionalFunctionDTO> getAllProfessionalFunctions();
}
