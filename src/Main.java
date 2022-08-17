import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        try {
            calculator.calc();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}