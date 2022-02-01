package SmartCity.Exceptions;

public class ShopDoesntExistException extends RuntimeException{

    private static final String MESSAGE = "Shop isn't registered in the platform.";

    public ShopDoesntExistException(){
        super(MESSAGE);
    }
}
