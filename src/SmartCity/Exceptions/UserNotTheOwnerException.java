package SmartCity.Exceptions;

public class UserNotTheOwnerException extends RuntimeException{

    private static final String MESSAGE = "User isn't the owner.";

    public UserNotTheOwnerException() {
        super(MESSAGE);
    }
}
