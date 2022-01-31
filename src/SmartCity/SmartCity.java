package SmartCity;

import Establishment.Establishment;
import Tip.Tip;
import User.User;
import SmartCity.Exceptions.*;

import java.util.*;


public class SmartCity {

    //Constants
    private static final int MINIMUM_AGE = 13;
    private static final int ADMIN = 1;
    private static final int REGULAR = 2;

    private Map<String, User> users; //id, User
    private Map<String, Tip> tipsByID; //id, tip
    //private Map<String, Tip> tipsByShop; //shopname, tip
    //private Map<String, Tip> tipsByStreet; //address, tip
    private Map<String, Establishment> shops; //name, Shop

    public SmartCity() {
        this.users = new HashMap<>();
        this.tipsByID = new HashMap<>();
//        this.tipsByShop = new HashMap<>();
//        this.tipsByStreet = new HashMap<>();
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
     * @param userID
     * @param tipID
     * @param shopName
     * @param description
     * @throws UserDoesntExistException
     */
    public void addTip(String userID, String tipID, String shopName, String address, String description)
            throws UserDoesntExistException {
        if (!users.containsKey(userID))
            throw new UserDoesntExistException();
        else{
            Establishment s;
            if (!shops.containsKey(shopName))
                s = new Establishment(shopName, address);
            else
                s = shops.get(shopName);

            Tip tip = new Tip(userID, tipID, s, address, description);
            //save tip
            tipsByID.put(tipID, tip);
            //tipsByShop.put(shopName, tip);
            //tipsByStreet.put(address, tip);

            //update user stats + save tip to user
            users.get(userID).updateLevel();
            users.get(userID).addTip(tip);
        }

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


    public Iterator<Tip> getTipsByUser(String userID) throws UserDoesntExistException, UserHasNoTipsException{
        if (!users.containsKey(userID))
            throw new UserDoesntExistException();
        else if (users.get(userID).getNumOfTips() == 0)
            throw new UserHasNoTipsException(users.get(userID).getName());
        else{
            return users.get(userID).tipsByUser();
        }
    }

    public Iterator<Tip> getTipsByStreet(String address) throws NoTipsForThatStreetException{
        List<Tip> tipsByStreet = new LinkedList<>();
        for (Tip t : tipsByID.values()) {
            if (t.getShop().getAddress().equals(address))
                tipsByStreet.add(t);
        }
        if (tipsByStreet.size() == 0) throw new NoTipsForThatStreetException();
        return tipsByStreet.iterator();
    }

    public Iterator<Tip> getTipsByShop(String shopName) throws NoTipsForThatShopException{
        List<Tip> tipsByShop = new LinkedList<>();
        for (Tip t : tipsByID.values()) {
            if (t.getShop().getName().equals(shopName))
                tipsByShop.add(t);
        }
        if (tipsByShop.size() == 0) throw new NoTipsForThatShopException();
        return tipsByShop.iterator();
    }

    public Iterator<User> listUsers(){
        return users.values().iterator();
    }

    public Iterator<Tip> listAllTips(){
        return tipsByID.values().iterator();
    }


}
