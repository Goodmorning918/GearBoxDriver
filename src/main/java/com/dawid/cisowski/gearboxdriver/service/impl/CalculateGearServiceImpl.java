package com.dawid.cisowski.gearboxdriver.service.impl;

import com.dawid.cisowski.gearboxdriver.model.ChangeGearOption;
import com.dawid.cisowski.gearboxdriver.model.DriveMode;
import com.dawid.cisowski.gearboxdriver.model.Rpm;
import com.dawid.cisowski.gearboxdriver.service.CalculateGearService;
import lombok.extern.slf4j.Slf4j;

import static com.dawid.cisowski.gearboxdriver.model.ChangeGearOption.*;

/*
 * GearboxDriver
 * Created by Dawid Cisowski
 * on 13.05.20.
 */
@Slf4j
public class CalculateGearServiceImpl implements CalculateGearService {

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeGearOption calculateChangeGear(Rpm currentRpm, DriveMode driveMode) {
    log.info("Calculate change gear operation for rpm: {}, and driveMode: {}", currentRpm.getValue(), driveMode.name());

    if (currentRpm.grater(driveMode.getIncreaseGearRPM())) {
      log.info("Gearbox should increase gear");
      return INCREASE;
    } else if (currentRpm.smaller(driveMode.getReduceGearRPM())) {
      log.info("Gearbox should reduce gear");
      return REDUCE;
    } else {
      log.info("Current gear is correct");
      return WITHOUT_CHANGE;
    }
  }
}
