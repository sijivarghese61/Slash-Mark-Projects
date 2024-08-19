// This is the main class that serves as the entry point to the Password Generator application.
// It is responsible for launching the initial frame where the user can interact with the application to generate passwords.
public class Main {
    public static void main(String[] args) {

        // Create a new instance of the FramePassword class to open the Password Generator frame.
        // This creates a GUI window for the user to generate passwords.
        // Since the FramePassword object is not used again after its creation,
        // it is instantiated without being assigned to a variable (anonymous object).
        new FramePassword();
    }
}
