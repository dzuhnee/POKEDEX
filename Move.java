import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Move {

    private final String name;
    private final String description;
    private final Classification classification;
    private final String primaryType;
    private final String secondaryType;

    public Move(String name, String description, Classification classification, String primaryType,
                String secondaryType) {
        this.name = name;
        this.description = description;
        this.classification = classification;
        this.primaryType = primaryType;
        this.secondaryType = secondaryType;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Classification getClassification() {
        return classification;
    }

    public String getPrimaryType() {
        return primaryType;
    }

    public String getSecondaryType() {
        return secondaryType;
    }

    public enum Classification {
        HM,
        TM
    }
}

class MoveInputHelper {

    private final Scanner scanner;

    public MoveInputHelper(final Scanner scanner) {
        this.scanner = scanner;
    }

    public String inputMoveName(MoveManager manager) {
        String name;

        while (true) {
            System.out.print("Enter Move Name: ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Name cannot be empty.");
            } else if (input.length() > 20) {
                System.out.println("Name is too long. Maximum length is 20 characters.");
            } else if (!input.matches("[A-Za-z0-9.\\-\\s'()]+")) {
                System.out.println(
                        "Invalid characters in name. Only letters, numbers, dashes, apostrophes, periods, parentheses, and spaces are allowed.");
            } else {
                name = input.substring(0, 1).toUpperCase() + input.substring(1);
                if (manager.isMoveNameTaken(name)) {
                    System.out.println("This move name already exists. Please enter a different one.");
                } else {
                    break;
                }
            }
        }
        return name;
    }

    public String inputMoveDescription() {
        String description;

        while (true) {
            System.out.print("Enter move description/effect: ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Description cannot be empty.");
            } else if (input.length() > 120) {
                System.out.println("Description is too long. Keep it under 120 characters.");
            } else if (!input.matches("[A-Za-z0-9.,'\\-()/\\%\\s]+")) {
                System.out.println(
                        "Invalid characters in description. Only letters, numbers, spaces, and . , - ' ( ) / % are allowed.");
            } else if (!input.endsWith(".")) {
                System.out.println("Description should end with a period.");
            } else {
                description = input.substring(0, 1).toUpperCase() + input.substring(1);
                break;
            }
        }
        return description;
    }

    public Move.Classification inputMoveClassification() {
        while (true) {
            System.out.print("Enter move classification (HM or TM): ");
            String input = scanner.nextLine().trim().toUpperCase();

            try {
                return Move.Classification.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid classification. Please enter either 'HM' or 'TM'.");
            }
        }
    }

    public String inputMoveTyping(String typeName) {
        while (true) {
            System.out.print("Enter " + typeName + " Type: ");
            String input = scanner.nextLine().trim();

            if (!input.isEmpty() && input.matches("[A-Za-z]+")) {
                return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
            } else {
                System.out.println("Invalid type. Only letters are allowed.");
            }
        }
    }

    public String inputSecondaryMoveType(String typeName) {
        System.out.print("Does this move have a Secondary Type? (0 = No, 1 = Yes): ");
        String input = scanner.nextLine().trim();

        if (input.equals("1")) {
            return inputMoveTyping(typeName);
        } else if (input.equals("0")) {
            return "";
        } else {
            System.out.println("Invalid input. Assuming no secondary type.");
            return "";
        }
    }
}

class MoveManager {

    private final List<Move> moveList;
    private final MoveInputHelper inputHelper;
    private final Scanner scanner;

    public MoveManager(final Scanner scanner) {
        inputHelper = new MoveInputHelper(scanner);
        moveList = new ArrayList<>();
        this.scanner = scanner;
    }

    public void addMove() {
        String name = inputHelper.inputMoveName(this);
        String description = inputHelper.inputMoveDescription();
        Move.Classification classification = inputHelper.inputMoveClassification();
        String primaryType = inputHelper.inputMoveTyping("primary");
        String secondaryType;

        while (true) {
            secondaryType = inputHelper.inputSecondaryMoveType("secondary");
            if (secondaryType.equalsIgnoreCase(primaryType)) {
                System.out.println("Secondary type must be different from primary type.");
            } else {
                break;
            }
        }

        Move newMove = new Move(name, description, classification, primaryType, secondaryType);
        moveList.add(newMove);

        System.out.println("Move added: " + newMove.getName() +
                " (" + newMove.getClassification() + ")");
    }

