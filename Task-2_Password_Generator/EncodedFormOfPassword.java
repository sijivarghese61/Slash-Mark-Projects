import java.util.Base64;
import java.util.Base64.Encoder;

// This class provides functionality to encode a given password using Base64 encoding.
public class EncodedFormOfPassword {

    // Encoder object for performing Base64 encoding.
    private final Encoder encoder;

    /*
     * Constructor initializes the Base64 encoder.
     */
    EncodedFormOfPassword() {
        encoder = Base64.getEncoder(); // Create an Encoder instance to perform Base64 encoding.
    }

    /*
     * Encodes the given password using Base64 encoding.
     * 
     * @param password The password to be encoded.
     * @return The Base64 encoded form of the password.
     */
    public String getEncodedForm(String password) {

        // Convert the given password to a byte array and then encode it to a Base64 encoded String.
        final String encodedpass = encoder.encodeToString(password.getBytes());
        return encodedpass; // Return the Base64 encoded password.
    }
}
