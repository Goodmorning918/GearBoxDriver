package com.dawid.cisowski.gearboxdriver.service.impl.CalculateGearService

import com.dawid.cisowski.gearboxdriver.model.ChangeGearOption
import com.dawid.cisowski.gearboxdriver.model.Rpm
import com.dawid.cisowski.gearboxdriver.service.impl.CalculateGearServiceCalculateChangeGearImpl
import spock.lang.Specification
import spock.lang.Unroll

import static com.dawid.cisowski.gearboxdriver.model.ChangeGearOption.REDUCE
import static com.dawid.cisowski.gearboxdriver.model.ChangeGearOption.WITHOUT_CHANGE
import static com.dawid.cisowski.gearboxdriver.model.DriveMode.*

/*
 * GearboxDriver
 * Created by Dawid Cisowski
 * on 14.05.20.
 */

class CheckIfBrakeByReduceImplTest extends Specification {
    CalculateGearServiceCalculateChangeGearImpl gearboxDriverService = new CalculateGearServiceCalculateChangeGearImpl()

    @Unroll
    def "Success brake car by reduce gear for Rpm: #rpmValue drive mode: #driveMode"() {
        given:
        Rpm rpm = new Rpm(rpmValue)

        when:
        ChangeGearOption calculatedChangeGearOption = gearboxDriverService.checkIfBrakeByReduceGear(rpm, driveMode)

        then:
        calculatedChangeGearOption == changeGearOption

        where:
        rpmValue | driveMode || changeGearOption
        1501     | ECO       || REDUCE
        2001     | COMFORT   || REDUCE
        3001     | SPORT     || REDUCE
    }

    @Unroll
    def "Success dont brake car by reduce gear for Rpm: #rpmValue drive mode: #driveMode"() {
        given:
        Rpm rpm = new Rpm(rpmValue)

        when:
        ChangeGearOption calculatedChangeGearOption = gearboxDriverService.checkIfBrakeByReduceGear(rpm, driveMode)

        then:
        calculatedChangeGearOption == changeGearOption

        where:
        rpmValue | driveMode || changeGearOption
        1499     | ECO       || WITHOUT_CHANGE
        1999     | COMFORT   || WITHOUT_CHANGE
        2999     | SPORT     || WITHOUT_CHANGE
    }
}
