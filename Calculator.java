import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Calculator extends Remote {

     /**
     * Pushes an integer value onto the stack associated with the specified client.
     * @param sessionId The identifier for the client whose stack is being accessed.
     * @param val value to be pushed onto the stack.
     */
    void pushValue(String sessionId, int val) throws RemoteException;

    /**
     * Performs the specified operation on the stack associated with the specified client.
     * Allowed operations ->  "min", "max", "lcm", and "gcd".
     * @param sessionId The identifier for the client whose stack is being accessed.
     * @param operator The operation to perform. Can be "min", "max", "lcm", or "gcd".
     */
    void pushOperation(String sessionId, String operator) throws RemoteException;

    /**
     * Pops the top value from the stack associated with the specified client.
     * @param sessionId The identifier for the client whose stack is being accessed.
     * @return The integer value removed from the top of the stack.
     */
    int pop(String sessionId) throws RemoteException;

    /**
     * Checks if the stack associated with the specified client is empty.
     *
     * @param sessionId The identifier for the client whose stack is being accessed.
     * @return True if the stack is empty, otherwise false.
     */
    boolean isEmpty(String sessionId) throws RemoteException;

     /**
     * Gives a copy of the stack associated with the specified client.
     * 
     * @param sessionId The identifier for the client whose stack is being accessed.
     * @return A list of integers representing the current contents of the stack.
     */
    List<Integer> getStack(String sessionId) throws RemoteException;

     /**
     * Pops the top value from the stack associated with the specified client after a specified delay.
     * @param sessionId The identifier for the client whose stack is being accessed.
     * @param millis The delay in milliseconds before popping the value.
     * @return The integer value removed from the top of the stack after the delay.
     */
    int delayPop(String sessionId, int millis) throws RemoteException;
}