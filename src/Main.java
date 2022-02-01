import SmartCity.Exceptions.*;
import SmartCity.SmartCity;
import Tip.Tip;
import User.User;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author dannythe21st on github
 * Project start: 27/01/22
 * Project end: ______
 */

public class Main {

    //font colors
    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_BLACK = "\u001B[30m";
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_GREEN = "\u001B[32m";
    public static final String TEXT_YELLOW = "\u001B[33m";
    public static final String TEXT_BLUE = "\u001B[34m";
    public static final String TEXT_PURPLE = "\u001B[35m";
    public static final String TEXT_CYAN = "\u001B[36m";
    public static final String TEXT_WHITE = "\u001B[37m";

    //Initial routine
    private static final String WELCOME = "WELCOME TO SMART CITY :D";
    private static final String AUTHOR = "powered by dannythe21st.";
    private static final String BOOTING_SERVICE = "Booting system...";
    private static final String PRINTING_MENU = "Menu:";
    private static final String BLUE_SCREEN = "Something went wrong :c";

    //Comms
    private static final String EXIT = "exit";
    private static final String MENU = "menu";
    private static final String ADD_USER = "adduser";
    private static final String REMOVE_USER = "removeuser";
    private static final String ADD_TIP = "addtip";
    private static final String REMOVE_TIP = "removetip";
    private static final String RATE_SHOP = "rateshop";
    private static final String USER_INFO = "userinfo";
    private static final String SHOP_INFO = "shopinfo";
    private static final String GET_TIPS_BY_USER = "tipbyuser";
    private static final String GET_TIPS_BY_STREET = "tipbystreet";
    private static final String GET_TIPS_BY_SHOP = "tipbyshop";
    private static final String LIST_USERS = "listusers";
    private static final String LIST_ALL_TIPS = "listalltips";

    //Prompts
    private static final String PROMPT = "> ";
    private static final String SHOP_NAME_P = "Shop name: ";
    private static final String USER_ID_P = "User ID: ";
    private static final String TIP_ID_P = "Tip ID: ";
    private static final String ADDRESS_P = "Address: ";
    private static final String DESCRIPTION_P = "Description: ";
    private static final String ID_P = "ID: ";
    private static final String NAME_P = "Name: ";
    private static final String AGE_P = "Age: ";
    private static final String USER_TYPE_P = "Type (Admin (1) | Regular (2)): ";
    private static final String ADMIN_ID_P = "Admin ID: ";
    private static final String RATE_SHOP_P = "Rate Shop (0-5): ";
    private static final String TIP_COUNT_P = "Tip count: ";
    private static final String LEVEL_P = "Level: ";
    private static final String STREET_NAME_P = "Street name: ";


    //Outputs
    private static final String USER_REGISTRATION = "Registration Complete!";
    private static final String USER_REMOVED = "User removed!";
    private static final String TIP_REGISTERED = "Tip registered!";
    private static final String TIP_REMOVED = "Tip removed!";
    private static final String REVIEW_REGISTERED = "Review registered!";
    private static final String UNKNOWN_COMMAND = "Unknown command.";
    private static final String BYE = "See you soon.";


    public static void main(String[] args) {

        //Initialization
        Scanner in = new Scanner(System.in);
        SmartCity s = new SmartCity();

        //print menu + read 1st comm
        welcome();
        System.out.print(PROMPT);
        String comm = in.nextLine().toLowerCase();
        while (!comm.equals(EXIT)) {
            switch (comm) {
                case MENU -> menu();
                case ADD_USER -> addUser(s, in);
                case REMOVE_USER -> removeUser(s, in);
                case ADD_TIP -> addTip(s, in);
                case REMOVE_TIP -> removeTip(s, in);
                case RATE_SHOP -> rateShop(s, in);
                case GET_TIPS_BY_USER -> getTipsByUser(s, in);
                case GET_TIPS_BY_STREET -> getTipsByStreet(s, in);
                case GET_TIPS_BY_SHOP -> getTipsByShop(s, in);
                case USER_INFO -> getUserInfo(s, in);
                case LIST_USERS -> listUsers(s);
                case LIST_ALL_TIPS -> listAllTips(s);
                default -> System.out.println(UNKNOWN_COMMAND);
            }
            System.out.println();
            System.out.print(PROMPT);
            comm = in.nextLine().toLowerCase();
        }
        System.out.println();
        System.out.print(TEXT_GREEN + BYE + TEXT_RESET);
        in.close();
    }

