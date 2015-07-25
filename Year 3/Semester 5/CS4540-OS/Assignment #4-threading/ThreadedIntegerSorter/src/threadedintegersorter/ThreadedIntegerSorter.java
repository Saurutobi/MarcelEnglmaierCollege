package threadedintegersorter;

import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * @author Saurutobi
 */
public class ThreadedIntegerSorter
{
    public static void main(String[] args) throws InterruptedException
    {
        //Get input on the arrays
        Scanner howManyInts = new Scanner(System.in);
        System.out.println("Enter the amount of integers in the array");
        int arraySize = howManyInts.nextInt();
        System.out.println("Enter the maximum integer");
        int intMax = howManyInts.nextInt();
       
        //This is the array that holds everything
        int[] sortingArray = new int[arraySize];
        
        //print and fill out the randomized array that we'll be working on
        for(int i = 0; i < arraySize - 1; i++)
        {
            sortingArray[i] = 0 + (int)(Math.random() * intMax);
        }
        sortingArray[arraySize - 1] = 0 + (int)(Math.random() * intMax);
        
        printStuff("Your Array is of size: " + arraySize + ".\nYour starting array looks like:", sortingArray);
        
        //initialize threads
        SortingThread topSorter = new SortingThread(Arrays.copyOfRange(sortingArray, 0, sortingArray.length/2));
        SortingThread bottomSorter = new SortingThread(Arrays.copyOfRange(sortingArray, sortingArray.length/2, sortingArray.length));
        
        //start the two sub-array sorting threads
        topSorter.start();
        bottomSorter.start();
        //make sure they're done before continueing
        topSorter.join();
        bottomSorter.join();
        
        //print out top and bottom for nice viewing
        printStuff("Top part of array:", topSorter.arrayToSort);
        printStuff("Bottom part of array:", bottomSorter.arrayToSort);
        
        //initialize mergingThread
        MergingThread mergingThread = new MergingThread(topSorter.arrayToSort, bottomSorter.arrayToSort);
        
        //start the final sorting thread after the merge
        mergingThread.start();
        mergingThread.join();
        
        //get the sorted/merged array
        sortingArray = mergingThread.sortedArray;
        
        //print the new array
        printStuff("Fully Sorted Array:", sortingArray);
    }
    
    //This method simple prints the title then the array in nice legible form
    private static void printStuff(String title, int [] arrayToPrint)
    {
        System.out.println(title);
        for(int i = 0; i < arrayToPrint.length - 1; i++)
        {
           System.out.print(arrayToPrint[i] + ",");
        }
        System.out.println(arrayToPrint[arrayToPrint.length - 1]);
    }
}
