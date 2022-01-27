package SmartCity.Exceptions;

public class UserAlreadyExistsException extends RuntimeException{

    private String MESSAGE = "User already exists.";

    public UserAlreadyExistsException() {
        super();
    }
}