    private static void menu() {
        System.out.println(EXIT);
        System.out.println(MENU);
        System.out.println(ADD_USER);
        System.out.println(REMOVE_USER);
        System.out.println(ADD_TIP);
        System.out.println(REMOVE_TIP);
        System.out.println(RATE_SHOP);
        System.out.println(USER_INFO);
        System.out.println(GET_TIPS_BY_USER);
        System.out.println(GET_TIPS_BY_STREET);
        System.out.println(GET_TIPS_BY_SHOP);
    }

    private static void addUser(SmartCity s, Scanner in) {
        System.out.print(ID_P);
        String id = in.next(); in.nextLine();

        System.out.print(NAME_P);
        String name = in.nextLine().trim();

        System.out.print(AGE_P);
        int age = in.nextInt();

        System.out.print(USER_TYPE_P);
        int type = in.nextInt(); in.nextLine();

        try{
            User u = new User(id, name, age, type);
            s.addUser(u);
            System.out.println(USER_REGISTRATION);
        }catch(UserAlreadyExistsException | InvalidAgeException | InvalidTypeException e){
            System.out.println(e.getMessage());
        } catch(InputMismatchException e){
            System.out.println("Incorrect information.");
        }
    }

    private static void removeUser(SmartCity s, Scanner in){
        System.out.print(ADMIN_ID_P);
        String removerID = in.next(); in.nextLine();

        System.out.print(USER_ID_P);
        String removedID = in.next(); in.nextLine();
        try{
            s.removeUser(removerID, removedID);
            System.out.println(USER_REMOVED);
        }catch(NotAdminException | UserDoesntExistException e){
            System.out.println(e.getMessage());
        }
    }

    private static void addTip(SmartCity s, Scanner in){
        System.out.print(USER_ID_P);
        String userID = in.next(); in.nextLine();

        System.out.print(TIP_ID_P);
        String tipID = in.next(); in.nextLine();

        System.out.print(SHOP_NAME_P);
        String shopName = in.nextLine();

        System.out.print(ADDRESS_P);
        String address = in.nextLine();

        System.out.print(DESCRIPTION_P);
        String description = in.nextLine();

        try{
            s.addTip(userID, tipID, shopName, address, description);
            System.out.println(TIP_REGISTERED);
        }catch(UserDoesntExistException e){
            System.out.println(e.getMessage());
        } catch(InvalidTypeException e){
            System.out.println("Wrong info.");
        }
    }

    private static void removeTip(SmartCity s, Scanner in){
        System.out.print(USER_ID_P);
        String userID = in.next(); in.nextLine();

        System.out.print(TIP_ID_P);
        String tipID = in.next(); in.nextLine();

        try{
            s.removeTip(userID, tipID);
            System.out.println(TIP_REMOVED);
        }catch (UserDoesntExistException | TipDoesntExistException e){
            System.out.println(e.getMessage());
        }
    }

    private static void rateShop(SmartCity s, Scanner in){
        System.out.println(SHOP_NAME_P);
        String shopName = in.nextLine();

        System.out.println(RATE_SHOP_P);
        double rating = in.nextDouble();

        try{
            s.rateShop(shopName, rating);
            System.out.println(REVIEW_REGISTERED);
        }catch(ShopDoesntExistException | InvalidRatingException e){
            System.out.println(e.getMessage());
        }
    }










