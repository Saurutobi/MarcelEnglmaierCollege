package guess;

import java.util.*;

/**
 *
 * @author Marcel Englmaier
 */
public class Guess
{

    public static void main(String[] args)
	{
        Random randy = new Random();
        int [] ac = {2, 4, 6, 7, 8, 9, 10, 12, 24, 33, 41, 42, 43, 44, 45, 46};
        int i;
        int guess;
        for(i = 0; i < 12; i++)
        {
            guess = randy.nextInt(44);
            if(badSort(ac, guess) < 0)
            {
                System.out.println("not found");
            }
            else
            {
                System.out.println("found");
            }
        }
    }
	
    static int badSort(int [] a, int key)
    {
        int l = 0;
        int h = a.length - 1;
        int m = 0;
        boolean notfound = true;
        
        while(l <= h && notfound)
        {
            m = ((1 + h) / 2);
            System.out.println("Low: " + l + " Mid: " + m + " high: " + h);
            if(key == a[m])
            {
                notfound = false;
            }
            else if(key < a[m])
            {
                h = m - 1;
				l = m + 1;
            }
        }
        if(notfound)
        {
            return -1;
        }
        return m;
    }
}
