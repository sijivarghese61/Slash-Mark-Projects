import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {

        // Load the game icon image
        ImageIcon icon = new ImageIcon("Resources\\Snakelogo.jpg");

        // Create an instance of the Panel class which contains the game logic
        Panel panel = new Panel();

        // Create and set up the game window (JFrame)
        JFrame frame = new JFrame("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close application when window is closed
        frame.setBounds(350, 10, 800, 800); // Set size and position of the window
        frame.setIconImage(icon.getImage()); // Set icon for the window
        frame.setResizable(false); // Prevent resizing of the window

        // Set the background of the panel to black and add it to the frame
        panel.setBackground(Color.BLACK);
        frame.add(panel);

        // Make the window visible
        frame.setVisible(true);
    }
}
