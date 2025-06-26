import java.util.Scanner;

/**
 * The Main class serves as the entry point for the Pokémon Database application.
 * It presents a menu to the user and allows interaction with various managers
 * to handle Pokémon, Moves, and Items. Each manager provides options to add,
 * view, or search their respective data.
 */
public class Main {
    /**
     * The main method runs the application, continuously prompting the user
     * for menu selections and executing corresponding actions.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        java.util.Scanner scan = new Scanner(System.in);
        int choice;


        // Initialize Managers
        PokemonManager pokemonManager = new PokemonManager();
        Items itemManager = new Items(scan);
        MoveManager moveManager = new MoveManager(scan);
        moveManager.loadDefaultMoves();

        while (true) {
            // Display Main Menu
            System.out.printf("\n%45s%n", "Pokémon Database Menu");
            System.out.println("========================================================================");
            System.out.println("| 1. Add Pokemon            4. Add Move            7. View Items       |");
            System.out.println("| 2. View All Pokemon       5. View Moves          8. Search Items     |");
            System.out.println("| 3. Search Pokemon         6. Search Moves        9. Exit             |");
            System.out.println("========================================================================");

            System.out.print("Enter choice: ");

            // Ensure valid integer input
            if (scan.hasNextInt()) {
                choice = scan.nextInt();
                scan.nextLine();

                if (choice < 1 || choice > 12) {
                    System.out.println("Incorrect input. Please try again!");
                } else {
                    switch (choice) {
                        case 1:
                            pokemonManager.addPokemon();
                            break;
                        case 2:
                            pokemonManager.displayAllPokemons();
                            break;
                        case 3:
                            pokemonManager.handlePokemonSearch();
                            break;
                        case 4:
                            moveManager.addMove();
                            break;
                        case 5:
                            moveManager.viewAllMovesAvailable();
                            break;
                        case 6:
                            moveManager.handleMoveSearch();
                            break;
                        case 7:
                            itemManager.viewAllItemsAvailable();
                            break;
                        case 8:
                            itemManager.handleItemSearch();
                            break;
                        case 9:
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