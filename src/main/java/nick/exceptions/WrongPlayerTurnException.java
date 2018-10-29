package nick.exceptions;

/**
 * Created by Nikola on 9/16/2018.
 */
public class WrongPlayerTurnException extends RuntimeException {
    public WrongPlayerTurnException(String message) {
        super(message);
    }
}
