package com.kata.tennis.app.game;

import com.kata.tennis.app.shared.result.Failure;
import com.kata.tennis.app.shared.result.Success;
import io.vavr.control.Either;

import static java.lang.String.format;

public class TennisGame {
    private Score playerOneScore;
    private Score playerTwoScore;
    private boolean isGameFinished;

    public TennisGame(Player player1, Player player2) {
        this.playerOneScore = Score.initial(player1);
        this.playerTwoScore = Score.initial(player2);
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


    public String getScore() {
        return new ScoreResultMapper().map(playerOneScore.points().value(), playerTwoScore.points().value());
    }
}
