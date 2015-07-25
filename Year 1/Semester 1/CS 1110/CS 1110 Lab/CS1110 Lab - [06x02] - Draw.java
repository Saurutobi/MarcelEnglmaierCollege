package lab06quiz;

import java.util.*;

/**
 *
 * @author Marcel Englmaier
 */
public class Draw {
        private int pyramidSize = 0;
        private int numberOfLines = 0;
		
    public Draw(char character, int size)
    {
		if(size == 0)
		{
			pyramid = 2;
		}
		else if(size == 1)
		{
			Random randomSize = new Random(10);
            pyramidSize = randomSize.nextInt(19) + 2;
		}
		else if(size >= 2)
		{
			pyramidSize = size;
		}
        System.out.println("Drawing the diamond:");
        numberOfLines = (2 * pyramidSize) + 1;
        boolean buildLine = true;
        int spaces =0;
        int linesBuilt = 0;
        int lineAlreadyDone = pyramidSize;
        while(buildLine = true)
        {
            System.out.print(" ");
            spaces ++;
            if(spaces == pyramidSize)
            {
                System.out.print(character);
                spaces ++;
            }
            if(spaces == (2*pyramidSize))
            {
                System.out.print("\n");
                linesBuilt ++;
            }
            if(linesBuilt == numberOfLines)
            {
               break;
            }
        }
        System.out.println("\nI'm sorry, i cannot complete this assignment because i could not figure out the draw method in the time alotted. obviously, it is only printing one line, but does go to the next line once the first is complete");
    }
}
    

