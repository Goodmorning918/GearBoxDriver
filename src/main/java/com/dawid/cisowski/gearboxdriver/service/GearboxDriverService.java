package com.dawid.cisowski.gearboxdriver.service;


/*
 * GearboxDriver
 * Created by Dawid Cisowski
 * on 13.05.20.
 */

import com.dawid.cisowski.gearboxdriver.model.DriveMode;

/**
 * Service to managing gearbox
 */
public interface GearboxDriverService {
  /**
   * Calculate operation which gearbox should do for given parameters
   *
   * @param driveMode current engine Drive Mode
   * @return operation which gearbox should do
   */
  void handleGas(DriveMode driveMode);

}
