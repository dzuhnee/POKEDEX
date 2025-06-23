import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class PokemonManager {
    Scanner scan = new Scanner(System.in);
    List<Pokemon> pokemons = new ArrayList<>();
    Items items = new Items(scan);

    public boolean addPokemon(MoveManager move, Items item) {
        // Variables to store data temporarily
        int pokedexNumber; 
        String name;
        String primaryType = null;
        String secondaryType = null; 
        int baseLevel;
        int evolvesFrom, evolvesTo, evolutionLevel;
        List<Move> moveSet; 
        Item heldItem;
        int hp, defense, attack, speed;


        // Get user input
        System.out.printf("\n--- ADD POKEMON ---\n");
        System.out.println("");

        
        // Name
        name = readValidString(scan, "name", "[A-Za-z\\s]+");

        // Pokedex Number
        pokedexNumber = readValidInt(scan, "pokedex number");

        // Type 1
        primaryType = readValidString(scan, primaryType, "[A-Za-z\\s]+");
        
        // If (there's a type 2)
        // Type 2
        System.out.print("Does " + name + " has a secondary type? [Y/N]: ");
        String choice = scan.nextLine();
        if (choice.equalsIgnoreCase("Y")) {
            secondaryType = readValidString(scan, primaryType, "[A-Za-z\\s]+");
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
            pokemon = new Pokemon(pokedexNumber, name, primaryType, secondaryType, baseLevel, evolvesFrom, evolvesTo, evolutionLevel, baseLevel, attack, defense, speed);
        } else {
            pokemon = new Pokemon(pokedexNumber, name, primaryType, baseLevel, evolvesFrom, evolvesTo, evolutionLevel, baseLevel, attack, defense, speed);
        }

        pokemons.add(pokemon);
        System.out.println("Pokemon \"" + name + "\" added successfully!");
        System.out.println("");
        return true;

    }

    // public void displayAllPokemons(List<Pokemon> pokemons) {
    //     divider();
    //     System.out.printf("%-4s %-12s %-15s %-9s %-15s %-30s\n","#", "Name", "Type(s)", "Base Lv", "Held Item", "Moves");
    //     divider();
    //     for (Pokemon p : pokemons) {
    //         System.out.println()
    //     }
    // }

    // Helper function for string input
    private static String readValidString(Scanner scan, String attribute, String regex) {
        String input;

        while(true) {
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

        while(true) {
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
        System.out.println("\"--------------------------------------------------------------------------------------\"");
    }
    
}
