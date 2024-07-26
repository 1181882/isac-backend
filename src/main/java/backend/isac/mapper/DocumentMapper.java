package backend.isac.mapper;

import backend.isac.dto.DocumentDTO;
import backend.isac.model.Document;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DocumentMapper {
    DocumentDTO toDTO(Document document);

    Document toEntity(DocumentDTO documentDTO);
}
