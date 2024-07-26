package backend.isac.service;

import backend.isac.dto.CompanyDTO;
import backend.isac.exception.ResourceNotFoundException;
import backend.isac.mapper.CompanyMapper;
import backend.isac.model.Company;
import backend.isac.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, CompanyMapper companyMapper) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
    }

    @Override
    public List<CompanyDTO> findAll() {
        return companyRepository.findAll().stream().map(companyMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public CompanyDTO findById(Long id) {
        return companyRepository.findById(id).map(companyMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id " + id));
    }

    @Override
    public CompanyDTO save(CompanyDTO companyDTO) {
        Company company = companyMapper.toEntity(companyDTO);
        return companyMapper.toDTO(companyRepository.save(company));
    }

    @Override
    public void deleteById(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id " + id));
        companyRepository.delete(company);
    }
}
