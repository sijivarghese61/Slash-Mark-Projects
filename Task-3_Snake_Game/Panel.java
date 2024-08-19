import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class Panel extends JPanel implements ActionListener, KeyListener {

    // Timer to control the snake's movement speed
    Timer timer;
    private int delay = 100; // Time interval in milliseconds between each action

    // Variables to track game state
    private int score = 0; // Score keeps track of how many apples the snake has eaten
    private int moves = 0; // Used to initialize the snake's starting position and movement
    private int length = 3; // Initial length of the snake
    private boolean gameover = false; // Flag to check if the game is over

    // Apple and score display icons
    ImageIcon apple = new ImageIcon("Resources\\apple.png");
    ImageIcon appleForScore = new ImageIcon("Resources\\appleForScore.png");

    // Random object to generate random positions for the apple
    Random random;
    private int appleX; // X-axis position of the apple
    private int appleY; // Y-axis position of the apple

    // Possible positions for the apple along the X and Y axes
    private int[] alocX = { 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475,
            500, 525, 550, 575, 600, 625, 650, 675, 700, 725 }; // All possible values of apple at X-axis.
    private int[] alocY = { 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475,
            500, 525, 550, 575, 600, 625, 650, 675, 700, 725 }; // All possible values of apple at Y-axis.

    // Unit size of each snake segment and the apple
    private int UNITSIZE = 25;

    // Arrays to store the X and Y positions of the snake's body
    private int[] snakeX = new int[750];
    private int[] snakeY = new int[750];

    // Variables to track the direction of the snake's movement
    private boolean left = false;
    private boolean right = true; // Initially moving right
    private boolean up = false;
    private boolean down = false;

    // Constructor to initialize the game panel
    public Panel() {
        timer = new Timer(delay, this); // Set timer for snake movement
        timer.start(); // Start the timer

        random = new Random(); // Initialize random object for apple placement
        newApple(); // Generate the first apple

        addKeyListener(this); // Add key listener to detect user input
        setFocusable(true); // Enable the panel to receive keyboard events
        setFocusTraversalKeysEnabled(true); // Allow key traversal in the panel
    }

    // Method to paint game elements on the screen
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Draw the score on the screen using the apple icon
        appleForScore.paintIcon(this, g, 650, 10);
        g.setColor(Color.GREEN);
        g.setFont(new Font("Dialog", Font.PLAIN, 40));
        g.drawString(": " + score, 700, 55);

        // Initialize snake's position at the start of the game
        if (moves == 0) {
            snakeX[0] = 100;
            snakeX[1] = 75;
            snakeX[2] = 50;
            snakeY[0] = 50;
            snakeY[1] = 50;
            snakeY[2] = 50;
        }

        // Draw the snake's head (cyan color)
        g.setColor(Color.CYAN);
        g.drawRect(snakeX[0], snakeY[0], UNITSIZE, UNITSIZE);
        g.fillRect(snakeX[0], snakeY[0], UNITSIZE, UNITSIZE);

        // Draw the snake's body
        for (int i = length - 1; i > 0; i--) {
            g.drawRect(snakeX[i], snakeY[i], UNITSIZE, UNITSIZE);
        }

        // Draw the apple on the screen
        apple.paintIcon(this, g, appleX, appleY);

        // Display game over message when the game is over
        if (gameover) {
            timer.stop(); // Stop the timer when the game is over
            g.setColor(Color.MAGENTA);
            g.setFont(new Font("Dialog", Font.BOLD, 90));
            g.drawString("Game Over", 150, 350);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Dialog", Font.PLAIN, 30));
            g.drawString("Your Score is: " + score, 290, 400);
            g.drawString("Press 'SPACE' button to Restart.", 170, 450);
        }
    }

    // Method to generate a new apple at a random position
    private void newApple() {
        appleX = alocX[random.nextInt(alocX.length)];
        appleY = alocY[random.nextInt(alocY.length)];
    }

    // Method to check if the snake has eaten the apple
    private void eatApple() {
        if (appleX == snakeX[0] && appleY == snakeY[0]) {
            score++; // Increase the score
            length++; // Increase the length of the snake
            snakeX[length - 1] = snakeX[length - 2]; // Extend the snake's tail
            snakeY[length - 1] = snakeY[length - 2];
            newApple(); // Generate a new apple
        }
    }

    // Method to restart the game when the user presses the space bar
    private void restart() {
        score = 0; // Reset score
        moves = 0; // Reset moves
        length = 3; // Reset snake length
        gameover = false; // Reset gameover flag

        // Reset direction to initial state
        left = false;
        right = true;
        up = false;
        down = false;

        newApple(); // Generate a new apple
        timer.restart(); // Restart the timer
        repaint(); // Repaint the game screen
    }

    // Method to handle the snake's movement and check for collisions
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = length - 1; i > 0; i--) {
            snakeX[i] = snakeX[i - 1]; // Move the body segments
            snakeY[i] = snakeY[i - 1];
        }

        // Move the snake's head in the current direction
        if (left) {
            snakeX[0] -= UNITSIZE;
        } else if (right) {
            snakeX[0] += UNITSIZE;
        } else if (up) {
            snakeY[0] -= UNITSIZE;
        } else if (down) {
            snakeY[0] += UNITSIZE;
        }

        // Teleport the snake if it moves off the screen (wrapping behavior)
        if (snakeX[0] > 775)
            snakeX[0] = 0;
        if (snakeX[0] < 0)
            snakeX[0] = 775;
        if (snakeY[0] > 775)
            snakeY[0] = 0;
        if (snakeY[0] < 0)
            snakeY[0] = 775;

        eatApple(); // Check if the snake eats an apple

        // Check for collisions with the snake's own body
        for (int i = length - 1; i > 0; i--) {
            if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i])
                gameover = true; // Set game over flag if collision occurs
        }
        repaint(); // Repaint the game screen
    }

    // Method to handle key presses for controlling the snake
    @Override
    public void keyPressed(KeyEvent e) {
        // Restart the game if the space bar is pressed during game over
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (gameover) {
                restart();
            }
        }

        // Change the direction of the snake based on arrow key presses
        if (e.getKeyCode() == KeyEvent.VK_LEFT && !right) {
            left = true;
            up = false;
            down = false;
            moves++;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && !left) {
            right = true;
            up = false;
            down = false;
            moves++;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP && !down) {
            up = true;
            left = false;
            right = false;
            moves++;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN && !up) {
            down = true;
            left = false;
            right = false;
            moves++;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Unused method required by the KeyListener interface
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Unused method required by the KeyListener interface
    }
}