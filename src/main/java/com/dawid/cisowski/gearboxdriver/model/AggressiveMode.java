package com.dawid.cisowski.gearboxdriver.model;

/*
 * GearboxDriver
 * Created by Dawid Cisowski
 * on 15.05.20.
 */

/**
 * Enum to define car drive modes and drive mode properties
 */
public enum AggressiveMode {

  ONE(1.0),
  TWO(1.2),
  THREE(1.3) {

  };

  AggressiveMode(Double aggressiveMultiplier) {
    this.aggressiveMultiplier = aggressiveMultiplier;
  }

  public Rpm multiplyRpmByAggressiveMultiplier(Rpm rpm) {
    return rpm.multiply(aggressiveMultiplier);
  }

  private final Double aggressiveMultiplier;

}