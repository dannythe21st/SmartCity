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
    private static final String ADD_USER = "add_user";
    private static final String BYE = "Goodbye";


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
                    addUser();
                    break;
            }
            comm = in.nextLine().toLowerCase();
        }
        System.out.println(BYE);
        in.close();
    }

    private static void helpCommandList() {

    }

    private static void addUser() {

    }


}
