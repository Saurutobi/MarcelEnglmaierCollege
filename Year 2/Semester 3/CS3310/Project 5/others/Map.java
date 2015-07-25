/* PROJECT: Shortest Path         CLASS: Map
* AUTHOR: Ekema
* COURSE INFO:  CS3310 (MWF) Fall 2012 - Asgn 5
* FILES ACCESSED: ?MapData.bin, ?CityNames.txt
* FILE STRUCTURE: External undirected adjacency matrix
*******************************************************************************/

package OOPClasses;

import java.io.*;
import java.util.Scanner;

public class Map 
{
    /************************ Private Declarations ****************************/
    private RandomAccessFile mapData;
    private Scanner cityNames;
    private String[] cityNameArray;
    private String temp;
    private int i, j, n, cityNameLength, offSet;
    private final int HEADER_REC_SIZE = 4;
    
    /************************ Public Getters/Setters **************************/
    public int GetN()
    {
        return n;
    }
    
    /************************ Constructor *************************************/
    public Map(String fileNamePrefix)
    {
        OpenFiles(fileNamePrefix);
        
        //try-catch block to read and store city names in an array
        try
        {
            n = mapData.readInt();
            cityNameArray = new String[n];
            cityNameLength = Integer.parseInt(cityNames.nextLine());
            temp = cityNames.nextLine();
            for(i = 0; i < n; i++)
            {
                for(j = 0; j < cityNameLength; j++)
                cityNameArray[i] = temp.substring((i * cityNameLength), (i + 1) * cityNameLength);
                cityNameArray[i] = cityNameArray[i].trim();
            }
        }
        catch(IOException e)
        {
            System.out.println("Could not read n from ?MapData");
        }
    }
    
    /************************ Public Service Methods **************************/
    public String GetCityName(int cityNumber)
    {
        return cityNameArray[cityNumber];
    }
    
    //--------------------------------------------------------------------------
    public int GetCityNumber(String cityName)
    {
        int retval = -1;
        
        //linear search of array
        for(i = 0; i < n; i++)
        {
            if(cityNameArray[i].equalsIgnoreCase(cityName))
            {                
                retval = i;
            }
        }
        return retval;
    }
    
    //--------------------------------------------------------------------------
    public int GetRoadDistance(int cityNumber1, int cityNumber2)
    {
        int retval = -1;
        
        //try-catch block to read road distance from external adjacency matrix
        offSet = CalculateOffSet(cityNumber1, cityNumber2);
        try
        {
            mapData.seek(offSet);
            retval = mapData.readInt();
        }
        catch(IOException e)
        {
            System.out.println("Could not read from ?MapData.bin");
        }
        return retval;
    }
    
    //--------------------------------------------------------------------------
    public void Finish()
    {
        CloseFiles();
    }
    
    /************************ Private Methods *********************************/
    private int CalculateOffSet(int cityNumber1, int cityNumber2)
    {
        return HEADER_REC_SIZE + (cityNumber1 * (n * 4)) + (cityNumber2 * 4);
    }
    
    //--------------------------------------------------------------------------
    private void OpenFiles(String fileNamePrefix)
    {
        //try-catch block to open ?MapData.bin and ?CityNames.txt
        try
        {
            mapData = new RandomAccessFile(new File("res/" + fileNamePrefix 
                    + "MapData.bin"), "rw");
            cityNames = new Scanner(new File("res/" + fileNamePrefix 
                    + "CityNames.txt"));
        }
        catch(IOException e)
        {
            System.out.println("Could not open ?MapData.bin and "
                    + "?CityNames.txt");
        }
    }
    
    //--------------------------------------------------------------------------
    private void CloseFiles()
    {
        //try-catch block to close ?MapData.bin and ?CityNames.txt
        try
        {
            mapData.close();
            cityNames.close();
        }
        catch(IOException e)
        {
            System.out.println("Could not close ?MapData.bin and "
                    + "?CityNames.txt");
        }
    }
}
