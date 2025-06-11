import java.util.ArrayList; // for an easier life
import java.util.List;

public class Pokemon {
    // Attributes
    private int pokedexNumber; 
    private String name;
    private String type1;
    private String type2; // optional
    private int baseLevel;
    private int evolveFrom;
    private int evolveTo;
    private int evolutionLevel;
    private List<String> moveSet; 
    private String heldItem;
    // Base Stats:
    private int baseHP;
    private int baseAttack;
    private int baseDefense;
    private int baseSpeed;


    // Constructor - primary purpose of a constructor is to initialize a new object
    public Pokemon(int pokedexNumber, String name, String type1, String type2, int baseLevel,
                   int evolveFrom, int evolveTo, int evolutionLevel, int baseHP, int baseAttack,
                   int baseDefense, int baseSpeed) {
        this.pokedexNumber = pokedexNumber;
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.baseLevel = baseLevel;
        this.evolveFrom = evolveFrom;
        this.evolveTo = evolveTo;
        this.evolutionLevel = evolutionLevel;

        this.baseHP = baseHP;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
        this.baseSpeed = baseSpeed;

        this.heldItem = null;

        // Not included in the parameters kasi tackle and defend are fixed and its subsequent changes are handled by setters
        this.moveSet = new ArrayList<>();
        this.moveSet.add("Tackle");
        this.moveSet.add("Defend");
    }


    // Getters - reads information
    public String getName() {
        return name;
    }

    public int getPokedexNumber() {
        return pokedexNumber;
    }

    public int getBaseLevel() {
        return baseLevel;
    }

    public String getType1() {
        return type1;
    }

    public String getType2() {
        return type2;
    }    

    public int getEvolveFrom() {
        return evolveFrom;
    }

    public int getEvolveTo() {
        return evolveTo;
    }

    public int getEvolutionLevel() {
        return evolutionLevel;
    }

    public int getHp() {
        return baseHP;
    }

    public int getAttack() {
        return baseAttack;
    }

    public int getDefense() {
        return baseDefense;
    }

    public int getSpeed() {
        return baseSpeed;
    }

    public List<String> getMoveSet() { 
        return new ArrayList<>(moveSet);
    }

    public String getHeldItem() {
        return heldItem;
    }


    // Setters - changes or writes information
    public void setBaseLevel(int baseLevel) {
        if (baseLevel >= 0) {
            this.baseLevel = baseLevel;
        } else {
            System.out.println("Base level cannot be negative.");
        }
    }

    public void setEvolveFrom(int evolveFrom) {
        this.evolveFrom = evolveFrom;
    }

    public void setEvolveTo(int evolveTo) {
        this.evolveTo = evolveTo;
    }

    public void setEvolutionLevel(int evolutionLevel) {
        this.evolutionLevel = evolutionLevel;
    }

    /*
     * Questions Dump:
     * Difference between list and array list?
     *  - List for declaring, ArrayList for Instantiating
     * How do I know when "this" is used ?
     *  - it refers to the current instance of the class, not the local one
     *  - also used to call another constructor within the same class
    */

    // Review me again!!
    public void setHeldItem(String heldItem) {
        String oldItem = this.heldItem; // to store the current item before changing
        this.heldItem = heldItem; // update to the new one
        // how to discard the previos item if no longer held

        if (heldItem == null || heldItem.trim().isEmpty()) {
            if (oldItem != null !oldItem.trim().isEmpty()) {
                System.out.println(this.name + " is no longer holding " + oldItem + ".");
            } else {
                System.out.println(this.name + " is not holding an item.");
            }
        } else {
            if (oldItem != null && !oldItem.trim().isEmpty()) {
                System.out.println(this.name + " unequipped " + oldItem + " and is now holding " + heldItem + ".");
            } else {
                System.out.println(this.name + " is now holding " + heldItem + ".");
            }
        }
    }



    // Methods
    public boolean teachMove(Move move) {
        // Check if compatible
        if (!isTypeCompatible(move)) {
            System.out.println(this.name + " can't learn " + move.getName() + " because type is incompatible!");
            return false;
        }

        // Check if move is in the set already
        if (this.moveSet.contains(move.getName())) {
            System.out.println(this.name + " already knows " + move.getName() + ".");
            return false;
        }

        // Check if adding a new move will exceed the limit
        if(this.moveSet.size() < 4) {
            this.moveSet.add(move.getName());
            System.out.println(move.getName() + " learned successfully!");
            return true;
        } else {
            System.out.println(this.name + " already knows 4 moves! A move must be forgotten to learn a new one unless it's an HM.");
            System.out.println("Current moves: " + String.join(", ", this.moveSet));
            return false;
        }
    }

    // Will do this after I make the Move Class
    public void forgetMove(List<Move> allMoves) {
        // if (move is not an HM) {
        //       forget move
        //       sout(success message)
        // } else {
        //       sout(move + " is an HM. Cant remove");
        // }
    }
    
    public void cry() {
        System.out.println(this.name + " cries: "); // pokemon sound ???
    }

    // Helper Method
    private boolean isTypeCompatible(Move move) {
        // Check Type 1
        // equalsIgnoreCase is like strcasecmp in C
        // assuming lang sa getType1
        if (this.type1 != null && this.type1.equalsIgnoreCase(move.getType1())) {
            return true;
        }
        if (this.type1 != null && move.getType2() != null && this.type1.equalsIgnoreCase(move.getType2())) {
            return true;
        }

        // Check Type 2 (if it exists!)
        if (this.type2 != null && this.type2.equalsIgnoreCase(move.getType1())) {
            return true;
        }
        if (this.type2 != null && move.getType2() != null && this.type2.equalsIgnoreCase(move.getType2())) {
            return true;
        }

        return false;
    }
}