package com.kata.tennis.app.game.tempdb;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryGameDb {
    private final Map<UUID, TennisGameDbModel> data = new ConcurrentHashMap<>();

    public void create(TennisGameDbModel tennisGameDbModel) {
        data.put(tennisGameDbModel.gameId(), tennisGameDbModel);
    }

    public Optional<TennisGameDbModel> fetchById(UUID gameId) {
        return Optional.ofNullable(data.get(gameId));
    }
}
