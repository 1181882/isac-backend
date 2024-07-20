package backend.isac.service;

import backend.isac.dto.CompanyDTO;
import backend.isac.exception.ResourceNotFoundException;
import backend.isac.model.Company;
import backend.isac.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public List<CompanyDTO> findAll() {
        return companyRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public CompanyDTO findById(Long id) {
        return companyRepository.findById(id).map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id " + id));
    }

    public CompanyDTO save(CompanyDTO companyDTO) {
        Company company = toEntity(companyDTO);
        return toDTO(companyRepository.save(company));
    }

    public void deleteById(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id " + id));
        companyRepository.delete(company);
    }

    private CompanyDTO toDTO(Company company) {
        CompanyDTO dto = new CompanyDTO();
        dto.setId(company.getId());
        dto.setAbbreviation(company.getAbbreviation());
        dto.setFullName(company.getFullName());
        return dto;
    }

    private Company toEntity(CompanyDTO dto) {
        Company company = new Company();
        company.setId(dto.getId());
        company.setAbbreviation(dto.getAbbreviation());
        company.setFullName(dto.getFullName());
        return company;
    }
}
