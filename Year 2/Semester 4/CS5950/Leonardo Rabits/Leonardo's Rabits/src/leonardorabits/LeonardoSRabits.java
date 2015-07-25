package leonardorabits;

import java.util.Scanner;

/**
 *
 * @author Saurutobi
 */
public class LeonardoSRabits {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        boolean run = true;
        int total = 0;
        int previous = 0;
        int previous2 = 0;
        int months = 0;
        int temp = 0;
        while(run)
        {
            System.out.println("enter number of months up to 40(0 will quite):");
            months = keyboard.nextInt();
            if(months <= 40 && months > 0)
            {
                if(months == 1)
                {
                    total = 1;
                }
                if(months == 2)
                {
                    total = 2;
                }
                if(months == 3)
                {
                    total = 3;
                }
                if(months > 3)
                {
                    previous = 3;
                    previous2 = 2;
                    for(int i = 3; i < months; i++)
                    {
                        temp = total;
                        total += previous;
                        previous = temp;
                    }
                }
                
            } else if(months == 0)
            {
                run = false;
            } else if(months > 40)
            {
                System.out.println("please enter the correct number range");
            }
            
            System.out.println(String.valueOf(total));
            total = 0;
            previous = 0;
            previous2 = 0;
            temp = 0;
        }
        
        
        
    }
}
