package backend.isac.service;

import backend.isac.dto.IUACodeDTO;
import backend.isac.exception.ResourceNotFoundException;
import backend.isac.model.IUACode;
import backend.isac.repository.IUACodeRepository;
import backend.isac.service.IUACodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IUACodeServiceImpl implements IUACodeService {

    private final IUACodeRepository iuaCodeRepository;

    @Autowired
    public IUACodeServiceImpl(IUACodeRepository iuaCodeRepository) {
        this.iuaCodeRepository = iuaCodeRepository;
    }

    @Override
    public IUACodeDTO createIUACode(IUACodeDTO iuaCodeDTO) {
        IUACode iuaCode = new IUACode();
        iuaCode.setAbbreviation(iuaCodeDTO.getAbbreviation());
        iuaCode.setFullName(iuaCodeDTO.getFullName());

        IUACode savedIUACode = iuaCodeRepository.save(iuaCode);
        return toDTO(savedIUACode);
    }

    @Override
    public IUACodeDTO updateIUACode(Long id, IUACodeDTO iuaCodeDTO) {
        IUACode iuaCode = iuaCodeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("IUACode not found with id " + id));

        iuaCode.setAbbreviation(iuaCodeDTO.getAbbreviation());
        iuaCode.setFullName(iuaCodeDTO.getFullName());

        IUACode updatedIUACode = iuaCodeRepository.save(iuaCode);
        return toDTO(updatedIUACode);
    }

    @Override
    public void deleteIUACode(Long id) {
        IUACode iuaCode = iuaCodeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("IUACode not found with id " + id));
        iuaCodeRepository.delete(iuaCode);
    }

    @Override
    public IUACodeDTO getIUACodeById(Long id) {
        IUACode iuaCode = iuaCodeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("IUACode not found with id " + id));
        return toDTO(iuaCode);
    }

    @Override
    public List<IUACodeDTO> getAllIUACodes() {
        return iuaCodeRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private IUACodeDTO toDTO(IUACode iuaCode) {
        IUACodeDTO dto = new IUACodeDTO();
        dto.setId(iuaCode.getId());
        dto.setAbbreviation(iuaCode.getAbbreviation());
        dto.setFullName(iuaCode.getFullName());
        return dto;
    }
}
