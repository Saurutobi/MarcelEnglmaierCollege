package javaapplication30;

import java.util.*;

/**
 *
 * @author Marcel Englmaier
 */
public class JavaApplication30
{

    public static void main(String[] args)
	{
        Scanner scanner = new Scanner(System.in);
        double big;
        double small;
        double ratio;
        int end = 0;
        while(end == 0)
        {
			big = scanner.nextInt();
			small = 6560 - big;
			ratio = (big * .09) / (small * .08);
			System.out.println(ratio);
			System.out.print(small);
			end = scanner.nextInt();
        }
    }
}
