/* PROJECT: Shortest Path         CLASS: UI
* AUTHOR: Ekema
* COURSE INFO:  CS3310 (MWF) Fall 2012 - Asgn 5
* FILES ACCESSED: LogSession.txt, ?CityPairs.txt
*******************************************************************************/

package OOPClasses;

import java.io.*;
import java.util.Scanner;

public class UI 
{
    /************************ Private Declarations ****************************/
    private PrintWriter logSession;
    private Scanner cityPairs;
    private String[] cityPairArray;
    private String startCity, destinationCity;
    
    /************************ Public Getters/Setters **************************/
    public String GetStartCity()
    {
        return startCity;
    }
    
    //--------------------------------------------------------------------------
    public String GetDestinationCity()
    {
        return destinationCity;
    }
    
    /************************ Constructor *************************************/
    public UI(String fileNamePrefix)
    {
        OpenFiles(fileNamePrefix);
    }
    
    /************************ Public Service Methods **************************/
    public void Finish()
    {
        CloseFiles();
    }
    
    //--------------------------------------------------------------------------
    public boolean ReadOneCommand()
    {
        boolean retval = false;
        if(cityPairs.hasNextLine())
        {
            cityPairArray = cityPairs.nextLine().split(" ");
            startCity = cityPairArray[0];
            destinationCity = cityPairArray[1];
            retval = true;
        }
        return retval;
    }
    
    //--------------------------------------------------------------------------
    public void LogThis(String message)
    {
        logSession.println(message);
    }
    
    /************************ Private Methods *********************************/
    private void OpenFiles(String fileNamePrefix)
    {
        //try-catch block to open LogSession.txt and ?CityPairs.txt
        try
        {
            logSession = new PrintWriter(new FileWriter(new File("res" 
                    + "/LogSession.txt"), true));
            cityPairs = new Scanner(new File("res/" + fileNamePrefix 
                    + "CityPairs.txt"));
        }
        catch(IOException e)
        {
            System.out.println("Could not open LogSession.txt and "
                    + "?CityPairs.txt");
        }
    }
    
    //--------------------------------------------------------------------------
    private void CloseFiles()
    {
        logSession.close();
        cityPairs.close();
    }
}
