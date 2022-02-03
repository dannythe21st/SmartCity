package Establishment;

import User.User;

public class Establishment implements EstablishmentIF{

    private User owner;
    private String name;
    private String address;
    private String password; //to delete the shop, the owner must enter this password
    private double rating;
    private int numRatings;

    public Establishment(User owner, String name, String address, String password) {
        this.owner = owner;
        this.name = name;
        this.address = address;
        this.password = password;
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
    public User getOwner() { return owner; }

    @Override
    public String getPassword() { return password; }

    @Override
    public void addReview(double rating) {
        this.rating = (this.rating + rating)/++numRatings;
    }
}
