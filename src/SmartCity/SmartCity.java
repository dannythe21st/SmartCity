package SmartCity;

import Establishment.Establishment;
import Tip.Tip;
import User.User;
import SmartCity.Exceptions.*;
import java.util.HashMap;
import java.util.Map;


public class SmartCity {

    //Constants
    private static final int MINIMUM_AGE = 13;
    private static final int ADMIN = 1;
    private static final int REGULAR = 2;

    private Map<String, User> users; //id, User
    private Map<String, Tip> tipsByID; //id, tip
    private Map<String, Establishment> shops; //name, Shop

    public SmartCity() {
        this.users = new HashMap<>();
        this.tipsByID = new HashMap<>();
        this.shops = new HashMap<>();
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
        else if(u.getType() != ADMIN && u.getType() != REGULAR)
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
        if (remover.getType() != (ADMIN))
            throw new NotAdminException(remover);
        else if (!users.containsKey(removedID) || !users.containsKey(removerID))
            throw new UserDoesntExistException();
        else
            users.remove(removedID);
    }

    /**
     * Add a new tip to a local in the city
     * @param tip
     * @throws UserDoesntExistException
     */
    public void addTip(Tip tip) throws UserDoesntExistException {
        if (!users.containsKey(tip.getAuthorID()))
            throw new UserDoesntExistException();
        else
            tipsByID.put(tip.getId(), tip);
    }

    /**
     * Remove tip
     * @param userID
     * @param tipID
     * @throws UserDoesntExistException
     * @throws TipDoesntExistException
     */
    public void removeTip(String userID, String tipID) throws UserDoesntExistException, TipDoesntExistException{
        User u = users.get(userID);
        if (!users.containsKey(userID))
            throw new UserDoesntExistException();
        else if (users.get(userID).getType() != ADMIN)
            throw new NotAdminException(u);
        else if (!tipsByID.containsKey(tipID))
            throw new TipDoesntExistException();
        else
            tipsByID.remove(tipID);
    }


}
