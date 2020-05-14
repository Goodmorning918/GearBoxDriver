package com.dawid.cisowski.gearboxdriver.model;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;


/*
 * GearboxDriver
 * Created by Dawid Cisowski
 * on 14.05.20.
 */

/**
 * Value object to cover revolutions per minute value
 */
@Slf4j
public class Rpm {
  @Getter
  private final Double value;

  public Rpm(Double value) {
    this.value = value;
  }

  public boolean grater(Rpm increaseGearRPM) {
    return value > increaseGearRPM.getValue();
  }

  public boolean smaller(Rpm reduceGearRPM) {
    return value < reduceGearRPM.getValue();
  }
}
