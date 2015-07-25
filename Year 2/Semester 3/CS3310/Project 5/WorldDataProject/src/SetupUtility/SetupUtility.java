/* PROJECT: WorldDataProject #5         PROGRAM: SetupUtility
 * Written By: Marcel Englmaier
 * FILES ACCESSED: LogSession.txt, all other files
 * DESCRIPTION: SetupUtility creates the files for the driving app to use
 ******************************************************************************/
package SetupUtility;

import java.io.*;
import java.util.Scanner;

public class SetupUtility
{

    //<editor-fold defaultstate="collapsed" desc="Public Declarations">
    
    
    //**************************** PUBLIC DECLARATIONS ************************
    //None
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Declarations">
    
    
    //**************************** PRIVATE DECLARATIONS ************************
    //None
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public Main">
    
    
    //****************************** PUBLIC Main *******************************
    public static void main(String[] args) throws IOException
    {
        //declarations
        String fileNamePrefix;
        Scanner scanLeConsole = new Scanner(System.in);
        System.out.println("Which Map Data should I use, Your Grace? *bows*\n");
        fileNamePrefix = scanLeConsole.next();
        System.out.println("\nAs you wish, Your Grace, I shall use -" + fileNamePrefix + "- as the map data\n");
        
        
        PrintWriter logSession = new PrintWriter(new File("LogSession.txt"));
        RandomAccessFile mapData = new RandomAccessFile(new File(fileNamePrefix + "MapData.bin"), "rw");
        PrintWriter cityNames = new PrintWriter(new File(fileNamePrefix + "CityNames.txt"));
        Scanner rawData = new Scanner(new File(fileNamePrefix + "RawData.txt"));
        int [][] adjMatrix;
        String [] cityNameArray;
        String [] temp;
        int n;
        int i;
        int j;
        int maxNameLength;
        
        //read ?RawData header
        n = Integer.parseInt(rawData.nextLine().split(" ")[0]);
        cityNameArray = new String[n];
        
        //read city names and write to ?CityNames.txt
        maxNameLength = 0;
        for(i = 0; i < n; i++)
        {
            cityNameArray[i] = rawData.nextLine();
            if(cityNameArray[i].length() > maxNameLength)
            {
                maxNameLength = cityNameArray[i].length();
            }
        }    
        maxNameLength++;
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
            adjMatrix[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])] = Integer.parseInt(temp[2]);
            adjMatrix[Integer.parseInt(temp[1])][Integer.parseInt(temp[0])] = Integer.parseInt(temp[2]);
        }
        
        //store to ?MapData.bin
        mapData.writeInt(n);
        //store graph
        for(i = 0; i < n; i++)
        {
            for(j = 0; j < n; j++)
            {
                mapData.writeInt(adjMatrix[i][j]);
            }
        }
        
        //Begin printing of the File to logSession
        logSession.println("=================================================");
        logSession.println("Map Data: " + fileNamePrefix + "  Number of cities: " + n);       
        
        //print cities
        for(i = 0; i < n; i++)
        {
            logSession.println(cityNameArray[i]);
        }
        
        //print graph
        logSession.print("   ");
        for(j = 0; j < n; j++)
        {
            logSession.printf("%5d", (j+1));
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
            logSession.printf("%2d|", (i + 1));
            for(j = 0; j < n; j++)
            {
                if(adjMatrix[i][j] != Integer.MAX_VALUE)
                {
                    logSession.printf(" %4d", adjMatrix[i][j]);
                }
                else
                {
                    logSession.printf("%5s", "8");
                }
            }
            logSession.println();
        }
        
        mapData.close();
        cityNames.close();
        rawData.close();
        logSession.close();
        System.out.println("All has been completed to your wishes, your grace");
    }
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public Get/Set Methods">
    
    
    //**************************** PUBLIC GET/SET METHODS **********************
    //None
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public Service Methods">
    
    
    //**************************** PUBLIC SERVICE METHODS **********************
    //None
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Methods">
    
    
    //**************************** PRIVATE METHODS *****************************
    
    
    //</editor-fold>
}