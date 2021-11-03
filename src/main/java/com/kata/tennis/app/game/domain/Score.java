package com.kata.tennis.app.game.domain;

record Score(Player player, Points points) {

    static Score initial(Player player) {
        return new Score(player, new Points(0));
    }

    Score addPoint() {
        return new Score(player, points.add(new Points(1)));
    }
}
