package SmartCity.Exceptions;

public class InvalidAgeException extends RuntimeException{

    private static final String MESSAGE = "Invalid age.";

    public InvalidAgeException() {
        super(MESSAGE);
    }
}
