package nick.exceptions;

/**
 * Created by Nikola on 9/16/2018.
 */
public class GameEndedException extends RuntimeException {
    public GameEndedException(String message) {
        super(message);
    }
}
