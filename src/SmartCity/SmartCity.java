package SmartCity;

import Tip.Tip;
import User.User;
import SmartCity.Exceptions.*;
import java.util.HashMap;
import java.util.Map;


public class SmartCity {

    //Constants
    private static final int MINIMUM_AGE = 13;
    private static final String ADMIN = "admin";
    private static final String REGULAR = "regular";

    private Map<String, User> users; //id, User
    private Map<String, Tip> tipsByID; //id, tip

    public SmartCity() {
        this.users = new HashMap<>();
        this.tipsByID = new HashMap<>();
    }

    /**
     * Adding new user to the platform
     * @param u
     * @throws UserAlreadyExistsException
     * @throws InvalidTypeException
     * @throws InvalidAgeException
     */
    public void addUser(User u) throws UserAlreadyExistsException, InvalidTypeException,
            InvalidAgeException{
        if (users.containsValue(u))
            throw new UserAlreadyExistsException();
        else if (u.getAge() < MINIMUM_AGE)
            throw new InvalidAgeException();
        else if(u.getType().equals(ADMIN) && u.getType().equals(REGULAR))
            throw new InvalidTypeException();
        else
            users.put(u.getID(), u);
    }

    /**
     * Only admins can remove other users
     * @param removerID
     * @param removedID
     */
    public void removeUser(String removerID, String removedID) throws NotAdminException, UserDoesntExistException{
        User remover = users.get(removerID);
        if (!remover.getType().equals(ADMIN))
            throw new NotAdminException(remover);
        else if (!users.containsKey(removedID) || !users.containsKey(removerID))
            throw new UserDoesntExistException();
        else
            users.remove(removedID);
    }


}
