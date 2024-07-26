package backend.isac.service;

import backend.isac.dto.DepartmentDTO;
import backend.isac.exception.ResourceNotFoundException;
import backend.isac.mapper.DepartmentMapper;
import backend.isac.model.BusinessLine;
import backend.isac.model.Department;
import backend.isac.repository.BusinessLineRepository;
import backend.isac.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final BusinessLineRepository businessLineRepository;
    private final DepartmentMapper departmentMapper;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository, BusinessLineRepository businessLineRepository, DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.businessLineRepository = businessLineRepository;
        this.departmentMapper = departmentMapper;
    }

    @Override
    public List<DepartmentDTO> findAll() {
        return departmentRepository.findAll().stream().map(departmentMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public DepartmentDTO findById(Long id) {
        return departmentRepository.findById(id).map(departmentMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + id));
    }

    @Override
    public DepartmentDTO save(DepartmentDTO departmentDTO) {
        Department department = departmentMapper.toEntity(departmentDTO);
        BusinessLine businessLine = businessLineRepository.findById(departmentDTO.getBusinessLineId())
                .orElseThrow(() -> new ResourceNotFoundException("BusinessLine not found with id " + departmentDTO.getBusinessLineId()));
        department.setBusinessLine(businessLine);
        return departmentMapper.toDTO(departmentRepository.save(department));
    }

    @Override
    public void deleteById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + id));
        departmentRepository.delete(department);
    }
}
