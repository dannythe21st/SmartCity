package SmartCity.Exceptions;

import User.User;

public class NotAdminException extends RuntimeException{

    private static final String MESSAGE = " isn't an admin in the platform.";

    public NotAdminException(User remover) {
        super(remover.getName() + MESSAGE);
    }
}
