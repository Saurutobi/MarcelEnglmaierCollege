/* PROJECT: Shortest Path         PROGRAM: DrivingApp
* AUTHOR: Ekema
* COURSE INFO:  CS3310 (MWF) Fall 2012 - Asgn 5
* DESCRIPTION: The main driving app for the program. It controls Map, UI, and
*               ShortestPath. 
*******************************************************************************/

package DrivingApp;

import OOPClasses.*;
import java.util.Scanner;

public class DrivingApp 
{
    public static void main(String args[])
    {
        //declarations
        Map map = null;
        UI ui = null;
        ShortestPath shortestPath = null;
        Scanner kbReader = new Scanner(System.in);
        String[] answers;
        String fileNamePrefix, startCity, destinationCity;
        int firstCityNumber, secondCityNumber;
        boolean loopAgain = false;
        
        //ask user which data to use
        System.out.print("Which map data would you like to use? ");
        fileNamePrefix = kbReader.nextLine();
        
        //declare 3 objects
        map = new Map(fileNamePrefix);
        ui = new UI(fileNamePrefix);
        shortestPath = new ShortestPath(map);
        
        //loop until UI indicates done
        loopAgain = ui.ReadOneCommand();
        while(loopAgain)
        {
            //get city pair
            startCity = ui.GetStartCity();
            destinationCity = ui.GetDestinationCity();
            firstCityNumber = map.GetCityNumber(startCity);
            secondCityNumber = map.GetCityNumber(destinationCity);
            
            //log command info
            ui.LogThis("#   #   #   #   #   #   #   #   #   #   #   #   #   #");
            ui.LogThis(startCity + " (" + firstCityNumber + ") TO " 
                    + destinationCity + " (" + secondCityNumber + ")");
            
            //do search
            if(firstCityNumber == -1 || secondCityNumber == -1)
            {
                ui.LogThis("Error - one of the cities is not on this map\n");
            }
            else
            { 
                answers = shortestPath.FindPath(firstCityNumber, secondCityNumber);
                
                //log answers
                ui.LogThis(answers[0]);
                ui.LogThis(answers[1]);
            }
            
            //read another command
            loopAgain = ui.ReadOneCommand();
        }

        //call finish methods
        map.Finish();
        ui.Finish();
    }
}
