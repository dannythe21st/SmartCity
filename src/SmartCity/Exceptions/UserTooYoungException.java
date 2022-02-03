package SmartCity.Exceptions;

public class UserTooYoungException extends RuntimeException{

    private static final String MESSAGE = "User is too young.";

    public UserTooYoungException() {
        super(MESSAGE);
    }
}
