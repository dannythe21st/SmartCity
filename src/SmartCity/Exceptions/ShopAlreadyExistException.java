package SmartCity.Exceptions;

public class ShopAlreadyExistException extends RuntimeException{

    private static final String MESSAGE = "Shop already registered!";

    public ShopAlreadyExistException() {
        super(MESSAGE);
    }
}
