package com.kata.tennis.app.game.infra;

import com.kata.tennis.app.game.domain.TennisGame;
import com.kata.tennis.app.game.domain.port.TennisGameGateway;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

class TennisGameInMemoryGateway implements TennisGameGateway {
    private final Map<UUID, TennisGame.Snapshot> data = new ConcurrentHashMap<>();

    @Override
    public void create(TennisGame tennisGame) {
        var snapshot = tennisGame.toSnapshot();
        data.put(snapshot.gameId(), snapshot);
    }

    @Override
    public Optional<TennisGame> fetchById(UUID gameId) {
        return Optional.ofNullable(data.get(gameId)).map(TennisGame::fromSnapshot);
    }
}
