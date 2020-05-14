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

    @Override
    public Threshold getMinKickDownThreshold() {
      return null;
    }

    @Override
    public Threshold getMinDoubleKickDownThreshold() {
      return null;
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

    @Override
    public Threshold getMinKickDownThreshold() {
      return new Threshold(COMFORT_MIN_THRESHOLD_FOR_KICK_DOWN);
    }

    @Override
    public Threshold getMinDoubleKickDownThreshold() {
      return null;
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

    @Override
    public Threshold getMinKickDownThreshold() {
      return new Threshold(SPORT_MIN_THRESHOLD_FOR_KICK_DOWN);
    }

    @Override
    public Threshold getMinDoubleKickDownThreshold() {
      return new Threshold(SPORT_MIN_THRESHOLD_FOR_DOUBLE_KICK_DOWN);
    }
  };

  public abstract Rpm getReduceGearRPM();

  public abstract Rpm getIncreaseGearRPM();

  public abstract Threshold getMinKickDownThreshold();

  public abstract Threshold getMinDoubleKickDownThreshold();
}
