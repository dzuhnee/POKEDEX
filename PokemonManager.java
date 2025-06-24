import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class PokemonManager {
    Scanner scan = new Scanner(System.in);
    List<Pokemon> pokemons = new ArrayList<>();
    Items items = new Items(scan);

    public boolean addPokemon() {
        // Variables to store data temporarily
        int pokedexNumber;
        String name;
        String primaryType = null;
        String secondaryType = null;
        int baseLevel;
        int evolvesFrom, evolvesTo, evolutionLevel;
        // List<Move> moveSet;
        Item heldItem;
        int hp, defense, attack, speed;

        // Get user input
        System.out.printf("\n--- Add Pokémon ---\n");
        System.out.println("");

        // Name
        name = readValidString(scan, "name", "[A-Za-z\\s]+");

        // Pokedex Number
        pokedexNumber = readValidInt(scan, "pokedex number");
        // for (Pokemon p : pokemons) {
        //     if (p.getPokedexNumber() == pokedexNumber) {

        //     }
        // }

        // Type 1
        primaryType = readValidString(scan, "primary type", "[A-Za-z\\s]+");

        // If (there's a type 2)
        // Type 2
        System.out.print("Does " + name + " has a secondary type? [Y/N]: ");
        String choice = scan.nextLine();
        if (choice.equalsIgnoreCase("Y")) {
            secondaryType = readValidString(scan, "secondary type", "[A-Za-z\\s]+");
        }

        // Base Level
        baseLevel = readValidInt(scan, "base level");

        // Evolve From
        evolvesFrom = readValidInt(scan, "evolves from");

        // Evolve To
        evolvesTo = readValidInt(scan, "evolves to");

        // Evolution Level
        evolutionLevel = readValidInt(scan, "evolution level");

        // Held Item
        String itemName = readValidString(scan, "held item (or enter 'none')", "[A-Za-z\\s]+");
        if (!itemName.equalsIgnoreCase("none")) {
            heldItem = items.findItem(itemName);

            if (heldItem == null) {
                System.out.println("Item is not on the list. Pokémon will hold nothing.");
            }
        }

        // Base Stats
        System.out.println("Enter the base stats:");
        hp = readValidInt(scan, "HP");
        attack = readValidInt(scan, "Attack");
        defense = readValidInt(scan, "Defense");
        speed = readValidInt(scan, "Speed");

        // Instantiate
        Pokemon pokemon;

        // if there are two types
        if (secondaryType != null) {
            pokemon = new Pokemon(pokedexNumber, name, primaryType, secondaryType, baseLevel, evolvesFrom, evolvesTo,
                    evolutionLevel, baseLevel, attack, defense, speed);
        } else {
            pokemon = new Pokemon(pokedexNumber, name, primaryType, baseLevel, evolvesFrom, evolvesTo, evolutionLevel,
                    baseLevel, attack, defense, speed);
        }

        System.out.print(name + " is ready to join! Add to your Pokémon [Y/N]: ");
        String c = scan.nextLine();
        if (c.equalsIgnoreCase("Y")) {
            pokemons.add(pokemon);
            System.out.println("Pokemon \"" + name + "\" added successfully!");
            System.out.println("");
            return true;
        }
        return false;
    }

    public void displayAllPokemons() {
        if (pokemons.isEmpty()) {
            System.out.println("No Pokémon in the database.");
            return;
        }

        divider();
        System.out.printf("%-4s %-12s %-15s %-9s %-15s %-30s\n", "#", "Name", "Type(s)", "Base Lv", "Held Item",
                "Moves");
        divider();
        for (Pokemon p : pokemons) {
            p.display();
        }

        System.out.println("");
    }

    public void searchByName(String s) {
        boolean isFound = false;

        for (Pokemon p : pokemons) {
            if (p.getName().toLowerCase().contains(s.toLowerCase())) {
                divider();
                System.out.printf("%-4s %-12s %-15s %-9s %-15s %-30s\n", "#", "Name", "Type(s)", "Base Lv", "Held Item",
                        "Moves");
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

    public void searchByType(String s) {
        boolean matchesPrimary = false;
        boolean matchesSecondary = false;

        for (Pokemon p : pokemons) {
            if (p.getPrimaryType().toLowerCase().contains(s.toLowerCase())
                    || p.getSecondaryType().toLowerCase().contains(s.toLowerCase())) {
                divider();
                System.out.printf("%-4s %-12s %-15s %-9s %-15s %-30s\n", "#", "Name", "Type(s)", "Base Lv", "Held Item",
                        "Moves");
                divider();
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

    public void searchByPokedexNumber(int n) {
        boolean isFound = false;
        for (Pokemon p : pokemons) {
            if (p.getPokedexNumber() == n) {
                divider();
                System.out.printf("%-4s %-12s %-15s %-9s %-15s %-30s\n", "#", "Name", "Type(s)", "Base Lv", "Held Item",
                        "Moves");
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

    public void handlePokemonSearch() {
        System.out.println("\n--- Search Pokémon ---");
        System.out.println("1. By Name");
        System.out.println("2. By Type");
        System.out.println("3. By Pokedex Number");
        System.out.print("Enter option: ");

        String option = scan.nextLine();

        switch (option) {
            case "1":
                System.out.print("Enter name: ");
                String name = scan.nextLine().trim();
                searchByName(name);
                break;
            case "2":
                System.out.print("Enter type (e.g., Fire, Water): ");
                String type = scan.nextLine().trim();
                searchByType(type);
                break;
            case "3":
                System.out.print("Enter pokedex number: ");
                int num = scan.nextInt();
                searchByPokedexNumber(num);
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    // Helper function for string input
    public static String readValidString(Scanner scan, String attribute, String regex) {
        String input;

        while (true) {
            System.out.print("Enter " + attribute + ": ");
            input = scan.nextLine().trim();

            if (!input.matches(regex)) {
                System.out.println("Invalid input. Please try again!");
            } else {
                return input;
            }
        }
    }

    // Helper function for int input
    private static int readValidInt(Scanner scan, String attribute) {
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

    private static void divider() {
        System.out.println("--------------------------------------------------------------------------------------");
    }

}
