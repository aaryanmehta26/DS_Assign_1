all: compile

compile:
	@echo "Compiling Java files..."
	javac *.java

# Run the server
run-server: compile
	@echo "Running Calculator Server..."
	java CalculatorServer

# Run the client
run-client: compile
	@echo "Running Calculator Client..."
	java CalculatorClient

# Clean up compiled files
clean:
	@echo "Cleaning up..."
	@rm -f *.class

run-all: run-server run-client

.PHONY: all compile run-server run-client clean run-all
