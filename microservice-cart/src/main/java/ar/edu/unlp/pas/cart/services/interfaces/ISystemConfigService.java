package ar.edu.unlp.pas.cart.services.interfaces;

import ar.edu.unlp.pas.cart.dtos.SystemConfig.SystemConfigUpdateDto;
import ar.edu.unlp.pas.cart.models.SystemConfig;

public interface ISystemConfigService {

    SystemConfig createSystemConfig(SystemConfig systemConfig);

    SystemConfig updateSystemConfig(SystemConfigUpdateDto systemConfigUpdateDto);

    SystemConfig getSystemConfig();
}
