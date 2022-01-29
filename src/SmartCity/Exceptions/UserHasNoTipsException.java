package SmartCity.Exceptions;

public class UserHasNoTipsException extends RuntimeException{

    private static final String MESSAGE = " has no tips.";

    public UserHasNoTipsException(String userName) {
        super(userName + MESSAGE);
    }
}
