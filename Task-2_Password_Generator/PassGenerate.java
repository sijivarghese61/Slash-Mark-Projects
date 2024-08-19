// Importing the Random class to generate random numbers for password creation.
import java.util.Random;

// This class handles the logic for generating passwords based on user specifications.
public class PassGenerate {

    // String variables to hold possible characters for each type of character set.
    private final String upperletters = "QWERTYUIOPASDFGHJKLZXCVBNM"; // Uppercase letters
    private final String lowerletters = "qwertyuiopasdfghjklzxcvbnm"; // Lowercase letters
    private final String numberset = "1234567890"; // Numeric digits
    private final String symbolset = "`~!@#$%^&*()_-+={}|;:'<,>.?/\\\"[]"; // Special symbols

    // Random object to generate random numbers for selecting characters.
    private final Random random = new Random();

    /*
     * Generates a password based on the specified parameters.
     * 
     * @param length The desired length of the password.
     * 
     * @param uppercase Whether to include uppercase letters.
     * 
     * @param lowercase Whether to include lowercase letters.
     * 
     * @param numbers Whether to include numeric digits.
     * 
     * @param symbols Whether to include special symbols.
     * 
     * @return A randomly generated password as a String.
     */
    public String generatePassword(int length,
            boolean uppercase,
            boolean lowercase,
            boolean numbers,
            boolean symbols) {

        // StringBuilder to build the password.
        StringBuilder password = new StringBuilder();

        // String to store valid characters based on user selections.
        String validchar = "";

        // Strings to store at least one random character of each selected type.
        String u = ""; // Uppercase letter
        String l = ""; // Lowercase letter
        String n = ""; // Numeric digit
        String s = ""; // Special symbol

        // Add uppercase letters to valid characters and select one random uppercase
        // letter.
        if (uppercase) {
            validchar += upperletters;
            int indexno = random.nextInt(upperletters.length());
            u += upperletters.charAt(indexno);
        }
        // Add lowercase letters to valid characters and select one random lowercase
        // letter.
        if (lowercase) {
            validchar += lowerletters;
            int indexno = random.nextInt(lowerletters.length());
            l += lowerletters.charAt(indexno);
        }
        // Add numeric digits to valid characters and select one random digit.
        if (numbers) {
            validchar += numberset;
            int indexno = random.nextInt(numberset.length());
            n += numberset.charAt(indexno);
        }
        // Add special symbols to valid characters and select one random symbol.
        if (symbols) {
            validchar += symbolset;
            int indexno = random.nextInt(symbolset.length());
            s += symbolset.charAt(indexno);
        }

        /*
         * If the desired password length is 4 or greater, ensure the password contains
         * at least one character from each selected type. Fill the rest of the password
         * with
         * randomly selected characters from the valid character set.
         */
        if (length >= 4) {
            // Append one random character from each selected type.
            password.append(s + u + n + l);
            for (int i = password.length(); i < length; i++) {
                int indexno = random.nextInt(validchar.length());
                password.append(validchar.charAt(indexno));
            }
        } else {
            // If the desired length is less than 4, simply generate a password of the
            // specified length
            // using randomly selected characters from the valid character set.

            for (int i = 1; i <= length; i++) {
                int indexno = random.nextInt(validchar.length());
                password.append(validchar.charAt(indexno));
            }
        }

        // Convert the StringBuilder to a String and return the generated password.
        return password.toString();
    }
}
