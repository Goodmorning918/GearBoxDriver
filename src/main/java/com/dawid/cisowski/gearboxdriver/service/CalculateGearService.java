package com.dawid.cisowski.gearboxdriver.service;


/*
 * GearboxDriver
 * Created by Dawid Cisowski
 * on 13.05.20.
 */

import com.dawid.cisowski.gearboxdriver.model.ChangeGearOption;
import com.dawid.cisowski.gearboxdriver.model.DriveMode;
import com.dawid.cisowski.gearboxdriver.model.Rpm;

/**
 * Service to calculate change gear
 */
public interface CalculateGearService {

  /**
   * Calculate operation which gearbox should do for given parameters
   *
   * @param currentRpm current engine RPM
   * @param driveMode  current engine Drive Mode
   * @return operation which gearbox should do
   */
  ChangeGearOption calculateChangeGear(Rpm currentRpm, DriveMode driveMode);
}
