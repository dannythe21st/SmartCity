package SmartCity.Exceptions;

public class UserDoesntExistException extends RuntimeException{

    private static final String MESSAGE = "User isn't registered in the platform.";

    public UserDoesntExistException() {
        super(MESSAGE);
    }
}
