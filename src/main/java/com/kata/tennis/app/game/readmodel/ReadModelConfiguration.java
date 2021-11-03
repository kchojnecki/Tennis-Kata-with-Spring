package com.kata.tennis.app.game.readmodel;

import com.kata.tennis.app.game.tempdb.InMemoryGameDb;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ReadModelConfiguration {

    @Bean
    FetchTennisGameGateway fetchTennisGameGateway(InMemoryGameDb gameDb) {
        return new FetchTennisGameInMemoryGateway(gameDb);
    }
}
