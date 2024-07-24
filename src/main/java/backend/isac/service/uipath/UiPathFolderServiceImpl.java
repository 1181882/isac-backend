package backend.isac.service.uipath;

import backend.isac.dto.uipath.*;
import backend.isac.mapper.EntityMapper;
import backend.isac.model.uipath.*;
import backend.isac.repository.uipath.UiPathFolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class UiPathFolderServiceImpl implements UiPathFolderService {

    private final UiPathFolderRepository folderRepository;
    private final EntityMapper entityMapper;

    @Autowired
    public UiPathFolderServiceImpl(UiPathFolderRepository folderRepository, EntityMapper entityMapper) {
        this.folderRepository = folderRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    public List<UiPathFolderDTO> findAll() {
        return folderRepository.findAll()
                .stream()
                .map(entityMapper::toUiPathFolderDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UiPathFolderDTO findById(Long id) {
        UiPathFolder folder = folderRepository.findById(id).orElse(null);
        return entityMapper.toUiPathFolderDTO(folder);
    }

    @Override
    public UiPathFolderDTO save(UiPathFolderDTO folderDTO) {
        UiPathFolder folder = entityMapper.toUiPathFolder(folderDTO);
        UiPathFolder savedFolder = folderRepository.save(folder);
        return entityMapper.toUiPathFolderDTO(savedFolder);
    }

    @Override
    public void delete(Long id) {
        folderRepository.deleteById(id);
    }
}
