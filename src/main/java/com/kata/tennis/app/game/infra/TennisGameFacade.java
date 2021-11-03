package com.kata.tennis.app.game.infra;

import com.kata.tennis.app.game.domain.CreateNewGame;
import com.kata.tennis.app.game.domain.port.api.CreateNewGameCommand;

import java.util.UUID;

public class TennisGameFacade {
    private final CreateNewGame createNewGame;

    TennisGameFacade(CreateNewGame createNewGame) {
        this.createNewGame = createNewGame;
    }

    public UUID createNewGame(CreateNewGameCommand command) {
        return createNewGame.create(command);
    }
}
