package SmartCity.Exceptions;

public class TipDoesntExistException extends RuntimeException{

    private static final String MESSAGE = "Tip isn't registered in the platform.";

    public TipDoesntExistException() {
        super(MESSAGE);
    }
}
