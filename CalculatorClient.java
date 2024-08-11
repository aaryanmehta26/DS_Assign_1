import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CalculatorClient {

    // Stores the session ID for the current client (Separate stack for each client)
    private static String sessionId;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your client Session ID: ");
        sessionId = scanner.nextLine();

        try {
            // Connect to the RMI registry
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            Calculator calculator = (Calculator) registry.lookup("CalculatorService");

            boolean running = true;

            while (running) {
                // user options
                System.out.println("\nCalculator Client Menu:");
                System.out.println("1. Push Value");
                System.out.println("2. Perform Operation");
                System.out.println("3. Pop Value");
                System.out.println("4. Check if Stack is Empty");
                System.out.println("5. Print Stack");
                System.out.println("6. Exit");

               // Getting user choice
                int choice = getUserInput("Select an option (1-6): ");

                switch (choice) {
                    case 1:
                        int value = getUserInput("Enter value to push: ");
                        calculator.pushValue(sessionId, value);
                        System.out.println("Value pushed: " + value);
                        break;

                    case 2:
                        System.out.println("Available operations: min, max, lcm, gcd");
                        String operation = getStringInput("Enter operation to perform: ");
                        calculator.pushOperation(sessionId, operation);
                        System.out.println("Operation performed: " + operation);
                        break;

                    case 3:
                        int poppedValue = calculator.pop(sessionId);
                        System.out.println("Value popped: " + poppedValue);
                        break;

                    case 4:
                        boolean isEmpty = calculator.isEmpty(sessionId);
                        System.out.println("Stack is empty: " + isEmpty);
                        break;

                    case 5:
                        printStack(calculator);
                        break;

                    case 6:
                        running = false;
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                        break;
                }
            }

            scanner.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

     /**
     * Prompts the user to enter an integer value.
     * @param msg The prompt message to display to the user.
     */
    private static int getUserInput(String msg) {
        Scanner scanner = new Scanner(System.in);
        int input = -1;
        boolean valid = false;

        while (!valid) {
            try {
                System.out.print(msg);
                input = scanner.nextInt();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next(); // clearing Input
            }
        }
        return input;
    }

    private static String getStringInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        return scanner.nextLine();
    }

    /**
     * Prints the current stack contents of the client's stack.
     * 
     * @param calculator Calculator service to get stack values.
     */
    private static void printStack(Calculator calculator) {
        try {
            List<Integer> stack = calculator.getStack(sessionId);
            System.out.print("Current stack contents: ");
            if (stack.isEmpty()) {
                System.out.println("Empty");
            } else {
                for (int i = 0; i < stack.size(); i++) {
                    System.out.print(stack.get(i));
                    if (i < stack.size() - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Error retrieving stack: " + e.getMessage());
        }
    }
}