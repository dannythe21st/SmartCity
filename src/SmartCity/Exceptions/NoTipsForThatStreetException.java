package SmartCity.Exceptions;

public class NoTipsForThatStreetException extends RuntimeException{

    private static final String MESSAGE = "No tips for that street.";

    public NoTipsForThatStreetException() {
        super(MESSAGE);
    }
}
