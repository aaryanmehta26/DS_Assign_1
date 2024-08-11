import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class CalculatorClientTest {

    public static void main(String[] args) {
        // Test multiple clients with different operations
        testClient1();
        testClient2();
    }

    private static void testClient1() {
        String sessionId = "Client1";
        System.out.println("\nTesting " + sessionId + ":\n");

        try {
            // Connect to the RMI registry
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            Calculator calculator = (Calculator) registry.lookup("CalculatorService");

            // Perform operations for Client1
            System.out.println("Connected as " + sessionId);

            // Push values
            calculator.pushValue(sessionId, 5);
            calculator.pushValue(sessionId, 15);
            calculator.pushValue(sessionId, 25);
            System.out.println("Values pushed to " + sessionId);

            // Print stack
            printStack(calculator, sessionId);

            // Perform operations
            calculator.pushOperation(sessionId, "max");
            System.out.println("Performed 'max' operation on " + sessionId);

            // Print stack after operation
            printStack(calculator, sessionId);

            // Pop value
            int poppedValue = calculator.pop(sessionId);
            System.out.println("Popped value from " + sessionId + ": " + poppedValue);

            // Check if stack is empty
            boolean isEmpty = calculator.isEmpty(sessionId);
            System.out.println("Stack is empty for " + sessionId + ": " + isEmpty);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testClient2() {
        String sessionId = "Client2";
        System.out.println("\nTesting " + sessionId + ":\n");

        try {
            // Connect to the RMI registry
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            Calculator calculator = (Calculator) registry.lookup("CalculatorService");

            // Perform operations for Client2
            System.out.println("Connected as " + sessionId);

            // Push values
            calculator.pushValue(sessionId, 8);
            calculator.pushValue(sessionId, 18);
            calculator.pushValue(sessionId, 28);
            System.out.println("Values pushed to " + sessionId);

            // Print stack
            printStack(calculator, sessionId);

            // Perform operations
            calculator.pushOperation(sessionId, "gcd");
            System.out.println("Performed 'sum' operation on " + sessionId);

            // Print stack after operation
            printStack(calculator, sessionId);

            // Pop value
            int poppedValue = calculator.pop(sessionId);
            System.out.println("Popped value from " + sessionId + ": " + poppedValue);

            // Check if stack is empty
            boolean isEmpty = calculator.isEmpty(sessionId);
            System.out.println("Stack is empty for " + sessionId + ": " + isEmpty);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printStack(Calculator calculator, String sessionId) {
        try {
            List<Integer> stack = calculator.getStack(sessionId);
            System.out.print("Current stack contents for " + sessionId + ": ");
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
            System.out.println("Error retrieving stack for " + sessionId + ": " + e.getMessage());
        }
    }
}
