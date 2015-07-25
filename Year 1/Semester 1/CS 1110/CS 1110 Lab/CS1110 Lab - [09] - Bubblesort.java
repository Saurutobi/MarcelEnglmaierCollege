// Program: <Lab 09> File: <Bubblesort.java;>
//
// Problem: <The program's purpose is seperate an input string into numbers, and sort them from lowest to highest.>
//
// Programmer(s): <Marcel Englmaier>
// Course: CS1110 <Fall 2011> <M/W 4:30-5:45>
// Lab: <M 12:30-2:20>

// Problem Requirement Analysis (Step 1 - What?) 
//
// Ref.: <List short references to algorithms, program segments, etc.>
// Revision History:
// Released: <12/4/11> <ME>
// Reason: due date
// <12/4/11, Marcel Englmaier, Documentation, lab requirements, finished progra0>

// Input:   <string, keyboard input to a string>
// Output:  <string, gives initial statement, sorted integers, and output statements>

// Functions, Methods, Operations, Tasks: 
// <string is split into an array of strings based on integers>
// <the array of strings is converted into an array of integers
// <the array is sorted and outputted>


// Problem Design – Quick Algorithm (Step 2 - How?) 
// 
// 1. <program makes variables>
// 2. <input and sorting>
// 3. <outputs sorted numbers>

package bubblesort;

import java.util.*;

public class Bubblesort {
    
        static int [] numbers;
        
    public static void main(String[] args) {
        //intial statement
        System.out.println("\nCS1110 Fall 2011, Lab09, Marcel Englmaier.\n");
        
        //declaration of variables
        Scanner input = new Scanner(System.in);
        String sort = "";
        String [] inputNumbers;
        int i = 0;
        String convert = "";
        
        //ask to sort
        System.out.print("Do you want to sort (yes/no)? ");
        sort = input.nextLine();
        
        //begin sorting
        while(sort.contains("yes"))
        {
            //numbers are asked for and inputted
            System.out.println("Enter your numbers seperated by commas below:");
            sort = input.nextLine();
            
            //array is split into integers
            inputNumbers = sort.split(",");
            
            //numbers is initialized
            numbers = new int[inputNumbers.length];
            
            //numbers is given values
            for(i = 0; i < inputNumbers.length; i++)
            {
                numbers[i] = Integer.valueOf(inputNumbers[i]);
            }
            
            //begins sorting
            sort(numbers);
            
            //print sorted numbers
            display(numbers);
            
            //asks to sort again
            System.out.print("Do you want to sort (yes/no)?");
            sort = input.nextLine();
        }
        
        //end of output statement
        System.out.append("\nEnd of Output\n");
    }
    
    public static void sort(int [] numbers)
    {
        int n = numbers.length;
        int pass;
        int i;
        int temp;
        for(pass = 1; pass < n; pass++)
        {
            for(i = 0; i < n - pass; i++)
            {
                if(numbers[i] > numbers[i + 1])
                {
                    //calls the swapping method to swap the numbers
                    swap(numbers[i], numbers[i + 1], i);
                }
            }
        }
    }
    
    public static void swap(int one, int two, int i)
    {
        //swaps the two numbers
        int temp;
        temp = numbers[i];
        numbers[i] = numbers[i + 1];
        numbers[i + 1] = temp;
    }
    
    public static void display(int [] array)
    {
       //creates a string of sorted numbers with commas in between
       String ps = "";
       int end = array.length;
       int i = 0;
       ps = "" + array[0];
       for(i = 1; i < end; i++)
       {
           ps = ps + "," + array[i];
       }
       //prints out the sorted numbers
       System.out.println("The Sorted Numbers are:");
       System.out.println(ps); 
    }
}
