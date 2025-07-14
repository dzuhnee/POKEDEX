import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Trainer {
    // Attributes
    private int trainerID;
    private String name;
    private LocalDate birthdate;
    private String sex;
    private String hometown;
    private String description;
    private int money;

    private List<Pokemon> lineup; // Up to 6
    private List<Pokemon> storage; // Storage for additional pokemon
    private List<Item> itemBag; // Up to 50 (max 10 unique)

    // Constructor
    public Trainer(int trainerId, String name, LocalDate birthdate, String sex, String hometown, String description) {
        this.trainerID = trainerId;
        this.name = name;
        this.birthdate = birthdate;
        this.sex = sex;
        this.hometown = hometown;
        this.description = description;
        this.money = 1000000;

        this.lineup = new ArrayList<>();
        this.storage = new ArrayList<>();
        this.itemBag = new ArrayList<>();
    }

    // Getters
    public int getTrainerID() {
        return trainerID;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getSex() {
        return sex;
    }

    public String getHometown() {
        return hometown;
    }

    public String getDescription() {
        return description;
    }

    public int getMoney() {
        return money;
    }

    public List<Pokemon> getLineup() {
        return lineup;
    }

    public List<Pokemon> getStorage() {
        return storage;
    }

    public List<Item> getItemBag() {
        return itemBag;
    }

    // Methods
    public boolean buyItem(Item item, Scanner scanner) {
        if (money < item.getBuyingPrice())
            return false;

        // Count unique items
        boolean isAlreadyOwned = false;
        List<String> seenNames = new ArrayList<>();
        for (Item i : itemBag) {
            if (!seenNames.contains(i.getName())) {
                seenNames.add(i.getName());
            }
            if (i.getName().equals(item.getName())) {
                isAlreadyOwned = true;
            }
        }

        if (!isAlreadyOwned && seenNames.size() >= 10) {
            System.out.println("Cannot have more than 10 unique item types.");
            return false;
        }

        if (itemBag.size() >= 50) {
            System.out.println("Item bag is full. Please choose an item to discard:");

            // Display all items
            for (int i = 0; i < itemBag.size(); i++) {
                System.out.println("[" + i + "] " + itemBag.get(i).getName());
            }

            int indexToRemove = -1;
            while (indexToRemove < 0 || indexToRemove >= itemBag.size()) {
                System.out.print("Enter index of item to discard: ");
                try {
                    indexToRemove = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                }
            }

            Item removed = itemBag.remove(indexToRemove);
            System.out.println(removed.getName() + " discarded.");
        }

        itemBag.add(item);
        money -= item.getBuyingPrice();
        return true;
    }

    public boolean sellItem(Item item) {
        if (itemBag.remove(item)) {
            money += item.getSellingPrice();
            return true;
        }
        return false;
    }

    public boolean useItem(Pokemon pokemon, Item item) {
        if (itemBag.contains(item)) {
            boolean success; // = item.applyEffect(pokemon)
            if (success == true) { // remove true
                itemBag.remove(item);
            }
            return true; // change to success
        }
        return false;
    }

    public boolean teachMove(Pokemon pokemon, Move move) {
        return pokemon.learnMove(move);
    }

    public boolean addPokemonToLineup(Pokemon p) {
        if (lineup.size() < 6) {
            lineup.add(p);
            return true;
        } else {
            storage.add(p);
            return false;
        }
    }

    public boolean switchToLineup(Pokemon p) {
        if (lineup.size() < 6 && storage.contains(p)) {
            storage.remove(p);
            lineup.add(p);
            return true;
        }
        return false;
    }

    public boolean switchToStorage(Pokemon p) {
        if (lineup.contains(p)) {
            lineup.remove(p);
            storage.add(p);
            return true;
        }
        return false;
    }

    public boolean releasePokemon(Pokemon p) {
        return lineup.remove(p) || storage.remove(p);
    }

    /*
     * private int trainerID;
     * private String name;
     * private LocalDate birthdate;
     * private String sex;
     * private String hometown;
     * private String description;
     * private int money;
     * 
     * private List<Pokemon> lineup; // Up to 6
     * private List<Pokemon> storage; // Storage for additional pokemon
     * private List<Item> itemBag; // Up to 50 (max 10 unique)
     */
    public String displayProfile() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("ID         : %04d\n", trainerID));
        sb.append(String.format("Name       : %s\n", name));
        sb.append(String.format("Sex        : %s\n", sex));
        sb.append(String.format("Birthdate  : %s\n", birthdate.toString()));
        sb.append(String.format("Hometown   : %s\n", hometown));
        sb.append(String.format("Money      : ₱%,d\n", money));
        sb.append(String.format("Description: %s\n", description));

        return sb.toString();
    }

}
