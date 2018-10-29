package nick.beans;

import nick.domain.APit;
import nick.domain.Game;
import nick.transferObject.CreateGameResponse;
import nick.transferObject.PlayStonesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Nikola on 9/16/2018.
 */
@Component
public class Converter {
    @Autowired
    Environment environment;

    /**
     * Convert {@link Game} to {@link PlayStonesResponse}
     * @param game to convert
     * @return instance of PlayStonesResponse
     * @throws UnknownHostException
     */
    public PlayStonesResponse convertToPlayStonesResponse(Game game) throws UnknownHostException {
        PlayStonesResponse response = new PlayStonesResponse();
        response.setId(String.valueOf(game.getGameID()));

        //get port and address
        String port = environment.getProperty("local.server.port");
        String adress = InetAddress.getLocalHost().getHostAddress();
        response.setUrl("http://" + adress + ":" + port );

        Iterator it = game.getPits().entrySet().iterator();
        HashMap<String, String> retMap = new HashMap<>();
        while (it.hasNext()) {
            APit pit = (APit) ((Map.Entry) it.next()).getValue();
            retMap.put(String.valueOf(pit.getPitID()), String.valueOf(pit.getNumberOfStones()));
        }
        response.setStatus(retMap);
        return response;
    }

    /**
     * Convert to {@link CreateGameResponse}
     * @param gameID id of game
     * @return instance of CreateGameResponse
     * @throws UnknownHostException
     */
    public CreateGameResponse convertToCreateGameResponse(int gameID) throws UnknownHostException {
        CreateGameResponse response = new CreateGameResponse();
        response.setId(String.valueOf(gameID));
        //get port and address
        String port = environment.getProperty("local.server.port");
        String adress = InetAddress.getLocalHost().getHostAddress();
        response.setUrl("http://" + adress + ":" + port );

        return response;

    }
}
