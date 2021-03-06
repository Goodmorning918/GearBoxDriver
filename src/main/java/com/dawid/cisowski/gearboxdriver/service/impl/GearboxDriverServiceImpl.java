package com.dawid.cisowski.gearboxdriver.service.impl;

import com.dawid.cisowski.gearboxdriver.adapter.ExternalSystemAdapter;
import com.dawid.cisowski.gearboxdriver.adapter.GearboxAdapter;
import com.dawid.cisowski.gearboxdriver.model.*;
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
  public void handleGasAutoMode(DriveMode driveMode, Threshold threshold, AggressiveMode aggressiveMode) {
    if (gearboxAdapter.isGearboxInDriveState(gearbox)) {
      log.info("Gearbox in Drive State, calculate change gear for Kick Dome Mode");
      ChangeGearOption changeGearOption = calculateGearService.calculateChangeGearForKickDown(threshold, driveMode);

      if (changeGearOption == ChangeGearOption.WITHOUT_CHANGE) {
        log.info("KickDown not detected. Normal calculateChangeGear run");

        Rpm currentRpm = new Rpm(externalSystemAdapter.getCurrentEngineRpm(externalSystems));
        changeGearOption = calculateGearService.calculateChangeGear(currentRpm, driveMode, aggressiveMode);
      }

      log.info("Try update Gearbox State with value: {}", changeGearOption.name());
      gearboxAdapter.updateGearboxState(gearbox, changeGearOption);
    }
  }

  @Override
  public void handleGasManualMode(DriveMode driveMode) {
    if (gearboxAdapter.isGearboxInDriveState(gearbox)) {
      log.info("Gearbox in Drive State, calculate engine braking by reduce gear");

      Rpm currentRpm = new Rpm(externalSystemAdapter.getCurrentEngineRpm(externalSystems));
      ChangeGearOption changeGearOption = calculateGearService.checkIfBrakeByReduceGear(currentRpm, driveMode);

      log.info("Try update Gearbox State with value: {}", changeGearOption.name());
      gearboxAdapter.updateGearboxState(gearbox, changeGearOption);
    }
  }
}