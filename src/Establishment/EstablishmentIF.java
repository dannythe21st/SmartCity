package Establishment;

import User.User;

public interface EstablishmentIF {

    String getName();

    String getAddress();

    double getRating();

    int getNumRatings();

    User getOwner();

    void addReview(double rating);

}
