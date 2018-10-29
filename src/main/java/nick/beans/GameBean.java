package nick.beans;

import nick.domain.Game;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Nikola on 9/16/2018.
 */
@Component
public class GameBean {
    int lastGameID = 0;
    HashMap<Integer, Game> games;

    public GameBean() {
        games = new HashMap<>();
    }

    public int startGame() {
        lastGameID++;
        games.put(lastGameID, new Game(lastGameID));
        return lastGameID;
    }

    public HashMap<Integer, Game> getGames() {
        return games;
    }


}
