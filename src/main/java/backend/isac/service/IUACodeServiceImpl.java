package backend.isac.service;

import backend.isac.dto.IUACodeDTO;
import backend.isac.exception.ResourceNotFoundException;
import backend.isac.mapper.IUACodeMapper;
import backend.isac.model.IUACode;
import backend.isac.repository.IUACodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IUACodeServiceImpl implements IUACodeService {

    private final IUACodeRepository iuaCodeRepository;
    private final IUACodeMapper iuaCodeMapper;

    @Autowired
    public IUACodeServiceImpl(IUACodeRepository iuaCodeRepository, IUACodeMapper iuaCodeMapper) {
        this.iuaCodeRepository = iuaCodeRepository;
        this.iuaCodeMapper = iuaCodeMapper;
    }

    @Override
    public IUACodeDTO createIUACode(IUACodeDTO iuaCodeDTO) {
        IUACode iuaCode = iuaCodeMapper.toEntity(iuaCodeDTO);
        IUACode savedIUACode = iuaCodeRepository.save(iuaCode);
        return iuaCodeMapper.toDTO(savedIUACode);
    }

    @Override
    public IUACodeDTO updateIUACode(Long id, IUACodeDTO iuaCodeDTO) {
        IUACode iuaCode = iuaCodeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("IUACode not found with id " + id));

        iuaCode.setAbbreviation(iuaCodeDTO.getAbbreviation());
        iuaCode.setFullName(iuaCodeDTO.getFullName());

        IUACode updatedIUACode = iuaCodeRepository.save(iuaCode);
        return iuaCodeMapper.toDTO(updatedIUACode);
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
        return iuaCodeMapper.toDTO(iuaCode);
    }

    @Override
    public List<IUACodeDTO> getAllIUACodes() {
        return iuaCodeRepository.findAll().stream()
                .map(iuaCodeMapper::toDTO)
                .collect(Collectors.toList());
    }
}
