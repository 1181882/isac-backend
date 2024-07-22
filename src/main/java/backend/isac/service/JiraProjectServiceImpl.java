package backend.isac.service;

import backend.isac.dto.JiraProjectDTO;
import backend.isac.exception.ResourceNotFoundException;
import backend.isac.model.JiraProject;
import backend.isac.repository.JiraProjectRepository;
import backend.isac.service.JiraProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JiraProjectServiceImpl implements JiraProjectService {

    private final JiraProjectRepository jiraProjectRepository;

    @Autowired
    public JiraProjectServiceImpl(JiraProjectRepository jiraProjectRepository) {
        this.jiraProjectRepository = jiraProjectRepository;
    }

    @Override
    public JiraProjectDTO createJiraProject(JiraProjectDTO jiraProjectDTO) {
        JiraProject jiraProject = toEntity(jiraProjectDTO);
        JiraProject savedProject = jiraProjectRepository.save(jiraProject);
        return toDTO(savedProject);
    }

    @Override
    public JiraProjectDTO updateJiraProject(Long id, JiraProjectDTO jiraProjectDTO) {
        JiraProject jiraProject = jiraProjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jira Project not found with id " + id));

        jiraProject.setName(jiraProjectDTO.getName());

        JiraProject updatedProject = jiraProjectRepository.save(jiraProject);
        return toDTO(updatedProject);
    }

    @Override
    public void deleteJiraProject(Long id) {
        JiraProject jiraProject = jiraProjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jira Project not found with id " + id));
        jiraProjectRepository.delete(jiraProject);
    }

    @Override
    public JiraProjectDTO getJiraProjectById(Long id) {
        JiraProject jiraProject = jiraProjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jira Project not found with id " + id));
        return toDTO(jiraProject);
    }

    @Override
    public List<JiraProjectDTO> getAllJiraProjects() {
        return jiraProjectRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private JiraProject toEntity(JiraProjectDTO jiraProjectDTO) {
        JiraProject jiraProject = new JiraProject();
        jiraProject.setName(jiraProjectDTO.getName());
        return jiraProject;
    }

    private JiraProjectDTO toDTO(JiraProject jiraProject) {
        JiraProjectDTO dto = new JiraProjectDTO();
        dto.setId(jiraProject.getId());
        dto.setName(jiraProject.getName());
        return dto;
    }
}
