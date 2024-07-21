package backend.isac.service;

import backend.isac.dto.BusinessLineDTO;
import backend.isac.dto.CompanyDTO;

import java.util.List;

public interface BusinessLineService {

    List<BusinessLineDTO> findAll();
    BusinessLineDTO findById(Long id);
    BusinessLineDTO save(BusinessLineDTO businessLineDTO);
    void deleteById(Long id);
}
