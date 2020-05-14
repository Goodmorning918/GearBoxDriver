package com.dawid.cisowski.gearboxdriver.adapter.impl


import com.dawid.cisowski.gearboxdriver.adapter.GearboxAdapter
import com.dawid.cisowski.gearboxdriver.exception.GearboxDriverException
import external.api.Gearbox
import spock.lang.Specification

import static com.dawid.cisowski.gearboxdriver.model.ChangeGearOption.*

/*
 * GearboxDriver
 * Created by Dawid Cisowski
 * on 14.05.20.
 */

class GearboxAdapterImplTest extends Specification {
    Gearbox gearbox = Mock(Gearbox)
    GearboxAdapter gearboxAdapter = new GearboxAdapterImpl()

    def "Success return true when Gearbox is in Drive Mode"() {
        given:
        boolean gearboxInDriveMode = true

        when:
        boolean isGearboxInDriveModeResult = gearboxAdapter.isGearboxInDriveState(gearbox)

        then:
        1 * gearbox.getState() >> Integer.valueOf(1)

        and:
        isGearboxInDriveModeResult == gearboxInDriveMode
    }

    def "Success return false when Gearbox is not in Drive Mode"() {
        given:
        boolean gearboxInDriveMode = false

        when:
        boolean isGearboxInDriveModeResult = gearboxAdapter.isGearboxInDriveState(gearbox)

        then:
        1 * gearbox.getState() >> Integer.valueOf(2)

        and:
        isGearboxInDriveModeResult == gearboxInDriveMode
    }

    def "Success reduce gear"() {
        given:
        Integer currentGear = 2

        when:
        gearboxAdapter.updateGearboxState(gearbox, REDUCE)

        then:
        1 * gearbox.getCurrentGear() >> currentGear
        1 * gearbox.setCurrentGear(currentGear - 1)
    }

    def "Success increase gear"() {
        given:
        Integer currentGear = 2
        Integer getMaxDrive = 6

        when:
        gearboxAdapter.updateGearboxState(gearbox, INCREASE)

        then:
        1 * gearbox.getCurrentGear() >> currentGear
        1 * gearbox.getMaxDrive() >> getMaxDrive
        1 * gearbox.setCurrentGear(currentGear + 1)
    }

    def "Success not change gear when ChangeGearOption = WITHOUT_CHANGE"() {
        given:

        when:
        gearboxAdapter.updateGearboxState(gearbox, WITHOUT_CHANGE)

        then:
        0 * gearbox.setCurrentGear(_ as int)
    }


    def "Success not change gear when ChangeGearOption = REDUCE and gear is minimal"() {
        given:
        Integer currentGear = 1

        when:
        gearboxAdapter.updateGearboxState(gearbox, REDUCE)

        then:
        1 * gearbox.getCurrentGear() >> currentGear
        0 * gearbox.setCurrentGear(_ as int)
    }

    def "Success not change gear when ChangeGearOption = INCREASE and gear is maximal"() {
        given:
        Integer currentGear = 6
        Integer getMaxDrive = 6

        when:
        gearboxAdapter.updateGearboxState(gearbox, INCREASE)

        then:
        1 * gearbox.getCurrentGear() >> currentGear
        1 * gearbox.getMaxDrive() >> getMaxDrive
        0 * gearbox.setCurrentGear(_ as int)
    }

    def "Success response current gear"() {
        given:
        Object currentGear = Integer.valueOf(2)

        when:
        Integer currentGearResponse = gearboxAdapter.getCurrentGear(gearbox)

        then:
        1 * gearbox.getCurrentGear() >> currentGear

        and:
        currentGearResponse == currentGear.intValue()
    }

    def "Throw GearboxDriverException when cant get current gear"() {
        given:
        Object currentGear = "TEST"

        when:
        gearboxAdapter.getCurrentGear(gearbox)

        then:
        1 * gearbox.getCurrentGear() >> currentGear

        and:
        thrown(GearboxDriverException)
    }
}