    public boolean isMoveNameTaken(String name) {
        for (Move m : moveList) {
            if (m.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public void viewAllMovesAvailable() {
        if (moveList.isEmpty()) {
            System.out.println("\nSystem: No moves in the database.");
        } else {
            System.out.printf("%-20s %-10s %-15s %-55s\n", "Move Name", "Class", "Type(s)", "Description");
            System.out.println("-------------------------------------------------------------------------------------------");
    
            for (Move m : moveList) {
                String types = m.getPrimaryType();
                if (m.getSecondaryType() != null && !m.getSecondaryType().isEmpty()) {
                    types += "/" + m.getSecondaryType();
                }
    
                String description = m.getDescription();
                int descWidth = 55;
    
                if (description.length() <= descWidth) {
                    System.out.printf("%-20s %-10s %-15s %-55s\n",
                            m.getName(),
                            m.getClassification(),
                            types,
                            description);
                } else {
                    System.out.printf("%-20s %-10s %-15s %-55s\n",
                            m.getName(),
                            m.getClassification(),
                            types,
                            description.substring(0, descWidth));
    
                    int start = descWidth;
                    while (start < description.length()) {
                        int end = Math.min(start + descWidth, description.length());
                        String line = description.substring(start, end);
                        System.out.printf("%-20s %-10s %-15s %-55s\n", "", "", "", line);
                        start += descWidth;
                    }
                }
            }
        }
    }    

    public void handleMoveSearch() {
        System.out.println("\n--- Search Pokémon Moves ---");
        System.out.println("1. By Name or Effect");
        System.out.println("2. By Type");
        System.out.println("3. By Classification (HM/TM)");
        System.out.print("Enter option: ");

        String option = scanner.nextLine().trim();

        switch (option) {
            case "1":
                System.out.print("Enter keyword (name or effect): ");
                String keyword = scanner.nextLine().trim();
                searchByNameOrEffect(keyword);
                break;
            case "2":
                System.out.print("Enter type (e.g., Fire, Water): ");
                String type = scanner.nextLine().trim();
                searchByType(type);
                break;
            case "3":
                System.out.print("Enter classification (HM or TM): ");
                String classInput = scanner.nextLine().trim().toUpperCase();
                try {
                    Move.Classification classification = Move.Classification.valueOf(classInput);
                    searchByClassification(classification);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid classification. Please enter HM or TM.");
                }
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    public void searchByNameOrEffect(String keyword) {
        List<Move> results = new ArrayList<>();
        for (Move m : moveList) {
            if (m.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                m.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(m);
            }
        }
        showSearchResults(results, "Keyword: " + keyword);
    }

    public void searchByType(String type) {
        List<Move> results = new ArrayList<>();
        for (Move m : moveList) {
            boolean matchesPrimary = m.getPrimaryType().equalsIgnoreCase(type);
            boolean matchesSecondary =
                    m.getSecondaryType() != null && m.getSecondaryType().equalsIgnoreCase(type);
            if (matchesPrimary || matchesSecondary) {
                results.add(m);
            }
        }
        showSearchResults(results, "Type: " + type);
    }

    public void searchByClassification(Move.Classification classification) {
        List<Move> results = new ArrayList<>();
        for (Move m : moveList) {
            if (m.getClassification() == classification) {
                results.add(m);
            }
        }
        showSearchResults(results, "Classification: " + classification);
    }

    public void showSearchResults(List<Move> results, String title) {
        if (results.isEmpty()) {
            System.out.println("No moves found for " + title);
            return;
        }

        System.out.println("Results for " + title);
        System.out.printf("%-20s %-10s %-20s\n", "Move Name", "Class", "Type(s)");
        System.out.println("-----------------------------------------------");

        for (Move m : results) {
            String types = m.getPrimaryType();
            if (m.getSecondaryType() != null && !m.getSecondaryType().isEmpty()) {
                types += "/" + m.getSecondaryType();
            }
            System.out.printf("%-20s %-10s %-20s\n",
                    m.getName(),
                    m.getClassification(),
                    types);
        }
    }

    public void loadDefaultMoves() {
        Move tackle = new Move("Tackle",
                "Tackle is one of the most common and basic moves a Pokémon learns. It deals damage with no additional effects.",
                Move.Classification.TM, "Normal", "");
        Move defend = new Move("Defend", "Raises user's defense stat temporarily.",
                Move.Classification.TM, "Normal", "");

        moveList.add(tackle);
        moveList.add(defend);
    }
}
