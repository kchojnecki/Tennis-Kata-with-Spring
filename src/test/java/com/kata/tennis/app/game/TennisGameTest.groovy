package com.kata.tennis.app.game

import spock.lang.Specification

class TennisGameTest extends Specification {

    def "won game can be finished"() {
        given:
        def game = new TennisGame(player1(), player2())

        and:
        setGameResult(game, 4, 0)

        when:
        def result = game.finishGame()

        then:
        result.get().reason() == "Game finished."
    }

    def "only won game can be finished"() {
        given:
        def game = new TennisGame(player1(), player2())

        and:
        setGameResult(game, 3, 0)

        when:
        def result = game.finishGame()

        then:
        result.getLeft().reason() == "Game in progress."
    }
    
    def "cannot finish already finished game"() {
        given:
        def game = new TennisGame(player1(), player2())

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
        def game = new TennisGame(player1(), player2())

        and:
        setGameResult(game, 4, 0)

        and:
        game.finishGame()

        when:
        def result = game.wonPoint(player1())

        then:
        result.getLeft().reason() == "Game is already finished."
    }
    
    def "should check all scores"() {
        given:
        def game = new TennisGame(player1(), player2())

        expect:
        checkScore(game, player1Score, player2Score, result)

        where:
        player1Score | player2Score | result
        0            | 0            | "Love-All"
        1            | 1            | "Fifteen-All"
        2            | 2            | "Thirty-All"
        3            | 3            | "Deuce"
        4            | 4            | "Deuce"

        1            | 0            | "Fifteen-Love"
        0            | 1            | "Love-Fifteen"
        2            | 0            | "Thirty-Love"
        0            | 2            | "Love-Thirty"
        3            | 0            | "Forty-Love"
        0            | 3            | "Love-Forty"
        4            | 0            | "Win for player1"
        0            | 4            | "Win for player2"

        2            | 1            | "Thirty-Fifteen"
        1            | 2            | "Fifteen-Thirty"
        3            | 1            | "Forty-Fifteen"
        1            | 3            | "Fifteen-Forty"
        4            | 1            | "Win for player1"
        1            | 4            | "Win for player2"

        3            | 2            | "Forty-Thirty"
        2            | 3            | "Thirty-Forty"
        4            | 2            | "Win for player1"
        2            | 4            | "Win for player2"

        4            | 3            | "Advantage player1"
        3            | 4            | "Advantage player2"
        5            | 4            | "Advantage player1"
        4            | 5            | "Advantage player2"
        15           | 14           | "Advantage player1"
        14           | 15           | "Advantage player2"

        6            | 4            | "Win for player1"
        4            | 6            | "Win for player2"
        16           | 14           | "Win for player1"
        14           | 16           | "Win for player2"
    }

    private static void checkScore(TennisGame game, int player1Score, int player2Score, String expectedScore) {
        setGameResult(game, player1Score, player2Score)
        assert expectedScore == game.getScore()
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
