import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// This class represents a Pokémon move
public class Move {

    private final String name;
    private final String description;
    private final Classification classification;
    private final String primaryType;
    private final String secondaryType;

    // Constructor to initialize all the move properties
    public Move(String name, String description, Classification classification, String primaryType,
                String secondaryType) {
        this.name = name;
        this.description = description;
        this.classification = classification;
        this.primaryType = primaryType;
        this.secondaryType = secondaryType;
    }

    // Getters
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

// Helper class for taking validated user input when adding a move
class MoveInputHelper {

    private final Scanner scanner;

    public MoveInputHelper(final Scanner scanner) {
        this.scanner = scanner;
    }

    // Lets the user input a valid move name, checks for duplicates & invalid characters
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

    // Lets the user input a valid description for the move
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

    // Lets the user choose between HM and TM
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

    // Lets the user enter a move's type (e.g., Fire, Water)
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

    // Optional input: asks if the move has a second type
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

// Manager class that handles move operations like adding, searching, viewing
class MoveManager {

    private final List<Move> moveList;
    private final MoveInputHelper inputHelper;
    private final Scanner scanner;

    public MoveManager(final Scanner scanner) {
        inputHelper = new MoveInputHelper(scanner);
        moveList = new ArrayList<>();
        this.scanner = scanner;
    }

    // Adds a new move, makes sure everything is valid
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

     // Checks if a move name is already used
    public boolean isMoveNameTaken(String name) {
        for (Move m : moveList) {
            if (m.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    // Displays all the moves in a clean, tabular format
    public void viewAllMovesAvailable() {
        if (moveList.isEmpty()) {
            System.out.println("\nSystem: No moves in the database.");
        } else {
            System.out.printf("%-20s %-10s %-15s %-55s\n", "Move Name", "Class", "Type(s)", "Description");
            System.out.println("=======================================================================================================");
    
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

    // Shows the search menu and handles search based on user choice
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

    // Search for moves that match a keyword in name or effect
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

    // Search for moves by type (either primary or secondary)
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

    // Search for moves by classification (HM or TM)
    public void searchByClassification(Move.Classification classification) {
        List<Move> results = new ArrayList<>();
        for (Move m : moveList) {
            if (m.getClassification() == classification) {
                results.add(m);
            }
        }
        showSearchResults(results, "Classification: " + classification);
    }

    // Displays the search results
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

    // Adds two default sample moves when the program starts
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
