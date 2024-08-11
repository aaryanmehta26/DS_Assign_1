import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculatorServer {
    public static void main(String[] args) {
        try {
            // Create an instance of the CalculatorImplementation class
            CalculatorImplementation calculator = new CalculatorImplementation();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("CalculatorService", calculator);
            System.out.println("Calculator Server is ready to connect!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
