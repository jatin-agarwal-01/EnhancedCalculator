import java.util.InputMismatchException;
import java.util.Scanner;

public class EnhancedCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueCalc = true;

        // Display the operation details to the user
        System.out.println("Welcome to the Enhanced Calculator!");
        displayOperationsMenu();

        while (continueCalc) {
            try {
                System.out.println("Choose the type of operation:");
                System.out.println("1. Basic Operations (+, -, *, /, %, ^)");
                System.out.println("2. Advanced Operations (sqrt, sin, cos, tan, ln, log, !, exp)");
                System.out.println("3. Constants (pi, e)");
                System.out.print("Enter 1 for Basic, 2 for Advanced, or 3 for Constants: ");
                int operationType = scanner.nextInt();

                double result = 0;

                // Handle basic operations
                if (operationType == 1) {
                    result = handleBasicOperation(scanner);
                } else if (operationType == 2) { 
                    result = handleAdvancedOperation(scanner);
                } else if (operationType == 3) { 
                    result = handleConstantOperation(scanner);
                } else {
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                    continue;
                }

                System.out.println("The result is: " + result);

                System.out.println("Do you want to perform another calculation? (yes/no): ");
                String choice = scanner.next();
                if (!choice.equalsIgnoreCase("yes")) {
                    continueCalc = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter numeric values only.");
                scanner.next(); // Clear the invalid input
            }
        }

        System.out.println("Calculator closed.");
        scanner.close();
    }

    // Display the list of operations
    public static void displayOperationsMenu() {
        System.out.println("Here are the available operations:");
        System.out.println("----------------------------------");
        System.out.println("1. Basic Operations:");
        System.out.println("   Addition (+), Subtraction (-), Multiplication (*), Division (/)");
        System.out.println("   Modulo (%), Exponentiation (^)");
        System.out.println("2. Advanced Operations:");
        System.out.println("   Square Root (sqrt), Sine (sin), Cosine (cos), Tangent (tan)");
        System.out.println("   Natural Logarithm (ln), Logarithm (log)");
        System.out.println("   Factorial (!), Exponential (exp)");
        System.out.println("3. Constants:");
        System.out.println("   Pi (pi), Euler's Number (e)");
        System.out.println("----------------------------------");
    }

    // Handle basic operations
    public static double handleBasicOperation(Scanner scanner) {
        System.out.println("Choose operation (+, -, *, /, %, ^): ");
        String operator = scanner.next();

        System.out.println("How many numbers do you want to input?");
        int numCount = scanner.nextInt();

        if (numCount < 2) {
            System.out.println("Basic operations require at least two numbers.");
            return 0;
        }

        double[] numbers = new double[numCount];
        System.out.println("Enter the numbers:");

        for (int i = 0; i < numCount; i++) {
            System.out.print("Number " + (i + 1) + ": ");
            numbers[i] = scanner.nextDouble();
        }

        double result = numbers[0];

        switch (operator) {
            case "+":
                for (int i = 1; i < numCount; i++) result += numbers[i];
                break;
            case "-":
                for (int i = 1; i < numCount; i++) result -= numbers[i];
                break;
            case "*":
                for (int i = 1; i < numCount; i++) result *= numbers[i];
                break;
            case "/":
                for (int i = 1; i < numCount; i++) {
                    if (numbers[i] != 0) result /= numbers[i];
                    else {
                        System.out.println("Cannot divide by zero.");
                        return 0;
                    }
                }
                break;
            case "%":
                for (int i = 1; i < numCount; i++) result %= numbers[i];
                break;
            case "^":
                if (numCount == 2) result = Math.pow(numbers[0], numbers[1]);
                else {
                    System.out.println("Exponentiation requires exactly two numbers.");
                    return 0;
                }
                break;
            default:
                System.out.println("Invalid operator. Please enter one of +, -, *, /, %, ^.");
                return 0;
        }

        return result;
    }

    // Handle advanced operations
    public static double handleAdvancedOperation(Scanner scanner) {
        System.out.println("Choose operation (sqrt, sin, cos, tan, ln, log, !, exp): ");
        String operator = scanner.next();

        System.out.println("Enter a number: ");
        double num = scanner.nextDouble();

        switch (operator) {
            case "sqrt":
                return Math.sqrt(num);
            case "sin":
                return Math.sin(Math.toRadians(num));
            case "cos":
                return Math.cos(Math.toRadians(num));
            case "tan":
                return Math.tan(Math.toRadians(num));
            case "ln":
                if (num > 0) return Math.log(num);
                else {
                    System.out.println("Natural logarithm undefined for non-positive numbers.");
                    return 0;
                }
            case "log":
                if (num > 0) return Math.log10(num);
                else {
                    System.out.println("Logarithm undefined for non-positive numbers.");
                    return 0;
                }
            case "!":
                if (num < 0 || num != (int) num) {
                    System.out.println("Factorial undefined for negative or non-integer numbers.");
                    return 0;
                }
                return factorial((int) num);
            case "exp":
                return Math.exp(num);
            default:
                System.out.println("Invalid operator.");
                return 0;
        }
    }

    // Handle constant operations
    public static double handleConstantOperation(Scanner scanner) {
        System.out.println("Choose constant (pi, e): ");
        String constant = scanner.next();

        switch (constant) {
            case "pi":
                return Math.PI;
            case "e":
                return Math.E;
            default:
                System.out.println("Invalid constant.");
                return 0;
        }
    }

    // Factorial function
    public static long factorial(int num) {
        if (num < 0) {
            System.out.println("Factorial undefined for negative numbers.");
            return -1;
        }
        long fact = 1;
        for (int i = 1; i <= num; i++) {
            fact *= i;
        }
        return fact;
    }
}
