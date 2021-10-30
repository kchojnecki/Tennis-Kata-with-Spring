package com.kata.tennis.app.game;

public class TennisGame {
    private Score score;
    private final String player1Name;
    private final String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.score = Score.initial();
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1")
            score = score.addPointForPlayer1();
        else
            score = score.addPointForPlayer2();
    }

    public String getScore() {
        return new ScoreResultMapper().map(score);
    }
}
