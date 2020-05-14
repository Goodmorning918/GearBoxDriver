package com.dawid.cisowski.gearboxdriver.service.impl

import com.dawid.cisowski.gearboxdriver.adapter.ExternalSystemAdapter
import com.dawid.cisowski.gearboxdriver.adapter.GearboxAdapter
import com.dawid.cisowski.gearboxdriver.model.ChangeGearOption
import com.dawid.cisowski.gearboxdriver.model.Rpm
import com.dawid.cisowski.gearboxdriver.model.Threshold
import com.dawid.cisowski.gearboxdriver.service.CalculateGearService
import com.dawid.cisowski.gearboxdriver.service.GearboxDriverService
import external.api.ExternalSystems
import external.api.Gearbox
import spock.lang.Specification

import static com.dawid.cisowski.gearboxdriver.model.ChangeGearOption.DOUBLE_REDUCE
import static com.dawid.cisowski.gearboxdriver.model.ChangeGearOption.REDUCE
import static com.dawid.cisowski.gearboxdriver.model.ChangeGearOption.WITHOUT_CHANGE
import static com.dawid.cisowski.gearboxdriver.model.DriveMode.SPORT

/*
 * GearboxDriver
 * Created by Dawid Cisowski
 * on 14.05.20.
 */

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

    def "success HandleGas method by calculateChangeGear"() {
        given:
        Double currentRpm = 2000
        Threshold threshold = new Threshold(0.5)
        ChangeGearOption changeGearOption = REDUCE

        when:
        gearboxDriverService.handleGas(SPORT, threshold)

        then:
        1 * gearboxAdapter.isGearboxInDriveState(gearbox) >> true
        1 * calculateGearService.calculateChangeGearForKickDown(threshold, SPORT) >> WITHOUT_CHANGE
        1 * externalSystemAdapter.getCurrentEngineRpm(externalSystems) >> currentRpm
        1 * calculateGearService.calculateChangeGear(_ as Rpm, SPORT) >> changeGearOption
        1 * gearboxAdapter.updateGearboxState(gearbox, changeGearOption)
    }
    def "success HandleGas method by calculateChangeGearForKickDown"() {
        given:
        Threshold threshold = new Threshold(0.5)
        ChangeGearOption changeGearOption = DOUBLE_REDUCE

        when:
        gearboxDriverService.handleGas(SPORT, threshold)

        then:
        1 * gearboxAdapter.isGearboxInDriveState(gearbox) >> true
        1 * calculateGearService.calculateChangeGearForKickDown(threshold, SPORT) >> changeGearOption
        1 * gearboxAdapter.updateGearboxState(gearbox, changeGearOption)
    }

    def "success skip nested method when gearbox state is park"() {
        given:
        ChangeGearOption changeGearOption = REDUCE
        Threshold threshold = new Threshold(0.5)

        when:
        gearboxDriverService.handleGas(SPORT, threshold)

        then:
        1 * gearboxAdapter.isGearboxInDriveState(gearbox) >> false
        0 * gearboxAdapter.updateGearboxState(gearbox, changeGearOption)
    }
}
