package com.kata.tennis.app.game.tempdb;

import java.util.UUID;

public record TennisGameDbModel(
        UUID gameId,
        String playerOneName,
        int playerOneScore,
        String playerTwoName,
        int playerTwoScore,
        boolean isGameFinished
) {
}
