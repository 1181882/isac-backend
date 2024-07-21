package backend.isac.service;

import backend.isac.dto.DocumentDTO;
import backend.isac.exception.ResourceNotFoundException;

import java.util.List;

public interface DocumentService {
    List<DocumentDTO> findAll();

    DocumentDTO findById(Long id) throws ResourceNotFoundException;

    DocumentDTO save(DocumentDTO documentDTO);

    void deleteById(Long id) throws ResourceNotFoundException;
}
