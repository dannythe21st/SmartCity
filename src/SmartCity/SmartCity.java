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
            InvalidAgeException, UserTooYoungException{
        if (users.containsValue(u))
            throw new UserAlreadyExistsException();
        else if (u.getAge() < 0)
            throw new InvalidAgeException();
        else if (u.getAge() < MINIMUM_AGE && u.getAge() > 0)
            throw new UserTooYoungException();
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
    public void addTip(String userID, String tipID, String shopName, String description)
            throws UserDoesntExistException, ShopDoesntExistException {
        if (!users.containsKey(userID))
            throw new UserDoesntExistException();
        else if (!shops.containsKey(shopName))
            throw new ShopDoesntExistException();
        else{
            //save tip
            Establishment s = shops.get(shopName);
            Tip tip = new Tip(userID, tipID, s, description);
            tipsByID.put(tipID, tip);

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
    public void removeTip(String userID, String tipID) throws UserDoesntExistException, TipDoesntExistException,
            NotAdminException{
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

    /**
     * Add a new shop to the city
     * @param userID
     * @param shopName
     * @param address
     * @param password
     * @throws UserDoesntExistException
     * @throws ShopAlreadyExistException
     */
    public void addShop(String userID, String shopName, String address, String password) throws UserDoesntExistException,
            ShopAlreadyExistException{
        if (!users.containsKey(userID))
            throw new UserDoesntExistException();
        else if (shops.containsKey(shopName))
            throw new ShopAlreadyExistException();
        else{
            User owner = users.get(userID);
            Establishment s = new Establishment(owner, shopName, address, password);
            shops.put(shopName, s);
        }
    }

    /**
     * Remove shop
     * @param userID
     * @param shopName
     * @param password
     * @throws ShopDoesntExistException
     * @throws UserDoesntExistException
     * @throws UserNotTheOwnerException
     * @throws WrongPasswordException
     */
    public void removeShop(String userID, String shopName, String password) throws ShopDoesntExistException, UserDoesntExistException,
            UserNotTheOwnerException, WrongPasswordException {
        if (!shops.containsKey(shopName))
            throw new ShopDoesntExistException();
        else if (!users.containsKey(userID))
            throw new UserDoesntExistException();
        else if (!shops.get(shopName).getOwner().getID().equals(userID))
            throw new UserNotTheOwnerException();
        else if (!shops.get(shopName).getPassword().equals(password))
            throw new WrongPasswordException();
        else{
            shops.remove(shopName);
        }
    }

    /**
     * Adds a rating to a shop
     * @param shopName
     * @param rating
     * @throws ShopDoesntExistException
     * @throws InvalidRatingException
     */
    public void rateShop(String shopName, double rating) throws ShopDoesntExistException, InvalidRatingException{
        if (!shops.containsKey(shopName))
            throw new ShopDoesntExistException();
        else if (rating < 0 || rating > 5)
            throw new InvalidRatingException();
        else{
            Establishment s = shops.get(shopName);
            s.addReview(rating);
        }
    }

    /**
     * Get the info of a user
     * @param userID
     * @return
     * @throws UserDoesntExistException
     */
    public User getUserInfo(String userID) throws UserDoesntExistException{
        if (!users.containsKey(userID))
            throw new UserDoesntExistException();
        return users.get(userID);
    }

    /**
     *
     * @param shopID
     * @return
     * @throws ShopDoesntExistException
     */
    public Establishment getShopInfo(String shopID) throws ShopDoesntExistException{
        if (!shops.containsKey(shopID))
            throw new ShopDoesntExistException();
        else
            return shops.get(shopID);
    }

    /**
     * List tips added by a user
     * @param userID
     * @return tip iterator
     * @throws UserDoesntExistException
     * @throws UserHasNoTipsException
     */
    public Iterator<Tip> getTipsByUser(String userID) throws UserDoesntExistException, UserHasNoTipsException{
        if (!users.containsKey(userID))
            throw new UserDoesntExistException();
        else if (users.get(userID).getNumOfTips() == 0)
            throw new UserHasNoTipsException(users.get(userID).getName());
        else{
            return users.get(userID).tipsByUser();
        }
    }

    /**
     * List tips associated to establishments in a particular street
     * @param address
     * @return tip iterator
     * @throws NoTipsForThatStreetException
     */
    public Iterator<Tip> getTipsByStreet(String address) throws NoTipsForThatStreetException{
        List<Tip> tipsByStreet = new LinkedList<>();
        for (Tip t : tipsByID.values()) {
            if (t.getShop().getAddress().equals(address))
                tipsByStreet.add(t);
        }
        if (tipsByStreet.size() == 0) throw new NoTipsForThatStreetException();
        return tipsByStreet.iterator();
    }

    /**
     * List tips associated with a particular establishment
     * @param shopName
     * @return tip iterator
     * @throws NoTipsForThatShopException
     */
    public Iterator<Tip> getTipsByShop(String shopName) throws NoTipsForThatShopException{
        List<Tip> tipsByShop = new LinkedList<>();
        for (Tip t : tipsByID.values()) {
            if (t.getShop().getName().equals(shopName))
                tipsByShop.add(t);
        }
        if (tipsByShop.size() == 0) throw new NoTipsForThatShopException();
        return tipsByShop.iterator();
    }

    /**
     * Lists all users
     * @return user iterator
     */
    public Iterator<User> listUsers(){
        return users.values().iterator();
    }

    /**
     * Lists all tips
     * @return tip iterator
     */
    public Iterator<Tip> listAllTips(){
        return tipsByID.values().iterator();
    }


}
