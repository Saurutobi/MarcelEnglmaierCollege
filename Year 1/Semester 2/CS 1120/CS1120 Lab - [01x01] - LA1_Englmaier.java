package Main;

import java.util.*;
import java.io.*;

/**
 *
 * @author Marcel Englmaier
 */
public class LA1_Englmaier
{
    public static int numberOfCorrectAnswers = 0;
    public static int numberOfStudents = 0;
    public static String correctAnswersInput;
    public static String studentAnswersInput;
        
    public static char[] correctAnswers;
    public static char [][] studentAnswers;
    
    public static Scanner keyboard;
    
    public static void main(String[] args) throws FileNotFoundException, IOException
	{ 
        keyboard = new Scanner(System.in);
        
        //read file input
        readFile();
        //send everything to MultipleChoiceExam class through constructor
        
        MultipleChoiceExam MultipleChoiceExam = new MultipleChoiceExam(numberOfCorrectAnswers, correctAnswers, numberOfStudents, studentAnswers);
        
        MultipleChoiceExam.printResults();
    }
    
    public static void readFile() throws IOException
    {
        File inputFile = new File("Input.txt");
        Scanner file = new Scanner(inputFile);
        numberOfCorrectAnswers = file.nextInt();
        file.nextLine();
        correctAnswersInput = file.next();
        file.nextLine();
        int i = 0;
        correctAnswers = new char[numberOfCorrectAnswers];
        for(i = 0; i < numberOfCorrectAnswers; i++)
        {
            correctAnswers[i] = correctAnswersInput.charAt(i);
        }
        numberOfStudents = file.nextInt();
        file.nextLine();
        int student = 0;
        int answer = 0;
        studentAnswers = new char[numberOfStudents][numberOfCorrectAnswers];
        for(student = 0; student < numberOfStudents; student++)
        {
            studentAnswersInput = file.next();
            for(answer = 0; answer < numberOfCorrectAnswers; answer++)
            {
                studentAnswers[student][answer] = studentAnswersInput.charAt(answer);
            }
            file.nextLine();
        }
    }
}
