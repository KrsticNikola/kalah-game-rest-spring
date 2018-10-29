package nick.facade;

import nick.beans.GameBean;
import nick.domain.Game;
import nick.transferObject.CreateGameResponse;
import nick.beans.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import nick.transferObject.PlayStonesResponse;

import java.net.UnknownHostException;

/**
 * Created by Nikola on 9/16/2018.
 */
@RestController
public class GameFacade {

    @Autowired
    GameBean gameBean;

    @Autowired
    Converter converter;

    @RequestMapping(path = "/games/{gameId}/pits/{pitId}", method = RequestMethod.PUT, produces = "application/json")
    public PlayStonesResponse playStones(@PathVariable Integer gameId, @PathVariable Integer pitId) {
        Game game = gameBean.getGames().get(gameId);
        if (game == null) {
            throw new RuntimeException("Game with id: " + gameId + " not started");
        }
        game.playStones(pitId);
        try {
            return converter.convertToPlayStonesResponse(game);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            throw new RuntimeException("Internal error");
        }
    }

    @RequestMapping(path = "/games", method = RequestMethod.POST, produces = "application/json")
    public CreateGameResponse createGame() {
        try {
            return converter.convertToCreateGameResponse(gameBean.startGame());
        } catch (UnknownHostException e) {
            e.printStackTrace();
            throw new RuntimeException("Internal error");
        }
    }

}
