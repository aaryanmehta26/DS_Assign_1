import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.ArrayList;

public class CalculatorImplementation extends UnicastRemoteObject implements Calculator {

    // Map to store stacks for different clients based on client sessionID
    private Map<String, Stack<Integer>> clientStacks;

    public CalculatorImplementation() throws RemoteException {
        clientStacks = new HashMap<>();
    }


     /**
     * Pushes an integer value onto the stack for the given client.
     * @param sessionId unique client stack id.
     * @param val  value to be pushed in the stack.
     */
    @Override
    public synchronized void pushValue(String sessionId, int val) throws RemoteException {
        Stack<Integer> stack = getClientStack(sessionId);
        stack.push(val);
    }

    /**
     * Performs the specified operation using the values in the client's stack.
     * @param sessionId unique client stack id.
     * @param operator operations to be performed ("min", "max", "lcm", "gcd").
     */
    @Override
    public synchronized void pushOperation(String sessionId, String operator) throws RemoteException {
        Stack<Integer> stack = getClientStack(sessionId);
        if (stack.isEmpty()) {
            throw new IllegalStateException("Cannot perform operation on an empty stack.");
        }
        
        int result;
        switch (operator) {
            case "min":
                result = stack.stream().min(Integer::compareTo).orElseThrow();
                break;
            case "max":
                result = stack.stream().max(Integer::compareTo).orElseThrow();
                break;
            case "lcm":
                result = calculateLCM(stack);
                break;
            case "gcd":
                result = calculateGCD(stack);
                break;
            default:
                throw new IllegalArgumentException("Unknown operation: " + operator);
        }
        
        stack.clear();
        stack.push(result);
    }

    /**
     * Pops the top value from the client's stack.
     * @param sessionId unique client stack id.
     */
    @Override
    public synchronized int pop(String sessionId) throws RemoteException {
        Stack<Integer> stack = getClientStack(sessionId);
        if (stack.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack.pop();
    }

    /**
     * Checks if the stack for the given client is empty.
     * @param sessionId unique client stack id.
     */
    @Override
    public synchronized boolean isEmpty(String sessionId) throws RemoteException {
        Stack<Integer> stack = getClientStack(sessionId);
        return stack.isEmpty();
    }

    /**
     * Retrieves the current stack contents for the given client.
     * @param sessionID
     */
    @Override
    public synchronized List<Integer> getStack(String sessionId) throws RemoteException {
        Stack<Integer> stack = getClientStack(sessionId);
        return new ArrayList<>(stack);
    }

    /**
     * Pops the top value from the client's stack after a specified delay.
     * @param sessionId
     * @param millis The time in milliseconds to wait before removing the top value.
     */
    @Override
    public synchronized int delayPop(String sessionId, int millis) throws RemoteException {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RemoteException("Operation interrupted during delay.", e);
        }
        return pop(sessionId);
    }

     /**
     * Retrieves or creates a stack for the given client ID.
     * @param sessionId The ID of the client.
     */
    private synchronized Stack<Integer> getClientStack(String sessionId) {
        return clientStacks.computeIfAbsent(sessionId, k -> new Stack<>());
    }


    /**
     * Calculates the greatest common divisor (GCD)
     * @param stack stack containing integers to find GCD.
     */
    private int calculateGCD(Stack<Integer> stack) {
        int result = stack.pop();
        while (!stack.isEmpty()) {
            result = gcd(result, stack.pop());
        }
        return result;
    }

    /**
     * Calculates the least common multiple (LCM)
     * @param stack stack containing integers to find LCM.
     */
    private int calculateLCM(Stack<Integer> stack) {
        int result = stack.pop();
        while (!stack.isEmpty()) {
            result = lcm(result, stack.pop());
        }
        return result;
    }

    private int gcd(int val1, int val2) {
        while (val2 != 0) {
            int temp = val2;
            val2 = val1 % val2;
            val1 = temp;
        }
        return val1;
    }

    private int lcm(int val1, int val2) {
        return val1 * (val2 / gcd(val1, val2));
    }
}