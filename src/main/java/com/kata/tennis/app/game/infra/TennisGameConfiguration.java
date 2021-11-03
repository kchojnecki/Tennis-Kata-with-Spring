package com.kata.tennis.app.game.infra;

import com.kata.tennis.app.game.domain.CreateNewGame;
import com.kata.tennis.app.game.domain.port.TennisGameGateway;
import org.springframework.context.annotation.Bean;

class TennisGameConfiguration {


    @Bean
    TennisGameGateway tennisGameGateway(){
        return new TennisGameInMemoryGateway();
    }

    @Bean
    TennisGameFacade tennisGameFacade(TennisGameGateway gameGateway) {
        var createNewGame = new CreateNewGame(gameGateway);
        return new TennisGameFacade(createNewGame);
    }
}
