package truefalse;

import java.util.Scanner;

/**
 *
 * @author Marcel Englmaier
 */
public class TrueFalse
{
 
    public static void main(String[] args)
	{
        int score;
        String Grade;
        score = 5;
        if(score > 90)
		{
			Grade = "A";
		}
        else if(score > 85)
		{
			Grade = "BA";
		}
        else if(score > 80)
		{
			Grade = "B";
		}
        else if(score > 75)
		{
			Grade = "CB";
		}
		else
		{
			Grade = "E";
		}
        System.out.println("Grade is: " + Grade);
        
        String a = "hello";
        String b = "hello";
        if(a == b)
		{
            System.out.println("equal");
		}
    }
}

