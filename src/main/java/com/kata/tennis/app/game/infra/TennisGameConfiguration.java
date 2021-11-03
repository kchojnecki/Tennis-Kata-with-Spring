package com.kata.tennis.app.game.infra;

import com.kata.tennis.app.game.domain.CreateNewGame;
import com.kata.tennis.app.game.domain.port.TennisGameGateway;
import com.kata.tennis.app.game.tempdb.InMemoryGameDb;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class TennisGameConfiguration {

    @Bean
    TennisGameGateway tennisGameGateway(InMemoryGameDb inMemoryGameDb){
        return new TennisGameInMemoryGateway(inMemoryGameDb);
    }

    @Bean
    TennisGameFacade tennisGameFacade(TennisGameGateway gameGateway) {
        var createNewGame = new CreateNewGame(gameGateway);
        return new TennisGameFacade(createNewGame);
    }
}
