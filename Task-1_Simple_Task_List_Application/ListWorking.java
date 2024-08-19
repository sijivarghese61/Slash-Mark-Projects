import java.util.ArrayList;
import java.util.Scanner;

// This class contains the logic for managing the task list, including adding, removing, displaying, and updating tasks.
public class ListWorking {

    // ArrayList to store the tasks as Strings.
    ArrayList<String> arraylist = new ArrayList<>();

    // Method to add a new task to the list.
    public void addTask(Scanner scanner) {
        System.out.print("Enter a new task to add to Task List: ");
        String newtask = scanner.nextLine(); // Read the new task input from the user.
        arraylist.add(newtask); // Add the task to the ArrayList.
        System.out.println("Task '" + newtask + "' added Successfully!");
    }

    // Method to remove a task from the list.
    public void removeTask(Scanner scanner) {
        // Display the current list of tasks before asking the user to choose which one
        // to remove.
        listTask();
        System.out.print("Enter the Task No. you wish to remove: ");
        int taskno = scanner.nextInt(); // Read the task number to be removed.
        if (0 < taskno && taskno <= arraylist.size()) {
            // Remove the task if the task number is valid.
            arraylist.remove(taskno - 1);
            System.out.println("Task removed successfully!");
        } else {
            // Handle invalid task numbers.
            System.out.println("Invalid Task No.! Try again with a valid Task No.");
        }
    }

    // Method to display the current list of tasks.
    public void listTask() {
        for (int i = 0; i < arraylist.size(); i++) {
            // Print the task number and task description.
            System.out.println((i + 1) + ". " + arraylist.get(i));
        }
    }

    // Method to update a specific task in the list.
    public void updateTask(Scanner scanner) {
        // Display the current list of tasks before asking the user which one to update.
        listTask();
        System.out.print("Enter the Task No. you wish to Update: ");
        int taskno = scanner.nextInt(); // Read the task number to be updated.
        scanner.nextLine(); // Consume newline character.
        if (0 < taskno && taskno <= arraylist.size()) {
            // Prompt the user to enter the updated task if the task number is valid.
            System.out.print("Enter the Updated version of the task: ");
            String updatedtask = scanner.nextLine(); // Read the updated task.
            arraylist.set(taskno - 1, updatedtask); // Update the task in the ArrayList.
            System.out.println("Task Updated Successfully!");
        } else {
            // Handle invalid task numbers.
            System.out.println("Invalid Task No.! Try again with a valid Task No.");
        }
    }

    // Method to check if the task list is empty.
    public boolean empty() {
        return arraylist.isEmpty(); // Return true if the ArrayList is empty, false otherwise.
    }
}
