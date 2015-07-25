/* PROJECT: WorldDataProject #5         PROGRAM: DrivingApp
 * Written By: Marcel Englmaier
 * PROJECT CLASSES USED: Map, UI, shortestPath
 * FILES ACCESSED: none
 * DESCRIPTION: this program asks for user input, then runs it through the other
 * 				classes
 *******************************************************************************/
package DrivingApp;

import OOPClasses.Map;
import OOPClasses.UI;
import OOPClasses.shortestPath;
import java.io.*;
import java.util.Scanner;

public class DrivingApp 
{

    //<editor-fold defaultstate="collapsed" desc="Public Declarations">
    
    
    //**************************** PUBLIC DECLARATIONS ************************
    //None
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Declarations">
    
    
    //**************************** PRIVATE DECLARATIONS ************************
    private static Map thisIsTheMap;
    private static UI thisIsTheUI;
    private static shortestPath thisIsTheShortestPath;
    private static String fileNamePrefix;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public Main">
    
    
    //****************************** PUBLIC Main *******************************
    public static void main(String[] args) throws IOException
    {
        int [] cityNumbers = new int[2];
        Scanner scanLeConsole = new Scanner(System.in);
        System.out.println("Which Map Data should I use, Your Grace? *bows*\n");
        fileNamePrefix = scanLeConsole.next();
        System.out.println("\nAs you wish, Your Grace, I shall use " + fileNamePrefix + " as the map data\n");
        thisIsTheMap = new Map(fileNamePrefix);
        thisIsTheUI = new UI(fileNamePrefix);
        thisIsTheShortestPath = new shortestPath(thisIsTheMap);
        
        String startCity = "";
        String destinationCity = "";
        
        //amIDone reads one command
        while(thisIsTheUI.amIDone())
        {
            startCity = thisIsTheUI.getStartCity();
            destinationCity = thisIsTheUI.getDestinationCity();
            cityNumbers[0] = thisIsTheMap.getCityNumber(startCity);
            cityNumbers[1] = thisIsTheMap.getCityNumber(destinationCity);
            thisIsTheUI.log("#   #   #   #   #   #   #   #   #   #   #   #   #   #");
            thisIsTheUI.log(startCity + " (" + cityNumbers[0] + ") TO " + destinationCity + " (" + cityNumbers[1] + ")");
            
            if(cityNumbers[0] == -1 || cityNumbers[1] == -1)
            {
                thisIsTheUI.log("Error - one of the cities is not on this map");
            }
            else
            {
                thisIsTheUI.log(thisIsTheShortestPath.findPath(cityNumbers));
            }
        }
        
        thisIsTheMap.finishUp();
        thisIsTheUI.finishUp();
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