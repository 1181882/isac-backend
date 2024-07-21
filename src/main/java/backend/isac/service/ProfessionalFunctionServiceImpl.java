package backend.isac.service;

import backend.isac.dto.ProfessionalFunctionDTO;
import backend.isac.exception.ResourceNotFoundException;
import backend.isac.model.ProfessionalFunction;
import backend.isac.repository.ProfessionalFunctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessionalFunctionServiceImpl implements ProfessionalFunctionService {

    private final ProfessionalFunctionRepository professionalFunctionRepository;

    @Autowired
    public ProfessionalFunctionServiceImpl(ProfessionalFunctionRepository professionalFunctionRepository) {
        this.professionalFunctionRepository = professionalFunctionRepository;
    }

    @Override
    public ProfessionalFunctionDTO createProfessionalFunction(ProfessionalFunctionDTO professionalFunctionDTO) {
        ProfessionalFunction professionalFunction = new ProfessionalFunction();
        professionalFunction.setName(professionalFunctionDTO.getName());
        ProfessionalFunction savedFunction = professionalFunctionRepository.save(professionalFunction);
        return toDTO(savedFunction);
    }

    @Override
    public ProfessionalFunctionDTO updateProfessionalFunction(Long id, ProfessionalFunctionDTO professionalFunctionDTO) {
        ProfessionalFunction professionalFunction = professionalFunctionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProfessionalFunction not found with id " + id));
        professionalFunction.setName(professionalFunctionDTO.getName());
        ProfessionalFunction updatedFunction = professionalFunctionRepository.save(professionalFunction);
        return toDTO(updatedFunction);
    }

    @Override
    public void deleteProfessionalFunction(Long id) {
        ProfessionalFunction professionalFunction = professionalFunctionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProfessionalFunction not found with id " + id));
        professionalFunctionRepository.delete(professionalFunction);
    }

    @Override
    public ProfessionalFunctionDTO getProfessionalFunctionById(Long id) {
        ProfessionalFunction professionalFunction = professionalFunctionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProfessionalFunction not found with id " + id));
        return toDTO(professionalFunction);
    }

    @Override
    public List<ProfessionalFunctionDTO> getAllProfessionalFunctions() {
        return professionalFunctionRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private ProfessionalFunctionDTO toDTO(ProfessionalFunction professionalFunction) {
        ProfessionalFunctionDTO dto = new ProfessionalFunctionDTO();
        dto.setId(professionalFunction.getId());
        dto.setName(professionalFunction.getName());
        return dto;
    }
}