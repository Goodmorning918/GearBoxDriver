package com.dawid.cisowski.gearboxdriver.service.impl

import com.dawid.cisowski.gearboxdriver.adapter.ExternalSystemAdapter
import com.dawid.cisowski.gearboxdriver.adapter.GearboxAdapter
import com.dawid.cisowski.gearboxdriver.model.ChangeGearOption
import com.dawid.cisowski.gearboxdriver.model.Rpm
import com.dawid.cisowski.gearboxdriver.service.CalculateGearService
import com.dawid.cisowski.gearboxdriver.service.GearboxDriverService
import external.api.ExternalSystems
import external.api.Gearbox
import spock.lang.Specification

import static com.dawid.cisowski.gearboxdriver.model.ChangeGearOption.REDUCE
import static com.dawid.cisowski.gearboxdriver.model.DriveMode.SPORT

class GearboxDriverServiceImplTest extends Specification {
    GearboxAdapter gearboxAdapter = Mock(GearboxAdapter)
    ExternalSystemAdapter externalSystemAdapter = Mock(ExternalSystemAdapter)
    ExternalSystems externalSystems = Mock(ExternalSystems)
    Gearbox gearbox = Mock(Gearbox)
    CalculateGearService calculateGearService = Mock(CalculateGearService)

    GearboxDriverService gearboxDriverService = GearboxDriverServiceImpl.builder()
            .gearboxAdapter(gearboxAdapter)
            .externalSystemAdapter(externalSystemAdapter)
            .gearbox(gearbox)
            .externalSystems(externalSystems)
            .calculateGearService(calculateGearService)
            .build()

    def "success HandleGas method"() {
        given:
        Double currentRpm = 2000
        ChangeGearOption changeGearOption = REDUCE

        when:
        gearboxDriverService.handleGas(SPORT)

        then:
        1 * gearboxAdapter.isGearboxInDriveState(gearbox) >> true
        1 * externalSystemAdapter.getCurrentEngineRpm(externalSystems) >> currentRpm
        1 * calculateGearService.calculateChangeGear(_ as Rpm, changeGearOption) >> changeGearOption
        1 * gearboxAdapter.updateGearboxState(gearbox, changeGearOption)
    }

    def "success skip nested method when gearbox state is park"() {
        given:
        ChangeGearOption changeGearOption = REDUCE

        when:
        gearboxDriverService.handleGas(SPORT)

        then:
        1 * gearboxAdapter.isGearboxInDriveState(gearbox) >> false
        0 * gearboxAdapter.updateGearboxState(gearbox, changeGearOption)
    }
}
