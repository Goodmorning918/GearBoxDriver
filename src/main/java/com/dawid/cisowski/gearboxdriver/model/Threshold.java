package com.dawid.cisowski.gearboxdriver.model;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;


/*
 * GearboxDriver
 * Created by Dawid Cisowski
 * on 15.05.20.
 */

/**
 * Value object to cover pulling the gas pedal value
 */
@Slf4j
public class Threshold {
  @Getter
  private final Double value;

  public Threshold(Double value) {
    this.value = value;
  }

  public boolean greater(Threshold increaseGearRPM) {
    return value > increaseGearRPM.getValue();
  }

  public boolean smaller(Threshold reduceGearRPM) {
    return value < reduceGearRPM.getValue();
  }
}
