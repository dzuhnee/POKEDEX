public class TypeUtils {
    private static final String[] VALID_TYPES = {
        "normal", "fire", "water", "electric", "grass", "ice",
        "fighting", "poison", "ground", "flying", "psychic", "bug",
        "rock", "ghost", "dragon", "dark", "steel", "fairy"
    };

    public static boolean isValidType(String type) {
        for (String validType : VALID_TYPES) {
            if (validType.equalsIgnoreCase(type)) {
                return true;
            }
        }
        return false;
    }

    public static String getValidTypesAsString() {
        return String.join(", ", VALID_TYPES);
    }
}
