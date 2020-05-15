package com.dawid.cisowski.gearboxdriver.service.impl;

import com.dawid.cisowski.gearboxdriver.model.ChangeGearOption;
import com.dawid.cisowski.gearboxdriver.model.DriveMode;
import com.dawid.cisowski.gearboxdriver.model.Rpm;
import com.dawid.cisowski.gearboxdriver.model.Threshold;
import com.dawid.cisowski.gearboxdriver.service.CalculateGearService;
import lombok.extern.slf4j.Slf4j;

import static com.dawid.cisowski.gearboxdriver.model.ChangeGearOption.*;

/*
 * GearboxDriver
 * Created by Dawid Cisowski
 * on 13.05.20.
 */
@Slf4j
public class CalculateGearServiceCalculateChangeGearImpl implements CalculateGearService {

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

  @Override
  public ChangeGearOption calculateChangeGearForKickDown(Threshold threshold, DriveMode driveMode) {
    ChangeGearOption changeGearOption;
    switch (driveMode) {
      case ECO: {
        changeGearOption = WITHOUT_CHANGE;

        break;
      }
      case COMFORT: {
        if (threshold.greater(driveMode.getMinKickDownThreshold())) {
          changeGearOption = REDUCE;
        } else {
          changeGearOption = WITHOUT_CHANGE;
        }

        break;
      }
      case SPORT: {
        if (threshold.greater(driveMode.getMinDoubleKickDownThreshold())) {
          changeGearOption = DOUBLE_REDUCE;
        } else if (threshold.greater(driveMode.getMinKickDownThreshold())) {
          changeGearOption = REDUCE;
        } else {
          changeGearOption = WITHOUT_CHANGE;
        }

        break;
      }
      default: {
        changeGearOption = WITHOUT_CHANGE;
      }
    }

    log.info("Calculated operation by kick down: {}", changeGearOption.name());
    return changeGearOption;
  }

  @Override
  public ChangeGearOption checkIfBrakeByReduceGear(Rpm currentRpm, DriveMode driveMode) {
    log.info("Calculate automate braking for rpm: {}, and driveMode: {}", currentRpm.getValue(), driveMode.name());

    if (currentRpm.grater(driveMode.getReduceGearForBreakingRPM())) {
      log.info("Gearbox should reduce gear for braking");
      return REDUCE;
    } else {
      log.info("Current gear is correct");
      return WITHOUT_CHANGE;
    }
  }
}
