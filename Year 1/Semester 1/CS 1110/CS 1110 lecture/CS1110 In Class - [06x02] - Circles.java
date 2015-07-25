package Randy;

import java.util.*;

/**
 *
 * @author Marcel Englmaier
 */
public class Circles
{
    public static void main(String[] args)
    {
        String ans;
        Scanner keyboard = new Scanner(System.in);
        System.out.print("enter a, b or c: ");
        ans = keyboard.nextLine();
        
        while(! ans.equalsIgnoreCase("a") && ! ans.equalsIgnoreCase("b") && ! ans.equalsIgnoreCase("c"))
        {
            System.out.print("FOOL I SAID enter a, b or c: ");
            ans = keyboard.nextLine();
        }
    }
}
