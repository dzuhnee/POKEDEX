import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TrainerManager {
    private final List<Trainer> trainers = new ArrayList<>();
    Scanner scan = new Scanner(System.in);

    public boolean addTrainer() {
        int trainerID;
        String name;
        LocalDate birthdate;
        String sex;
        String hometown;
        String description;
        
        System.out.println("--- Add Trainer ---");
        trainerID = readValidInt(scan, "trainer ID");
        name = readValidString(scan, "name", "[A-Za-z\\\\s]+");
        
        System.out.print("Enter birthdate (YYYY-MM-DD): ");
        String input = scan.nextLine();
        try {
            birthdate = LocalDate.parse(input);
        } catch (Exception e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
        }

        sex = readValidString(scan, "sex", "[A-Za-z\\\\s]+");
        hometown = readValidString(scan, "hometown", "[A-Za-z\\\\s]+");
        description = readValidString(scan, "description", "[A-Za-z\\\\s]+");


        Trainer trainer = new Trainer(trainerID, name, birthdate, sex, hometown, description);

        System.out.printf("Add" + name + " to your Trainers [Y/N]: ");
        String c;
        do {
            c = scan.nextLine();
            if (!c.equalsIgnoreCase("Y") && !c.equalsIgnoreCase("N")) {
                System.out.print("Invalid input. Please try again: ");
            }
        } while (!c.equalsIgnoreCase("Y") && !c.equalsIgnoreCase("N"));

        if (c.equalsIgnoreCase("Y")) {
            trainers.add(trainer);
            System.out.println("Trainer \"" + name + "\" added successfully!");
            System.out.println("");
            return true;
        }

        return false;
    }

    public void viewTrainers() {
        System.out.println("--- View Trainers ---");
        for (Trainer t : trainers) {
            t.displayProfile();
            System.out.println("-------------------------------------------------------------------------------");
        }
    }

    public List<Trainer> searchTrainers() {
        List<Trainer> foundTrainers = new ArrayList<>();

        System.out.println("--- Search Trainer ---");
        System.out.print("Enter any keyword: ");
        String keyword = scan.nextLine();
        String k = keyword.toLowerCase();

        for (Trainer t : trainers) {
            if (t.getName().toLowerCase().contains(k) || t.getSex().toLowerCase().contains(k) ||
                    t.getBirthdate().toString().contains(k) || String.valueOf(t.getTrainerID()).contains(k) ||
                    t.getDescription().toLowerCase().contains(k) || t.getHometown().toLowerCase().contains(k)) {
                foundTrainers.add(t);
            }
        }

        return foundTrainers;
    }

    public int readValidInt(Scanner scan, String attribute) {
        int input;

        while (true) {
            System.out.print("Enter " + attribute + ": ");

            try {
                input = Integer.parseInt(scan.nextLine());
                return input;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    public static String readValidString(Scanner scan, String attribute, String regex) {
        String input;

        while (true) {
            System.out.print("Enter " + attribute + ": ");
            input = scan.nextLine().trim();

            if (!input.matches(regex) || input.isEmpty()) {
                System.out.println("Input is invalid or empty. Please try again!");
            } else {
                return input;
            }
        }
    }
}
