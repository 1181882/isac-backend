package backend.isac.service.uipath;

import backend.isac.dto.uipath.UiPathUserDTO;
import backend.isac.mapper.uipath.UiPathUserMapper;
import backend.isac.model.uipath.UiPathUser;
import backend.isac.repository.uipath.UiPathUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UiPathUserServiceImpl implements UiPathUserService {

    private final UiPathUserRepository userRepository;
    private final UiPathUserMapper entityMapper;

    @Autowired
    public UiPathUserServiceImpl(UiPathUserRepository userRepository, UiPathUserMapper entityMapper) {
        this.userRepository = userRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    public List<UiPathUserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(entityMapper::toUiPathUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UiPathUserDTO findById(Long id) {
        UiPathUser user = userRepository.findById(id).orElse(null);
        return entityMapper.toUiPathUserDTO(user);
    }

    @Override
    public UiPathUserDTO save(UiPathUserDTO userDTO) {
        UiPathUser user = entityMapper.toUiPathUser(userDTO);
        UiPathUser savedUser = userRepository.save(user);
        return entityMapper.toUiPathUserDTO(savedUser);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
