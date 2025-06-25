import java.util.ArrayList;
import java.util.List;

public class Pokemon {
    private String pokedexNumber;
    private String name;
    private String primaryType;
    private String secondaryType;
    private int baseLevel;
    private int evolvesFrom;
    private int evolvesTo;
    private int evolutionLevel;
    private List<Move> moveSet;
    private Item heldItem;
    private PokemonBaseStats baseStats;

    // Constructor for dual-type Pokemon
    public Pokemon(String pokedexNumber, String name, String primaryType, String secondaryType, int baseLevel,
            int evolvesFrom,
            int evolvesTo, int evolutionLevel, int hp, int attack, int defense, int spAttack, int spDefense,
            int speed) {
        this.pokedexNumber = pokedexNumber;
        this.name = name;
        this.primaryType = primaryType;
        this.secondaryType = secondaryType;
        this.baseLevel = baseLevel;
        this.evolvesFrom = evolvesFrom;
        this.evolvesTo = evolvesTo;
        this.evolutionLevel = evolutionLevel;
        this.baseStats = new PokemonBaseStats(hp, attack, defense, spAttack, spDefense, speed);

        this.heldItem = null;
        this.moveSet = new ArrayList<>();
    }

    // Constructor for single-type Pokemon
    public Pokemon(String pokedexNumber, String name, String primaryType, int baseLevel, int evolvesFrom, int evolvesTo,
            int evolutionLevel, int hp, int attack, int defense, int spAttack, int spDefense, int speed) {
        this(pokedexNumber, name, primaryType, null, baseLevel, evolvesFrom, evolvesTo, evolutionLevel,
                hp, attack, defense, spAttack, spDefense, speed);
    }

    public String getName() {
        return name;
    }

    public String getPokedexNumber() {
        return pokedexNumber;
    }

    public int getBaseLevel() {
        return baseLevel;
    }

    public String getPrimaryType() {
        return primaryType;
    }

    public String getSecondaryType() {
        return secondaryType;
    }

    public int getEvolvesFrom() {
        return evolvesFrom;
    }

    public int getEvolvesTo() {
        return evolvesTo;
    }

    public int getEvolutionLevel() {
        return evolutionLevel;
    }

    public int getHP() {
        return baseStats.getHP();
    }

    public int getAttack() {
        return baseStats.getAttack();
    }

    public int getDefense() {
        return baseStats.getDefense();
    }

    public int getSpecialAttack() {
        return baseStats.getSpecialAttack();
    }

    public int getSpecialDefense() {
        return baseStats.getSpecialDefense();
    }

    public int getSpeed() {
        return baseStats.getSpeed();
    }

    public List<Move> getMoveSet() {
        return new ArrayList<>(moveSet);
    }

    public Item getHeldItem() {
        return heldItem;
    }

    public void cry() {
        System.out.println(this.name + " cries!");
    }

    public void display() {
        // Displays both types if there are two
        String types = primaryType;
        if (secondaryType != null && !secondaryType.isEmpty()) {
            types += "/" + secondaryType;
        }

        System.out.printf("%-6s %-12s %-15s %-7d %-5d %-7d %-8d %-9d %-9d %-6d\n", pokedexNumber, name, types,
                baseStats.getTotal(), baseStats.getHP(), baseStats.getAttack(), baseStats.getDefense(),
                baseStats.getSpecialAttack(), baseStats.getSpecialDefense(), baseStats.getSpeed());
    }
}

/**
 * This class epresents the base stats of a Pokémon, including HP, Attack,
 * Defense, Special Attack,
 * Special Defense, and Speed.
 */
class PokemonBaseStats {
    private int hp;
    private int attack;
    private int defense;
    private int spAttack;
    private int spDefense;
    private int speed;

    /**
     * Constructs a PokemonBaseStats object with the given base stat values.
     *
     * @param hp        the base HP (Hit Points) stat of the Pokémon
     * @param attack    the base Attack stat of the Pokémon
     * @param defense   the base Defense stat of the Pokémon
     * @param spAttack  the base Special Attack stat of the Pokémon
     * @param spDefense the base Special Defense stat of the Pokémon
     * @param speed     the base Speed stat of the Pokémon
     */
    public PokemonBaseStats(int hp, int attack, int defense, int spAttack, int spDefense, int speed) {
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.spAttack = spAttack;
        this.spDefense = spDefense;
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
     * Gets the base Special Attack stat.
     *
     * @return the base Special Attack value
     */
    public int getSpecialAttack() {
        return spAttack;
    }

    /**
     * Gets the base Special Defense stat.
     *
     * @return the base Special Defense value
     */
    public int getSpecialDefense() {
        return spDefense;
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
        return hp + attack + defense + spAttack + spDefense + speed;
    }
}
