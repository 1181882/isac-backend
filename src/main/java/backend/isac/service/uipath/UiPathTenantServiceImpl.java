package backend.isac.service.uipath;

import backend.isac.dto.uipath.UiPathTenantDTO;
import backend.isac.mapper.uipath.UiPathTenantMapper;
import backend.isac.model.uipath.UiPathTenant;
import backend.isac.repository.uipath.UiPathTenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UiPathTenantServiceImpl implements UiPathTenantService {

    private final UiPathTenantRepository tenantRepository;
    private final UiPathTenantMapper entityMapper;

    @Autowired
    public UiPathTenantServiceImpl(UiPathTenantRepository tenantRepository, UiPathTenantMapper entityMapper) {
        this.tenantRepository = tenantRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    public List<UiPathTenantDTO> findAll() {
        return tenantRepository.findAll()
                .stream()
                .map(entityMapper::toUiPathTenantDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UiPathTenantDTO findById(Long id) {
        UiPathTenant tenant = tenantRepository.findById(id).orElse(null);
        return entityMapper.toUiPathTenantDTO(tenant);
    }

    @Override
    public UiPathTenantDTO save(UiPathTenantDTO tenantDTO) {
        UiPathTenant tenant = entityMapper.toUiPathTenant(tenantDTO);
        UiPathTenant savedTenant = tenantRepository.save(tenant);
        return entityMapper.toUiPathTenantDTO(savedTenant);
    }

    @Override
    public void delete(Long id) {
        tenantRepository.deleteById(id);
    }
}