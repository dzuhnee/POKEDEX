import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    private Item heldItem;
    private PokemonBaseStats baseStats;

    // Constructor for dual-type Pokemon
    public Pokemon(int pokedexNumber, String name, String primaryType, String secondaryType, int baseLevel,
            int evolvesFrom, int evolvesTo, int evolutionLevel, int hp, int attack, int defense, int speed) {
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

    // Constructor for single-type Pokemon
    public Pokemon(int pokedexNumber, String name, String primaryType, int baseLevel, int evolvesFrom, int evolvesTo,
            int evolutionLevel, int hp, int attack, int defense, int speed) {
        this(pokedexNumber, name, primaryType, null, baseLevel, evolvesFrom, evolvesTo, evolutionLevel,
                hp, attack, defense, speed);
    }

    public String getName() {
        return name;
    }

    public int getPokedexNumber() {
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

    public int getSpeed() {
        return baseStats.getSpeed();
    }

    public List<Move> getMoveSet() {
        return new ArrayList<>(moveSet);
    }

    public Item getHeldItem() {
        return heldItem;
    }

    public void setBaseLevel(int baseLevel) {
        if (baseLevel >= 0) {
            this.baseLevel = baseLevel;
        } else {
            System.out.println("Base level cannot be negative.");
        }
    }

    public void setHeldItem(Item heldItem) {
        Item oldItem = this.heldItem; // to store the current item before changing
        this.heldItem = heldItem; // update to the new one

        if (heldItem == null) {
            if (oldItem != null) {
                System.out.println(this.name + " is no longer holding " + oldItem.getName() + ".");
            } else {
                System.out.println(this.name + " is not holding an item.");
            }
        } else {
            if (oldItem != null) {
                System.out.println(this.name + " unequipped " + oldItem.getName() + " and is now holding "
                        + heldItem.getName() + ".");
            } else {
                System.out.println(this.name + " is now holding " + heldItem.getName() + ".");
            }
        }
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

    public void cry() {
        System.out.println(this.name + " cries!");
    }

    // Helper Method
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

    // public void display() {
    //     System.out.printf("%-4s %-12s %-15s %-9s %-15s %-30s\\n", pokedexNumber, name, );
    // } 
}