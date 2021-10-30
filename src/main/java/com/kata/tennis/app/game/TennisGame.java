package com.kata.tennis.app.game;

public class TennisGame {
    private Score score;
    private final String player1Name;
    private final String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.score = Score.initial();
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1")
            score = score.addPointForPlayer1();
        else
            score = score.addPointForPlayer2();
    }

    public String getScore() {
        Score result = this.score;
        String score = "";
        int tempScore = 0;
        if (result.player1Score() == result.player2Score()) {
            switch (result.player1Score()) {
                case 0:
                    score = "Love-All";
                    break;
                case 1:
                    score = "Fifteen-All";
                    break;
                case 2:
                    score = "Thirty-All";
                    break;
                default:
                    score = "Deuce";
                    break;

            }
        } else if ( result.player1Score() >= 4 || result.player2Score() >= 4) {
            int minusResult =  result.player1Score() - result.player2Score();
            if (minusResult == 1) score = "Advantage player1";
            else if (minusResult == -1) score = "Advantage player2";
            else if (minusResult >= 2) score = "Win for player1";
            else score = "Win for player2";
        } else {
            for (int i = 1; i < 3; i++) {
                if (i == 1) tempScore =  result.player1Score();
                else {
                    score += "-";
                    tempScore = result.player2Score();
                }
                switch (tempScore) {
                    case 0:
                        score += "Love";
                        break;
                    case 1:
                        score += "Fifteen";
                        break;
                    case 2:
                        score += "Thirty";
                        break;
                    case 3:
                        score += "Forty";
                        break;
                }
            }
        }
        return score;
    }
}
