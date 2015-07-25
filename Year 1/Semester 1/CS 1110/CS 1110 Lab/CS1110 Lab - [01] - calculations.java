// Program: <Lab 01> File: <calculations.java>
//
// Problem: <The program asks for input, then calculates and outputs the processed information.>
//
// Programmer(s): <Marcel Englmaier>
// Course: CS1110 <Fall 2011> <M/W 4:30-5:45>
// Lab: <M 12:30-2:20>

// Problem Requirement Analysis (Step 1 - What?) 
//
// Ref.: <List short references to algorithms, program segments, etc.>
// Revision History:
// Released: <9/26/11> <ME>
// Reason: due date
// <9/12/11, Marcel Englmaier, Entire program, Began Project>
// <9/14/11, Marcel Englmaier, Documentation, lab requirements>

// Input:   <integer, "firstNum" through keyboard>
//          <integer, "secondNum" through keyboard>
//          <integer, "thirdNum" through keyboard> 
// Output:  <strings describing what is going on and the input variables>
//          <float, the mathematical answer displayed on the screen>

// Functions, Methods, Operations, Tasks: 
// <Addition, two numbers being added>
// <Subraction, two numbers being subtracted>
// <Division, two numbers being divided>
// <Remainder, the remainder of the above division>
// <Multiplication, multiplication of three numbers in the order written>
// <forced Multiplication, multiplication forced to follow the Order of operators through parentheses>
// 


// Problem Design – Quick Algorithm (Step 2 - How?) 
// 
// 1. <Asks for input>
// 2. <performs math and subsequently outputs solution>
// 3. <says goodbye>

package lab01;

import java.util.Scanner;

/**
 *
 * @author Marcel Englmaier
 */
public class calculations {

    public static void main(String[] args) {
        
        //initial identifier
        System.out.println("CS1110 Fall 2011, Lab01, Marcel Englmaier.\n");
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("This program obtains three integer values from the user, \nperforms some math, and displays the results.");  //This is the Introduction to what the program does
        
        //Read the inputs and confirm
        System.out.println("\nPlease enter a value for the first integer");
        int firstNum = Integer.parseInt(scanner.next());
        System.out.println("The value of firstNum is " + firstNum);
        
        System.out.println("\nPlease enter a value for the second integer(cannont be zero)");
        int secondNum = Integer.parseInt(scanner.next());
        System.out.println("The value of secondNum is " + secondNum);
        
        System.out.println("\nPlease enter a value for the third integer");
        int thirdNum = Integer.parseInt(scanner.next());
        System.out.println("The value of thirdNum is " + thirdNum);
        
        //display the output
        //Addition:
        System.out.println("\nAddition:");
        System.out.println(firstNum + " + " + secondNum + " = " + (float)(firstNum + secondNum));
        //Subtraction:
        System.out.println("\nSubtraction:");
        System.out.println(firstNum + " - " + secondNum + " = " + (float)(firstNum - secondNum));
        //Multiplication:
        System.out.println("\nMultiplication:");
        System.out.println(firstNum + " * " + secondNum + " = " + (float)(firstNum * secondNum));
        //Division:
        System.out.println("\nDivision:");
        System.out.println(firstNum + " / " + secondNum + " = " + (float)((float)firstNum / secondNum));
        //Modulus:
        System.out.println("\nModulus:");
        System.out.println("The remainder of " + firstNum + " / " + secondNum + " is " + (float)(firstNum % secondNum));
        //Addition and multiplication
        System.out.println("\nAddition and Multiplication together,with normal operator precedence:");
        System.out.println(firstNum + " * " + secondNum + " + " + thirdNum + " = " + (float)(firstNum * secondNum + thirdNum));
        //Addition and multiplication forced
        System.out.println("\nAddition and Multiplication together,forced:");
        System.out.println(firstNum + " * " + "(" + secondNum + " + " + thirdNum + ")" + " = " + (float)(firstNum * (secondNum + thirdNum)));
        //goodbye
        System.out.println("\nGoodbye\n\nEnd of Output");
        

        //exits
        System.exit(0);
        
    }
}
