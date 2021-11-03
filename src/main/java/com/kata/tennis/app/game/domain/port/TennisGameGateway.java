package com.kata.tennis.app.game.domain.port;

import com.kata.tennis.app.game.domain.TennisGame;

import java.util.Optional;
import java.util.UUID;

public interface TennisGameGateway {

    void create(TennisGame snapshot);

    Optional<TennisGame> fetchById(UUID gameId);
}
