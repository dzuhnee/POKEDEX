import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class represents a Pokémon with identifying information, types,
 * evolution details,
 * stats, moves, and held item.
 */
public class Pokemon {
    private String pokedexNumber;
    private String name;
    private String primaryType;
    private String secondaryType;
    private int baseLevel;
    private String evolvesFrom;
    private String evolvesTo;
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
    public Pokemon(String pokedexNumber, String name, String primaryType, String secondaryType, int baseLevel,
            String evolvesFrom,
            String evolvesTo, int evolutionLevel, int hp, int attack, int defense, int speed) {
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
    public Pokemon(String pokedexNumber, String name, String primaryType, int baseLevel, String evolvesFrom, String evolvesTo,
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
    public String getPokedexNumber() {
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
    public String getEvolvesFrom() {
        return evolvesFrom;
    }

    /**
     * Gets the Pokédex number it evolves to.
     *
     * @return the Pokédex number it evolves to
     */
    public String getEvolvesTo() {
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

    /**
     * Displays the Pokémon's details including name, types, and base stats in a
     * formatted line.
     */
    public void display() {
        String types = primaryType;
        if (secondaryType != null && !secondaryType.isEmpty()) {
            types += "/" + secondaryType;
        }

        System.out.printf("%-6s %-12s %-15s %-7d %-5d %-7d %-8d %-6d\n", pokedexNumber, name, types,
    baseStats.getTotal(), baseStats.getHP(), baseStats.getAttack(), baseStats.getDefense(),
    baseStats.getSpeed()
);

    }
}


/**
 * This class represents the base stats of a Pokémon, including HP, Attack,
 * Defense, Special Attack,
 * Special Defense, and Speed.
 */
class PokemonBaseStats {
    private final int hp;
    private final int attack;
    private final int defense;
    private final int speed;

    /**
     * Constructs a PokemonBaseStats object with the given base stat values.
     *
     * @param hp        the base HP (Hit Points) stat of the Pokémon
     * @param attack    the base Attack stat of the Pokémon
     * @param defense   the base Defense stat of the Pokémon
     * @param speed     the base Speed stat of the Pokémon
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

    /**
     * Calculates and returns the total of all base stats.
     *
     * @return the total of HP, Attack, Defense, Special Attack, Special Defense,
     *         and Speed
     */
    public int getTotal() {
        return hp + attack + defense  + speed;
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
