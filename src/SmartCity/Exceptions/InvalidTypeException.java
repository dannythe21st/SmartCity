package SmartCity.Exceptions;

public class InvalidTypeException extends RuntimeException{

    private static final String MESSAGE = "Invalid type of user.";

    public InvalidTypeException() {
        super(MESSAGE);
    }
}
