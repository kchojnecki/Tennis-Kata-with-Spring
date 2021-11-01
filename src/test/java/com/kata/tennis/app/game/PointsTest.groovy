package com.kata.tennis.app.game

import spock.lang.Specification

class PointsTest extends Specification {
    
    def "points cannot be negative"() {
        when:
        new Points(-1)

        then:
        thrown(RuntimeException)
    }
}
