package com.kata.tennis.app.game.infra;

import com.kata.tennis.app.game.domain.TennisGame;
import com.kata.tennis.app.game.domain.port.TennisGameGateway;
import com.kata.tennis.app.game.tempdb.InMemoryGameDb;
import com.kata.tennis.app.game.tempdb.TennisGameDbModel;

import java.util.Optional;
import java.util.UUID;

class TennisGameInMemoryGateway implements TennisGameGateway {
    private final InMemoryGameDb inMemoryGameDb;

    TennisGameInMemoryGateway(InMemoryGameDb inMemoryGameDb) {
        this.inMemoryGameDb = inMemoryGameDb;
    }

    @Override
    public void create(TennisGame tennisGame) {
        var snapshot = tennisGame.toSnapshot();
        var dbModel = new TennisGameDbModel(snapshot.gameId(),
                snapshot.playerOneName(), snapshot.playerOneScore(),
                snapshot.playerTwoName(), snapshot.playerTwoScore(),
                snapshot.isGameFinished()
        );
        inMemoryGameDb.create(dbModel);
    }

    @Override
    public Optional<TennisGame> fetchById(UUID gameId) {
        return inMemoryGameDb.fetchById(gameId)
                .map(this::toSnapshot)
                .map(TennisGame::fromSnapshot);
    }

    private TennisGame.Snapshot toSnapshot(TennisGameDbModel tennisGameDbModel) {
        return new TennisGame.Snapshot(
                tennisGameDbModel.gameId(),
                tennisGameDbModel.playerOneName(),
                tennisGameDbModel.playerOneScore(),
                tennisGameDbModel.playerTwoName(),
                tennisGameDbModel.playerTwoScore(),
                tennisGameDbModel.isGameFinished());
    }
}
