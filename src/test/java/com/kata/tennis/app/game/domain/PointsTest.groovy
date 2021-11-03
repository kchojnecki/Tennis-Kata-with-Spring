package com.kata.tennis.app.game.domain

import com.kata.tennis.app.game.domain.Points
import spock.lang.Specification

class PointsTest extends Specification {
    
    def "points cannot be negative"() {
        when:
        new Points(-1)

        then:
        thrown(RuntimeException)
    }
}
