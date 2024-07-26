package backend.isac.service;

import backend.isac.dto.BusinessLineDTO;
import backend.isac.exception.ResourceNotFoundException;
import backend.isac.mapper.BusinessLineMapper;
import backend.isac.model.BusinessLine;
import backend.isac.model.Company;
import backend.isac.repository.BusinessLineRepository;
import backend.isac.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusinessLineServiceImpl implements BusinessLineService {

    private final BusinessLineRepository businessLineRepository;
    private final CompanyRepository companyRepository;
    private final BusinessLineMapper businessLineMapper;

    @Autowired
    public BusinessLineServiceImpl(BusinessLineRepository businessLineRepository, CompanyRepository companyRepository, BusinessLineMapper businessLineMapper) {
        this.businessLineRepository = businessLineRepository;
        this.companyRepository = companyRepository;
        this.businessLineMapper = businessLineMapper;
    }

    @Override
    public List<BusinessLineDTO> findAll() {
        return businessLineRepository.findAll().stream().map(businessLineMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public BusinessLineDTO findById(Long id) {
        return businessLineRepository.findById(id).map(businessLineMapper::toDTO).orElseThrow(() -> new ResourceNotFoundException("BusinessLine not found with id " + id));
    }

    @Override
    public BusinessLineDTO save(BusinessLineDTO businessLineDTO) {
        BusinessLine businessLine = businessLineMapper.toEntity(businessLineDTO);
        Company company = companyRepository.findById(businessLineDTO.getCompanyId()).orElseThrow(() -> new ResourceNotFoundException("Company not found with id " + businessLineDTO.getCompanyId()));
        businessLine.setCompany(company);
        return businessLineMapper.toDTO(businessLineRepository.save(businessLine));
    }

    @Override
    public void deleteById(Long id) {
        BusinessLine businessLine = businessLineRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("BusinessLine not found with id " + id));
        businessLineRepository.delete(businessLine);
    }
}
