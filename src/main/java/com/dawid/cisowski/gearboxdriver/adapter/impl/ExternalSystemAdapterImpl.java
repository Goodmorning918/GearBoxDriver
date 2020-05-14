package com.dawid.cisowski.gearboxdriver.adapter.impl;

import com.dawid.cisowski.gearboxdriver.adapter.ExternalSystemAdapter;
import external.api.ExternalSystems;
import lombok.extern.slf4j.Slf4j;

/*
 * GearboxDriver
 * Created by Dawid Cisowski
 * on 15.05.20.
 */

/**
 * {@inheritDoc}
 */
@Slf4j
public class ExternalSystemAdapterImpl implements ExternalSystemAdapter {

  /**
   * {@inheritDoc}
   */
  @Override
  public Double getCurrentEngineRpm(ExternalSystems externalSystems) {
    Double currentEngineRpm = externalSystems.getCurrentRpm();
    log.info("Success return current RPM: {}", currentEngineRpm);

    return currentEngineRpm;
  }
}
