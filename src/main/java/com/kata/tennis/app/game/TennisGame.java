package com.kata.tennis.app.game;

public class TennisGame {
    private final Player player1;
    private final Player player2;
    private Score score;

    public TennisGame(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.score = Score.initial();
    }

    public void wonPoint(Player player) {
        if (player1.equals(player)) {
            score = score.addPointForPlayer1();
        } else {
            score = score.addPointForPlayer2();
        }
    }

    public String getScore() {
        return new ScoreResultMapper().map(score);
    }
}
