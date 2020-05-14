package com.dawid.cisowski.gearboxdriver.service.impl.CalculateGearService

import com.dawid.cisowski.gearboxdriver.model.ChangeGearOption
import com.dawid.cisowski.gearboxdriver.model.Rpm
import com.dawid.cisowski.gearboxdriver.service.impl.CalculateGearServiceCalculateChangeGearImpl
import spock.lang.Specification
import spock.lang.Unroll

import static com.dawid.cisowski.gearboxdriver.model.ChangeGearOption.*
import static com.dawid.cisowski.gearboxdriver.model.DriveMode.*

/*
 * GearboxDriver
 * Created by Dawid Cisowski
 * on 14.05.20.
 */

class CalculateChangeGearImplTest extends Specification {
    CalculateGearServiceCalculateChangeGearImpl gearboxDriverService = new CalculateGearServiceCalculateChangeGearImpl()

    @Unroll
    def "Success reduce gear for Rpm: #rpmValue drive mode: #driveMode"() {
        given:
        Rpm rpm = new Rpm(rpmValue)

        when:
        ChangeGearOption calculatedChangeGearOption = gearboxDriverService.calculateChangeGear(rpm, driveMode)

        then:
        calculatedChangeGearOption == changeGearOption

        where:
        rpmValue | driveMode || changeGearOption
        999      | ECO       || REDUCE
        999      | COMFORT   || REDUCE
        1499     | SPORT     || REDUCE
    }

    @Unroll
    def "Success increase gear for Rpm: #rpmValue drive mode: #driveMode"() {
        given:
        Rpm rpm = new Rpm(rpmValue)

        when:
        ChangeGearOption calculatedChangeGearOption = gearboxDriverService.calculateChangeGear(rpm, driveMode)

        then:
        calculatedChangeGearOption == changeGearOption

        where:
        rpmValue | driveMode || changeGearOption
        2001     | ECO       || INCREASE
        2501     | COMFORT   || INCREASE
        5001     | SPORT     || INCREASE
    }

    @Unroll
    def "Success not change gear for Rpm: #rpmValue drive mode: #driveMode"() {
        given:
        Rpm rpm = new Rpm(rpmValue)

        when:
        ChangeGearOption calculatedChangeGearOption = gearboxDriverService.calculateChangeGear(rpm, driveMode)

        then:
        calculatedChangeGearOption == changeGearOption

        where:
        rpmValue | driveMode || changeGearOption
        1700     | ECO       || WITHOUT_CHANGE
        1700     | COMFORT   || WITHOUT_CHANGE
        3000     | SPORT     || WITHOUT_CHANGE
    }
}