    private static void getUserInfo(SmartCity s, Scanner in){
        System.out.print(USER_ID_P);
        String userID = in.next(); in.nextLine();
        try{
            User u = s.getUserInfo(userID);
            System.out.println(NAME_P + u.getName());
            System.out.println(AGE_P + u.getAge());
            System.out.println(TIP_COUNT_P + u.getNumOfTips());
            System.out.println(LEVEL_P + u.getLevel());
        }catch(UserDoesntExistException e){
            System.out.println(e.getMessage());
        }
    }

    //Listings
    private static void getTipsByUser(SmartCity s, Scanner in){
        System.out.print(USER_ID_P);
        String userID = in.next(); in.nextLine();
        try{
            Iterator<Tip> it = s.getTipsByUser(userID);
            int tipNum = 1;
            while(it.hasNext()){
                Tip t = it.next();
                System.out.println(tipNum++ +". " + t.getShop().getName());
                System.out.println(ADDRESS_P + t.getAddress());
                System.out.println(DESCRIPTION_P + t.getDescription());
            }
        }catch(UserDoesntExistException | UserHasNoTipsException e){
            System.out.println(e.getMessage());
        }
    }

    private static void getTipsByStreet(SmartCity s, Scanner in){
        System.out.print(STREET_NAME_P);
        String address = in.nextLine();
        try{
            Iterator<Tip> it = s.getTipsByStreet(address);
            int tipNum = 1;
            while(it.hasNext()){
                Tip t = it.next();
                System.out.println(tipNum++ +". " + t.getShop().getName());
                System.out.println(ADDRESS_P + t.getAddress());
                System.out.println(DESCRIPTION_P + t.getDescription());
            }
        }catch(NoTipsForThatStreetException e){
            System.out.println(e.getMessage());
        }
    }


    private static void getTipsByShop(SmartCity s, Scanner in){
        System.out.print(SHOP_NAME_P);
        String shopName = in.nextLine();
        try{
            Iterator<Tip> it = s.getTipsByShop(shopName);
            int tipNum = 1;
            while(it.hasNext()){
                Tip t = it.next();
                System.out.println(tipNum++ +". " + t.getShop().getName());
                System.out.println(ADDRESS_P + t.getAddress());
                System.out.println(DESCRIPTION_P + t.getDescription());
            }
        }catch(NoTipsForThatShopException e){
            System.out.println(e.getMessage());
        }
    }

    private static void listUsers(SmartCity s){
        Iterator<User> it = s.listUsers();
        while (it.hasNext()){
            User u = it.next();
            int i = u.getType();
            switch (i) {
                case 1 -> System.out.println(u.getName() + ", " + u.getAge() + ", " + "ADMIN" +
                        ", " + TIP_COUNT_P + u.getNumOfTips() + ", " + LEVEL_P + u.getLevel() + "\n");
                case 2 -> System.out.println(u.getName() + ", " + u.getAge() + ", " + "REGULAR" +
                        ", " + TIP_COUNT_P + u.getNumOfTips() + ", " + LEVEL_P + u.getLevel() + "\n");
            }
        }
    }

    private static void listAllTips(SmartCity s){
        Iterator<Tip> it = s.listAllTips();
        int tipNum = 1;
        while (it.hasNext()){
            Tip t = it.next();
            System.out.println(tipNum++ +". " + SHOP_NAME_P + t.getShop().getName());
            System.out.println(ADDRESS_P + t.getAddress());
            System.out.println(DESCRIPTION_P + t.getDescription());
        }
    }

    //fluff
    private static void welcome() {
        System.out.println(TEXT_PURPLE + WELCOME + TEXT_RESET);
        delayPrints(1);
        System.out.println(TEXT_PURPLE + AUTHOR + TEXT_RESET + "\n");
        delayPrints(1);
        System.out.println(TEXT_RED + BOOTING_SERVICE + TEXT_RESET + "\n");
        delayPrints(2);
        System.out.println(TEXT_GREEN + PRINTING_MENU + TEXT_RESET);
        delayPrints(2);
        menu();
        System.out.println();
    }

    private static void delayPrints(long delay){
        try{
            TimeUnit.SECONDS.sleep(delay);
        }catch (InterruptedException e){
            System.out.println(BLUE_SCREEN);
        }
    }
}
