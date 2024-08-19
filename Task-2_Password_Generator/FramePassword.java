import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/* 
This class is responsible for creating the graphical user interface (GUI) for the Password Generator.
It extends JFrame to create a window and implements ActionListener to define actions for user interactions with the components.
*/
public class FramePassword extends JFrame implements ActionListener {

    // Instance variables for password generation, input fields, and buttons.
    PassGenerate passwordgenerate;
    JTextArea inputlengthpass;
    JToggleButton uppercase;
    JToggleButton lowercase;
    JToggleButton numbers;
    JToggleButton symbols;
    JButton generate;
    JTextArea outputpass;

    // Constructor for initializing the frame and adding components to it when this class is instantiated.
    FramePassword() {

        // Create an instance of PassGenerate to handle password generation logic.
        passwordgenerate = new PassGenerate();

        // Icon for the frame's logo (located in Resources folder).
        ImageIcon imagelogo = new ImageIcon("Resources\\PasswordLogo.png");

        /*
         * 1. Set the title of the window -> Password Generator Application.
         * 2. Close the window when the user clicks the close button ("x").
         * 3. Set the size of the window to 600x600 pixels.
         * 4. Set the background color of the window.
         * 5. Make the window non-resizable.
         * 6. Set the layout manager to null, meaning we manually set positions of components.
         * 7. Center the window on the screen.
         * 8. Set the window icon to a custom image.
         * 9. Add components (e.g. buttons, text areas) to the frame using a separate method.
         * 10. Make the window visible to the user.
         */
        this.setTitle("Password Generator Application"); // 1.
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); // 2.
        this.setSize(600, 600); // 3.
        this.getContentPane().setBackground(new Color(0x024950)); // 4.
        this.setResizable(false); // 5.
        this.setLayout(null); // 6.
        this.setLocationRelativeTo(null); // 7.
        this.setIconImage(imagelogo.getImage()); // 8.

        this.addComponentsToFrame(); // 9.

        this.setVisible(true); // 10.
    }

    // Method to add components (buttons, text areas, etc.) to the frame.
    public void addComponentsToFrame() {

        // Icon for the main heading.
        ImageIcon imageicon = new ImageIcon("Resources\\PassIcon.png");

        // Adding a label for the main heading "Password Generator" at the top.
        JLabel titlelabelpass = new JLabel("Password Generator");
        titlelabelpass.setIcon(imageicon);
        titlelabelpass.setFont(new Font("Monaco", Font.BOLD, 40));
        titlelabelpass.setHorizontalAlignment(SwingConstants.CENTER);
        titlelabelpass.setBounds(-30, -10, 630, 100);
        titlelabelpass.setForeground(Color.WHITE);
        titlelabelpass.setBackground(new Color(0x0FA4AF));
        titlelabelpass.setOpaque(true);
        this.add(titlelabelpass);

        // Adding a label for "Password Length :" below the main heading.
        JLabel lengthpass = new JLabel("Password Length : ");
        lengthpass.setFont(new Font("Monaco", Font.PLAIN, 25));
        lengthpass.setForeground(new Color(0xAFDDE5));
        lengthpass.setBounds(40, 100, 300, 120);
        this.add(lengthpass);

        // Text area for inputting the desired length of the password, located beside the label.
        inputlengthpass = new JTextArea();
        inputlengthpass.setFont(new Font("Dialog", Font.PLAIN, 25));
        inputlengthpass.setBounds(290, 143, 250, 35);
        inputlengthpass.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        inputlengthpass.setBackground(new Color(0xAFDDE5));
        inputlengthpass.setForeground(new Color(0x024950));
        this.add(inputlengthpass);

        /*
         * Adding toggle buttons for:
         * - Uppercase letters
         * - Lowercase letters
         * - Numbers
         * - Symbols
         * These allow users to choose the character set for the password.
         */
        uppercase = new JToggleButton("UpperCase");
        uppercase.setBounds(40, 220, 240, 50);
        uppercase.setFont(new Font("Dialog", Font.BOLD, 20));
        uppercase.setFocusable(false);
        uppercase.setBackground(new Color(0xAFDDE5));
        uppercase.setForeground(new Color(0x024950));
        this.add(uppercase);

        lowercase = new JToggleButton("LowerCase");
        lowercase.setBounds(300, 220, 240, 50);
        lowercase.setFont(new Font("Dialog", Font.BOLD, 20));
        lowercase.setFocusable(false);
        lowercase.setBackground(new Color(0xAFDDE5));
        lowercase.setForeground(new Color(0x024950));
        this.add(lowercase);

        numbers = new JToggleButton("Numbers");
        numbers.setBounds(40, 300, 240, 50);
        numbers.setFont(new Font("Dialog", Font.BOLD, 20));
        numbers.setFocusable(false);
        numbers.setBackground(new Color(0xAFDDE5));
        numbers.setForeground(new Color(0x024950));
        this.add(numbers);

        symbols = new JToggleButton("Symbols");
        symbols.setBounds(300, 300, 240, 50);
        symbols.setFont(new Font("Dialog", Font.BOLD, 20));
        symbols.setFocusable(false);
        symbols.setBackground(new Color(0xAFDDE5));
        symbols.setForeground(new Color(0x024950));
        this.add(symbols);

        // Button to generate the password based on the user's selections.
        generate = new JButton("Generate Password");
        generate.setBounds(140, 385, 300, 50);
        generate.setFont(new Font("Dialog", Font.BOLD, 20));
        generate.setFocusable(false);
        generate.setForeground(new Color(0x024950));
        generate.addActionListener(this);
        this.add(generate);

        // Text area for displaying the generated password, non-editable.
        outputpass = new JTextArea();
        outputpass.setEditable(false);
        outputpass.setFont(new Font("Dialog", Font.PLAIN, 25));
        outputpass.setBounds(40, 470, 500, 40);
        outputpass.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        outputpass.setBackground(new Color(0xAFDDE5));
        this.add(outputpass);
    }

    // This method is invoked when an action is performed (e.g. clicking the Generate button).
    @Override
    public void actionPerformed(ActionEvent e) {

        // Handling possible exceptions if the input length is not a valid integer.
        try {
            // Check if the password length is entered and valid, otherwise clear the output.
            if (inputlengthpass.getText().length() == 0 || Integer.parseInt(inputlengthpass.getText()) <= 0) {
                outputpass.setText("");
                return;
            }

            // Check if at least one toggle button is selected to include a character type.
            boolean togglebuttonselected = uppercase.isSelected()
                    || lowercase.isSelected()
                    || numbers.isSelected()
                    || symbols.isSelected();

            // Generate the password only if at least one character type is selected.
            if (togglebuttonselected) {
                int length = Integer.parseInt(inputlengthpass.getText());

                // Call the generatePassword method of PassGenerate to create the password.
                final String password = passwordgenerate.generatePassword(length,
                        uppercase.isSelected(),
                        lowercase.isSelected(),
                        numbers.isSelected(),
                        symbols.isSelected());

                // Display the generated password in the output text area.
                outputpass.setText(password);

                // Print the encoded version of the generated password to the console.
                EncodedFormOfPassword encodedformpass = new EncodedFormOfPassword();
                System.out.println("Encoded Form of Generated Password: " + encodedformpass.getEncodedForm(password));

            } else {
                outputpass.setText(""); // Clear output if no character type is selected.
            }

        } catch (NumberFormatException n) {
            outputpass.setText(""); // Clear output if an invalid number is entered.
            System.out.println("Password Length Should Be Of Integer Type."); // Display error in the console.
        }
    }
}
