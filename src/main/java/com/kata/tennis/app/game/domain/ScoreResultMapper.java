package com.kata.tennis.app.game.domain;

class ScoreResultMapper {

    String map(int player1Score, int player2Score) {
        String result = "";
        int tempScore = 0;
        if (player1Score == player2Score) {
            switch (player1Score) {
                case 0:
                    result = "Love-All";
                    break;
                case 1:
                    result = "Fifteen-All";
                    break;
                case 2:
                    result = "Thirty-All";
                    break;
                default:
                    result = "Deuce";
                    break;

            }
        } else if (player1Score >= 4 || player2Score >= 4) {
            int minusResult = player1Score - player2Score;
            if (minusResult == 1) result = "Advantage player1";
            else if (minusResult == -1) result = "Advantage player2";
            else if (minusResult >= 2) result = "Win for player1";
            else result = "Win for player2";
        } else {
            for (int i = 1; i < 3; i++) {
                if (i == 1) tempScore = player1Score;
                else {
                    result += "-";
                    tempScore = player2Score;
                }
                switch (tempScore) {
                    case 0:
                        result += "Love";
                        break;
                    case 1:
                        result += "Fifteen";
                        break;
                    case 2:
                        result += "Thirty";
                        break;
                    case 3:
                        result += "Forty";
                        break;
                }
            }
        }
        return result;
    }
}
