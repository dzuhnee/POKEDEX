public class Move {
    // Attributes
    private String name;
    private String description;
    private String classification; // Hidden Machine (HM) or Technical Machine (TM)
    private String primaryType;
    private String secondaryType; // optional


    // Constructor 
    // with type 2
    public Move(String name, String description, String classification, String primaryType, String secondaryType) {
        this.name = name;
        this.description = description;
        this.classification = classification;
        this.primaryType = primaryType;
        this.secondaryType = secondaryType;
    }
    // without type 2
    public Move(String name, String description, String classification, String primaryType) {
        this(name, description, classification, primaryType, null);
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

    public String getprimaryType() {
        return primaryType;
    }

    public String getsecondaryType() {
        return secondaryType;
    }
}
