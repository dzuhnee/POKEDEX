import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a single Pokémon item with attributes such as name, category, 
 * description, effect, buying/selling price, and stock quantity.
 */
public abstract class Item {
    // These are the properties of the item
    private final int buyingPrice;
    private final String name;
    private final String category;
    private final String description;
    private final String effect;
    private final int sellingPrice;
    private int stock;

    /**
     * Constructs an Item with all necessary properties.
     * 
     * @param name the name of the item
     * @param category the category of the item (e.g., Vitamin, Feather)
     * @param description a short description of the item
     * @param effect the effect it has on Pokémon
     * @param buyingPrice price to buy the item
     * @param sellingPrice price to sell the item
     * @param stock initial stock quantity
     */
    public Item(String name, String category, String description, String effect,
                int buyingPrice, int sellingPrice, int stock) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.effect = effect;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
        this.stock = stock;
    }

    // Getters

    /** @return the name of the item */
    public String getName() {
        return name;
    }

    /** @return the category of the item */
    public String getCategory() {
        return category;
    }

    /** @return the description of the item */
    public String getDescription() {
        return description;
    }

    /** @return the effect the item has */
    public String getEffect() {
        return effect;
    }

    /** @return the buying price of the item */
    public int getBuyingPrice() {
        return buyingPrice;
    }

    /** @return the selling price of the item */
    public int getSellingPrice() {
        return sellingPrice;
    }

    /** @return the current stock quantity */
    public int getStock() {
        return stock;
    }

    /**
     * Sets the stock value for the item.
     * 
     * @param stock the new stock quantity
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    public abstract void use(Pokemon pokemon, PokemonManager manager);
}

/**
 * Manages a collection of Pokémon items and provides functionality
 * for viewing, searching, and retrieving items.
 */
class ItemManager {

    private final Scanner scanner; // For taking user input
    private final List<Item> itemList; // List to store all the item objects

    /**
     * Constructs the Items manager and populates it with default items.
     * 
     * @param scanner the Scanner object for user input
     */
    public ItemManager(final Scanner scanner) {
        itemList = new ArrayList<>();
        populateInitialItems();
        this.scanner = scanner;
    }

    /**
     * Populates the item list with predefined sample items.
     * Includes Vitamins, Feathers, Leveling Items, and Evolution Stones.
     */
    public void populateInitialItems() {
        // Vitamins (boost EVs)
        itemList.add(new Vitamin("Calcium", "A nutritious drink for Pokémon.", "+10 Special Attack EVs", 10000, 5000, 10, "Special Attack"));
        itemList.add(new Vitamin("Carbos", "A nutritious drink for Pokémon.", "+10 Speed EVs", 10000, 5000, 10, "Speed"));
        itemList.add(new Vitamin("HP Up", "A nutritious drink for Pokémon.", "+10 HP EVs", 10000, 5000, 10, "HP"));
        itemList.add(new Vitamin("Iron", "A nutritious drink for Pokémon.", "+10 Defense EVs", 10000, 5000, 10, "Defense"));
        itemList.add(new Vitamin("Protein", "A nutritious drink for Pokémon.", "+10 Attack EVs", 10000, 5000, 10, "Attack"));
        itemList.add(new Vitamin("Zinc", "A nutritious drink for Pokémon.", "+10 Special Defense EVs", 10000, 5000, 10, "Special Defense"));

        // Feathers
        itemList.add(new Feather("Health Feather", "Slightly increases HP.", "+1 HP EV", 300, 150, 10, "HP"));
        itemList.add(new Feather("Muscle Feather", "Slightly increases Attack.", "+1 Attack EV", 300, 150, 10, "Attack"));
        itemList.add(new Feather("Resist Feather", "Slightly increases Defense.", "+1 Defense EV", 300, 150, 10, "Defense"));
        itemList.add(new Feather("Swift Feather", "Slightly increases Speed.", "+1 Speed EV", 300, 150, 10, "Speed"));
        itemList.add(new Feather("Genius Feather", "Slightly increases Special Attack.", "+1 Special Attack EV", 300, 150, 10, "Special Attack"));
        itemList.add(new Feather("Clever Feather", "Slightly increases Special Defense.", "+1 Special Defense EV", 300, 150, 10, "Special Defense"));

        // Others
        itemList.add(new RareCandy("Rare Candy", "A candy packed with energy.", "Increases level by 1", -1, 2400, 10));

        // Evolution Stones
        itemList.add(new EvolutionStone("Fire Stone", "Radiates heat.", "Evolves Vulpix, Growlithe, Eevee, etc.", 3000, 1500, 10, "Fire"));
        itemList.add(new EvolutionStone("Water Stone",  "Blue, watery appearance.", "Evolves Poliwhirl, Shellder, Eevee, etc.", 3000, 1500, 10, "Water"));
        itemList.add(new EvolutionStone("Thunder Stone",  "Sparkles with electricity.", "Evolves Pikachu, Eelektrik, Eevee, etc.", 3000, 1500, 10, "Electric"));
        itemList.add(new EvolutionStone("Leaf Stone",  "Leaf pattern.", "Evolves Gloom, Weepinbell, Exeggcute etc.", 3000, 1500, 10, "Grass"));
        itemList.add(new EvolutionStone("Moon Stone",  "Glows faintly.", "Evolves Nidorina, Clefairy, Jigglypuff, etc.", -1, 1500, 10, "Moon"));
        itemList.add(new EvolutionStone("Sun Stone",  "Glows like the sun.", "Evolves Gloom, Sunkern, Cottonee, etc.", 3000, 1500, 10, "Sun"));
        itemList.add(new EvolutionStone("Shiny Stone",  "Sparkles brightly.", "Evolves Togetic, Roselia, Minccino, etc.", 3000, 1500, 10, "Fairy"));
        itemList.add(new EvolutionStone("Dusk Stone",  "Ominous appearance.", "Evolves Murkrow, Misdreavus, Doublade, etc.", 3000, 1500, 10, "Dark"));
        itemList.add(new EvolutionStone("Dawn Stone",  "Sparkles like the morning sky.", "Evolves male Kirlia and female Snorunt.", 3000, 1500, 10, "Psychic"));
        itemList.add(new EvolutionStone("Ice Stone",  "Cold to the touch.", "Evolves Alolan Vulpix, etc.", 3000, 1500, 10, "Ice"));
    }

