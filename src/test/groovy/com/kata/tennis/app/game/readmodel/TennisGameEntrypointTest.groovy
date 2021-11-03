package com.kata.tennis.app.game.readmodel

import org.springframework.http.HttpStatus
import spock.lang.Specification

class TennisGameEntrypointTest extends Specification {

    def gateway = new InMemoryFetchTennisGameTestGateway()
    def entrypoint = new TennisGameEntrypoint(gateway)

    def "should fetch Tennis Game score by Id"() {

    }

    def "should return failure if game not found"() {
        when:
        def result = entrypoint.getScore(UUID.randomUUID())

        then:
        result.statusCode == HttpStatus.NOT_FOUND
        result.getBody() == "NOT FOUND"
    }
}
