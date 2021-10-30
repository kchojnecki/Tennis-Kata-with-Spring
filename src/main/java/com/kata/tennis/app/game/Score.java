package com.kata.tennis.app.game;

class Score {
    private final int player1;
    private final int player2;

    Score(int player1, int player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    static Score initial() {
        return new Score(0, 0);
    }

    Score addPointForPlayer1(){
        return new Score(player1 + 1, player2);
    }

    Score addPointForPlayer2(){
        return new Score(player1, player2 + 1);
    }

    public int player1Score() {
        return player1;
    }

    public int player2Score() {
        return player2;
    }
}
