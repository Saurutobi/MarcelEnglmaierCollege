// Program: <Lab 07> File: <Main.java;>
//
// Problem: <The program's purpose is to take file and user input, and create graphs accordingly.>
//
// Programmer(s): <Marcel Englmaier>
// Course: CS1110 <Fall 2011> <M/W 4:30-5:45>
// Lab: <M 12:30-2:20>

// Problem Requirement Analysis (Step 1 - What?) 
//
// Ref.: <List short references to algorithms, program segments, etc.>
// Revision History:
// Released: <11/06/11> <ME>
// Reason: due date
// <11/06/11, Marcel Englmaier, Documentation, lab requirements, created and finished program>

// Input:   <string, keyboard input for input file>
//          <string, keyboard input for output file>
//          <int, keyboard input for xStart>
//          <int, keyboard input for xEnd>
//          <int, keyboard input for yStart>
//          <int, keyboard input for yEnd>
// Output:  <string, the graph>
//          <file string, output the graph as string into file>
//          <string, asks for user input>

// Functions, Methods, Operations, Tasks: 
// <main, main function of program asking for input, and calling other methods>
// <coordinates, asks for user input, and prints graph>
// <coordinates, checks files as input, checks for incorrect input, then prints graph>
// <doGraph, creates the actual graph, then passes it up the chain to be printed again>
// <getInName, asks for input file name and checks for existence>
// <getOutName, asks for output file name and checks for existence>
// <inRange, checks for if the input range is ok>

// Problem Design – Quick Algorithm (Step 2 - How?) 
// 
// 1. <program makes variables to use and begins>
// 2. <first coordinates is called, and prints graph>
// 3. <second coordinates is called, checks finle input, prints to file>
// 4. <end of output>


package Main;

//load imports
import java.io.*;
import java.util.*;
        
/**
 *
 * @author Marcel Englmaier
 */
public class Main {
    //initial variables
    public static int xStart = 0;
    public static int xEnd = 0;
    public static int yStart = 0;
    public static int yEnd = 0;
    public static Scanner keyboard;
    
    public static void main(String[] args) throws FileNotFoundException, IOException {

        //initial output identification
        System.out.println("CS1110 Fall 2011; Lab 07;  Marcel Englmaier");
        
        //initialize keyboard
        keyboard = new Scanner(System.in);
        
        //make user-defined graph
        do
		{
            coordinates();
            System.out.println("Please enter y to continue");
        }while(keyboard.nextLine().equalsIgnoreCase("y"));
        
        //make file-defined graph
        coordinates(getInName(), getOutName());
        
        //final end of output message
        System.out.println("\nEnd of ouput\n");
    }
    
    static void coordinates()
    {
        //asks for user-defined coordinates
            System.out.println("please enter the positive x value for graph(0 to 9)");
            xEnd = keyboard.nextInt();
            while(!inRange(xEnd))
            {
                System.out.println("please enter the positive x value for graph(0 to 9)");
                xEnd = keyboard.nextInt();
            }
            System.out.println("please enter the negative x value for graph(0 to -9)");
            xStart = keyboard.nextInt();
            while(!inRange(xStart))
            {
                System.out.println("please enter the negative x value for graph(0 to -9)");
                xStart = keyboard.nextInt();
            }
            System.out.println("please enter the positive y value for graph(0 to 9)");
            yStart = keyboard.nextInt();
            while(!inRange(yStart))
            {
                System.out.println("please enter the positive y value for graph(0 to 9)");
                yStart = keyboard.nextInt();
            }
            System.out.println("please enter the negative y value for graph(0 to -9)");
            yEnd = keyboard.nextInt();
            while(!inRange(yEnd))
            {
                System.out.println("please enter the negative y value for graph(0 to -9)");
                yEnd = keyboard.nextInt();
            }
            
            //makes user-defined graph
            doGraph(System.out);
    }
    
    static void coordinates(String inName, String outName) throws IOException{
        
        //begin file printing
        PrintStream myStream = null;
        Scanner s = null;
        try
		{
            myStream = new PrintStream(outName);
            s = new Scanner(new BufferedReader(new FileReader(inName)));
            while (s.hasNext()) 
            {
                //read file coordinates
                xStart = s.nextInt();
                xEnd = s.nextInt();
                yStart = s.nextInt();
                yEnd = s.nextInt();
                
                //makes file-defined graph if all values are allowed otherwise
                if(inRange(xStart) && inRange(xEnd) && inRange(yStart) && inRange(xEnd) && (xStart < xEnd) && (yStart > yEnd))
                {
                    doGraph(myStream);
                }
                else
                {
                    myStream.println("bad inputs");
                }
            }
        } 
        finally
		{
            //close all used files
            if (s != null)
			{
				s.close();
			}
            if(myStream != null )
			{
				myStream.close();
			}
        }
    }
    
    static void doGraph(PrintStream ps){
        
        //initial graph size printed
        ps.println("X: " + xStart + " to " + xEnd + "; Y: " + yStart + " to " + yEnd + ";");
        
        //for loop for printing coordinates
            int x = 0;
            int y = 0;
            int i = 0;
            for(y=yStart; y>=yEnd; y--)
            {
                //prints an x-line
                for(x=xStart; x<=xEnd; x++)
                {
                    ps.print(" " + buildPoint(x, y) + " ");
                }
                
                //prints x-axis line
                if(y == 0)
                {
                    ps.println();
                    for(x=xStart; x<=xEnd; x++)
                    {
                        for(i=0; i<(buildPoint(x,y).length() + 2); i++)
                        {
                            ps.print("-");
                        }
                    }
                }
                
                //steps down one line on the y axis, and repeat printing x-axis
                ps.println();
            }
    }
    
    static String buildPoint(int x, int y)
    {
        //builds the coordinates at a point and pushes them up the chain for printing in the line
        String retval = "";
        if(x > 0)
        {
            retval = " " + x + ",";
        }
        else if(x == 0)
        {
            retval = x + "|";
        }
        else
        {
            retval = x + ",";
        }
        if(y >= 0)
        {
            retval = retval + y + "  ";
        }
        else
        {
            retval = retval + y + " ";
        }
        return retval;
    }
    
    static String getInName()
    {
        //gets input file name and checks for existence
        String name = "";
        do
		{
            System.out.println("Enter input file name: ");
            name = keyboard.nextLine();
        }while(!(new File(name).exists()));
        return name;
    }
	
    static String getOutName()
    {
        //gets output file name and checks for existence
        String name = "";
        do
		{
            System.out.println("Enter output file name: ");
            name = keyboard.nextLine();
        }while(!(new File(name).exists()));
        return name;
    }
	
    static boolean inRange(int i)
    {
        //checks if input numbers are within appropriate range
        if(i >=-9 && i<= 9)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}