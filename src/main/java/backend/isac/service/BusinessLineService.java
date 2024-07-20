package backend.isac.service;

import backend.isac.dto.BusinessLineDTO;
import backend.isac.exception.ResourceNotFoundException;
import backend.isac.model.BusinessLine;
import backend.isac.repository.BusinessLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusinessLineService {

    @Autowired
    private BusinessLineRepository businessLineRepository;

    public List<BusinessLineDTO> findAll() {
        return businessLineRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public BusinessLineDTO findById(Long id) {
        return businessLineRepository.findById(id).map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("BusinessLine not found with id " + id));
    }

    public BusinessLineDTO save(BusinessLineDTO businessLineDTO) {
        BusinessLine businessLine = toEntity(businessLineDTO);
        return toDTO(businessLineRepository.save(businessLine));
    }

    public void deleteById(Long id) {
        BusinessLine businessLine = businessLineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BusinessLine not found with id " + id));
        businessLineRepository.delete(businessLine);
    }

    private BusinessLineDTO toDTO(BusinessLine businessLine) {
        BusinessLineDTO dto = new BusinessLineDTO();
        dto.setId(businessLine.getId());
        dto.setAbbreviation(businessLine.getAbbreviation());
        dto.setFullName(businessLine.getFullName());
        dto.setEntity(businessLine.getEntity());
        dto.setCompanyId(businessLine.getCompany().getId());

        return dto;
    }

    private BusinessLine toEntity(BusinessLineDTO dto) {
        BusinessLine businessLine = new BusinessLine();
        businessLine.setId(dto.getId());
        businessLine.setAbbreviation(dto.getAbbreviation());
        businessLine.setFullName(dto.getFullName());

        return businessLine;
    }
}