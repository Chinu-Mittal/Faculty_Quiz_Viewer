package quiz;

import java.io.*;
import java.util.Scanner;

public class Quiz {
    private final String fileName = "questions.txt";

    public void startQuiz(String subject, String name) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName));
             Scanner scanner = new Scanner(System.in)) {

            String line;
            int score = 0;
            int questionCount = 0;
            boolean subjectFound = false;
            boolean readingQuestions = false; // To track if we're in the correct subject

            System.out.println("Starting Quiz on: " + subject);

            while ((line = reader.readLine()) != null) {
                // Check if the line is a subject
                if (line.equalsIgnoreCase(subject)) {
                    subjectFound = true; // Mark subject as found
                    readingQuestions = true; // Start reading questions
                    System.out.println("You have selected the subject: " + subject);
                    continue; // Skip to the next line to read questions
                }

                if (readingQuestions) {
                    // Stop if we reach the next subject
                    if (line.trim().isEmpty() || isSubject(line)) {
                        break; // End reading questions for this subject
                    }

                    // Display the question
                    System.out.println(line); // Question
                    questionCount++;

                    // Display the answer choices
                    for (int i = 0; i < 3; i++) {
                        line = reader.readLine();
                        System.out.println(line);
                    }

                    // Read the correct answer
                    line = reader.readLine();
                    int correctAnswer = Integer.parseInt(line.split(": ")[1]);

                    // Get user's answer
                    System.out.print("Your answer (1/2/3): ");
                    int userAnswer = scanner.nextInt();

                    // Check the answer
                    if (userAnswer == correctAnswer) {
                        System.out.println("Correct!\n");
                        score++;
                    } else {
                        System.out.println("Incorrect. The correct answer was " + correctAnswer + ".\n");
                    }

                    // Move to the next question
                    reader.readLine(); // Skip the empty line between questions
                }
            }

            if (!subjectFound) {
                System.out.println("Subject not found.");
            } else {
                System.out.println("Quiz Finished! Your score: " + score + "/" + questionCount);
                saveScore(score, questionCount,name,subject);
            }

        } catch (IOException e) {
            System.out.println("Error loading quiz questions: " + e.getMessage());
        }
    }

    private boolean isSubject(String line) {
        // Check if the line is a subject
        return line.equalsIgnoreCase("EC") || line.equalsIgnoreCase("CSE") || 
               line.equalsIgnoreCase("EI");
    }

    private void saveScore(int score, int questionCount,String name,String subject) {
        try (FileWriter writer = new FileWriter("quiz_results.txt", true)) {
            writer.write("Name: " + name+", Stream: "+subject+ ", Score: " + score + "/" + questionCount + "\n");
        } catch (IOException e) {
            System.out.println("Error saving quiz results.");
        }
    }
}
