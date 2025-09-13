import java.util.Scanner;

public class Hello {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  // Create scanner object

        System.out.print("Enter something: ");
        String input = sc.nextLine();         // Read user input

        System.out.println("You entered: " + input);
        
        sc.close();  // Close scanner
    }
}
