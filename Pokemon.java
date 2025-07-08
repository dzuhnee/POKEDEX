import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class represents a Pokémon with identifying information, types,
 * evolution details,
 * stats, moves, and held item.
 */
public class Pokemon {
    private int pokedexNumber;
    private String name;
    private String primaryType;
    private String secondaryType;
    private int baseLevel;
    private int evolvesFrom;
    private int evolvesTo;
    private int evolutionLevel;
    private List<Move> moveSet;
    private Item heldItem = null;
    private final PokemonBaseStats baseStats;

    /**
     * Constructs a dual-type Pokémon with specified information.
     *
     * @param pokedexNumber  the Pokédex number of the Pokémon
     * @param name           the name of the Pokémon
     * @param primaryType    the primary type of the Pokémon
     * @param secondaryType  the secondary type of the Pokémon
     * @param baseLevel      the base level of the Pokémon
     * @param evolvesFrom    the Pokédex number it evolves from
     * @param evolvesTo      the Pokédex number it evolves to
     * @param evolutionLevel the level at which it evolves
     * @param hp             the base HP stat
     * @param attack         the base Attack stat
     * @param defense        the base Defense stat
     * @param speed          the base Speed stat
     */
    public Pokemon(int pokedexNumber, String name, String primaryType, String secondaryType, int baseLevel,
            int evolvesFrom,
            int evolvesTo, int evolutionLevel, int hp, int attack, int defense, int speed) {
        this.pokedexNumber = pokedexNumber;
        this.name = name;
        this.primaryType = primaryType;
        this.secondaryType = secondaryType;
        this.baseLevel = baseLevel;
        this.evolvesFrom = evolvesFrom;
        this.evolvesTo = evolvesTo;
        this.evolutionLevel = evolutionLevel;
        this.baseStats = new PokemonBaseStats(hp, attack, defense, speed);

        this.heldItem = null;
        this.moveSet = new ArrayList<>();
    }

    /**
     * Constructs a single-type Pokémon (no secondary type).
     *
     * @param pokedexNumber  the Pokédex number of the Pokémon
     * @param name           the name of the Pokémon
     * @param primaryType    the primary type of the Pokémon
     * @param baseLevel      the base level of the Pokémon
     * @param evolvesFrom    the Pokédex number it evolves from
     * @param evolvesTo      the Pokédex number it evolves to
     * @param evolutionLevel the level at which it evolves
     * @param hp             the base HP stat
     * @param attack         the base Attack stat
     * @param defense        the base Defense stat
     * @param speed          the base Speed stat
     */
    public Pokemon(int pokedexNumber, String name, String primaryType, int baseLevel, int evolvesFrom, int evolvesTo,
            int evolutionLevel, int hp, int attack, int defense, int speed) {
        this(pokedexNumber, name, primaryType, null, baseLevel, evolvesFrom, evolvesTo, evolutionLevel,
                hp, attack, defense, speed);
    }

    /**
     * Gets the name of the Pokémon.
     *
     * @return the Pokémon's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the Pokédex number.
     *
     * @return the Pokédex number
     */
    public int getPokedexNumber() {
        return pokedexNumber;
    }

    /**
     * Gets the base level of the Pokémon.
     *
     * @return the base level
     */
    public int getBaseLevel() {
        return baseLevel;
    }

    /**
     * Gets the primary type of the Pokémon.
     *
     * @return the primary type
     */
    public String getPrimaryType() {
        return primaryType;
    }

    /**
     * Gets the secondary type of the Pokémon.
     *
     * @return the secondary type, or null if none
     */
    public String getSecondaryType() {
        return secondaryType;
    }

    /**
     * Gets the Pokédex number it evolves from.
     *
     * @return the Pokédex number it evolves from
     */
    public int getEvolvesFrom() {
        return evolvesFrom;
    }

    /**
     * Gets the Pokédex number it evolves to.
     *
     * @return the Pokédex number it evolves to
     */
    public int getEvolvesTo() {
        return evolvesTo;
    }

    /**
     * Gets the evolution level of the Pokémon.
     *
     * @return the level at which it evolves
     */
    public int getEvolutionLevel() {
        return evolutionLevel;
    }

    /**
     * Gets the base HP stat.
     *
     * @return the HP stat
     */
    public int getHP() {
        return baseStats.getHP();
    }

    /**
     * Gets the base Attack stat.
     *
     * @return the Attack stat
     */
    public int getAttack() {
        return baseStats.getAttack();
    }

