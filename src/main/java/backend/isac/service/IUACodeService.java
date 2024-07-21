package backend.isac.service;

import backend.isac.dto.IUACodeDTO;

import java.util.List;

public interface IUACodeService {
    IUACodeDTO createIUACode(IUACodeDTO iuaCodeDTO);

    IUACodeDTO updateIUACode(Long id, IUACodeDTO iuaCodeDTO);

    void deleteIUACode(Long id);

    IUACodeDTO getIUACodeById(Long id);

    List<IUACodeDTO> getAllIUACodes();
}