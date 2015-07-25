package Main;

/*
 * @author Marcel Englmaier
 */
public class InClass08
{

    public static void main(String[] args)
	{
        System.out.println("Program written by Marcel Englmaier");
        
        //addition:
        int totalAdd = 0;
        int counterAdd = 0;
        while(!(counterAdd > 100))
        {
            totalAdd = counterAdd + totalAdd;
            counterAdd += 2;
        }
        System.out.println("Addition with While loop: " + totalAdd);
        
        //multiplication
        double totalMultiply = 1;
        double counterMultiply = 2;
        while(!(counterMultiply >= 12))
        {
            totalMultiply = counterMultiply * totalMultiply;
            counterMultiply += 2;
        }
        System.out.println("Multiplication with While loop: " + totalMultiply);
        
        //For Next in multiplication
        double totalMultiplyForNext = 1;
        double counterMultiplyForNext;
        for (counterMultiplyForNext = 2; counterMultiplyForNext <= 10; counterMultiplyForNext = counterMultiplyForNext + 2)
        {
            totalMultiplyForNext = counterMultiplyForNext * totalMultiplyForNext;
        }
        System.out.println("Multiplication with For/Next loop: " + totalMultiplyForNext);
    }
}
