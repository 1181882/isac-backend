package backend.isac.service;

import backend.isac.dto.JiraIssueDTO;
import backend.isac.exception.ResourceNotFoundException;
import backend.isac.mapper.JiraIssueMapper;
import backend.isac.model.JiraIssue;
import backend.isac.model.JiraProject;
import backend.isac.repository.JiraIssueRepository;
import backend.isac.repository.JiraProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JiraIssueServiceImpl implements JiraIssueService {

    private final JiraIssueRepository jiraIssueRepository;
    private final JiraProjectRepository jiraProjectRepository;
    private final JiraIssueMapper jiraIssueMapper;

    @Autowired
    public JiraIssueServiceImpl(JiraIssueRepository jiraIssueRepository, JiraProjectRepository jiraProjectRepository, JiraIssueMapper jiraIssueMapper) {
        this.jiraIssueRepository = jiraIssueRepository;
        this.jiraProjectRepository = jiraProjectRepository;
        this.jiraIssueMapper = jiraIssueMapper;
    }

    @Override
    public JiraIssueDTO createJiraIssue(JiraIssueDTO jiraIssueDTO) {
        JiraProject jiraProject = jiraProjectRepository.findById(jiraIssueDTO.getJiraProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("JiraProject not found with id " + jiraIssueDTO.getJiraProjectId()));

        JiraIssue jiraIssue = jiraIssueMapper.toEntity(jiraIssueDTO);
        jiraIssue.setJiraProject(jiraProject);

        JiraIssue savedIssue = jiraIssueRepository.save(jiraIssue);
        return jiraIssueMapper.toDTO(savedIssue);
    }

    @Override
    public JiraIssueDTO updateJiraIssue(Long id, JiraIssueDTO jiraIssueDTO) {
        JiraIssue jiraIssue = jiraIssueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("JiraIssue not found with id " + id));

        JiraProject jiraProject = jiraProjectRepository.findById(jiraIssueDTO.getJiraProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("JiraProject not found with id " + jiraIssueDTO.getJiraProjectId()));

        jiraIssue.setName(jiraIssueDTO.getName());
        jiraIssue.setIssueType(jiraIssueDTO.getIssueType());
        jiraIssue.setJiraProject(jiraProject);

        JiraIssue updatedIssue = jiraIssueRepository.save(jiraIssue);
        return jiraIssueMapper.toDTO(updatedIssue);
    }

    @Override
    public void deleteJiraIssue(Long id) {
        JiraIssue jiraIssue = jiraIssueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("JiraIssue not found with id " + id));
        jiraIssueRepository.delete(jiraIssue);
    }

    @Override
    public JiraIssueDTO getJiraIssueById(Long id) {
        JiraIssue jiraIssue = jiraIssueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("JiraIssue not found with id " + id));
        return jiraIssueMapper.toDTO(jiraIssue);
    }

    @Override
    public List<JiraIssueDTO> getAllJiraIssues() {
        return jiraIssueRepository.findAll().stream()
                .map(jiraIssueMapper::toDTO)
                .collect(Collectors.toList());
    }
}
