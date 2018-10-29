package nick.enums;

/**
 * Created by Nikola on 9/16/2018.
 */
public enum EPlayer {
    GAME_STARTED(0),
    PLAYER1(1),
    PLAYER2(2);
    int id;
    EPlayer(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Player " + id;
    }
}
