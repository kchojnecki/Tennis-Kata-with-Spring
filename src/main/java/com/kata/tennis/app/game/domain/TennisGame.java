package com.kata.tennis.app.game.domain;

import com.kata.tennis.app.shared.result.Failure;
import com.kata.tennis.app.shared.result.Success;
import io.vavr.control.Either;

import java.util.UUID;

import static java.lang.String.format;

public class TennisGame {
    private final UUID gameId;
    private Score playerOneScore;
    private Score playerTwoScore;
    private boolean isGameFinished;

    private TennisGame(UUID gameId, Score playerOneScore, Score playerTwoScore, boolean isGameFinished) {
        this.gameId = gameId;
        this.playerOneScore = playerOneScore;
        this.playerTwoScore = playerTwoScore;
        this.isGameFinished = isGameFinished;
    }

    public static TennisGame newGame(Player player1, Player player2) {
        return new TennisGame(UUID.randomUUID(), Score.initial(player1), Score.initial(player2), false);
    }

    public static TennisGame fromSnapshot(Snapshot snapshot) {
        var gameId = snapshot.gameId();
        var playerOneScore = new Score(new Player(snapshot.playerOneName), new Points(snapshot.playerOneScore));
        var playerTwoScore = new Score(new Player(snapshot.playerTwoName), new Points(snapshot.playerTwoScore));
        var isGameFinished = snapshot.isGameFinished;
        return new TennisGame(gameId, playerOneScore, playerTwoScore, isGameFinished);
    }

    UUID id() {
        return gameId;
    }

    public Either<Failure, Success> wonPoint(Player player) {
        if (isGameFinished) {
            return Either.left(new Failure("Game is already finished."));
        }
        if (playerOneScore.player().equals(player)) {
            playerOneScore = playerOneScore.addPoint();
            return pointWonBy(player);
        }
        if (playerTwoScore.player().equals(player)) {
            playerTwoScore = playerTwoScore.addPoint();
            return pointWonBy(player);
        }
        return playerNotFound(player);
    }

    private Either<Failure, Success> pointWonBy(Player player) {
        return Either.right(new Success(format("Player %s won a point.", player.name())));
    }

    private Either<Failure, Success> playerNotFound(Player player) {
        return Either.left(new Failure(format("Player %s is not in the game.", player.name())));
    }

    public Either<Failure, Success> finishGame() {
        if (isGameFinished) {
            return Either.left(new Failure("Game is already finished."));
        }
        if (!isGameWon()) {
            return Either.left(new Failure("Game in progress."));
        }
        isGameFinished = true;
        return Either.right(new Success("Game finished."));
    }

    private boolean isGameWon() {
        return atLeast4PointsScored() &&
                (isTwoPointsAhead(playerOneScore, playerTwoScore) || isTwoPointsAhead(playerTwoScore, playerOneScore));
    }

    private boolean atLeast4PointsScored() {
        Points fourPoints = new Points(4);
        return playerOneScore.points().greaterOrEqual(fourPoints) ||
                playerTwoScore.points().greaterOrEqual(fourPoints);
    }

    private boolean isTwoPointsAhead(Score p1, Score p2) {
        Points twoPoints = new Points(2);
        return p1.points().greaterOrEqual(p2.points().add(twoPoints));
    }

    public Snapshot toSnapshot() {
        return new Snapshot(
                gameId,
                playerOneScore.player().name(),
                playerOneScore.points().value(),
                playerTwoScore.player().name(),
                playerTwoScore.points().value(),
                isGameFinished
        );
    }

    public static record Snapshot(
            UUID gameId,
            String playerOneName,
            int playerOneScore,
            String playerTwoName,
            int playerTwoScore,
            boolean isGameFinished
    ) {
    }
}
