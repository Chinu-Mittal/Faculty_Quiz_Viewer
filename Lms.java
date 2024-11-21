import user.*;
import material.*;
import quiz.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Lms {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Ask if the user is a student or faculty
            System.out.print("Are you a (1) Student or (2) Faculty? Enter 1 or 2: ");
            int role = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            if (role == 1) {
                // Student Flow
                System.out.print("Enter your name: ");
                String name = scanner.nextLine();

                System.out.print("Enter your stream (EC, CSE, EI): ");
                String stream = scanner.nextLine().trim();

                // Create Student and display info
                student students = new student(name, stream);
                students.displayInfo("Welcome");

                // Choose and display material based on stream
                Material material;
                switch (stream.toLowerCase()) {
                    case "ei":
                        material = new Ei();
                        break;
                    case "cse":
                        material = new Cse();
                        break;
                    case "ec":
                        material = new Ec();
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid stream");
                }

                material.displayContent();

                // Start Quiz if user opts to
                System.out.print("Do you want to start the quiz? (yes/no): ");
                String response = scanner.nextLine();
                if (response.equalsIgnoreCase("yes")) {
                    Quiz quiz = new Quiz();
                    quiz.startQuiz(stream, name);
                } else {
                    System.out.println("Exiting. Thank you!");
                }

            } else if (role == 2) {
                int choice;
                do {
                    System.out.print("Enter the student name to view marks: ");
                    String studentName = scanner.nextLine().trim();

                    System.out.print("Enter the student's stream (EC, CSE, EI): ");
                    String stream = scanner.nextLine().trim();
                    String fileName = "quiz_results.txt";
                    boolean resultFound = false;

                    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            // Check if line contains the specified student and stream (ignoring case)
                            if (line.toLowerCase().contains("name: " + studentName.toLowerCase()) &&
                                line.toLowerCase().contains("stream: " + stream.toLowerCase())) {
                                System.out.println(line);  // Print the result
                                resultFound = true;
                                break;  // Assuming only one entry per student per stream
                            }
                        }
                        if (!resultFound) {
                            System.out.println("No results found for " + studentName + " in " + stream + " stream.");
                        }
                    } catch (IOException e) {
                        System.out.println("Error reading quiz results: " + e.getMessage());
                    }

                    // Prompt to search more
                    System.out.print("Do you want to search more? (0 for No, 1 for Yes): ");
                    choice = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                } while (choice == 1);

            } else {
                System.out.println("Invalid option. Exiting program.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter numeric values where required.");
        } finally {
            scanner.close();
        }
    }
}
