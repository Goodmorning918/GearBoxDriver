package com.dawid.cisowski.gearboxdriver.service;


/*
 * GearboxDriver
 * Created by Dawid Cisowski
 * on 13.05.20.
 */

import com.dawid.cisowski.gearboxdriver.model.AggressiveMode;
import com.dawid.cisowski.gearboxdriver.model.DriveMode;
import com.dawid.cisowski.gearboxdriver.model.Threshold;

/**
 * Service to managing gearbox
 */
public interface GearboxDriverService {
  /**
   * Calculate operation which gearbox should do for given parameters in automate mode
   *
   * @param driveMode current engine Drive Mode
   * @return operation which gearbox should do
   */
  void handleGasAutoMode(DriveMode driveMode, Threshold threshold, AggressiveMode aggressiveMode);

  /**
   * Calculate operation which gearbox should do for given parameters in manual mode
   *
   * @param driveMode current engine Drive Mode
   * @return operation which gearbox should do
   */
  void handleGasManualMode(DriveMode driveMode);
}
