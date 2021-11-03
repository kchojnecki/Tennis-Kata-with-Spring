package com.kata.tennis.app.game.domain

import com.kata.tennis.app.game.domain.port.api.CreateNewGameCommand
import spock.lang.Specification

class CreateNewGameTest extends Specification {

    def gameGateway = new InMemoryTennisGameTestRepository()
    def createNewGame = new CreateNewGame(gameGateway)

    def "should create new game"() {
        given:
        def command = new CreateNewGameCommand()
                .setPlayerOneName("p1")
                .setPlayerTwoName("p2")

        when:
        def result = createNewGame.create(command)

        then:
        with(gameGateway.fetchById(result).get().toSnapshot(), {
            it.playerOneName() == "p1"
            it.playerOneScore() == 0
            it.playerTwoName() == "p2"
            it.playerTwoScore() == 0
        })
    }
}
