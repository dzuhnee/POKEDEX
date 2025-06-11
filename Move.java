public class Move {
    // Attributes
    private String name;
    private String description;
    private String classification; // Hidden Machine (HM) or Technical Machine (TM)
    private String type1;
    private String type2;


    // Constructor
    public Move(String name, String description, String classification, String type1, String type2) {
        this.name = name;
        this.description = description;
        this.classification = classification;
        this.type1 = type1;
        this.type2 = type2;
    }


    // Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getClassification() {
        return classification;
    }

    public String getType1() {
        return type1;
    }

    public String getType2() {
        return type2;
    }
}
