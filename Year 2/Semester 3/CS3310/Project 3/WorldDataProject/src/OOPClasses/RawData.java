/* PROJECT: WorldDataProject         CLASS: RawData
* Written By: Marcel Englmaier
 * Edited: Marcel Englmaier
 * FILES ACCESSED: RawData.csv
 * FILE STRUCTURE: OOP
 * DESCRIPTION: This class reads 1 line at a time and returns a boolean if the
 *          pointer is at the end of the file.
 *******************************************************************************/
package OOPClasses;

import java.io.*;

public class RawData {
    
    //<editor-fold defaultstate="collapsed" desc="Public Declarations">
    
    
    //**************************** PUBLIC DECLARATIONS ************************
    //None
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Declarations">
    
    
    //**************************** PRIVATE DECLARATIONS ************************
    private BufferedReader rawDataReader;
    private String currentLine;
    private boolean readingIsDone;
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public Constructor(s)">
    
    
    //**************************** PUBLIC CONSTRUCTOR(S) ***********************
    public RawData() throws FileNotFoundException
    {
        //initializes a new Reader for the RawData.csv
        rawDataReader = new BufferedReader(new FileReader("RawData.csv"));
        readingIsDone = false;
    }
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public Get/Set Methods">
    
    
    //**************************** PUBLIC GET/SET METHODS **********************
    public boolean isRawDataReadingDone()
    {
        //returns whether  or not the reading is past the end of file
        return readingIsDone;
    }
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public Service Methods">
    
    
    //**************************** PUBLIC SERVICE METHODS **********************
    public String read1Line()
    {
        try
        {
            //reads one line
            currentLine = rawDataReader.readLine();
            //if the line isn't 'bad', return it, otherwise set the EOF boolean to true
            if (currentLine != null)
            {
                return currentLine;
            }
            else
            {
                readingIsDone = true;
            }
        }
        catch(IOException e)
        {
            readingIsDone = true;
        }
        return "";
    }
    
//******************************************************************************
    
    public void close() throws IOException
    {
        //close the file
        rawDataReader.close();
    }
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Methods">
    
    
    //**************************** PRIVATE METHODS *****************************
    //None
    
    
    //</editor-fold>
}
