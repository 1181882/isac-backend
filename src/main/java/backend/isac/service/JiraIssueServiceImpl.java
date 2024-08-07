package backend.isac.service;

import backend.isac.dto.JiraIssueDTO;
import backend.isac.exception.ResourceNotFoundException;
import backend.isac.mapper.JiraIssueMapper;
import backend.isac.model.JiraIssue;
import backend.isac.model.JiraProject;
import backend.isac.model.ProjectVersion;
import backend.isac.repository.JiraIssueRepository;
import backend.isac.repository.JiraProjectRepository;
import backend.isac.repository.ProjectVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JiraIssueServiceImpl implements JiraIssueService {

    private final JiraIssueRepository jiraIssueRepository;
    private final JiraProjectRepository jiraProjectRepository;
    private final ProjectVersionRepository projectVersionRepository;
    private final JiraIssueMapper jiraIssueMapper;

    @Autowired
    public JiraIssueServiceImpl(JiraIssueRepository jiraIssueRepository, JiraProjectRepository jiraProjectRepository, ProjectVersionRepository projectVersionRepository, JiraIssueMapper jiraIssueMapper) {
        this.jiraIssueRepository = jiraIssueRepository;
        this.jiraProjectRepository = jiraProjectRepository;
        this.projectVersionRepository = projectVersionRepository;
        this.jiraIssueMapper = jiraIssueMapper;
    }

    @Override
    public JiraIssueDTO createJiraIssue(JiraIssueDTO jiraIssueDTO) {
        JiraIssue jiraIssue = new JiraIssue();
        setCommonJiraIssueFields(jiraIssue, jiraIssueDTO);
        JiraIssue savedIssue = jiraIssueRepository.save(jiraIssue);
        return jiraIssueMapper.toDTO(savedIssue);
    }

    @Override
    public JiraIssueDTO updateJiraIssue(Long id, JiraIssueDTO jiraIssueDTO) {
        JiraIssue jiraIssue = jiraIssueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jira Issue not found with id " + id));
        setCommonJiraIssueFields(jiraIssue, jiraIssueDTO);
        JiraIssue updatedIssue = jiraIssueRepository.save(jiraIssue);
        return jiraIssueMapper.toDTO(updatedIssue);
    }
    
    @Override
    public void deleteJiraIssue(Long id) {
        JiraIssue jiraIssue = jiraIssueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jira Issue not found with id " + id));
        jiraIssueRepository.delete(jiraIssue);
    }

    @Override
    public JiraIssueDTO getJiraIssueById(Long id) {
        JiraIssue jiraIssue = jiraIssueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jira Issue not found with id " + id));
        JiraIssueDTO jiraIssueDTO = jiraIssueMapper.toDTO(jiraIssue);
        jiraIssueDTO.setProjectVersionIds(jiraIssue.getProjectVersions().stream().map(ProjectVersion::getId).collect(Collectors.toList()));
        return jiraIssueDTO;
    }

    @Override
    public List<JiraIssueDTO> getAllJiraIssues() {
        return jiraIssueRepository.findAll().stream()
                .map(this::convertToDtoWithRelations)
                .collect(Collectors.toList());
    }

    private void setCommonJiraIssueFields(JiraIssue jiraIssue, JiraIssueDTO jiraIssueDTO) {
        JiraProject jiraProject = jiraProjectRepository.findById(jiraIssueDTO.getJiraProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Jira Project not found with id " + jiraIssueDTO.getJiraProjectId()));
        jiraIssue.setName(jiraIssueDTO.getName());
        jiraIssue.setIssueType(jiraIssueDTO.getIssueType());
        jiraIssue.setIssueStatus(jiraIssueDTO.getIssueStatus());
        jiraIssue.setJiraProject(jiraProject);

        if (jiraIssueDTO.getProjectVersionIds() != null) {
            List<ProjectVersion> projectVersions = projectVersionRepository.findAllById(jiraIssueDTO.getProjectVersionIds());
            jiraIssue.setProjectVersions(projectVersions);
        }
    }

    private JiraIssueDTO convertToDtoWithRelations(JiraIssue jiraIssue) {
        JiraIssueDTO dto = jiraIssueMapper.toDTO(jiraIssue);
        dto.setProjectVersionIds(jiraIssue.getProjectVersions().stream().map(ProjectVersion::getId).collect(Collectors.toList()));
        return dto;
    }
}
