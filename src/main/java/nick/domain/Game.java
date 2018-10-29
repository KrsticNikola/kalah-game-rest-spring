package nick.domain;

import nick.enums.EPlayer;
import nick.exceptions.GameEndedException;
import nick.exceptions.WrongPlayerTurnException;

/**
 * Created by Nikola on 9/16/2018.
 */
public class Game {
    int gameID;
    TreeMapPits pits = new TreeMapPits();
    EPlayer lastPlayer = EPlayer.GAME_STARTED;

    public Game(int gameID) {
        this.gameID = gameID;
    }

    /**
     * Move stones
     *
     * @param pitID start pit id
     */
    public void playStones(int pitID) {
        if (pits.isGameEnded()) {
            EPlayer winner =
                    pits.firstPlayerPit.getNumberOfStones() > pits.getSecondPlayerPit().getNumberOfStones() ? EPlayer.PLAYER1 : EPlayer.PLAYER2;
            throw new GameEndedException("Game ended, winner is: " + winner.toString());
        }
        if (pitID >= 1 && pitID <= 7) {
            if (lastPlayer == EPlayer.PLAYER1) {
                throw new WrongPlayerTurnException("It is not player 1 turn");
            }
            pits.playStones(pitID, EPlayer.PLAYER1);
            lastPlayer = EPlayer.PLAYER1;
        } else {
            if (lastPlayer == EPlayer.PLAYER2) {
                throw new WrongPlayerTurnException("It is not player 2 turn");
            }
            pits.playStones(pitID, EPlayer.PLAYER2);
            lastPlayer = EPlayer.PLAYER2;
        }
    }


    public int getGameID() {
        return gameID;
    }

    public TreeMapPits getPits() {
        return pits;
    }

}
