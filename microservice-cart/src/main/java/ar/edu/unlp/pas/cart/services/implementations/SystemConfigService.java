package ar.edu.unlp.pas.cart.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlp.pas.cart.dtos.SystemConfig.SystemConfigUpdateDto;
import ar.edu.unlp.pas.cart.models.SystemConfig;
import ar.edu.unlp.pas.cart.repositories.JpaSystemConfigRepository;
import ar.edu.unlp.pas.cart.services.interfaces.ISystemConfigService;

import javax.transaction.Transactional;

@Service
@Transactional
public class SystemConfigService implements ISystemConfigService {

    @Autowired
    JpaSystemConfigRepository systemConfigRepository;

    @Override
    public SystemConfig createSystemConfig(SystemConfig systemConfig) {
        // return systemConfigRepository.save(systemConfig);
        return null;
    }

    @Override
    public SystemConfig updateSystemConfig(SystemConfigUpdateDto systemConfigUpdateDto) {
        // SystemConfig systemConfig = systemConfigRepository.findAll().get(0);
        // systemConfig.setCommission(systemConfigUpdateDto.getCommission());
        // systemConfig.setMaxOpinionsToShow(systemConfigUpdateDto.getMaxOpinionsToShow());
        // return systemConfigRepository.save(systemConfig);
        return null;
    }

    @Override
    public SystemConfig getSystemConfig() {
        // return systemConfigRepository.findAll().get(0);
        return null;
    }
}
