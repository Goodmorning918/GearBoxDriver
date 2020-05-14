package com.dawid.cisowski.gearboxdriver.adapter;

import external.api.ExternalSystems;

/*
 * GearboxDriver
 * Created by Dawid Cisowski
 * on 15.05.20.
 */

/**
 * Adapter to execute operation on External System
 */
public interface ExternalSystemAdapter {
  /**
   * Return current Engine Rpm
   *
   * @param externalSystems externalSystems device
   * @return current Engine
   */
  Double getCurrentEngineRpm(ExternalSystems externalSystems);
}
