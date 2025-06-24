import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// This class represents a single Pokémon item
public class Item {
    // These are the properties of the item
    private final int buyingPrice;
    private final String name;
    private final String category;
    private final String description;
    private final String effect;
    private final int sellingPrice;
    private int stock;

    // Constructor: sets all the values when an item is created
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
    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getEffect() {
        return effect;
    }

    public int getBuyingPrice() {
        return buyingPrice;
    }

    public int getSellingPrice() {
        return sellingPrice;
    }

    public int getStock() {
        return stock;
    }

    // Setter
    public void setStock(int stock) {
        this.stock = stock;
    }
}

// This class manages a whole list of items
class Items {

    private final Scanner scanner; // For taking user input
    private final List<Item> itemList; // List to store all the item objects

    // Constructor: initializes the list and adds default items
    public Items(final Scanner scanner) {
        itemList = new ArrayList<>();
        populateInitialItems();
        this.scanner = scanner;
    }

    // Adds sample items to the database (vitamins, feathers, stones, etc.)
    public void populateInitialItems() {
        // Vitamins (boost EVs)
        itemList.add(new Item("Calcium", "Vitamin", "A nutritious drink for Pokémon.", "+10 Special Attack EVs", 10000, 5000, 10));
        itemList.add(new Item("Carbos", "Vitamin", "A nutritious drink for Pokémon.", "+10 Speed EVs", 10000, 5000, 10));
        itemList.add(new Item("HP Up", "Vitamin", "A nutritious drink for Pokémon.", "+10 HP EVs", 10000, 5000, 10));
        itemList.add(new Item("Iron", "Vitamin", "A nutritious drink for Pokémon.", "+10 Defense EVs", 10000, 5000, 10));
        itemList.add(new Item("Protein", "Vitamin", "A nutritious drink for Pokémon.", "+10 Attack EVs", 10000, 5000, 10));
        itemList.add(new Item("Zinc", "Vitamin", "A nutritious drink for Pokémon.", "+10 Special Defense EVs", 10000, 5000, 10));

        // Feathers
        itemList.add(new Item("Health Feather", "Feather", "Slightly increases HP.", "+1 HP EV", 300, 150, 10));
        itemList.add(new Item("Muscle Feather", "Feather", "Slightly increases Attack.", "+1 Attack EV", 300, 150, 10));
        itemList.add(new Item("Resist Feather", "Feather", "Slightly increases Defense.", "+1 Defense EV", 300, 150, 10));
        itemList.add(new Item("Swift Feather", "Feather", "Slightly increases Speed.", "+1 Speed EV", 300, 150, 10));
        itemList.add(new Item("Genius Feather", "Feather", "Slightly increases Special Attack.", "+1 Special Attack EV", 300, 150, 10));
        itemList.add(new Item("Clever Feather", "Feather", "Slightly increases Special Defense.", "+1 Special Defense EV", 300, 150, 10));

        // Others
        itemList.add(new Item("Rare Candy", "Leveling Item", "A candy packed with energy.", "Increases level by 1", -1, 2400, 10));

        // Evolution Stones
        itemList.add(new Item("Fire Stone", "Evolution Stone", "Radiates heat.", "Evolves Vulpix, Growlithe, Eevee, etc.", 3000, 1500, 10));
        itemList.add(new Item("Water Stone", "Evolution Stone", "Blue, watery appearance.", "Evolves Poliwhirl, Shellder, Eevee, etc.", 3000, 1500, 10));
        itemList.add(new Item("Thunder Stone", "Evolution Stone", "Sparkles with electricity.", "Evolves Pikachu, Eelektrik, Eevee, etc.", 3000, 1500, 10));
        itemList.add(new Item("Leaf Stone", "Evolution Stone", "Leaf pattern.", "Evolves Gloom, Weepinbell, Exeggcute etc.", 3000, 1500, 10));
        itemList.add(new Item("Moon Stone", "Evolution Stone", "Glows faintly.", "Evolves Nidorina, Clefairy, Jigglypuff, etc.", -1, 1500, 10));
        itemList.add(new Item("Sun Stone", "Evolution Stone", "Glows like the sun.", "Evolves Gloom, Sunkern, Cottonee, etc.", 3000, 1500, 10));
        itemList.add(new Item("Shiny Stone", "Evolution Stone", "Sparkles brightly.", "Evolves Togetic, Roselia, Minccino, etc.", 3000, 1500, 10));
        itemList.add(new Item("Dusk Stone", "Evolution Stone", "Ominous appearance.", "Evolves Murkrow, Misdreavus, Doublade, etc.", 3000, 1500, 10));
        itemList.add(new Item("Dawn Stone", "Evolution Stone", "Sparkles like the morning sky.", "Evolves male Kirlia and female Snorunt.", 3000, 1500, 10));
        itemList.add(new Item("Ice Stone", "Evolution Stone", "Cold to the touch.", "Evolves Alolan Vulpix, etc.", 3000, 1500, 10));
    }

    // Displays all items
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

    // Menu to handle search options (by name/effect or by category)
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

    // Searches for items by name or by their effect text
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

    // Searches for items by their category (Vitamin, Feather, etc.)
    public void searchByCategory(String category) {
        List<Item> results = new ArrayList<>();
        for (Item item : itemList) {
            if (item.getCategory().equalsIgnoreCase(category)) {
                results.add(item);
            }
        }
        showItemSearchResults(results, "Category: " + category);
    }

    // Show item results
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
}