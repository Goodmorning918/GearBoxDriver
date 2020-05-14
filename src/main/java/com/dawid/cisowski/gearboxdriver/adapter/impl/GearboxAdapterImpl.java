package com.dawid.cisowski.gearboxdriver.adapter.impl;

import com.dawid.cisowski.gearboxdriver.exception.GearboxDriverException;
import com.dawid.cisowski.gearboxdriver.adapter.GearboxAdapter;
import com.dawid.cisowski.gearboxdriver.model.ChangeGearOption;
import external.api.Gearbox;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/*
 * GearboxDriver
 * Created by Dawid Cisowski
 * on 15.05.20.
 */

/**
 * {@inheritDoc}
 */
@Slf4j
public class GearboxAdapterImpl implements GearboxAdapter {

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isGearboxInDriveState(Gearbox gearbox) {
    boolean isDriveState = Optional.ofNullable(gearbox)
            .map(Gearbox::getState)
            .map(state -> state.equals(1))
            .orElse(false);

    log.info("Gearbox is in drive state: {}", isDriveState);

    return isDriveState;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updateGearboxState(Gearbox gearbox, ChangeGearOption changeGearOption) {
    switch (changeGearOption) {
      case REDUCE: {
        Integer currentGear = this.getCurrentGear(gearbox);

        if (currentGear > 1) {
          log.info("Reduce gear to:{}", currentGear - 1);
          gearbox.setCurrentGear(currentGear - 1);
        }
        break;
      }
      case INCREASE: {
        Integer currentGear = this.getCurrentGear(gearbox);

        if (currentGear < gearbox.getMaxDrive()) {
          log.info("Increase gear to:{}", currentGear + 1);
          gearbox.setCurrentGear(currentGear + 1);
        }
        break;
      }
      case WITHOUT_CHANGE:
        log.info("Gear shouldn't be changed");
        break;

      default:
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Integer getCurrentGear(Gearbox gearbox) {
    Object currentGearObject = gearbox.getCurrentGear();
    Integer currentGear;
    try {
      currentGear = (Integer) (currentGearObject);
    } catch (Exception e) {
      throw new GearboxDriverException("Unable map value:" + currentGearObject.toString() + "to Integer");
    }

    log.info("Succes return currentgear: {}", currentGear);
    return currentGear;
  }
}
