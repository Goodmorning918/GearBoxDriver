package com.dawid.cisowski.gearboxdriver.model;

import static com.dawid.cisowski.gearboxdriver.properties.NormalizedCharacteristic.*;

/*
 * GearboxDriver
 * Created by Dawid Cisowski
 * on 14.05.20.
 */

/**
 * Enum to define car drive modes and drive mode properties
 */
public enum DriveMode {

  ECO {
    @Override
    public Rpm getReduceGearRPM() {
      return new Rpm(ECO_RPM_TO_REDUCE_GEAR);
    }

    @Override
    public Rpm getIncreaseGearRPM() {
      return new Rpm(ECO_RPM_TO_INCREASE_GEAR);
    }
  },

  COMFORT {
    @Override
    public Rpm getReduceGearRPM() {
      return new Rpm(COMFORT_RPM_TO_REDUCE_GEAR);
    }

    @Override
    public Rpm getIncreaseGearRPM() {
      return new Rpm(COMFORT_RPM_TO_INCREASE_GEAR);
    }
  },

  SPORT {
    @Override
    public Rpm getReduceGearRPM() {
      return new Rpm(SPORT_RPM_TO_REDUCE_GEAR);
    }

    @Override
    public Rpm getIncreaseGearRPM() {
      return new Rpm(SPORT_RPM_TO_INCREASE_GEAR);
    }
  };

  public abstract Rpm getReduceGearRPM();

  public abstract Rpm getIncreaseGearRPM();
}
