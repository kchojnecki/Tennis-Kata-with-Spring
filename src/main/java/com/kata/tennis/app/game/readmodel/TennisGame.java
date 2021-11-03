package com.kata.tennis.app.game.readmodel;

import java.util.UUID;

class TennisGame {
    private final UUID gameId;
    private final String playerOneName;
    private final String playerTwoName;
    private final String gameStatus;
    private final String score;

    TennisGame(UUID gameId, Score playerOneScore, Score playerTwoScore, boolean isGameFinish) {
        this.gameId = gameId;
        this.playerOneName = playerOneScore.playerName();
        this.playerTwoName = playerTwoScore.playerName();
        this.gameStatus = gameStatus(isGameFinish);
        this.score = new ScoreResultMapper().map(playerOneScore.points(), playerTwoScore.points());
    }

    private String gameStatus(boolean isGameFinished) {
        return isGameFinished? "FINISHED" : "IN PROGRESS";
    }

    public UUID getGameId() {
        return gameId;
    }

    public String getPlayerOneName() {
        return playerOneName;
    }

    public String getPlayerTwoName() {
        return playerTwoName;
    }

    public String getScore() {
        return score;
    }

    public String getGameStatus() {
        return gameStatus;
    }

    static record Score(String playerName, Integer points) { }
}
