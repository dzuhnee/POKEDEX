import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a Pokémon move with a name, description, classification (HM/TM),
 * and types (primary and optional secondary).
 */
public class Move {

    private final String name;
    private final String description;
    private final Classification classification;
    private final String primaryType;
    private final String secondaryType;

    /**
     * Constructs a Move with the specified details.
     *
     * @param name            the name of the move
     * @param description     the description or effect of the move
     * @param classification  whether the move is an HM or TM
     * @param primaryType     the primary type of the move (e.g., Fire, Water)
     * @param secondaryType   the secondary type of the move (optional, can be empty)
     */
    public Move(String name, String description, Classification classification, String primaryType,
                String secondaryType) {
        this.name = name;
        this.description = description;
        this.classification = classification;
        this.primaryType = primaryType;
        this.secondaryType = secondaryType;
    }

    // Getters

    /** @return the name of the move */
    public String getName() {
        return name;
    }

    /** @return the move's description or effect */
    public String getDescription() {
        return description;
    }

    /** @return the classification of the move (HM or TM) */
    public Classification getClassification() {
        return classification;
    }

    /** @return the move's primary type */
    public String getPrimaryType() {
        return primaryType;
    }

    /** @return the move's secondary type (can be empty) */
    public String getSecondaryType() {
        return secondaryType;
    }

    /**
     * Enum representing a move's classification: either HM or TM.
     */
    public enum Classification {
        HM,
        TM
    }
}

/**
 * Helper class that provides validated user input functionality for creating moves.
 */
class MoveInputHelper {

    private final Scanner scanner;

    /**
     * Constructs a MoveInputHelper using a provided Scanner.
     *
     * @param scanner the Scanner to read user input
     */
    public MoveInputHelper(final Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Prompts the user to input a valid move name, checking for duplicates and formatting.
     *
     * @param manager the MoveManager to check for duplicate names
     * @return a valid, formatted move name
     */
    public String inputMoveName(MoveManager manager) {
        String name;

        while (true) {
            System.out.print("Enter move name: ");
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

    /**
     * Prompts the user to input a valid move description.
     *
     * @return a validated and formatted move description
     */
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

    /**
     * Prompts the user to input a move classification (HM or TM).
     *
     * @return the selected Classification
     */
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

    /**
     * Prompts the user to enter a valid type for a move.
     *
     * @param typeName the label for the type (e.g., "primary" or "secondary")
     * @return a properly capitalized valid type string
     */
    public String inputMoveTyping(String typeName) {
        while (true) {
            System.out.print("Enter " + typeName + " type: ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (TypeUtils.isValidType(input)) {
                return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
            } else {
                System.out.println("Invalid type. Please enter a valid Pokémon type.");
            }
        }
    }

    /**
     * Asks the user if the move has a secondary type and captures it.
     *
     * @param typeName the label for the type
     * @return the secondary type if provided, otherwise an empty string
     */
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

/**
 * Manages a collection of Pokémon moves, including adding, viewing, and searching.
 */
class MoveManager {

    private final List<Move> moveList;
    private final MoveInputHelper inputHelper;
    private final Scanner scanner;

    /**
     * Constructs a MoveManager instance with user input capabilities.
     *
     * @param scanner the Scanner to read user input
     */
    public MoveManager(final Scanner scanner) {
        inputHelper = new MoveInputHelper(scanner);
        moveList = new ArrayList<>();
        this.scanner = scanner;
    }

    /**
     * Prompts the user to input a new move and adds it to the list if valid.
     */
    public void addMove() {
        String name = inputHelper.inputMoveName(this);
        String description = inputHelper.inputMoveDescription();
        Move.Classification classification = inputHelper.inputMoveClassification();
        System.out.println("Choose from: normal, fire, water, electric, grass, ice, fighting, poison, ground, \nflying, psychic, bug, rock, ghost, dragon, dark, steel, fairy");
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

    /**
     * Checks if a move name already exists in the list.
     *
     * @param name the move name to check
     * @return true if name is taken, false otherwise
     */
    public boolean isMoveNameTaken(String name) {
        for (Move m : moveList) {
            if (m.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Displays all moves in a tabular format with name, classification, types, and description.
     */
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

    /**
     * Displays a menu and lets the user choose how to search for a move.
     */
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
                System.out.print("Enter type (\"Choose from: normal, fire, water, electric, grass, ice, fighting, poison, ground, \\n" + //
                        "flying, psychic, bug, rock, ghost, dragon, dark, steel, fairy\"): ");
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

     /**
     * Searches for moves containing the given keyword in name or effect.
     *
     * @param keyword the keyword to search
     */
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

    /**
     * Searches for moves by type.
     *
     * @param type the type to match (primary or secondary)
     */
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

    /**
     * Searches for moves by classification.
     *
     * @param classification the classification to search (HM or TM)
     */
    public void searchByClassification(Move.Classification classification) {
        List<Move> results = new ArrayList<>();
        for (Move m : moveList) {
            if (m.getClassification() == classification) {
                results.add(m);
            }
        }
        showSearchResults(results, "Classification: " + classification);
    }

    /**
     * Displays a formatted list of matching moves.
     *
     * @param results the list of matching moves
     * @param title   the header/title for the search result
     */
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

    /**
     * Loads two default sample moves when the program starts.
     */
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
