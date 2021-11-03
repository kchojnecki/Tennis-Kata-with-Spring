package com.kata.tennis.app.game.domain

import spock.lang.Specification

class TennisGameTest extends Specification {

    def "won game can be finished"() {
        given:
        def game = TennisGame.newGame(player1(), player2())

        and:
        setGameResult(game, p1Score, p2Score)

        when:
        def result = game.finishGame()

        then:
        result.get().reason() == "Game finished."

        where:
        p1Score | p2Score
        4       | 0
        0       | 4
    }

    def "only won game can be finished"() {
        given:
        def game = TennisGame.newGame(player1(), player2())

        and:
        setGameResult(game, p1Score, p2Score)

        when:
        def result = game.finishGame()

        then:
        result.getLeft().reason() == "Game in progress."

        where:
        p1Score | p2Score
        3       | 0
        0       | 3
        4       | 3
        3       | 4
    }

    def "cannot finish already finished game"() {
        given:
        def game = TennisGame.newGame(player1(), player2())

        and:
        setGameResult(game, 4, 0)

        and: "game is finished"
        game.finishGame()

        when:
        def result = game.finishGame()

        then:
        result.getLeft().reason() == "Game is already finished."
    }

    def "cannot add points when a game is finished"() {
        given:
        def game = TennisGame.newGame(player1(), player2())

        and:
        setGameResult(game, 4, 0)

        and:
        game.finishGame()

        when:
        def result = game.wonPoint(player1())

        then:
        result.getLeft().reason() == "Game is already finished."
    }

    def "should throw exception when unknown player score a point"() {
        given:
        def game = TennisGame.newGame(player1(), player2())

        when:
        def result = game.wonPoint(new Player("unknown"))

        then:
        result.getLeft().reason() == "Player unknown is not in the game."
    }


    private static void setGameResult(TennisGame game, int player1Score, int player2Score) {
        int highestScore = Math.max(player1Score, player2Score)
        for (int i = 0; i < highestScore; i++) {
            if (i < player1Score)
                game.wonPoint(player1())
            if (i < player2Score)
                game.wonPoint(player2())
        }
    }

    private static Player player1() {
        new Player("player1")
    }

    private static Player player2() {
        new Player("player2")
    }
}
