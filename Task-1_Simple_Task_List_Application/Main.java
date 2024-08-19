import java.util.Scanner;

// This class contains the main method that serves as the entry point of the Task List Application.
// It presents a menu to the user and handles the choices using a switch-case structure.
public class MainListApp {
    public static void main(String[] args) {

        // Create an instance of ListWorking class to manage tasks.
        ListWorking listwork = new ListWorking();

        // Scanner object to receive input from the user.
        Scanner scan = new Scanner(System.in);

        // Infinite loop to keep showing the menu until the user decides to exit.
        while (true) {
            // Display the menu options.
            menuOptions();

            // Read the user's choice and handle invalid input.
            int userchoice = scan.nextInt();
            scan.nextLine(); // Consume newline left-over when the user presses Enter after their choice.

            // Switch-case to handle different choices based on user's input.
            switch (userchoice) {
                case 1:
                    // Call method to add a new task.
                    listwork.addTask(scan);
                    break;
                case 2:
                    // Check if the task list is empty before attempting to remove a task.
                    if (listwork.empty()) {
                        System.out.println("No Task to remove as List is EMPTY. Try adding a new task.");
                    } else {
                        // Call method to remove a task.
                        listwork.removeTask(scan);
                    }
                    break;
                case 3:
                    // Check if the task list is empty before showing tasks.
                    if (listwork.empty()) {
                        System.out.println("Nothing to show as Task List is EMPTY. Try adding a new task.");
                    } else {
                        // Call method to display the list of tasks.
                        listwork.listTask();
                    }
                    break;
                case 4:
                    // Check if the task list is empty before attempting to update a task.
                    if (listwork.empty()) {
                        System.out.println("Can't perform updation as Task List is EMPTY. Try adding a new task.");
                    } else {
                        // Call method to update a task.
                        listwork.updateTask(scan);
                    }
                    break;
                case 5:
                    // Exit the application.
                    System.out.println("Exiting ... ");
                    scan.close(); // Close the Scanner object to prevent resource leaks.
                    return; // Exit the loop and terminate the program.
                default:
                    // Handle invalid input by the user.
                    System.out.println("Invalid Choice! Try again with a valid number.");
            }
        }
    }

    // Method to display the menu options to the user.
    public static void menuOptions() {
        System.out.println();
        System.out.println("--------------------Task List Application--------------------");
        System.out.println("Options:");
        System.out.println("1. Add a Task.");
        System.out.println("2. Remove a Task.");
        System.out.println("3. Show Task List.");
        System.out.println("4. Update a Task.");
        System.out.println("5. EXIT.");
        System.out.print("Select an Option NO. (between 1-5): ");
    }
}
