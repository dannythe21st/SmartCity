package SmartCity.Exceptions;

public class WrongPasswordException extends RuntimeException{

    private static final String MESSAGE = "Wrong password.";

    public WrongPasswordException() {
        super(MESSAGE);
    }
}
