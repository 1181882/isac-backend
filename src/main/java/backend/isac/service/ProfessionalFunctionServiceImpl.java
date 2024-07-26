package backend.isac.service;

import backend.isac.dto.ProfessionalFunctionDTO;
import backend.isac.exception.ResourceNotFoundException;
import backend.isac.mapper.ProfessionalFunctionMapper;
import backend.isac.model.ProfessionalFunction;
import backend.isac.repository.ProfessionalFunctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessionalFunctionServiceImpl implements ProfessionalFunctionService {

    private final ProfessionalFunctionRepository professionalFunctionRepository;
    private final ProfessionalFunctionMapper professionalFunctionMapper;

    @Autowired
    public ProfessionalFunctionServiceImpl(ProfessionalFunctionRepository professionalFunctionRepository, ProfessionalFunctionMapper professionalFunctionMapper) {
        this.professionalFunctionRepository = professionalFunctionRepository;
        this.professionalFunctionMapper = professionalFunctionMapper;
    }

    @Override
    public ProfessionalFunctionDTO createProfessionalFunction(ProfessionalFunctionDTO professionalFunctionDTO) {
        ProfessionalFunction professionalFunction = professionalFunctionMapper.toEntity(professionalFunctionDTO);
        ProfessionalFunction savedFunction = professionalFunctionRepository.save(professionalFunction);
        return professionalFunctionMapper.toDTO(savedFunction);
    }

    @Override
    public ProfessionalFunctionDTO updateProfessionalFunction(Long id, ProfessionalFunctionDTO professionalFunctionDTO) {
        ProfessionalFunction professionalFunction = professionalFunctionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProfessionalFunction not found with id " + id));
        professionalFunction.setName(professionalFunctionDTO.getName());
        ProfessionalFunction updatedFunction = professionalFunctionRepository.save(professionalFunction);
        return professionalFunctionMapper.toDTO(updatedFunction);
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
        return professionalFunctionMapper.toDTO(professionalFunction);
    }

    @Override
    public List<ProfessionalFunctionDTO> getAllProfessionalFunctions() {
        return professionalFunctionRepository.findAll().stream()
                .map(professionalFunctionMapper::toDTO)
                .collect(Collectors.toList());
    }
}
