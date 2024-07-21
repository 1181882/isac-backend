package backend.isac.service;

import backend.isac.dto.CompanyDTO;

import java.util.List;

public interface CompanyService {
    List<CompanyDTO> findAll();

    CompanyDTO findById(Long id);

    CompanyDTO save(CompanyDTO companyDTO);

    void deleteById(Long id);
}