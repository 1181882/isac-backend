package backend.isac.service;

import backend.isac.dto.EmployeeDTO;
import backend.isac.exception.ResourceNotFoundException;
import backend.isac.mapper.EmployeeMapper;
import backend.isac.model.Department;
import backend.isac.model.Employee;
import backend.isac.model.ProfessionalFunction;
import backend.isac.repository.DepartmentRepository;
import backend.isac.repository.EmployeeRepository;
import backend.isac.repository.ProfessionalFunctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final DepartmentRepository departmentRepository;
    private final ProfessionalFunctionRepository professionalFunctionRepository;
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeServiceImpl(DepartmentRepository departmentRepository, ProfessionalFunctionRepository professionalFunctionRepository, EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.departmentRepository = departmentRepository;
        this.professionalFunctionRepository = professionalFunctionRepository;
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public List<EmployeeDTO> findAll() {
        return employeeRepository.findAll().stream().map(employeeMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO findById(Long id) throws ResourceNotFoundException {
        return employeeRepository.findById(id).map(employeeMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
    }

    @Override
    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.toEntity(employeeDTO);
        return employeeMapper.toDTO(employeeRepository.save(employee));
    }

    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
        employeeRepository.delete(employee);
    }
}
