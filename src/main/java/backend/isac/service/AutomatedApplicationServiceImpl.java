package backend.isac.service;

import backend.isac.dto.AutomatedApplicationDTO;
import backend.isac.exception.ResourceNotFoundException;
import backend.isac.mapper.AutomatedApplicationMapper;
import backend.isac.model.AutomatedApplication;
import backend.isac.model.uipath.UiPathProcess;
import backend.isac.repository.AutomatedApplicationRepository;
import backend.isac.repository.uipath.UiPathProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutomatedApplicationServiceImpl implements AutomatedApplicationService {

    private final AutomatedApplicationRepository automatedApplicationRepository;
    private final UiPathProcessRepository uiPathProcessRepository;
    private final AutomatedApplicationMapper automatedApplicationMapper;

    @Autowired
    public AutomatedApplicationServiceImpl(AutomatedApplicationRepository automatedApplicationRepository, UiPathProcessRepository uiPathProcessRepository, AutomatedApplicationMapper automatedApplicationMapper) {
        this.automatedApplicationRepository = automatedApplicationRepository;
        this.uiPathProcessRepository = uiPathProcessRepository;
        this.automatedApplicationMapper = automatedApplicationMapper;
    }

    @Override
    public AutomatedApplicationDTO createAutomatedApplication(AutomatedApplicationDTO automatedApplicationDTO) {
        AutomatedApplication automatedApplication = automatedApplicationMapper.toEntity(automatedApplicationDTO);
        if (automatedApplicationDTO.getUipathProcessIds() != null) {
            List<UiPathProcess> uiPathProcesses = uiPathProcessRepository.findAllById(automatedApplicationDTO.getUipathProcessIds());
            automatedApplication.setUipathProcesses(uiPathProcesses);
        }
        AutomatedApplication savedApplication = automatedApplicationRepository.save(automatedApplication);
        return automatedApplicationMapper.toDTO(savedApplication);
    }

    @Override
    public AutomatedApplicationDTO updateAutomatedApplication(Long id, AutomatedApplicationDTO automatedApplicationDTO) {
        AutomatedApplication automatedApplication = automatedApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found with id " + id));

        automatedApplication.setName(automatedApplicationDTO.getName());
        automatedApplication.setApplicationType(automatedApplicationDTO.getApplicationType());
        automatedApplication.setAutomationType(automatedApplicationDTO.getAutomationType());

        if (automatedApplicationDTO.getUipathProcessIds() != null) {
            List<UiPathProcess> uiPathProcesses = uiPathProcessRepository.findAllById(automatedApplicationDTO.getUipathProcessIds());
            automatedApplication.setUipathProcesses(uiPathProcesses);
        }

        AutomatedApplication updatedApplication = automatedApplicationRepository.save(automatedApplication);
        return automatedApplicationMapper.toDTO(updatedApplication);
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
        return automatedApplicationMapper.toDTO(automatedApplication);
    }

    @Override
    public List<AutomatedApplicationDTO> getAllAutomatedApplications() {
        return automatedApplicationRepository.findAll().stream()
                .map(automatedApplicationMapper::toDTO)
                .collect(Collectors.toList());
    }
}
