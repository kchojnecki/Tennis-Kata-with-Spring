package com.kata.tennis.app.game.readmodel;

import com.kata.tennis.app.game.tempdb.InMemoryGameDb;
import com.kata.tennis.app.game.tempdb.TennisGameDbModel;
import com.kata.tennis.app.shared.result.Failure;
import io.vavr.control.Either;

import java.util.UUID;

class FetchTennisGameInMemoryGateway implements FetchTennisGameGateway {
    private final InMemoryGameDb inMemoryGameDb;

    FetchTennisGameInMemoryGateway(InMemoryGameDb inMemoryGameDb) {
        this.inMemoryGameDb = inMemoryGameDb;
    }

    @Override
    public Either<Failure, TennisGame> fetchById(UUID id) {
        return inMemoryGameDb.fetchById(id)
                .map(this::toTennisGame)
                .<Either<Failure, TennisGame>>map(Either::right)
                .orElse(Either.left(new Failure(String.format("Game[%s] doesn't exists", id))));
    }

    private TennisGame toTennisGame(TennisGameDbModel dbModel) {
        var p1Score = new TennisGame.Score(dbModel.playerOneName(), dbModel.playerOneScore());
        var p2Score = new TennisGame.Score(dbModel.playerTwoName(), dbModel.playerTwoScore());
        return new TennisGame(dbModel.gameId(), p1Score, p2Score, dbModel.isGameFinished());
    }
}
