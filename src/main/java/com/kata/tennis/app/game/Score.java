package com.kata.tennis.app.game;

class Score {
    private final Player player;
    private final Points points;

    private Score(Player player, Points points) {
        this.player = player;
        this.points = points;
    }

    public static Score initial(Player player) {
        return new Score(player, new Points(0));
    }

    Score addPoint() {
        return new Score(player, points.add(new Points(1)));
    }

    Player player() {
        return player;
    }

    Points points() {
        return points;
    }
}
