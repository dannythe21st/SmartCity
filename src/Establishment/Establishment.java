package Establishment;

public class Establishment implements EstablishmentIF{

    private String name;
    private String address;
    private double rating;
    private int numRatings;

    public Establishment(String name, String address) {
        this.name = name;
        this.address = address;
        this.rating = 0;
        this.numRatings = 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public double getRating() {
        return rating;
    }

    @Override
    public int getNumRatings() {
        return numRatings;
    }

    @Override
    public void addReview(double rating) {
        this.rating = (this.rating + rating)/++numRatings;
    }
}
