package com.kata.tennis.app.game.readmodel;

import com.kata.tennis.app.shared.result.Failure;
import io.vavr.control.Either;

import java.util.UUID;

interface FetchTennisGameGateway {
    Either<Failure, TennisGame> fetchById(UUID id);
}
