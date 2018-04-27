package uni_defense.logic.exceptions;

public class WorldNotFittingException extends RuntimeException {

    public WorldNotFittingException() {
        super("Passed file does not fit format.");
    }
}
