package nick.domain;

import nick.enums.EPlayer;

/**
 * Created by Nikola on 9/16/2018.
 */
public class PlayerPit extends APit {

    public PlayerPit(int pitID, EPlayer playerID) {
        super(pitID, playerID);
    }

    public void addToPit(int numberOfStones){
        this.numberOfStones += numberOfStones;
    }

    @Override
    boolean isAllowedToAddStones(EPlayer playerID) {
        if (this.playerID == playerID){
            return true;
        }
        return false;
    }

}
