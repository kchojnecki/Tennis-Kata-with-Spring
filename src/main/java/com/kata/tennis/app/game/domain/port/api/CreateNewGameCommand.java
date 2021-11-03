package com.kata.tennis.app.game.domain.port.api;

public class CreateNewGameCommand {
    private String playerOneName;
    private String playerTwoName;

    public String getPlayerOneName() {
        return playerOneName;
    }

    public CreateNewGameCommand setPlayerOneName(String playerOneName) {
        this.playerOneName = playerOneName;
        return this;
    }

    public String getPlayerTwoName() {
        return playerTwoName;
    }

    public CreateNewGameCommand setPlayerTwoName(String playerTwoName) {
        this.playerTwoName = playerTwoName;
        return this;
    }
}
