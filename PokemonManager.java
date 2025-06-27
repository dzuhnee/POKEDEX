import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class manages a list of Pokémon and allows adding, displaying, and
 * searching Pokémon.
 */
public class PokemonManager {
    final Scanner scan = new Scanner(System.in);
    final List<Pokemon> pokemons = new ArrayList<>();

    /**
     * Prompts the user to enter all necessary data to add a new Pokémon.
     * Validates input formats and adds the Pokémon to the list once confirmed.
     *
     * @return true if the Pokémon was successfully added, false otherwise
     */
    public boolean addPokemon() {
        // Variables to store data temporarily
        String pokedexNumber;
        String name;
        String primaryType = null;
        String secondaryType = null;
        int hp, defense, attack, speed;
        String evolvesFrom, evolvesTo;

        System.out.printf("\n--- Add Pokémon ---\n\n");

        // Pokedex Number
        do {
            pokedexNumber = readValidString(scan,
                    "Pokédex number (format as 4 digits with leading zeros, e.g., 0001)", "^\\d{4}$");
        } while (!isValidDexNumber(pokedexNumber));

        // Name
        name = readValidString(scan, "name", "[A-Za-z\\s]+");

        // Type 1
        do {
            primaryType = readValidString(scan, "primary type", "[A-Za-z\\s]+");
        } while (!TypeUtils.isValidType(primaryType));

        // Type 2
        System.out.print("Does " + name + " has a secondary type? [Y/N]: ");
        String choice;
        do {
            choice = scan.nextLine();
            if (!choice.equalsIgnoreCase("Y") && !choice.equalsIgnoreCase("N")) {
                System.out.print("Invalid input. Please try again: ");
            }
        } while (!choice.equalsIgnoreCase("Y") && !choice.equalsIgnoreCase("N"));
        if (choice.equalsIgnoreCase("Y")) {
            do {
                secondaryType = readValidString(scan, "secondary type", "[A-Za-z\\s]+");
            } while (!TypeUtils.isValidType(secondaryType));
        }

        // Evolves from
        do {
            evolvesFrom = readValidString(scan, "dex number that Pokémon evolves from", "^\\d{4}$");
        } while (!isValidDexNumber(evolvesFrom));

        // Evolves to
        do {
            evolvesTo = readValidString(scan, "dex number that Pokémon evolves to", "^\\d{4}$");
        } while (!isValidDexNumber(evolvesTo));

        // Base Stats
        System.out.printf("\nEnter the Base Stats\n");
        hp = PokemonBaseStats.readValidBaseStat(scan, "HP");
        attack = PokemonBaseStats.readValidBaseStat(scan, "Attack");
        defense = PokemonBaseStats.readValidBaseStat(scan, "Defense");
        speed = PokemonBaseStats.readValidBaseStat(scan, "Speed");

        // Instantiate Pokémon
        Pokemon pokemon;
        if (secondaryType != null) {
            pokemon = new Pokemon(pokedexNumber, name, primaryType, secondaryType, 0, null, null,
                    0, hp, attack, defense, speed);
        } else {
            pokemon = new Pokemon(pokedexNumber, name, primaryType, 0, null, null, 0,
                    hp, attack, defense, speed);
        }

        // Confirmation
        System.out.printf("\n" + name + " is ready to join! Add to your Pokémon [Y/N]: ");
        String c;
        do {
            c = scan.nextLine();
            if (!c.equalsIgnoreCase("Y") && !c.equalsIgnoreCase("N")) {
                System.out.print("Invalid input. Please try again: ");
            }
        } while (!c.equalsIgnoreCase("Y") && !c.equalsIgnoreCase("N"));

        if (c.equalsIgnoreCase("Y")) {
            pokemons.add(pokemon);
            System.out.println("Pokémon \"" + name + "\" added successfully!");
            System.out.println("");
            return true;
        }
        return false;
    }

    /**
     * Displays all Pokémon currently stored in the system.
     */
    public void displayAllPokemons() {
        System.out.printf("\n--- View All Pokémon ---\n\n");

        if (pokemons.isEmpty()) {
            System.out.println("No Pokémon in the database.");
            return;
        }

        divider();
        header();
        divider();

        for (Pokemon p : pokemons) {
            p.display();
        }

        System.out.println("");
    }

