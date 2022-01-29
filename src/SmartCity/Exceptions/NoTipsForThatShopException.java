package SmartCity.Exceptions;

public class NoTipsForThatShopException extends RuntimeException{

    private static final String MESSAGE = "No tips for that street.";

    public NoTipsForThatShopException() {
        super(MESSAGE);
    }
}