    /**
     * Gets the base Defense stat.
     *
     * @return the Defense stat
     */
    public int getDefense() {
        return baseStats.getDefense();
    }

    /**
     * Gets the base Speed stat.
     *
     * @return the Speed stat
     */
    public int getSpeed() {
        return baseStats.getSpeed();
    }

    /**
     * Gets the move set of the Pokémon.
     *
     * @return a copy of the move set list
     */
    public List<Move> getMoveSet() {
        return new ArrayList<>(moveSet);
    }

    /**
     * Gets the held item of the Pokémon.
     *
     * @return the held item, or null if none
     */

    public Item getHeldItem() {
        return heldItem;
    }

    /**
     * Simulates the Pokémon crying (prints a simple message).
     */
    public void cry() {
        System.out.println(this.name + " cries!");
    }

    public boolean learnMove(Move move) {
        // Check if type is compatible
        if (!isTypeCompatible(move)) {
            System.out.println(this.name + " can't learn " + move.getName() + " because type is incompatible!");
            return false;
        }

        // Check if move is in the set already
        if (this.moveSet.contains(move)) {
            System.out.println(this.name + " already knows " + move.getName() + ".");
            return false;
        }

        // Check if adding a new move will exceed the limit
        if (this.moveSet.size() < 4) {
            this.moveSet.add(move);
            System.out.println(move.getName() + " learned successfully!");
            return true;
        } else {
            System.out.println(this.name
                    + " already knows 4 moves! A move must be forgotten to learn a new one unless it's an HM.");
            System.out.print("Current moves: ");
            for (Move m : moveSet) {
                System.out.print(m.getName() + " ");
            }
            System.out.println();
            return false;
        }
    }

    public void forgetMove(Move move) {
        if (!moveSet.contains(move)) {
            System.out.println(name + " does not know " + move.getName() + ".");
            return;
        }

        if (move.getClassification() == Move.Classification.HM) {
            System.out.println(move.getName() + " is an HM move and cannot be forgotten.");
        } else {
            moveSet.remove(move);
            System.out.println(move.getName() + " has been forgotten by " + name + ".");
        }
    }

    public void increaseStat(String statName, int amount) {
        switch (statName.toLowerCase()) {
            case "hp":
                baseStats.setHP(amount);
                break;
            case "attack":
                baseStats.setAttack(amount);
                break;
            case "defense":
                baseStats.setDefense(amount);
                break;
            case "speed":
                baseStats.setSpeed(amount);
                break;
            default:
                System.out.println("Invalid stat: " + statName);
        }
    }

    public boolean levelUpWithRareCandy(PokemonManager manager) {
        this.baseLevel++;

        int newHP = (int) (baseStats.getHP() * 0.10);
        int newAttack = (int) (baseStats.getAttack() * 0.10);
        int newDefense = (int) (baseStats.getDefense() * 0.10);
        int newSpeed = (int) (baseStats.getSpeed() * 0.10);

        baseStats.setHP(newHP);
        baseStats.setAttack(newAttack);
        baseStats.setDefense(newDefense);
        baseStats.setSpeed(newSpeed);

        System.out.println(this.name + " leveled up to level + " + this.baseLevel);

        if (this.baseLevel >= this.evolutionLevel && this.evolvesTo != 0) {
            evolve(manager);
            return true;
        }
        return false;
    }

    /**
     * Displays the Pokémon's details including name, types, and base stats in a
     * formatted line.
     */
    public void display() {
        String types = primaryType;
        if (secondaryType != null && !secondaryType.isEmpty()) {
            types += "/" + secondaryType;
        }

        System.out.printf("%04d %-12s %-15s %-7d %-5d %-7d %-8d %-6d\n", pokedexNumber, name, types,
                baseStats.getTotal(), baseStats.getHP(), baseStats.getAttack(), baseStats.getDefense(),
                baseStats.getSpeed());

    }

    private boolean isTypeCompatible(Move move) {
        // Check Type 1
        if (this.primaryType != null && this.primaryType.equalsIgnoreCase(move.getPrimaryType())) {
            return true;
        }
        if (this.primaryType != null && move.getSecondaryType() != null
                && this.primaryType.equalsIgnoreCase(move.getSecondaryType())) {
            return true;
        }

        // Check Type 2 (if it exists!)
        if (this.secondaryType != null && this.secondaryType.equalsIgnoreCase(move.getPrimaryType())) {
            return true;
        }
        if (this.secondaryType != null && move.getSecondaryType() != null
                && this.secondaryType.equalsIgnoreCase(move.getSecondaryType())) {
            return true;
        }

        return false;
    }