    /**
     * Searches the Pokémon list for any Pokémon whose name contains the given
     * string.
     *
     * @param s the name or part of a name to search for
     */
    public void searchByName(String s) {
        boolean isFound = false;

        for (Pokemon p : pokemons) {
            if (p.getName().toLowerCase().contains(s.toLowerCase())) {
                divider();
                header();
                divider();
                p.display();
                isFound = true;
            }
        }

        if (!isFound) {
            System.out.println("No Pokémon matched your search.");
        }

        System.out.println("");
    }

    /**
     * Searches the Pokémon list by type (either primary or secondary).
     *
     * @param s the type name to search for
     */
    public void searchByType(String s) {
        boolean matchesPrimary = false;
        boolean matchesSecondary = false;

        divider();
        header();
        divider();

        for (Pokemon p : pokemons) {
            if (p.getPrimaryType().toLowerCase().contains(s.toLowerCase())
                    || p.getSecondaryType().toLowerCase().contains(s.toLowerCase())) {
                p.display();
                matchesPrimary = true;
                matchesSecondary = true;
            }
        }

        if (!matchesPrimary || !matchesSecondary) {
            System.out.println("No Pokémon matched your search.");
        }

        System.out.println("");
    }

    /**
     * Searches the Pokémon list by Pokédex number.
     *
     * @param n the Pokédex number to search for (must be 4 digits)
     */
    public void searchByPokedexNumber(String n) {
        boolean isFound = false;

        divider();
        header();
        divider();

        for (Pokemon p : pokemons) {
            if (p.getPokedexNumber().equalsIgnoreCase(n)) {
                p.display();
                isFound = true;
            }
        }

        if (!isFound) {
            System.out.println("No Pokémon matched your search.");
        }

        System.out.println("");
    }

    /**
     * Handles the Pokémon search menu and lets the user choose how to search.
     */
    public void handlePokemonSearch() {
        System.out.println("\n--- Search Pokémon ---");
        System.out.println("1. By Name");
        System.out.println("2. By Type");
        System.out.println("3. By Pokédex Number");
        System.out.print("Enter option: ");

        String option = scan.nextLine();

        System.out.println("");
        switch (option) {
            case "1":
                String name = readValidString(scan, "name", "[A-Za-z\\s]+");
                searchByName(name);
                break;
            case "2":
                String type = readValidString(scan, "type", "[A-Za-z\\s]+");
                searchByType(type);
                break;
            case "3":
                String num = readValidString(scan, "Pokédex number (must be 4 digits)", "^\\d{4}$");
                searchByPokedexNumber(num);
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    /**
     * Validates that the given Pokédex number is in the correct format and range
     * (0001 to 1010).
     *
     * @param pokedexNumber the number to validate
     * @return {@code true} if valid; {@code false} otherwise
     */
    private static boolean isValidDexNumber(String pokedexNumber) {
        // Checks if input is unique
        if (pokedexNumber.matches("\\d{4}")) {
            int number = Integer.parseInt(pokedexNumber);
            return number >= 1 && number <= 1010;
        }
        return false;
    }

    /**
     * Reads a valid string from the user that matches the given regex pattern.
     *
     * @param scan      the Scanner object to read input
     * @param attribute the name of the attribute to display in the prompt
     * @param regex     the regular expression pattern to match
     * @return a valid string input from the user
     */
    public static String readValidString(Scanner scan, String attribute, String regex) {
        String input;

        while (true) {
            System.out.print("Enter " + attribute + ": ");
            input = scan.nextLine().trim();

            if (!input.matches(regex) || input.isEmpty()) {
                System.out.println("Input is invalid or empty. Please try again!");
            } else {
                return input;
            }
        }
    }

    /**
     * Reads a valid integer from the user.
     *
     * @param scan      the Scanner object to read input
     * @param attribute the name of the attribute to display in the prompt
     * @return a valid integer input from the user
     */
    public static int readValidInt(Scanner scan, String attribute) {
        int input;

        while (true) {
            System.out.print("Enter " + attribute + ": ");

            try {
                input = Integer.parseInt(scan.nextLine());
                return input;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    /**
     * Prints a divider line for formatting.
     */
    private static void divider() {
        System.out.println(
                "------------------------------------------------------------------------------");
    }

    /**
     * Prints the header row for Pokémon display output.
     */
    private static void header() {
        System.out.printf("%-6s %-12s %-15s %-7s %-5s %-7s %-8s %-6s\n",
                "#", "Name", "Type(s)", "Total", "HP", "Attack", "Defense", "Speed");
    }    

}
