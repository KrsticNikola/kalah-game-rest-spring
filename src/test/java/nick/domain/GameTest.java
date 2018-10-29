package nick.domain;

import nick.exceptions.WrongPlayerTurnException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Nikola on 10/3/2018.
 */
public class GameTest {

    Game game;

    @Test(expected = WrongPlayerTurnException.class)
    public void playStonesExceptionBadPlayerTurn() {
        game.playStones(2);
        game.playStones(3);

    }

    @Before
    public void setUp() throws Exception {
        game = new Game(1);
    }

    @Test
    public void playStones() {
        game.playStones(1);
        assertEquals(game.getPits().get(1).getNumberOfStones(), 0);
        assertEquals(game.getPits().get(2).getNumberOfStones(), 7);
        assertEquals(game.getPits().get(3).getNumberOfStones(), 7);
        game.playStones(10);
        assertEquals(game.getPits().get(10).getNumberOfStones(), 0);
        assertEquals(game.getPits().get(11).getNumberOfStones(), 7);
        assertEquals(game.getPits().get(12).getNumberOfStones(), 7);
        assertEquals(game.getPits().get(13).getNumberOfStones(), 7);

        game.playStones(2);
        assertEquals(game.getPits().get(2).getNumberOfStones(), 0);
        assertEquals(game.getPits().get(3).getNumberOfStones(), 8);


    }

    @Test
    public void getGameID() {
        game.getGameID();
        assertEquals(game.getGameID(), 1);
    }

}
