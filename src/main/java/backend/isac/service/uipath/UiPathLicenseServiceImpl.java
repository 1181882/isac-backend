package backend.isac.service.uipath;

import backend.isac.dto.uipath.UiPathLicenseDTO;
import backend.isac.mapper.uipath.UiPathLicenseMapper;
import backend.isac.model.uipath.UiPathLicense;
import backend.isac.repository.uipath.UiPathLicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UiPathLicenseServiceImpl implements UiPathLicenseService {

    private final UiPathLicenseRepository licenseRepository;
    private final UiPathLicenseMapper entityMapper;

    @Autowired
    public UiPathLicenseServiceImpl(UiPathLicenseRepository licenseRepository, UiPathLicenseMapper entityMapper) {
        this.licenseRepository = licenseRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    public List<UiPathLicenseDTO> findAll() {
        return licenseRepository.findAll()
                .stream()
                .map(entityMapper::toUiPathLicenseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UiPathLicenseDTO findById(Long id) {
        UiPathLicense license = licenseRepository.findById(id).orElse(null);
        return entityMapper.toUiPathLicenseDTO(license);
    }

    @Override
    public UiPathLicenseDTO save(UiPathLicenseDTO licenseDTO) {
        UiPathLicense license = entityMapper.toUiPathLicense(licenseDTO);
        UiPathLicense savedLicense = licenseRepository.save(license);
        return entityMapper.toUiPathLicenseDTO(savedLicense);
    }

    @Override
    public void delete(Long id) {
        licenseRepository.deleteById(id);
    }
}