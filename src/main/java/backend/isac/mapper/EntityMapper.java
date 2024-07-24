package backend.isac.mapper;

import backend.isac.dto.uipath.*;
import backend.isac.model.uipath.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EntityMapper {

    // UiPathOrchestrator
    @Mapping(source = "tenants", target = "tenants")
    UiPathOrchestratorDTO toUiPathOrchestratorDTO(UiPathOrchestrator orchestrator);
    @Mapping(target = "tenants", ignore = true)
    UiPathOrchestrator toUiPathOrchestrator(UiPathOrchestratorDTO orchestratorDTO);

    // UiPathTenant
    @Mapping(source = "orchestrator.id", target = "orchestratorId")
    UiPathTenantDTO toUiPathTenantDTO(UiPathTenant tenant);
    @Mapping(source = "orchestratorId", target = "orchestrator.id")
    UiPathTenant toUiPathTenant(UiPathTenantDTO tenantDTO);

    // UiPathFolder
    @Mapping(source = "tenant.id", target = "tenantId")
    UiPathFolderDTO toUiPathFolderDTO(UiPathFolder folder);
    @Mapping(source = "tenantId", target = "tenant.id")
    UiPathFolder toUiPathFolder(UiPathFolderDTO folderDTO);

    // UiPathProcess
    @Mapping(source = "folder.id", target = "folderId")
    UiPathProcessDTO toUiPathProcessDTO(UiPathProcess process);
    @Mapping(source = "folderId", target = "folder.id")
    UiPathProcess toUiPathProcess(UiPathProcessDTO processDTO);

    // UiPathTrigger
    @Mapping(source = "process.id", target = "processId")
    UiPathTriggerDTO toUiPathTriggerDTO(UiPathTrigger trigger);
    @Mapping(source = "processId", target = "process.id")
    UiPathTrigger toUiPathTrigger(UiPathTriggerDTO triggerDTO);

    // UiPathLicense
    @Mapping(source = "tenant.id", target = "tenantId")
    UiPathLicenseDTO toUiPathLicenseDTO(UiPathLicense license);
    @Mapping(source = "tenantId", target = "tenant.id")
    UiPathLicense toUiPathLicense(UiPathLicenseDTO licenseDTO);

    // UiPathUser
    @Mapping(source = "tenant.id", target = "tenantId")
    UiPathUserDTO toUiPathUserDTO(UiPathUser user);
    @Mapping(source = "tenantId", target = "tenant.id")
    UiPathUser toUiPathUser(UiPathUserDTO userDTO);

    // UiPathQueue
    @Mapping(source = "folder.id", target = "folderId")
    UiPathQueueDTO toUiPathQueueDTO(UiPathQueue queue);
    @Mapping(source = "folderId", target = "folder.id")
    UiPathQueue toUiPathQueue(UiPathQueueDTO queueDTO);

    // UiPathLibrary
    @Mapping(source = "folder.id", target = "folderId")
    UiPathLibraryDTO toUiPathLibraryDTO(UiPathLibrary library);
    @Mapping(source = "folderId", target = "folder.id")
    UiPathLibrary toUiPathLibrary(UiPathLibraryDTO libraryDTO);

    // UiPathRobot
    @Mapping(source = "machine.id", target = "machineId")
    @Mapping(source = "tenant.id", target = "tenantId")
    @Mapping(source = "environment.id", target = "environmentId")
    UiPathRobotDTO toUiPathRobotDTO(UiPathRobot robot);
    @Mapping(source = "machineId", target = "machine.id")
    @Mapping(source = "tenantId", target = "tenant.id")
    @Mapping(source = "environmentId", target = "environment.id")
    UiPathRobot toUiPathRobot(UiPathRobotDTO robotDTO);

    // UiPathMachine
    @Mapping(source = "tenant.id", target = "tenantId")
    UiPathMachineDTO toUiPathMachineDTO(UiPathMachine machine);
    @Mapping(source = "tenantId", target = "tenant.id")
    UiPathMachine toUiPathMachine(UiPathMachineDTO machineDTO);

    // UiPathEnvironment
    @Mapping(source = "tenant.id", target = "tenantId")
    UiPathEnvironmentDTO toUiPathEnvironmentDTO(UiPathEnvironment environment);
    @Mapping(source = "tenantId", target = "tenant.id")
    UiPathEnvironment toUiPathEnvironment(UiPathEnvironmentDTO environmentDTO);


}
