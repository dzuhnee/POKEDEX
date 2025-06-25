/**
 * This class epresents the base stats of a Pokémon, including HP, Attack,
 * Defense, Special Attack,
 * Special Defense, and Speed.
 */
public class PokemonBaseStats {
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
     * @return the total of HP, Attack, Defense, Special Attack, Special Defense, and Speed
     */
    public int getTotal() {
        return hp + attack + defense + spAttack + spDefense + speed;
    }
}
