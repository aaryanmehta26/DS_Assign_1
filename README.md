# Calculator RMI

This project demonstrates a basic implementation of a Remote Method Invocation (RMI) based Calculator service in Java. It includes both the server and client components, along with a Makefile to simplify the build and execution process.

## Project Structure

- `CalculatorServer.java` - Contains the implementation of the Calculator server.
- `CalculatorClient.java` - Contains the implementation of the Calculator client.
- `Calculator.java` - The remote interface used by both server and client.
- `CalculatorImplementation.java` - Contains logic for the functions performed.
- `CalculatorClientTest.java` - Test class for running multiple clients with different operations.
- `Makefile` - Makefile to manage the build and execution process.

## Makefile Targets

- `all`: Default target that triggers the `compile` target.
- `compile`: Compiles all Java source files.
- `run-server`: Compiles the Java files and runs the Calculator server.
- `run-client`: Compiles the Java files and runs the Calculator client.
- `clean`: Removes all compiled `.class` files.
- `run-all`: Compiles and runs both the Calculator server and client.

### Compilation

To compile all Java files, simply run:

```sh
make compile