    private void evolve(PokemonManager manager) {
        System.out.println(this.name + " is evolving. . .");

        Pokemon evolved = manager.getPokemonByDex(this.evolvesTo);

        if (evolved == null) {
            System.out.println("Evolved form not found in database.");
            return;
        }
        this.name = evolved.getName();
        this.evolvesFrom = this.pokedexNumber;
        this.pokedexNumber = evolved.getPokedexNumber();
        this.evolvesTo = evolved.getEvolvesTo();

        this.baseStats.setHP(evolved.getHP() - this.getHP());
        this.baseStats.setAttack(evolved.getAttack() - this.getAttack());
        this.baseStats.setDefense(evolved.getDefense() - this.getDefense());
        this.baseStats.setSpeed(evolved.getSpeed() - this.getSpeed());

        System.out.println(this.name + " has evolved successfully!");
    }

    public boolean evolveUsingStone(String stoneType, PokemonManager manager) {
        if (!TypeUtils.isValidType(stoneType)) {
            System.out.println("Invalid stone type: " + stoneType);
            return false;
        }

        if (this.evolvesTo == 0) {
            System.out.println(this.name + " has no stone-based evolution.");
            return false;
        }

        // Get the evolved Pokémon data
        Pokemon evolved = manager.getPokemonByDex(this.evolvesTo);
        if (evolved == null) {
            System.out.println("Evolved form not found in database.");
            return false;
        }

        // Evolution is allowed only if the evolved Pokémon shares the stone type
        if (!evolved.getPrimaryType().equalsIgnoreCase(stoneType)
                && (evolved.getSecondaryType() == null || !evolved.getSecondaryType().equalsIgnoreCase(stoneType))) {
            System.out.println("The " + stoneType + " Stone has no effect on " + this.name + ".");
            return false;
        }

        // Proceed with evolution
        evolve(manager);
        return true;
    }

}

/**
 * This class represents the base stats of a Pokémon, including HP, Attack,
 * Defense, Special Attack,
 * Special Defense, and Speed.
 */
class PokemonBaseStats {
    private int hp;
    private int attack;
    private int defense;
    private int speed;

    /**
     * Constructs a PokemonBaseStats object with the given base stat values.
     *
     * @param hp      the base HP (Hit Points) stat of the Pokémon
     * @param attack  the base Attack stat of the Pokémon
     * @param defense the base Defense stat of the Pokémon
     * @param speed   the base Speed stat of the Pokémon
     */
    public PokemonBaseStats(int hp, int attack, int defense, int speed) {
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
    }

    /**
     * Gets the base HP stat.
     *
     * @return the base HP value
     */
    public int getHP() {
        return hp;
    }

    /**
     * Gets the base Attack stat.
     *
     * @return the base Attack value
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Gets the base Defense stat.
     *
     * @return the base Defense value
     */
    public int getDefense() {
        return defense;
    }

    /**
     * Gets the base Speed stat.
     *
     * @return the base Speed value
     */
    public int getSpeed() {
        return speed;
    }

    public void setHP(int hp) {
        this.hp += hp;
    }

    public void setAttack(int attack) {
        this.attack += attack;
    }

    public void setDefense(int defense) {
        this.defense += defense;
    }

    public void setSpeed(int speed) {
        this.speed += speed;
    }

    /**
     * Calculates and returns the total of all base stats.
     *
     * @return the total of HP, Attack, Defense, Special Attack, Special Defense,
     *         and Speed
     */
    public int getTotal() {
        return hp + attack + defense + speed;
    }

    /**
     * Prompts the user to enter a valid base stat value for a given attribute.
     * The input must be an integer between 1 and 255 (inclusive).
     * If the input is invalid, the user will be prompted again until a valid value
     * is entered.
     *
     * @param scan      the Scanner object used to read user input
     * @param attribute the name of the attribute being set (e.g., "HP", "Attack")
     * @return a valid integer value between 1 and 255 for the specified attribute
     */
    public static int readValidBaseStat(Scanner scan, String attribute) {
        int input;

        do {
            input = PokemonManager.readValidInt(scan, attribute);
            if (input < 1 || input > 255) {
                System.out.println(attribute + " must be between 1-255 only!");
            }
        } while (input < 1 || input > 255);

        return input;
    }
}
