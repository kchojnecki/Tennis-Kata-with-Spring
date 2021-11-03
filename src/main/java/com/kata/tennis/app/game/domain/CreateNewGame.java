package com.kata.tennis.app.game.domain;

import com.kata.tennis.app.game.domain.port.TennisGameGateway;
import com.kata.tennis.app.game.domain.port.api.CreateNewGameCommand;

import java.util.UUID;

public class CreateNewGame {
    private final TennisGameGateway gameGateway;

    public CreateNewGame(TennisGameGateway gameGateway) {
        this.gameGateway = gameGateway;
    }

    public UUID create(CreateNewGameCommand command) {
        var player1 = new Player(command.getPlayerOneName());
        var player2 = new Player(command.getPlayerTwoName());
        var game = TennisGame.newGame(player1, player2);
        gameGateway.create(game);
        return game.id();
    }
}
