package backend.isac.service;

import backend.isac.dto.DocumentDTO;
import backend.isac.exception.ResourceNotFoundException;
import backend.isac.model.Document;
import backend.isac.repository.DocumentRepository;
import backend.isac.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public List<DocumentDTO> findAll() {
        return documentRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public DocumentDTO findById(Long id) throws ResourceNotFoundException {
        return documentRepository.findById(id).map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Document not found with id " + id));
    }

    @Override
    public DocumentDTO save(DocumentDTO documentDTO) {
        Document document = toEntity(documentDTO);
        return toDTO(documentRepository.save(document));
    }

    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document not found with id " + id));
        documentRepository.delete(document);
    }

    private DocumentDTO toDTO(Document document) {
        DocumentDTO dto = new DocumentDTO();
        dto.setId(document.getId());
        dto.setName(document.getName());
        dto.setUrl(document.getUrl());
        dto.setComment(document.getComment());
        dto.setLanguage(document.getLanguage());
        dto.setDocumentType(document.getDocumentType());
        dto.setCompletionLevel(document.getCompletionLevel());
        return dto;
    }

    private Document toEntity(DocumentDTO dto) {
        Document document = new Document();
        document.setId(dto.getId());
        document.setName(dto.getName());
        document.setUrl(dto.getUrl());
        document.setComment(dto.getComment());
        document.setLanguage(dto.getLanguage());
        document.setDocumentType(dto.getDocumentType());
        document.setCompletionLevel(dto.getCompletionLevel());
        return document;
    }
}