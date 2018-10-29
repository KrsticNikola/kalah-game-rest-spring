package nick.domain;

import nick.enums.EPlayer;

/**
 * Created by Nikola on 9/16/2018.
 */
public class NormalPit extends APit {
    NormalPit paralelPit;

    public NormalPit(int numberOfStones, int pitID, EPlayer playerID) {
        super(pitID, playerID);
        this.numberOfStones = numberOfStones;
    }

    public NormalPit getParalelPit() {
        return paralelPit;
    }

    public void setParalelPit(NormalPit paralelPit) {
        this.paralelPit = paralelPit;
    }

    @Override
    boolean isAllowedToAddStones(EPlayer playerID) {
        return true;
    }
}
