package backend.isac.service;

import backend.isac.model.Department;
import backend.isac.model.ProfessionalFunction;
import backend.isac.dto.EmployeeDTO;
import backend.isac.exception.ResourceNotFoundException;
import backend.isac.model.Employee;
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

    @Autowired
    public EmployeeServiceImpl(DepartmentRepository departmentRepository, ProfessionalFunctionRepository professionalFunctionRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.professionalFunctionRepository = professionalFunctionRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeDTO> findAll() {
        return employeeRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO findById(Long id) throws ResourceNotFoundException {
        return employeeRepository.findById(id).map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
    }

    @Override
    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        Employee employee = toEntity(employeeDTO);
        return toDTO(employeeRepository.save(employee));
    }

    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
        employeeRepository.delete(employee);
    }

    private EmployeeDTO toDTO(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setUsername(employee.getUsername());
        dto.setEmail(employee.getEmail());
        dto.setActive(employee.isActive());
        dto.setInternal(employee.isInternal());
        dto.setDepartmentId(employee.getDepartment().getId());
        dto.setProfessionalFunctionId(employee.getProfessionalFunction().getId());
        return dto;
    }

    private Employee toEntity(EmployeeDTO dto) {
        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setName(dto.getName());
        employee.setUsername(dto.getUsername());
        employee.setEmail(dto.getEmail());
        employee.setActive(dto.isActive());
        employee.setInternal(dto.isInternal());

        // Retrieve department by ID or throw exception if not found
        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + dto.getDepartmentId()));

        employee.setDepartment(department);

        ProfessionalFunction professionalFunction = professionalFunctionRepository.findById(dto.getProfessionalFunctionId())
                .orElseThrow(() -> new ResourceNotFoundException("Professional Funcion not found with id " + dto.getProfessionalFunctionId()));

        employee.setProfessionalFunction(professionalFunction);

        return employee;
    }
}
