/* PROJECT: WorldDataProject #5         CLASS: Map
* Written By: Marcel Englmaier
* FILES ACCESSED: ?Mapdata.bin, ?Citynames.txt
* FILE STRUCTURE: OOP
* DESCRIPTION: is the map
*******************************************************************************/

package OOPClasses;

import java.io.*;
import java.util.Scanner;

public class Map
{
    //<editor-fold defaultstate="collapsed" desc="Public Declarations">
    
    
    //**************************** PUBLIC DECLARATIONS ************************
    //None
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Declarations">
    
    
    //**************************** PRIVATE DECLARATIONS ************************
    private RandomAccessFile mapData;
    private Scanner cityNames;
    private String [] cityNameArray;
    private String temp;
    private int i;
    private int j;
    private int n;
    private int cityNameLength;
    private int offSet;
    private final int HEADER_REC_SIZE = 4;
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public Constructor(s)">
    
    
    //**************************** PUBLIC CONSTRUCTOR(S) ***********************
    public Map(String fileNamePrefix)
    {
        try
        {
            mapData = new RandomAccessFile(new File(fileNamePrefix + "MapData.bin"), "rw");
            cityNames = new Scanner(new File(fileNamePrefix + "CityNames.txt"));
        }
        catch(IOException e)
        {
            System.out.println("Could not open ?MapData.bin and "
                    + "?CityNames.txt");
        }
        
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
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public Get/Set Methods">
    
    
    //**************************** PUBLIC GET/SET METHODS **********************
    public int getN()
    {
        return n;
    }
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public Service Methods">
    
    
    //**************************** PUBLIC SERVICE METHODS **********************
    public String getCityName(int cityNumber)
    {
        return cityNameArray[cityNumber];
    }
    
    public int getCityNumber(String cityName)
    {
        int retval = -1;
        for(i = 0; i < n; i++)
        {
            if(cityNameArray[i].equalsIgnoreCase(cityName))
                retval = i;
        }
        return retval;
    }
    
    public int getRoadDistance(int cityNumber1, int cityNumber2)
    {
        int retval = -1;
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
    
    public void finishUp()
    {
        try
        {
            mapData.close();
            cityNames.close();
        }
        catch(IOException e)
        {
            System.out.println("Could not close ?MapData.bin and ?CityNames.txt");
        }
    }
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Methods">
    
    
    //**************************** PRIVATE METHODS *****************************
    private int CalculateOffSet(int cityNumber1, int cityNumber2)
    {
        return HEADER_REC_SIZE + (cityNumber1 * (19 * 4)) + (cityNumber2 * 4);
    }
    
    
    //</editor-fold>
}