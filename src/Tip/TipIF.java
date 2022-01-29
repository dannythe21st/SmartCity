package Tip;

import Establishment.Establishment;
import User.User;

public interface TipIF {
    String getAuthorID();

    String getId();

    Establishment getShop();

    String getAddress();

    String getDescription();

}
