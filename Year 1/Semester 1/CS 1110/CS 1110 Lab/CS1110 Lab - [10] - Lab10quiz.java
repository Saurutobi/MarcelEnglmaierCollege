package lab10quiz;

import java.util.*;

public class Lab10quiz {

    static int[] numberOfRows;

    public static void main(String[] args)
	{
        Scanner input = new Scanner(System.in);
        Random randy = new Random();
        int i = 0;

        System.out.print("How many rows?");
        numberOfRows = new int[input.nextInt()];
        for(i = 0; i < numberOfRows.length; i++)
        {
            numberOfRows[i] = randy.nextInt(100);
            System.out.println(numberOfRows[i]);
        }
        largestNumber(numberOfRows);
        smallestNumber(numberOfRows);
        maths(numberOfRows);
    }

    public static void largestNumber(int[] rows)
	{
        int largest = 0;
        int largestPosition = 0;
        int i = 0;
        
        for(i = 1; i < rows.length; i++)
        {
            if(rows[i] > rows[i-1])
            {
                largest = rows[i];
                largestPosition = i + 1;
            }
        }
        
        System.out.println("Largest Number = " + largest);
        System.out.println("Largest Number Position(row) = " + largestPosition);
    }

    public static void smallestNumber(int[] rows)
	{
        int smallest = 0;
        int smallestPosition = 0;
        int i = 0;
        
        for(i = 1; i < rows.length; i++)
        {
            if(rows[i] < rows[i-1])
            {
                smallest = rows[i];
                smallestPosition = i + 1;
            }
        }
        
        System.out.println("Smallest Number = " + smallest);
        System.out.println("Smallest Number Position(row) = " + smallestPosition);
    }
    
    public static void maths(int[] rows)
	{
        int sum = 0;
        int average = 0;
        int i = 0;
        
        for(i = 0; i < rows.length; i++)
        {
            sum = sum + rows[i];
        }
        average = sum / rows.length;
        
        System.out.println("Sum of elements: " + sum);
        System.out.println("Average of elements: " + average);
    }
}