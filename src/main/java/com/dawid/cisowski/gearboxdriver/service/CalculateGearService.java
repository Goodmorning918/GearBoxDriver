package com.dawid.cisowski.gearboxdriver.service;


/*
 * GearboxDriver
 * Created by Dawid Cisowski
 * on 13.05.20.
 */

import com.dawid.cisowski.gearboxdriver.model.ChangeGearOption;
import com.dawid.cisowski.gearboxdriver.model.DriveMode;
import com.dawid.cisowski.gearboxdriver.model.Rpm;
import com.dawid.cisowski.gearboxdriver.model.Threshold;

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

  /**
   * Calculate operation which gearbox should do for given parameters in Kickdown mode
   *
   * @param threshold value describing how much gas pedal has pull
   * @param driveMode current engine Drive Mode
   * @return operation which gearbox should do
   */
  ChangeGearOption calculateChangeGearForKickDown(Threshold threshold, DriveMode driveMode);
}
