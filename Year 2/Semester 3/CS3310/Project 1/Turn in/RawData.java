/* PROJECT: WorldDataProject         CLASS: RawData
* AUTHOR: 
* FILES ACCESSED:
*       INPUT:  ?RawData.csv (class user specifies fileNamePrefix)
* FILE STRUCTURE:  Serial file (i.e., record are in no particular order).
*       An ASCII TEXT file.
*       A "Comma Separated Values" ("csv") file.
* DESCRIPTION: All file and record handling for MainData file is handled in
*       this class.  The actual program (Setup) does NOT know that the RawData
*       is coming from a FILE, nor what file structure is used.  
*******************************************************************************/

package OOPClasses;

import java.io.*;
import java.util.*;

public class RawData
{
    
    //********************************* DECLARATIONS ***************************
    private String nameOfFile;
    private boolean finishedStatus = false;
    
    
    //**************************** PUBLIC GET/SET METHODS **********************
    
    
    //**************************** PUBLIC CONSTRUCTOR(S) ***********************
    BufferedReader rawDataFileScanner;
    public boolean failed = false;
    public RawData(String fileName) throws IOException
    {
        //create a filename for general use
        nameOfFile = fileName;
        //opens ?RawData.csv file
        rawDataFileScanner = new BufferedReader(new FileReader(nameOfFile));

    }
    
    
    //**************************** PUBLIC SERVICE METHODS **********************
    public String [] getLine()
    {
        //creates output array
        String [] output = new String[9];
        //checks for EOF, then reads line
        try
        {
            
            output = rawDataFileScanner.readLine().split(",");
        }
        catch(Exception e)
        {
            //if end of file, make this boolean true
            finishedStatus = true;
        }
        return output;
    }
    
    public void closeRawData() throws IOException
    {
        //closes RawData
        rawDataFileScanner.close();
    }
    
    public boolean endOfFileCheck()
    {
        //returns a boolean that states if the file reading is done or not
        return finishedStatus;
    }
    
    
    //**************************** PRIVATE METHODS *****************************
}
