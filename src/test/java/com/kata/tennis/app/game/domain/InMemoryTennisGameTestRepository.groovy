package com.kata.tennis.app.game.domain

import com.kata.tennis.app.game.domain.port.TennisGameGateway

import java.util.concurrent.ConcurrentHashMap

class InMemoryTennisGameTestRepository implements TennisGameGateway {
    Map<UUID, TennisGame.Snapshot> db = new ConcurrentHashMap()

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
