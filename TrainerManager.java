import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TrainerManager {
    private final List<Trainer> trainers = new ArrayList<>();

    public TrainerManager() {
        populateInitialTrainers();
    }

    public boolean addTrainer(int id, String name, LocalDate birthdate, String sex, String hometown, String description) {
        if (name == null || name.trim().isEmpty() || birthdate == null || sex == null || hometown == null || description == null) {
            return false;
        }

        if (getTrainerByID(id) != null) {
            return false;
        }

        Trainer trainer = new Trainer(id, name, birthdate, sex, hometown, description);
        trainers.add(trainer);
        return true;
    }

    public Trainer getTrainerByID(int trainerID) {
        for (Trainer t : trainers) {
            if (t.getTrainerID() == trainerID) {
                return t;
            }
        }
        return null;
    }

    public List<Trainer> getAllTrainers() {
        return new ArrayList<>(trainers);
    }

    public List<String> getAllTrainerProfiles() {
        List<String> profiles = new ArrayList<>();

        for (Trainer t : trainers) {
            profiles.add(t.displayProfile());
        }
        return profiles;
    }

    public List<Trainer> searchTrainers(String keyword) {
        List<Trainer> matches = new ArrayList<>();
        String lowerString = keyword.toLowerCase();

        for (Trainer t : trainers) {
            if (t.getName().toLowerCase().contains(lowerString) ||
                    t.getSex().toLowerCase().contains(lowerString) ||
                    t.getBirthdate().toString().toLowerCase().contains(lowerString) || // Convert date to string for search
                    String.valueOf(t.getTrainerID()).contains(lowerString) || // Convert ID to string for search
                    t.getDescription().toLowerCase().contains(lowerString) ||
                    t.getHometown().toLowerCase().contains(lowerString)) {
                matches.add(t);
            }
        }
        return matches;
    }

    public void populateInitialTrainers() {
        if (trainers.isEmpty()) {
            trainers.add(new Trainer(1001, "Kyle", LocalDate.of(2006, 1, 21), "Female", "Cabanatuan City, Nueva Ecija", "A determined Pokémon Trainer aiming to be a Pokémon Master."));
            trainers.add(new Trainer(1002, "Ella", LocalDate.of(2005, 11, 20), "Female", "Bocaue, Bulacan", "A Pokémon Coordinator with a cheerful personality."));
        }
    }
}
