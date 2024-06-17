import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GuessTheNumber game = new GuessTheNumber();
        game.playGame();
    }

    static class GuessTheNumber {
        private final int randomNumber;
        private int attemptsLeft;
        private int score;

        public GuessTheNumber() {
            Random random = new Random();
            this.randomNumber = random.nextInt(100) + 1; // Random number between 1 and 100
            this.attemptsLeft = 10; // Number of attempts allowed
            this.score = 0; // Initialize score
        }

        public void playGame() {
            System.out.println("Welcome to Guess the Number Game!");
            System.out.println("I'm thinking of a number between 1 and 100.");
            System.out.println("You have " + attemptsLeft + " attempts.");

            while (attemptsLeft > 0) {
                int guess = getGuessFromUser();
                if (guess == randomNumber) {
                    System.out.println("Congratulations! You guessed the number!");
                    score += attemptsLeft * 10; // Award points based on attempts left
                    break;
                } else if (guess < randomNumber) {
                    System.out.println("The number is higher than " + guess + ".");
                } else {
                    System.out.println("The number is lower than " + guess + ".");
                }
                attemptsLeft--;
                System.out.println("Attempts left: " + attemptsLeft);
            }

            if (attemptsLeft == 0) {
                System.out.println("Game over! The number was " + randomNumber + ".");
            }

            System.out.println("Your score is: " + score);
        }

        private int getGuessFromUser() {
            // Code to get user input (you can use Scanner or JOptionPane for GUI)
            // For simplicity, let's assume we are using Scanner for console input
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your guess: ");
            return scanner.nextInt();
        }
    }
}
