import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class PokemonManager {
    Scanner scan = new Scanner(System.in);
    List<Pokemon> pokemons = new ArrayList<>();
    Items items = new Items(scan);

    public boolean addPokemon() {
        // Variables to store data temporarily
        String pokedexNumber;
        String name;
        String primaryType = null;
        String secondaryType = null;
        int hp, defense, spDefense, attack, spAttack, speed;
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
        spAttack = PokemonBaseStats.readValidBaseStat(scan, "Special Attack");
        spDefense = PokemonBaseStats.readValidBaseStat(scan, "Special Defense");
        speed = PokemonBaseStats.readValidBaseStat(scan, "Speed");

        // Instantiate 
        Pokemon pokemon;
        if (secondaryType != null) {
            pokemon = new Pokemon(pokedexNumber, name, primaryType, secondaryType, 0, 0, 0,
                    0, hp, attack, defense, spAttack, spDefense, speed);
        } else {
            pokemon = new Pokemon(pokedexNumber, name, primaryType, 0, 0, 0, 0,
                    hp, attack, defense, spAttack, spDefense, speed);
        }

        System.out.printf("\n" + name + " is ready to join! Add to your Pokémon [Y/N]: ");      // Confirmation
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

    public void displayAllPokemons() {
        System.out.printf("\n--- View All Pokémon ---\n\n");

        // Check if there is an existing Pokémon
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

    // Helper function to ensure pokedex numbers are unique
    private boolean isValidDexNumber(String pokedexNumber) {
        // Checks if input is unique
        if (pokedexNumber.matches("\\d{4}")) {
            int number = Integer.parseInt(pokedexNumber);
            return number >= 1 && number <= 1010;
        }
        return false;
    }

    // Helper function for string input
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

    // Helper function for int input
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

    private static void divider() {
        System.out.println(
                "--------------------------------------------------------------------------------------------");
    }

    private static void header() {
        System.out.printf("%-6s %-12s %-15s %-7s %-5s %-7s %-8s %-9s %-9s %-6s\n", "#", "Name", "Type(s)", "Total",
                "HP",
                "Attack", "Defense", "Sp.Atk", "Sp.Def", "Speed");
    }

}
