package backend.isac.service;

import backend.isac.dto.EmployeeDTO;
import backend.isac.exception.ResourceNotFoundException;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> findAll();

    EmployeeDTO findById(Long id) throws ResourceNotFoundException;

    EmployeeDTO save(EmployeeDTO employeeDTO);

    void deleteById(Long id) throws ResourceNotFoundException;
}
