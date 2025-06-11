import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        java.util.Scanner scan = new Scanner(System.in);
        int choice;

        // Main Menu
        System.out.println("========================================================================");
        System.out.println("| 1. Add Pokemon            5. View Moves           9. Add Trainer     |");
        System.out.println("| 2. View All Pokemon       6. Search Moves        10. View Trainers   |");
        System.out.println("| 3. Search Pokemon         7. View Items          11. Search Trainers |");
        System.out.println("| 4. Add Move               8. Search Items        12. Exit            |");
        System.out.println("========================================================================");

        while (true) {
            System.out.print("Enter choice: ");

            if (scan.hasNextInt()) {
                choice = scan.nextInt();

                if (choice < 1 || choice > 12) {
                    System.out.println("Incorrect input. Please try again!");
                } else {
                    switch (choice) {
                        case 1:
                            // addPokemon()
                            break;
                        case 2:
                            // viewAllPokemon()
                            break;
                        case 3:
                            // searchPokemon()
                            break;
                        case 4:
                            // addMove()
                            break;
                        case 5:
                            // viewMoves()
                            break;
                        case 6:
                            // searchMoves()
                            break;
                        case 7:
                            // viewItems()
                            break;
                        case 8:
                            // searchItems()
                            break;
                        case 9:
                            // addTrainer()
                            break;
                        case 10:
                            // viewTrainers()
                            break;
                        case 11:
                            // searchTrainers()
                            break;
                        case 12:
                            System.out.println("Exiting program. . .");
                            scan.close();
                            System.exit(0);
                    }
                }
            } else {
                System.out.println("Invalid input. Please enter a number!");
                scan.next();
            }
        }
    }
}
