package backend.isac.service.uipath;

import backend.isac.dto.uipath.UiPathLibraryDTO;
import backend.isac.mapper.EntityMapper;
import backend.isac.model.uipath.UiPathLibrary;
import backend.isac.repository.uipath.UiPathLibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UiPathLibraryServiceImpl implements UiPathLibraryService {

    private final UiPathLibraryRepository libraryRepository;
    private final EntityMapper entityMapper;

    @Autowired
    public UiPathLibraryServiceImpl(UiPathLibraryRepository libraryRepository, EntityMapper entityMapper) {
        this.libraryRepository = libraryRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    public List<UiPathLibraryDTO> findAll() {
        return libraryRepository.findAll()
                .stream()
                .map(entityMapper::toUiPathLibraryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UiPathLibraryDTO findById(Long id) {
        UiPathLibrary library = libraryRepository.findById(id).orElse(null);
        return entityMapper.toUiPathLibraryDTO(library);
    }

    @Override
    public UiPathLibraryDTO save(UiPathLibraryDTO libraryDTO) {
        UiPathLibrary library = entityMapper.toUiPathLibrary(libraryDTO);
        UiPathLibrary savedLibrary = libraryRepository.save(library);
        return entityMapper.toUiPathLibraryDTO(savedLibrary);
    }

    @Override
    public void delete(Long id) {
        libraryRepository.deleteById(id);
    }
}