package com.dawid.cisowski.gearboxdriver.adapter;

import com.dawid.cisowski.gearboxdriver.model.ChangeGearOption;
import external.api.Gearbox;

/*
 * GearboxDriver
 * Created by Dawid Cisowski
 * on 15.05.20.
 */

/**
 * Adapter to execute operation on gearbox
 */
public interface GearboxAdapter {

  /**
   * Check if gearbox is in driver mode
   *
   * @param gearbox car gearbox
   * @return true if gearbox is in drive state
   */
  boolean isGearboxInDriveState(Gearbox gearbox);

  /**
   * Update gearbox state
   *
   * @param gearbox          gearbox device
   * @param changeGearOption operation to do on gearbox
   */
  void updateGearboxState(Gearbox gearbox, ChangeGearOption changeGearOption);

  /**
   * Return current gear from gearbox
   *
   * @param gearbox gearbox device
   * @return current gear
   */
  Integer getCurrentGear(Gearbox gearbox);
}
