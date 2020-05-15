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
    public Rpm getReduceGearRPM(AggressiveMode aggressiveMode) {
      return new Rpm(ECO_RPM_TO_REDUCE_GEAR);
    }

    @Override
    public Rpm getIncreaseGearRPM(AggressiveMode aggressiveMode) {
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

    @Override
    public Rpm getReduceGearForBreakingRPM() {
      return new Rpm(ECO_RPM_TO_REDUCE_GEAR_FOR_BREAKING);
    }
  },

  COMFORT {
    @Override
    public Rpm getReduceGearRPM(AggressiveMode aggressiveMode) {
      Rpm rpm = new Rpm(COMFORT_RPM_TO_REDUCE_GEAR);

      return aggressiveMode.multiplyRpmByAggressiveMultiplier(rpm);
    }

    @Override
    public Rpm getIncreaseGearRPM(AggressiveMode aggressiveMode) {
      Rpm rpm = new Rpm(COMFORT_RPM_TO_INCREASE_GEAR);

      return aggressiveMode.multiplyRpmByAggressiveMultiplier(rpm);
    }

    @Override
    public Threshold getMinKickDownThreshold() {
      return new Threshold(COMFORT_MIN_THRESHOLD_FOR_KICK_DOWN);
    }

    @Override
    public Threshold getMinDoubleKickDownThreshold() {
      return null;
    }

    @Override
    public Rpm getReduceGearForBreakingRPM() {
      return new Rpm(COMFORT_RPM_TO_REDUCE_GEAR_FOR_BREAKING);
    }
  },

  SPORT {
    @Override
    public Rpm getReduceGearRPM(AggressiveMode aggressiveMode) {
      Rpm rpm = new Rpm(SPORT_RPM_TO_REDUCE_GEAR);

      return aggressiveMode.multiplyRpmByAggressiveMultiplier(rpm);
    }

    @Override
    public Rpm getIncreaseGearRPM(AggressiveMode aggressiveMode) {
      Rpm rpm = new Rpm(SPORT_RPM_TO_INCREASE_GEAR);

      return aggressiveMode.multiplyRpmByAggressiveMultiplier(rpm);
    }

    @Override
    public Threshold getMinKickDownThreshold() {
      return new Threshold(SPORT_MIN_THRESHOLD_FOR_KICK_DOWN);
    }

    @Override
    public Threshold getMinDoubleKickDownThreshold() {
      return new Threshold(SPORT_MIN_THRESHOLD_FOR_DOUBLE_KICK_DOWN);
    }

    @Override
    public Rpm getReduceGearForBreakingRPM() {
      return new Rpm(SPORT_RPM_TO_REDUCE_GEAR_FOR_BREAKING);
    }
  };

  public abstract Rpm getReduceGearRPM(AggressiveMode aggressiveMode);

  public abstract Rpm getIncreaseGearRPM(AggressiveMode aggressiveMode);

  public abstract Threshold getMinKickDownThreshold();

  public abstract Threshold getMinDoubleKickDownThreshold();

  public abstract Rpm getReduceGearForBreakingRPM();
}
