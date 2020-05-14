package com.dawid.cisowski.gearboxdriver.adapter.impl

import com.dawid.cisowski.gearboxdriver.adapter.ExternalSystemAdapter
import external.api.ExternalSystems
import spock.lang.Specification

class ExternalSystemAdapterImplTest extends Specification {
    ExternalSystems externalSystems = Mock(ExternalSystems)
    ExternalSystemAdapter externalSystemAdapter = new ExternalSystemAdapterImpl()

    def "Success get current engine rpm"() {
        given:
        double currentRpm = 1000

        when:
        double currentRpmResponse = externalSystemAdapter.getCurrentEngineRpm(externalSystems)

        then:
        1 * externalSystems.getCurrentRpm() >> currentRpm

        and:
        currentRpmResponse == currentRpm
    }
}
