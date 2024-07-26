package backend.isac.service;

import backend.isac.dto.JiraProjectDTO;
import backend.isac.exception.ResourceNotFoundException;
import backend.isac.mapper.JiraProjectMapper;
import backend.isac.model.JiraProject;
import backend.isac.repository.JiraProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JiraProjectServiceImpl implements JiraProjectService {

    private final JiraProjectRepository jiraProjectRepository;
    private final JiraProjectMapper jiraProjectMapper;

    @Autowired
    public JiraProjectServiceImpl(JiraProjectRepository jiraProjectRepository, JiraProjectMapper jiraProjectMapper) {
        this.jiraProjectRepository = jiraProjectRepository;
        this.jiraProjectMapper = jiraProjectMapper;
    }

    @Override
    public JiraProjectDTO createJiraProject(JiraProjectDTO jiraProjectDTO) {
        JiraProject jiraProject = jiraProjectMapper.toEntity(jiraProjectDTO);
        JiraProject savedProject = jiraProjectRepository.save(jiraProject);
        return jiraProjectMapper.toDTO(savedProject);
    }

    @Override
    public JiraProjectDTO updateJiraProject(Long id, JiraProjectDTO jiraProjectDTO) {
        JiraProject jiraProject = jiraProjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jira Project not found with id " + id));

        jiraProject.setName(jiraProjectDTO.getName());

        JiraProject updatedProject = jiraProjectRepository.save(jiraProject);
        return jiraProjectMapper.toDTO(updatedProject);
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
        return jiraProjectMapper.toDTO(jiraProject);
    }

    @Override
    public List<JiraProjectDTO> getAllJiraProjects() {
        return jiraProjectRepository.findAll().stream()
                .map(jiraProjectMapper::toDTO)
                .collect(Collectors.toList());
    }
}
