package nick.domain;

import nick.enums.EPlayer;

/**
 * Created by Nikola on 9/16/2018.
 */
public abstract class APit implements Comparable<APit> {

    int pitID;
    EPlayer playerID;
    int numberOfStones;

    /**
     * if it is true, it will not be skipped when adding stones
     */
    abstract boolean isAllowedToAddStones(EPlayer playerID);

    public APit(int pitID, EPlayer playerID) {
        this.pitID = pitID;
        this.playerID = playerID;
    }

    public int getNumberOfStones() {
        return numberOfStones;
    }

    public int getPitID() {
        return pitID;
    }

    public void incrementStones() {
        numberOfStones++;
    }

    public void cleanStones() {
        numberOfStones = 0;
    }

    public boolean isEmpty() {
        return numberOfStones == 0;
    }

    public EPlayer getPlayerID() {
        return playerID;
    }

    @Override
    public String toString() {
        return pitID + ":" + numberOfStones;
    }

    @Override
    public int compareTo(APit o) {
        return Integer.compare(this.pitID, o.getPitID());
    }
}
