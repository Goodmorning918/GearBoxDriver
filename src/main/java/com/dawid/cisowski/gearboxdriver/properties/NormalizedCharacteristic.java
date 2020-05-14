package com.dawid.cisowski.gearboxdriver.properties;

/*
 * GearboxDriver
 * Created by Dawid Cisowski
 * on 14.05.20.
 */

import lombok.experimental.UtilityClass;

/**
 * Normalized characteristic from buissnes analytic
 * Make sure if values from file: ../external/api/charakterystyki.txt has not change
 */
@UtilityClass
public class NormalizedCharacteristic {

  public final double ECO_RPM_TO_INCREASE_GEAR = 2000;
  public final double ECO_RPM_TO_REDUCE_GEAR = 1000;

  public final double COMFORT_RPM_TO_INCREASE_GEAR = 2500;
  public final double COMFORT_RPM_TO_REDUCE_GEAR = 1000;
  public final double COMFORT_MIN_THRESHOLD_FOR_KICK_DOWN = 0.5;

  public final double SPORT_RPM_TO_INCREASE_GEAR = 5000;
  public final double SPORT_RPM_TO_REDUCE_GEAR = 1500;
  public final double SPORT_MIN_THRESHOLD_FOR_KICK_DOWN = 0.5;
  public final double SPORT_MIN_THRESHOLD_FOR_DOUBLE_KICK_DOWN = 0.7;
}
