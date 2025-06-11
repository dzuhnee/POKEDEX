import java.time.LocalDate;

public class Trainer {
    // Attributes
    private int trainerID;
    private String name;
    private LocalDate birthdate; // what type?
    private String sex;
    private String hometown;
    private String description;
    private int money; // float or string?


    // Constructor
    public Trainer(int trainerId, String name, LocalDate birthdate, String sex, String hometown, String description, int money) {
        this.trainerID = trainerId;
        this.name = name;
        this.birthdate = birthdate;
        this.sex = sex;
        this.hometown = hometown;
        this.description = description;
        this.money = money;
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


    // Setters


    // Methods
    
    
}
