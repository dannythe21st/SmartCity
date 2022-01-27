package SmartCity;

import Tip.Tip;
import User.User;
import SmartCity.Exceptions.*;

import java.util.HashMap;
import java.util.Map;


public class SmartCity {

    private Map<String, User> users;
    private Map<String, Tip> tipsByID;

    public SmartCity() {
        this.users = new HashMap<>();
        this.tipsByID = new HashMap<>();
    }

    private void addUser(User u) throws UserAlreadyExistsException{
        if (users.containsValue(u))
            throw new UserAlreadyExistsException();
        else{
            users.put(u.getID(), u);
        }
    }


}
