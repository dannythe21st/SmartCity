package SmartCity.Exceptions;

public class InvalidRatingException extends RuntimeException{

    private static final String MESSAGE = "Invalid rating.";

    public InvalidRatingException(){
        super(MESSAGE);
    }
}
