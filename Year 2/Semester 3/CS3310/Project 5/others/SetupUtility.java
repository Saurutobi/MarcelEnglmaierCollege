/* PROJECT: Shortest Path         PROGRAM: SetupUtility
* AUTHOR: Ekema
* COURSE INFO:  CS3310 (MWF) Fall 2012 - Asgn 5
* FILES ACCESSED: ?MapData.bin, ?CityNames.txt, ?RawData.txt, LogSession.txt
* DESCRIPTION: Creates ?CityNames.txt (a list of fixed-length strings) and 
*               ?MapData.bin (a binary adjacency matrix).
*******************************************************************************/

package SetupUtility;

import java.io.*;
import java.util.Scanner;

public class SetupUtility 
{
    public static void main(String[] args) 
    {
        //declarations
        Scanner kbReader = null;
        RandomAccessFile mapData = null;
        PrintWriter logSession = null;
        PrintWriter cityNames = null;
        File rawDataFile = null;
        Scanner rawData = null;
        String fileNamePrefix;
        int[][] adjMatrix;
        String[] cityNameArray, temp;
        int n;
        int i, j, maxNameLength;
        
        //try-catch block to open LogSession.txt
        try
        {
            logSession = new PrintWriter(new File("res/LogSession.txt"));
        }
        catch(IOException e)
        {
            System.out.println("Could not open LogSession.txt");
        }
        
        //ask user what rawData to use
        kbReader = new Scanner(System.in);
        System.out.print("Which map data would you like to use? ");
        fileNamePrefix = kbReader.nextLine();
        
        //try-catch block to open ?RawData.txt and create ?MapData.bin and 
        //?CityNames.txt
        try
        {
            rawDataFile = new File("res/" + fileNamePrefix + "RawData.txt");
            rawData = new Scanner(rawDataFile);
            mapData = new RandomAccessFile(new File("res/" + fileNamePrefix 
                    + "MapData.bin"), "rw");
            cityNames = new PrintWriter(new File("res/" + fileNamePrefix 
                    + "CityNames.txt"));
        }
        catch(IOException e)
        {
            System.out.println("Could not open ?RawData.txt, ?MapData.bin, and "
                    + "?CityNames.txt");
        }
        
        //read ?RawData header
        n = Integer.parseInt(rawData.nextLine().split(" ")[0]);
        cityNameArray = new String[n];
        
        //read city names and write to ?CityNames.txt
        maxNameLength = 0;
        for(i = 0; i < n; i++)
        {
            cityNameArray[i] = rawData.nextLine();
            if(cityNameArray[i].length() > maxNameLength)
                maxNameLength = cityNameArray[i].length();
        }
        cityNames.println(maxNameLength);
        for(i = 0; i < n; i++)
        {
            cityNames.printf("%-" + maxNameLength + "s", cityNameArray[i]);
        }
        
        //create 2d array from RawData.txt
        adjMatrix = new int[n][n];
        
        //initialize values
        for(i = 0; i < n; i++)
        {
            for(j = 0; j < n; j++)
            {
                adjMatrix[i][j] = Integer.MAX_VALUE;
            }
            adjMatrix[i][i] = 0;
        }
        
        //read and store actual edge weights
        while(rawData.hasNextLine())
        {
            temp = rawData.nextLine().split(" ");
            adjMatrix[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])] 
                    = Integer.parseInt(temp[2]);
            //we also want the mirrored value because it is an undirected graph
            adjMatrix[Integer.parseInt(temp[1])][Integer.parseInt(temp[0])] 
                    = Integer.parseInt(temp[2]);
        }
        
        //try-catch block to store to ?MapData.bin
        try
        {
            //store header record
            mapData.writeInt(n);
            
            //store graph
            for(i = 0; i < n; i++)
            {
                for(j = 0; j < n; j++)
                {
                    mapData.writeInt(adjMatrix[i][j]);
                }
            }
        }
        catch(IOException e)
        {
            System.out.println("Error writing to ?MapData.bin");
        }
        
        /**********************Pretty print to LogSession.txt******************/
        //print header
        logSession.println("=================================================");
        logSession.println("Map Data: " + fileNamePrefix 
                + "  Number of cities: " + n);       
        
        //print cities
        for(i = 0; i < n; i++)
        {
            logSession.println(cityNameArray[i]);
        }
        
        //print graph
        logSession.print("   ");
        for(j = 0; j < n; j++)
        {
            logSession.printf("%5d", j);
        }
        logSession.println();
        logSession.print("---");
        for(j = 0; j < n; j++)
        {
            logSession.print("-----");
        }
        logSession.println();
        for(i = 0; i < n; i++)
        {
            logSession.printf("%2d|", i);
            for(j = 0; j < n; j++)
            {
                if(adjMatrix[i][j] != Integer.MAX_VALUE)
                {
                    logSession.printf(" %4d", adjMatrix[i][j]);
                }
                else
                {
                    logSession.printf("%5s", "∞");
                }
            }
            logSession.println();
        }
        
        //try-catch block to close opened files
        try
        {
            mapData.close();
            cityNames.close();
            rawData.close();
            logSession.close();
        }
        catch(IOException e)
        {
            System.out.println("Could not close ?MapData.bin, ?CityNames.txt, "
                    + "?RawData, and LogSession.txt");
        }      
    }
}
