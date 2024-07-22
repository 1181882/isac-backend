package backend.isac.service;

import backend.isac.dto.AutomatedApplicationDTO;

import java.util.List;

public interface AutomatedApplicationService {
    AutomatedApplicationDTO createAutomatedApplication(AutomatedApplicationDTO automatedApplicationDTO);

    AutomatedApplicationDTO updateAutomatedApplication(Long id, AutomatedApplicationDTO automatedApplicationDTO);

    void deleteAutomatedApplication(Long id);

    AutomatedApplicationDTO getAutomatedApplicationById(Long id);

    List<AutomatedApplicationDTO> getAllAutomatedApplications();
}
