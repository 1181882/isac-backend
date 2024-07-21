package backend.isac.service;

import backend.isac.dto.CompanyDTO;
import backend.isac.dto.DepartmentDTO;

import java.util.List;

public interface DepartmentService {
    List<DepartmentDTO> findAll();

    DepartmentDTO findById(Long id);

    DepartmentDTO save(DepartmentDTO departmentDTO);

    void deleteById(Long id);
}