    /**
     * Displays all available items in a formatted list.
     */
    public void viewAllItemsAvailable() {
        if (itemList.isEmpty()) {
            System.out.println("\nSystem: No items in the database.");
            return;
        }
    
        // Center the title
        String title = "Item Database";
        int consoleWidth = 60; // approximate width based on the table
        int padding = (consoleWidth - title.length()) / 2;
        System.out.println(" ".repeat(Math.max(0, padding)) + title);
    
        // Table header
        String formatHeader = "%-25s %-20s %-40s\n";
        System.out.printf(formatHeader, "\nItem Name", "Category", "Effect");
        System.out.println("=========================================================================");
    
        // Loop through each item and print it
        for (Item item : itemList) {
            String name = item.getName();
            String category = item.getCategory();
            String effect = item.getEffect();
    
            if (effect.length() <= 40) {
                System.out.printf("%-25s %-20s %-40s\n", name, category, effect);
            } else {
                System.out.printf("%-25s %-20s %-40s\n", name, category, effect.substring(0, 40));
                int index = 40;
                while (index < effect.length()) {
                    int end = Math.min(index + 40, effect.length());
                    System.out.printf("%-25s %-20s %-40s\n", "", "", effect.substring(index, end));
                    index += 40;
                }
            }
        }
    }    

    /**
     * Displays a menu to let the user choose a search option.
     * Allows searching by item name/effect or by category.
     */
    public void handleItemSearch() {
        System.out.println("\n--- Search Pokémon Items ---");
        System.out.printf("%-5s %-30s%n", "1.", "By Name or Effect");
        System.out.printf("%-5s %-30s%n", "2.", "By Category (e.g., Vitamin, Feather)");
        System.out.printf("%-5s", "");
        System.out.print("Enter option: ");

        String option = scanner.nextLine().trim();

        switch (option) {
            case "1":
                System.out.print("Enter keyword (name or effect): ");
                String keyword = scanner.nextLine().trim();
                searchItemsByNameOrEffect(keyword);
                break;

            case "2":
                System.out.print("Enter category (Vitamin, Feather, Leveling Item, Evolution Stone): ");
                String category = scanner.nextLine().trim();
                searchByCategory(category);
                break;

            default:
                System.out.println("Invalid option.");
        }
    }

    /**
     * Searches items by their name or effect using a keyword.
     * 
     * @param keyword the search keyword entered by the user
     */
    public void searchItemsByNameOrEffect(String keyword) {
        List<Item> results = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();

        for (Item item : itemList) {
            if (item.getName().toLowerCase().contains(lowerKeyword) ||
                item.getEffect().toLowerCase().contains(lowerKeyword)) {
                results.add(item);
            }
        }
        showItemSearchResults(results, "Name/Effect contains: " + keyword);
    }

    /**
     * Searches items by category (e.g., Vitamin, Feather).
     * 
     * @param category the category name to search
     */
    public void searchByCategory(String category) {
        List<Item> results = new ArrayList<>();
        for (Item item : itemList) {
            if (item.getCategory().equalsIgnoreCase(category)) {
                results.add(item);
            }
        }
        showItemSearchResults(results, "Category: " + category);
    }

    /**
     * Displays the search results in a tabular format.
     * 
     * @param results the list of matching items
     * @param title   a title to indicate the search context
     */
    private void showItemSearchResults(List<Item> results, String title) {
        if (results.isEmpty()) {
            System.out.println("No items found for " + title);
            return;
        }

        System.out.println("Search Results for " + title);
        String formatHeader = "%-25s %-20s %-35s\n";
        System.out.printf(formatHeader, "\nItem Name", "Category", "Effect");
        System.out.println("=========================================================================");

        String formatRow = "%-25s %-20s %-35s\n";
        for (Item item : results) {
            System.out.printf(formatRow, item.getName(), item.getCategory(), item.getEffect());
        }
    }

    /**
     * Finds and returns an item by its name (case-insensitive).
     * 
     * @param heldItem the name of the item to find
     * @return the Item if found, or null otherwise
     */
    public Item findItem(String heldItem) {
        for (Item item : itemList) {
            if (item.getName().equalsIgnoreCase(heldItem)) {
                return item;
            }
        }
        return null;
    }

    public boolean isValid(String heldItem){
        Item item = findItem(heldItem);

        if (item != null) {
            return true;
        }

        return false;
    }

}