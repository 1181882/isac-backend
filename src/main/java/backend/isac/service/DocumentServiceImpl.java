package backend.isac.service;

import backend.isac.dto.DocumentDTO;
import backend.isac.exception.ResourceNotFoundException;
import backend.isac.mapper.DocumentMapper;
import backend.isac.model.Document;
import backend.isac.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final DocumentMapper documentMapper;

    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository, DocumentMapper documentMapper) {
        this.documentRepository = documentRepository;
        this.documentMapper = documentMapper;
    }

    @Override
    public List<DocumentDTO> findAll() {
        return documentRepository.findAll().stream().map(documentMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public DocumentDTO findById(Long id) throws ResourceNotFoundException {
        return documentRepository.findById(id).map(documentMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Document not found with id " + id));
    }

    @Override
    public DocumentDTO save(DocumentDTO documentDTO) {
        Document document = documentMapper.toEntity(documentDTO);
        return documentMapper.toDTO(documentRepository.save(document));
    }

    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document not found with id " + id));
        documentRepository.delete(document);
    }
}
