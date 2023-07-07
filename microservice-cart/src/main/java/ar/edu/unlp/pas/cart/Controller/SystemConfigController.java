package ar.edu.unlp.pas.cart.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ar.edu.unlp.pas.cart.services.implementations.SystemConfigService;

@Controller
public class SystemConfigController {

    @Autowired
    private SystemConfigService systemConfigService;
    /*
     * @PatchMapping("/api/systemConfig")
     * public ResponseEntity<Object> updateSystemConfigs(@Valid @RequestBody
     * SystemConfigUpdateDto systemConfigUpdateDto) {
     * try {
     * return ResponseEntity.ok(systemConfigService.updateSystemConfig(
     * systemConfigUpdateDto));
     * } catch (Exception e) {
     * return ResponseEntity.badRequest().body(e.getMessage() + " //// " + e);
     * }
     * }
     * 
     * @GetMapping("/api/systemConfig")
     * public ResponseEntity<SystemConfigGetDto> getSystemConfig() {
     * SystemConfig systemConfig = systemConfigService.getSystemConfig();
     * SystemConfigGetDto systemConfigGetDto = new
     * SystemConfigGetDto(systemConfig.getCommission(),
     * systemConfig.getMaxOpinionsToShow());
     * return ResponseEntity.ok(systemConfigGetDto);
     * }
     */
}
