import java.util.Random;
import java.util.Scanner;

public class Selection {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number;
        System.out.print("Enter a number between 1 and 10: ");
        number = scanner.nextInt();
        //1 <= number <= 10
        //1 > number or 10 < number

        while(!(1 <= number && number <= 10)) {
            System.out.println("Number not between 1 and 10.");
            System.out.print("Please Enter number again: ");
            number = scanner.nextInt();

        }

        int randomNumber;
        Random random = new Random();
        randomNumber = random.nextInt(1, 11);
        System.out.printf("The random number is %d\n", randomNumber);
        //Compare number to randomNumber
        if (randomNumber > number){
            System.out.println("Your number is less than the one of the computer!!");
        }
        else if(randomNumber < number) {
            System.out.println("Your number is greater than the one of the computer!!");
        }
        else  {
            System.out.println("Your number is equal to the one of the computer!!");
        }

        for(int i = 1; i<= 10; i++) {
            System.out.printf("%d * %d = %d\n", number, i, number*i);
        }



//        Prompt the user to enter an integer between 1 and 10.
//        Read the user's input.
//        Check if the input is within the valid range (1 to 10). If not, display an error message
//        and prompt the user again until a valid input is provided.
//        If the input is valid, print the multiplication table for that number from 1 to 10.

//
    }
}
