package backend.isac.mapper;

import backend.isac.dto.DocumentDTO;
import backend.isac.model.Document;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DocumentMapper {
    @Mapping(source = "projectVersion.id", target = "projectVersionId")
    DocumentDTO toDTO(Document document);

    @Mapping(source = "projectVersionId", target = "projectVersion.id")
    Document toEntity(DocumentDTO documentDTO);
}
