package com.kata.tennis.app.game.readmodel;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

class TennisGameEntrypoint {
    private final FetchTennisGameGateway gateway;

    TennisGameEntrypoint(FetchTennisGameGateway gateway) {
        this.gateway = gateway;
    }

    ResponseEntity<?> getScore(UUID id) {
        return gateway.fetchById(id)
                .fold(failure -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(failure.reason()), ResponseEntity::ok);
    }
}
