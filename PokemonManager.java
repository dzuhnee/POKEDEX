import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class manages a list of Pokémon and allows adding, displaying, and
 * searching Pokémon.
 */
public class PokemonManager {
    private final Scanner scan = new Scanner(System.in);
    private final List<Pokemon> pokemons = new ArrayList<>();

    Pokemon pokemon1 = new Pokemon(1, "Bulbasaur", "Grass", "Poison", 0, 0, 0, 0, 45, 49, 49, 45);
    Pokemon pokemon2 = new Pokemon(12, "Butterfree", "Bug", "Flying", 0, 0, 0, 0, 60, 45, 50, 70);
    Pokemon pokemon3 = new Pokemon(25, "Pikachu", "Electric", null, 0, 0, 0, 0, 35, 55, 40, 90);

    /**
     * Populates the Pokémon list with predefined initial Pokémon.
     * 
     * This method adds predefined Pokémon objects (e.g., {@code pokemon1},
     * {@code pokemon2}, {@code pokemon3}) to the internal Pokémon list
     * for initial setup or testing purposes.
     */
    public void populateInitialPokemon() {
        pokemons.add(pokemon1);
        pokemons.add(pokemon2);
        pokemons.add(pokemon3);
    }

    /**
     * Prompts the user to enter all necessary data to add a new Pokémon.
     * Validates input formats and adds the Pokémon to the list once confirmed.
     *
     * @return true if the Pokémon was successfully added, false otherwise
     */
    public boolean addPokemon() {
        // Variables to store data temporarily
        int pokedexNumber;
        String name;
        String primaryType = null;
        String secondaryType = null;
        int hp, defense, attack, speed;
        int evolvesFrom, evolvesTo;
        int baseLevel, evolutionLevel;

        System.out.printf("\n--- Add Pokémon ---\n\n");

        // Pokédex Number
        do {
            pokedexNumber = readValidInt(scan, "Pokédex number (1-1010)");

            if (!isValidDexNumber(pokedexNumber)) {
                System.out.println("Pokedex already exists or invalid input. Please try again.");
            }
        } while (!isValidDexNumber(pokedexNumber));

        // Name - Pokémon name
        name = readValidString(scan, "name", "[A-Za-z\\s]+");

        // Type 1 - Pokémon's primary type
        System.out.println("--------------------- CHOOSE TYPE FROM ---------------------------");
        System.out.println("Normal      Fire       Water     Electric      Grass        Ice");
        System.out.println("Fighting    Poison     Ground    Flying        Psychic      Bug");
        System.out.println("Rock        Ghost      Dragon    Dark          Steel        Fairy");
        System.out.println("------------------------------------------------------------------");
        do {
            primaryType = readValidString(scan, "primary type", "[A-Za-z\\s]+");
        } while (!TypeUtils.isValidType(primaryType));

        // Type 2 - Pokémon's secondary type
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

        // Base Level
        baseLevel = readValidInt(scan, "base level");

        // Evolves from - the Pokémon that the current Pokémon transformed from
        do {
            evolvesFrom = readValidInt(scan, "dex number that Pokémon evolves from");
        } while (!isValidDexNumber(evolvesFrom));

        // Evolves to - the Pokémon that the current Pokémon will transform into
        do {
            evolvesTo = readValidInt(scan, "dex number that Pokémon evolves to");
        } while (!isValidDexNumber(evolvesTo));

        // Evolution Level - specific level a Pokémon must reach to evolve into its next form
        do {
            evolutionLevel = readValidInt(scan, "evolution level");
        } while (!isValidDexNumber(evolutionLevel));

        // Base Stats
        System.out.printf("\nEnter the Base Stats\n");
        hp = PokemonBaseStats.readValidBaseStat(scan, "HP");
        attack = PokemonBaseStats.readValidBaseStat(scan, "Attack");
        defense = PokemonBaseStats.readValidBaseStat(scan, "Defense");
        speed = PokemonBaseStats.readValidBaseStat(scan, "Speed");

        // Instantiate Pokémon
        Pokemon pokemon;
        if (secondaryType != null) {
            pokemon = new Pokemon(pokedexNumber, name, primaryType, secondaryType, 0, 0, 0,
                    0, hp, attack, defense, speed);
        } else {
            pokemon = new Pokemon(pokedexNumber, name, primaryType, 0, 0, 0, 0,
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
    public void displayAllPokemon() {
        System.out.printf("\n--- View All Pokémon ---\n\n");

        if (pokemons.isEmpty()) {
            System.out.println("No Pokémon in the database.");
            return;
        }

        divider();
        header();
        divider();

        for (Pokemon p : pokemons) {
            p.displayInfo();
        }

        System.out.println("");
    }

    public Pokemon getPokemonByDex(int pokedexNumber) {
        for (Pokemon p : pokemons) {
            if (p.getPokedexNumber() == pokedexNumber) {
                return p;
            }
        }
        return null;
    }

    public String getNameByDex(int pokedexNumber) {
        Pokemon p = getPokemonByDex(pokedexNumber);
        if (p != null) {
            return p.getName();
        }
        return null;
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
                p.displayInfo();
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
        boolean foundMatch = false;
    
        divider();
        header();
        divider();
    
        for (Pokemon p : pokemons) {
            String primary = p.getPrimaryType();
            String secondary = p.getSecondaryType();
    
            if ((primary != null && primary.toLowerCase().contains(s.toLowerCase())) ||
                (secondary != null && secondary.toLowerCase().contains(s.toLowerCase()))) {
                p.displayInfo();
                foundMatch = true;
            }
        }
    
        if (!foundMatch) {
            System.out.println("No Pokémon matched your search.");
        }
    
        System.out.println();
    }    

    /**
     * Searches the Pokémon list by Pokédex number.
     *
     * @param n the Pokédex number to search for (must be 4 digits)
     */
    public void searchByPokedexNumber(int n) {
        boolean isFound = false;

        divider();
        header();
        divider();

        for (Pokemon p : pokemons) {
            if (p.getPokedexNumber() == n) {
                p.displayInfo();
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
                int num = readValidInt(scan, "Pokédex number");
                searchByPokedexNumber(num);
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    /**
     * Validates that the given Pokédex number is in the correct range (0001 to
     * 1010).
     *
     * @param pokedexNumber the number to validate
     * @return {@code true} if valid; {@code false} otherwise
     */
    private boolean isValidDexNumber(int pokedexNumber) {
        if (pokedexNumber <= 1 || pokedexNumber >= 1010) {
            return false;
        }
    
        for (Pokemon p : pokemons) {
            if (p.getPokedexNumber() == pokedexNumber) {
                return false;
            }
        }
    
        return true;
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
        System.out.printf("%-4s %-12s %-15s %-7s %-5s %-7s %-8s %-6s\n",
                "#", "Name", "Type(s)", "Total", "HP", "Attack", "Defense", "Speed");
    }

}
