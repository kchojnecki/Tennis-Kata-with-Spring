package com.kata.tennis.app.game.readmodel

import com.kata.tennis.app.shared.result.Failure
import io.vavr.control.Either

class InMemoryFetchTennisGameTestGateway implements FetchTennisGameGateway {
    private final Map<UUID, TennisGame> db = new HashMap<>()

    @Override
    Either<Failure, TennisGame> fetchById(UUID id) {
        def game = db.get(id)
        return (game == null ? Either.left(new Failure("NOT FOUND")) : Either.right(game)) as Either<Failure, TennisGame>
    }

    void add(TennisGame tennisGame) {
        db.put(tennisGame.gameId, tennisGame)
    }
}
