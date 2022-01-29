import SmartCity.Exceptions.*;
import SmartCity.SmartCity;
import Tip.Tip;
import User.User;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author dannythe21st on github
 * Project start: 27/01/22
 * Project end: ______
 */

public class Main {

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


    //Outputs
    private static final String BYE = "Goodbye :D";
    private static final String USER_REGISTRATION = "Registration Complete!";
    private static final String USER_REMOVED = "User removed!";
    private static final String TIP_REMOVED = "Tip removed!";
    private static final String UNKNOWN_COMMAND = "Unknown command.";


    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        SmartCity s = new SmartCity();
        System.out.print(PROMPT);
        String comm = in.nextLine().toLowerCase();
        while (!comm.equals(EXIT)) {
            switch (comm) {
                case MENU:
                    helpCommandList();
                    break;
                case ADD_USER:
                    addUser(s, in);
                    break;
                case REMOVE_USER:
                    removeUser(s, in);
                    break;
                case ADD_TIP:
                    addTip(s, in);
                    break;
                case REMOVE_TIP:
                    removeTip(s, in);
                    break;
                case GET_TIPS_BY_USER:
                    getTipsByUser(s, in);
                    break;
                case GET_TIPS_BY_STREET:
                    getTipsByStreet(s, in);
                    break;
                case GET_TIPS_BY_SHOP:
                    getTipsByShop(s, in);
                    break;
                default:
                    System.out.println(UNKNOWN_COMMAND);
            }
            System.out.println();
            System.out.print(PROMPT);
            comm = in.nextLine().toLowerCase();
        }
        System.out.println();
        System.out.print(BYE);
        in.close();
    }

    private static void helpCommandList() {
        System.out.println(EXIT);
        System.out.println(MENU);
        System.out.println(ADD_USER);
        System.out.println(REMOVE_USER);
        System.out.println(ADD_TIP);
        System.out.println(REMOVE_TIP);
        System.out.println(GET_TIPS_BY_USER);
        System.out.println(GET_TIPS_BY_STREET);
        System.out.println(GET_TIPS_BY_SHOP);
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

        System.out.print("Description: ");
        String description = in.nextLine();

        try{
            s.addTip(userID, tipID, shopName, description);
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
        System.out.println("User ID: ");
        String userID = in.next();
        try{
            Iterator<Tip> it = s.getTipsByUser(userID);
            int tipNum = 1;
            while(it.hasNext()){
                System.out.print(tipNum++ +". " + it.next().getShop());
                System.out.print("  " + it.next().getDescription());
            }
        }catch(UserDoesntExistException | UserHasNoTipsException e){
            System.out.println(e.getMessage());
        }
    }

    private static void getTipsByStreet(SmartCity s, Scanner in){
        System.out.println("Street name: ");
        String address = in.nextLine();
        try{
            Iterator<Tip> it = s.getTipsByStreet(address);
            int tipNum = 1;
            while(it.hasNext()){
                System.out.print(tipNum++ +". " + it.next().getShop());
                System.out.print("  " + it.next().getDescription());
            }
        }catch(NoTipsForThatStreetException e){
            System.out.println(e.getMessage());
        }
    }


    private static void getTipsByShop(SmartCity s, Scanner in){
        System.out.println("Shop name: ");
        String shopName = in.nextLine();
        try{
            Iterator<Tip> it = s.getTipsByShop(shopName);
            int tipNum = 1;
            while(it.hasNext()){
                System.out.print(tipNum++ +". " + it.next().getShop());
                System.out.print("  " + it.next().getDescription());
            }
        }catch(NoTipsForThatStreetException e){
            System.out.println(e.getMessage());
        }
    }

}
