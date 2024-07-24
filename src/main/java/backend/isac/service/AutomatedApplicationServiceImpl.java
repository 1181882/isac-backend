package backend.isac.service;

import backend.isac.dto.AutomatedApplicationDTO;
import backend.isac.exception.ResourceNotFoundException;
import backend.isac.model.AutomatedApplication;
import backend.isac.repository.AutomatedApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutomatedApplicationServiceImpl implements AutomatedApplicationService {

    private final AutomatedApplicationRepository automatedApplicationRepository;

    @Autowired
    public AutomatedApplicationServiceImpl(AutomatedApplicationRepository automatedApplicationRepository) {
        this.automatedApplicationRepository = automatedApplicationRepository;
    }

    @Override
    public AutomatedApplicationDTO createAutomatedApplication(AutomatedApplicationDTO automatedApplicationDTO) {
        AutomatedApplication automatedApplication = toEntity(automatedApplicationDTO);
        AutomatedApplication savedApplication = automatedApplicationRepository.save(automatedApplication);
        return toDTO(savedApplication);
    }

    @Override
    public AutomatedApplicationDTO updateAutomatedApplication(Long id, AutomatedApplicationDTO automatedApplicationDTO) {
        AutomatedApplication automatedApplication = automatedApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found with id " + id));

        automatedApplication.setName(automatedApplicationDTO.getName());
        automatedApplication.setApplicationType(automatedApplicationDTO.getApplicationType());
        automatedApplication.setAutomationType(automatedApplicationDTO.getAutomationType());

        AutomatedApplication updatedApplication = automatedApplicationRepository.save(automatedApplication);
        return toDTO(updatedApplication);
    }

    @Override
    public void deleteAutomatedApplication(Long id) {
        AutomatedApplication automatedApplication = automatedApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found with id " + id));
        automatedApplicationRepository.delete(automatedApplication);
    }

    @Override
    public AutomatedApplicationDTO getAutomatedApplicationById(Long id) {
        AutomatedApplication automatedApplication = automatedApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found with id " + id));
        return toDTO(automatedApplication);
    }

    @Override
    public List<AutomatedApplicationDTO> getAllAutomatedApplications() {
        return automatedApplicationRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private AutomatedApplication toEntity(AutomatedApplicationDTO automatedApplicationDTO) {
        AutomatedApplication automatedApplication = new AutomatedApplication();
        automatedApplication.setName(automatedApplicationDTO.getName());
        automatedApplication.setApplicationType(automatedApplicationDTO.getApplicationType());
        automatedApplication.setAutomationType(automatedApplicationDTO.getAutomationType());
        return automatedApplication;
    }

    private AutomatedApplicationDTO toDTO(AutomatedApplication automatedApplication) {
        AutomatedApplicationDTO dto = new AutomatedApplicationDTO();
        dto.setId(automatedApplication.getId());
        dto.setName(automatedApplication.getName());
        dto.setApplicationType(automatedApplication.getApplicationType());
        dto.setAutomationType(automatedApplication.getAutomationType());
        return dto;
    }
}
