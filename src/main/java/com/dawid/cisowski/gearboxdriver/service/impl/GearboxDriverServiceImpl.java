package com.dawid.cisowski.gearboxdriver.service.impl;

import com.dawid.cisowski.gearboxdriver.adapter.ExternalSystemAdapter;
import com.dawid.cisowski.gearboxdriver.adapter.GearboxAdapter;
import com.dawid.cisowski.gearboxdriver.model.ChangeGearOption;
import com.dawid.cisowski.gearboxdriver.model.DriveMode;
import com.dawid.cisowski.gearboxdriver.model.Rpm;
import com.dawid.cisowski.gearboxdriver.model.Threshold;
import com.dawid.cisowski.gearboxdriver.service.CalculateGearService;
import com.dawid.cisowski.gearboxdriver.service.GearboxDriverService;
import external.api.ExternalSystems;
import external.api.Gearbox;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

/**
 * {@inheritDoc}
 */
@Slf4j
@Builder
@AllArgsConstructor
public class GearboxDriverServiceImpl implements GearboxDriverService {
  private final GearboxAdapter gearboxAdapter;
  private final Gearbox gearbox;
  private final ExternalSystems externalSystems;
  private final CalculateGearService calculateGearService;
  private final ExternalSystemAdapter externalSystemAdapter;

  /**
   * {@inheritDoc}
   */
  @Override
  public void handleGas(DriveMode driveMode, Threshold threshold) {
    if (gearboxAdapter.isGearboxInDriveState(gearbox)) {
      log.info("Gearbox in Drive State, calculate change gear");
      ChangeGearOption changeGearOption = calculateGearService.calculateChangeGearForKickDown(threshold, driveMode);

      if (changeGearOption == ChangeGearOption.WITHOUT_CHANGE) {
        log.info("KickDown not detected. Normal calculateChangeGear run");

        Rpm currentRpm = new Rpm(externalSystemAdapter.getCurrentEngineRpm(externalSystems));
        changeGearOption = calculateGearService.calculateChangeGear(currentRpm, driveMode);
      }

      log.info("Try update Gearbox State with value: {}", changeGearOption.name());
      gearboxAdapter.updateGearboxState(gearbox, changeGearOption);
    }
  }
}
