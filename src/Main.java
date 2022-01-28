import SmartCity.Exceptions.UserAlreadyExistsException;
import SmartCity.SmartCity;
import User.User;

import java.util.Scanner;

/**
 * @author dannythe21st on github
 * Project start: 27/01/22
 * Project end: ______
 */

public class Main {

    //Comms
    private static final String EXIT = "exit";
    private static final String HELP = "help";
    private static final String ADD_USER = "addUser";
    private static final String REMOVE_USER = "removeUser";
    private static final String ADD_TIP = "addTip";



    //Outputs
    private static final String BYE = "Goodbye";
    private static final String USER_REGISTRATION = "Registration Complete!";
    private static final String UNKNOWN_COMMAND = "Unknown command.";


    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        SmartCity s = new SmartCity();
        String comm = in.nextLine().toLowerCase();
        while (!comm.equals(EXIT)) {
            switch (comm) {
                case HELP:
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
                default:
                    System.out.println(UNKNOWN_COMMAND);
            }
            comm = in.nextLine().toLowerCase();
        }
        System.out.println(BYE);
        in.close();
    }

    private static void helpCommandList() {
        System.out.println(EXIT);
        System.out.println(HELP);
        System.out.println(ADD_USER);
        System.out.println(REMOVE_USER);
        System.out.println(ADD_TIP);
        System.out.println(ADD_USER);
        System.out.println(ADD_USER);
    }

    private static void addUser(SmartCity s, Scanner in) {
        System.out.print("ID: ");
        String id = in.next();
        System.out.println();

        System.out.print("Name: ");
        String name = in.nextLine().trim();
        System.out.println();

        System.out.print("Age: ");
        int age = in.nextInt();
        System.out.println();

        System.out.print("Type (Admin|Regular): ");
        String type = in.next();
        System.out.println();

        try{
            User u = new User(id, name, age, type);
            s.addUser(u);
            System.out.println(USER_REGISTRATION);
        }catch(UserAlreadyExistsException e){
            System.out.println(e.getMessage());
        }
    }

    private static void removeUser(SmartCity s, Scanner in){

    }

    private static void addTip(SmartCity s, Scanner in){

    }


}
