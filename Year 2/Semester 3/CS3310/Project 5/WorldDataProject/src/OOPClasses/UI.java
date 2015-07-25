/* PROJECT: WorldDataProject #5         CLASS: UI
* Written By: Marcel Englmaier
* FILES ACCESSED: ?CityPairs.txt, LogSession.txt
* FILE STRUCTURE: OOP
* INDEX STRUCTURE:  
* DESCRIPTION: reads citypairs and writes to logsession
*******************************************************************************/

package OOPClasses;

import java.io.*;
import java.util.Scanner;


public class UI
{
    //<editor-fold defaultstate="collapsed" desc="Public Declarations">
    
    
    //**************************** PUBLIC DECLARATIONS ************************
    //None
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Declarations">
    
    
    //**************************** PRIVATE DECLARATIONS ************************
    private PrintWriter logSession;
    private Scanner cityPairs;
    private String [] cityPairArray;
    private String startCity;
    private String destinationCity;
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public Constructor(s)">
    
    
    //**************************** PUBLIC CONSTRUCTOR(S) ***********************
    public UI(String fileNamePrefix)
    {
        try
        {
            logSession = new PrintWriter(new File("LogSession.txt"));
            cityPairs = new Scanner(new File(fileNamePrefix + "CityPairs.txt"));
        }
        catch(IOException e)
        {
            System.out.println("Could not open LogSession.txt and " + fileNamePrefix + "CityPairs.txt");
        }
    }
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public Get/Set Methods">
    
    
    //**************************** PUBLIC GET/SET METHODS **********************
    public String getStartCity()
    {
        return startCity;
    }
    
    public String getDestinationCity()
    {
        return destinationCity;
    }
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public Service Methods">
    
    
    //**************************** PUBLIC SERVICE METHODS **********************
     public void finishUp()
    {
        logSession.close();
        cityPairs.close();
    }
     
    public boolean amIDone()
    {
        boolean ret = false;
        if(cityPairs.hasNextLine())
        {
            cityPairArray = cityPairs.nextLine().split(" ");
            startCity = cityPairArray[0];
            destinationCity = cityPairArray[1];
            ret = true;
        }
        return ret;
    }
    
    public void log(String message)
    {
        logSession.println(message);
    }
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Methods">
    
    
    //**************************** PRIVATE METHODS *****************************
    
    
    //</editor-fold>
}