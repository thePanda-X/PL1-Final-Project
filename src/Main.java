/*
 *      code by: Alejandro Asensi Calatayud
 *      date: 03/11/23
 *      project: PL1 Standings table assignment
 */

import java.util.Scanner;

/*
* this script is responsible for showing the main menu and creating the standingManager.
* it will also handle the user input and will call the necessary methods.
*/
public class Main {
    public static void main(String[] args) {

        boolean exit = false; // initialize the exit flag
        StandingsManager sm = new StandingsManager();

        while (!exit) {
            int opt = menu();

            switch (opt) {
                case 1:
                    sm.addScores();
                    break;
                case 2:
                    // TODO: Add the full print results feature.
                    sm.showResults();
                    break;
                case 3:
                    System.out.println("exiting the program...");
                    // TODO: Save scores to a file once the program is exited.
                    exit = true; // if the user has decided to exit set exit flag to true.
                    break;
            }
        }

    }

    static int menu() {
        System.out.println("\n\n\n\n");
        int option = 0; // this is the option that the user will select
        boolean valid = false; // boolean to check if the input is a valid one.

        do {
            try {
                // show the user the menu.
                System.out.println("-------------------------");
                System.out.println("|  1. add scores        |");
                System.out.println("|  2. print standings   |");
                System.out.println("|  3. exit program      |");
                System.out.println("-------------------------");

                System.out.println();

                System.out.print("Choose an option [1-3]: ");
                Scanner sc = new Scanner(System.in); // create scanner to read user input from system.in
                option = sc.nextInt(); // read input

                valid = true; // input was read properly

                if (option < 1 || option > 3) {
                    System.out.println("\nPlease input a valid number. [1-3]\n");
                    valid = false; // the number was out of range.
                }

            } catch (Exception e) {

                // if we enter this block the user input was not an integer
                System.out.println("\nPlease input a valid number. [1-3]\n");
                valid = false;
            }

        } while (!valid);

        return option;
    }
}