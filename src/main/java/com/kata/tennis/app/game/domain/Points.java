package com.kata.tennis.app.game.domain;

record Points(int value) {
    Points {
        if (value < 0) {
            throw new RuntimeException("Points cannot be negative.");
        }
    }

    Points add(Points points){
        return new Points(value + points.value());
    }

    boolean greaterOrEqual(Points points){
        return value >= points.value();
    }
}
