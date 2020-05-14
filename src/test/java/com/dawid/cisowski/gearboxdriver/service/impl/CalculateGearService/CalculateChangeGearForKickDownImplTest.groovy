package com.dawid.cisowski.gearboxdriver.service.impl.CalculateGearService

import com.dawid.cisowski.gearboxdriver.model.ChangeGearOption
import com.dawid.cisowski.gearboxdriver.model.Threshold
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

class CalculateChangeGearForKickDownImplTest extends Specification {
    CalculateGearServiceCalculateChangeGearImpl gearboxDriverService = new CalculateGearServiceCalculateChangeGearImpl()

    @Unroll
    def "Success return option: #changeGearOption for Threshold: #thresholdValue drive mode: #driveMode"() {
        given:
        Threshold threshold = new Threshold(thresholdValue)

        when:
        ChangeGearOption calculatedChangeGearOption = gearboxDriverService.calculateChangeGearForKickDown(threshold, driveMode)

        then:
        calculatedChangeGearOption == changeGearOption

        where:
        thresholdValue | driveMode || changeGearOption
        0.5            | ECO       || WITHOUT_CHANGE
        0.2            | COMFORT   || WITHOUT_CHANGE
        0.6            | COMFORT   || REDUCE
        0.2            | SPORT     || WITHOUT_CHANGE
        0.6            | SPORT     || REDUCE
        0.8            | SPORT     || DOUBLE_REDUCE
    }
}
