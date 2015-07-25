package Main;

import java.io.*;
import java.util.*;

/**
 *
 * @author Marcel Englmaier
 */

public class TreasureHunt {
    
    //static variables
    static char [][] maze;
    static int [] startSpot;
    static int dimensionX = 0;
    static int dimensionY = 0;
    static int [] endSpot;
    
    public static void main(String[] args) throws FileNotFoundException, IOException 
    {
        //read input
        readFile();
        //print output and perform calculations
        print();
    }
    
    public static void readFile() throws IOException
    {
        //create file
        File inputFile = new File("Input.txt");
        Scanner file = new Scanner(inputFile);
        //read in dimensions and split them
        String dimensions = file.nextLine();
        //the -48 accounts for the unicode '0'
        dimensionX = dimensions.charAt(0) - 48;
        dimensionY = dimensions.charAt(2) - 48;
        //print dimensions to test accuracy
        //skip line with start coordinates
        file.nextLine();
        //create maze
        maze = new char[dimensionX][dimensionY];
        //read in the maze
        int column = 0;
        int row = 0;
        String input = "";
        for(row = 0; row < dimensionX; row++)
        {
            input = file.nextLine();
            for(column = 0; column < input.length(); column++)
            {
                maze[row][column] = input.charAt(column);
                //check if it's the starting spot
                if(maze[row][column] == 'S')
                {
                    startSpot = new int[2];
                    startSpot[0] = row;
                    startSpot[1] = column;
                }
            }
        }
    }
    
    public static int [] findTreasure(int [] location)
    {
        //create boundary booleans. actual booleans have never worked, so i have resorted to 1s and 0s
        int noTop = 0;
        int noBottom = 0;
        int noLeft = 0;
        int noRight = 0;
        //create the directional characters
        char top = ' ';
        char bottom = ' ';
        char right = ' ';
        char left = ' ';
        //if out of bound, don't do the loop below
        if(!(location[1] - 1 < 0))
        {
            top = maze[location[0]][location[1] - 1];
        }
        else
        {
            noTop = 1;
        }
		
        if(!(location[1] + 1 > dimensionX))
        {
            bottom = maze[location[0]][location[1] + 1];
        }
        else
        {
            noBottom = 1;
        }
		
        if(!(location[0] - 1 < 0))
        {
            left = maze[location[0] - 1][location[1]];
        }
        else
        {
            noLeft = 1;
        }
		
        if(!(location[0] + 1 > dimensionY))
        {
            right = maze[location[0] + 1][location[1]];
        }
        else
        {
            noRight = 1;
        }
		
        //terminating condition
        if(maze[location[0]][location[1]] == '$')
        {
            return location;
        }
		
        //begin recursion
        if(noTop == 0 && top != '#')
        {
            location[1] = location[1] - 1;
            return findTreasure(location);
        }
        if(noBottom == 0 && bottom != '#')
        {
            location[1] = location[1] + 1;
            return findTreasure(location);
        }
        if(noLeft == 0 && left != '#')
        {
            location[0] = location[0] - 1;
            return findTreasure(location);
        }
        if(noRight == 0 && right != '#')
        {
            location[0] = location[0] + 1;
            return findTreasure(location);
        }
        return location;
    }
    
    public static void print()
    {
        //print the input
        printInput();
        //create treasure coordinates array
        endSpot = new int[2];
        //find the treasure and copyt into array
        endSpot = findTreasure(startSpot);
        //print the final ending spot
        System.out.println("The treasure was found at location [" + endSpot[0] + "] [" + endSpot[1] + "]");
    }
    
    public static void printInput()
    {
        //print the array exactly as inputted
        int column = 0;
        int row = 0;
        for(row = 0; row < dimensionX; row++)
        {
            for(column = 0; column < dimensionY; column++)
            {
                System.out.print(maze[row][column]);
            }
            System.out.println();
        }
    }
    
}
