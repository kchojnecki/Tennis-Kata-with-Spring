package com.kata.tennis.app.game.domain

import com.kata.tennis.app.game.domain.port.TennisGameGateway

class InMemoryTennisGameTestGateway implements TennisGameGateway {
    private final Map<UUID, TennisGame.Snapshot> db = new HashMap<>()

    @Override
    void create(TennisGame tennisGame) {
        def snapshot = tennisGame.toSnapshot()
        db.put(snapshot.gameId(), snapshot)
    }

    @Override
    Optional<TennisGame> fetchById(UUID gameId) {
        return Optional.ofNullable(db.get(gameId))
                .map({ it -> TennisGame.fromSnapshot(it as TennisGame.Snapshot) })
    }
}
