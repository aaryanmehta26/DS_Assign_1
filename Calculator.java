import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculator extends Remote {
    /**
     * Adds an integer value to the top of the stack.
     * 
     * @param value The integer value to be added.
     * @throws RemoteException if there is an error during the remote method call.
     */
    void pushValue(int val) throws RemoteException;

    /**
     * Performs the specified operation using the values in the stack.
     * 
     * @param operation The operation to be performed ("min", "max", "lcm", "gcd").
     * @throws RemoteException if there is an error during the remote method call or invalid operation.
     */
    void pushOperation(String operator) throws RemoteException;

    /**
     * Checks whether the stack is currently empty.
     * 
     * @return True if the stack is empty, otherwise false.
     * @throws RemoteException if there is an error during the remote method call.
     */
    int pop() throws RemoteException;

     /**
     * Checks whether the stack is currently empty.
     * 
     * @return True if the stack is empty, otherwise false.
     * @throws RemoteException if there is an error during the remote method call.
     */
    boolean isEmpty() throws RemoteException;

    /**
     * Removes the top value from the stack after a specified delay.
     * 
     * @param delay The time in milliseconds to wait before removing the top value.
     * @return The integer value that was at the top of the stack after the delay.
     * @throws RemoteException if there is an error during the remote method call or delay operation.
     */
    int delayPop(int delay) throws RemoteException;
}
