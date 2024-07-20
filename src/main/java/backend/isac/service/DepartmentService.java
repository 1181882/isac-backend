package backend.isac.service;
import backend.isac.dto.DepartmentDTO;
import backend.isac.exception.ResourceNotFoundException;
import backend.isac.model.Department;
import backend.isac.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<DepartmentDTO> findAll() {
        return departmentRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public DepartmentDTO findById(Long id) {
        return departmentRepository.findById(id).map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + id));
    }

    public DepartmentDTO save(DepartmentDTO departmentDTO) {
        Department department = toEntity(departmentDTO);
        return toDTO(departmentRepository.save(department));
    }

    public void deleteById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + id));
        departmentRepository.delete(department);
    }

    private DepartmentDTO toDTO(Department department) {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(department.getId());
        dto.setAbbreviation(department.getAbbreviation());
        dto.setFullName(department.getFullName());
        dto.setBusinessLineId(department.getBusinessLine().getId());
        return dto;
    }

    private Department toEntity(DepartmentDTO dto) {
        Department department = new Department();
        department.setId(dto.getId());
        department.setAbbreviation(dto.getAbbreviation());
        department.setFullName(dto.getFullName());

        return department;
    }

}
