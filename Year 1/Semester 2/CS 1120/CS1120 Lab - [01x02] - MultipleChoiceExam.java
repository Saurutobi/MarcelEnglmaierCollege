package Main;

/**
 *
 * @author Marcel Englmaier
 */
public class MultipleChoiceExam
{
    private static int numberOfCorrectAnswers = 0;
    private static int numberOfStudents = 0;
        
    private static int [] numberOfCorrectStudentAnswers;
    private static double [] studentPercentages;
    private static char[] correctAnswers;
    private static char [][] studentAnswers;
    
    public MultipleChoiceExam(int numAnswers, char[] corAnswers, int numStudents, char[][] stuAnswers)
    {
        numberOfCorrectAnswers = numAnswers;
        correctAnswers = corAnswers;
        numberOfStudents = numStudents;
        studentAnswers = stuAnswers;
        
        //check for student correctness
        //for row
        int student = 0;
        //for column
        int answer = 0;
        numberOfCorrectStudentAnswers = new int[numberOfStudents];
        for(student = 0; student < numberOfStudents; student++)
        {
            numberOfCorrectStudentAnswers[student] = numberOfCorrectAnswers;
        }
        for(student = 0; student < numberOfStudents; student++)
        {
            for(answer = 0; answer < numberOfCorrectAnswers; answer++)
            {
                if(correctAnswers[answer] != studentAnswers[student][answer])
                {
                    //if the answer is wrong, subtract 1 to respective array 
                    numberOfCorrectStudentAnswers[student]--;
                }
            }
        }
        calculateStudentPercentages();
    }
    
    public void calculateStudentPercentages()
    {
        int i = 0;
        studentPercentages = new double[numberOfStudents];
        for(i = 0; i < numberOfStudents; i++)
        {
            studentPercentages[i] = (((double)numberOfCorrectStudentAnswers[i] / numberOfCorrectAnswers) * 100);
        }
    }
    
    public void printResults()
    {
        //print out all answers
        printAnswerKey();
        System.out.println("");
        printStudents();
        System.out.println("");
        //print out percentages
        printPercentages();
    }
    
    public void printAnswerKey()
    {
        System.out.println("Answer Key:");
        int i = 0;
        for(i = 0; i < numberOfCorrectAnswers; i++)
        {
            System.out.println((i + 1) + ") " + correctAnswers[i]);
        }
    }
    
    public void printStudents()
    {
        System.out.println("Student Answers:");
        int student = 0;
        int answer = 0;
        for(student = 0; student < numberOfStudents; student++)
        {
            System.out.print("Student " + (student + 1) + "   ");            
        }
        System.out.println("");
        for(answer = 0; answer < numberOfCorrectAnswers; answer++)
        {
            for(student = 0; student < numberOfStudents; student++)
            {
                if(studentAnswers[student][answer] != correctAnswers[answer])
                {
                    System.out.printf("%2d) %c (%c)   ", (answer + 1), studentAnswers[student][answer], correctAnswers[answer]);
                }
                else
                {
                    System.out.printf("%2d) %c       ", (answer + 1), studentAnswers[student][answer]);
                }
            }
            //advance to next line
            System.out.println("");
        }
    }
    
    public void printPercentages()
    {
        int student = 0;
        for(student = 0; student < numberOfStudents; student++)
        {
            System.out.printf("Student " + (student + 1) + ": %.0f%% ", studentPercentages[student]);
            if(studentPercentages[student] <= 100 && studentPercentages[student] >= 90)
            {
                System.out.println("A");
            }
            else if(studentPercentages[student] < 90 && studentPercentages[student] >= 80)
            {
                System.out.println("B");
            }
            else if(studentPercentages[student] < 80 && studentPercentages[student] >= 70)
            {
                System.out.println("C");
            }
            else if(studentPercentages[student] < 70 && studentPercentages[student] >= 60)
            {
                System.out.println("D");
            }
            else
            {
                System.out.println("F");
            }
        }
    }
}
