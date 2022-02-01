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

    //Comms
    private static final String PROMPT = "> ";
    private static final String EXIT = "exit";
    private static final String MENU = "menu";
    private static final String ADD_USER = "adduser";
    private static final String REMOVE_USER = "removeuser";
    private static final String ADD_TIP = "addtip";
    private static final String REMOVE_TIP = "removetip";
    private static final String GET_TIPS_BY_USER = "tipbyuser";
    private static final String GET_TIPS_BY_STREET = "tipbystreet";
    private static final String GET_TIPS_BY_SHOP = "tipbyshop";
    private static final String LIST_USERS = "listusers";
    private static final String USER_INFO = "userinfo";
    private static final String LIST_ALL_TIPS = "listalltips";


    //Outputs

    private static final String WELCOME = "WELCOME TO SMART CITY :D";
    private static final String AUTHOR = "powered by dannythe21st.";
    private static final String BOOTING_SERVICE = "Booting system...";
    private static final String PRINTING_MENU = "Menu:";
    private static final String BLUE_SCREEN = "Something went wrong :c";
    private static final String BYE = "See you soon.";
    private static final String USER_REGISTRATION = "Registration Complete!";
    private static final String USER_REMOVED = "User removed!";
    private static final String TIP_REMOVED = "Tip removed!";
    private static final String TIP_REGISTERED = "Tip registered!";
    private static final String UNKNOWN_COMMAND = "Unknown command.";


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

    private static void menu() {
        System.out.println(EXIT);
        System.out.println(MENU);
        System.out.println(ADD_USER);
        System.out.println(REMOVE_USER);
        System.out.println(ADD_TIP);
        System.out.println(REMOVE_TIP);
        System.out.println(GET_TIPS_BY_USER);
        System.out.println(GET_TIPS_BY_STREET);
        System.out.println(GET_TIPS_BY_SHOP);
        System.out.println(USER_INFO);
    }

    private static void addUser(SmartCity s, Scanner in) {
        System.out.print("ID: ");
        String id = in.next(); in.nextLine();

        System.out.print("Name: ");
        String name = in.nextLine().trim();

        System.out.print("Age: ");
        int age = in.nextInt();

        System.out.print("Type (Admin (1) | Regular (2)): ");
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
        System.out.print("Admin ID: ");
        String removerID = in.next(); in.nextLine();

        System.out.print("User ID: ");
        String removedID = in.next(); in.nextLine();
        try{
            s.removeUser(removerID, removedID);
            System.out.println(USER_REMOVED);
        }catch(NotAdminException | UserDoesntExistException e){
            System.out.println(e.getMessage());
        }
    }

    private static void addTip(SmartCity s, Scanner in){
        System.out.print("User ID: ");
        String userID = in.next(); in.nextLine();

        System.out.print("Tip ID: ");
        String tipID = in.next(); in.nextLine();

        System.out.print("Shop: ");
        String shopName = in.nextLine();

        System.out.print("Address: ");
        String address = in.nextLine();

        System.out.print("Description: ");
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
        System.out.print("User ID: ");
        String userID = in.next(); in.nextLine();

        System.out.print("Tip ID: ");
        String tipID = in.next(); in.nextLine();

        try{
            s.removeTip(userID, tipID);
            System.out.println(TIP_REMOVED);
        }catch (UserDoesntExistException | TipDoesntExistException e){
            System.out.println(e.getMessage());
        }
    }

    private static void getTipsByUser(SmartCity s, Scanner in){
        System.out.print("User ID: ");
        String userID = in.next(); in.nextLine();
        try{
            Iterator<Tip> it = s.getTipsByUser(userID);
            int tipNum = 1;
            while(it.hasNext()){
                Tip t = it.next();
                System.out.println(tipNum++ +". " + t.getShop().getName());
                System.out.println("Address: " + t.getAddress());
                System.out.println("Description: " + t.getDescription());
            }
        }catch(UserDoesntExistException | UserHasNoTipsException e){
            System.out.println(e.getMessage());
        }
    }

    private static void getTipsByStreet(SmartCity s, Scanner in){
        System.out.print("Street name: ");
        String address = in.nextLine();
        try{
            Iterator<Tip> it = s.getTipsByStreet(address);
            int tipNum = 1;
            while(it.hasNext()){
                Tip t = it.next();
                System.out.println(tipNum++ +". " + t.getShop().getName());
                System.out.println("Address: " + t.getAddress());
                System.out.println("Description: " + t.getDescription());
            }
        }catch(NoTipsForThatStreetException e){
            System.out.println(e.getMessage());
        }
    }


    private static void getTipsByShop(SmartCity s, Scanner in){
        System.out.print("Shop name: ");
        String shopName = in.nextLine();
        try{
            Iterator<Tip> it = s.getTipsByShop(shopName);
            int tipNum = 1;
            while(it.hasNext()){
                Tip t = it.next();
                System.out.println(tipNum++ +". " + t.getShop().getName());
                System.out.println("Address: " + t.getAddress());
                System.out.println("Description: " + t.getDescription());
            }
        }catch(NoTipsForThatShopException e){
            System.out.println(e.getMessage());
        }
    }

    private static void getUserInfo(SmartCity s, Scanner in){
        System.out.print("User ID: ");
        String userID = in.next(); in.nextLine();
        try{
            User u = s.getUserInfo(userID);
            System.out.println("Name: " + u.getName());
            System.out.println("Age: " + u.getAge());
            System.out.println("Tip count: " + u.getNumOfTips());
            System.out.println("Level: " + u.getLevel());
        }catch(UserDoesntExistException e){
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
                        ", Tip count:" + u.getNumOfTips() + ", Level: " + u.getLevel() + "\n");
                case 2 -> System.out.println(u.getName() + ", " + u.getAge() + ", " + "REGULAR" +
                        ", Tip count:" + u.getNumOfTips() + ", Level: " + u.getLevel() + "\n");
            }
        }
    }

    private static void listAllTips(SmartCity s){
        Iterator<Tip> it = s.listAllTips();
        int tipNum = 1;
        while (it.hasNext()){
            Tip t = it.next();
            System.out.println(tipNum++ +". Shop name: " + t.getShop().getName());
            System.out.println("Address: " + t.getAddress());
            System.out.println("Description: " + t.getDescription());
        }
    }

    private static void delayPrints(long delay){
        try{
            TimeUnit.SECONDS.sleep(delay);
        }catch (InterruptedException e){
            System.out.println(BLUE_SCREEN);
        }
    }
}
