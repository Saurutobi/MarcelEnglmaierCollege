/* PROJECT: WorldDataProject #3         PROGRAM: Setup
 * Written By: Marcel Englmaier
 * PROJECT CLASSES USED: RawData, CodeIndex
 * FILES ACCESSED: none
 * DESCRIPTION: this class gets one line from RawData, and passes it to CodeIndex
 ******************************************************************************/
package Setup;

import OOPClasses.CodeIndex;
import OOPClasses.RawData;
import java.io.*;


public class Setup {

    //<editor-fold defaultstate="collapsed" desc="Public Declarations">
    
    
    //**************************** PUBLIC DECLARATIONS ************************
    //None
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Declarations">
    
    
    //**************************** PRIVATE DECLARATIONS ************************
    private static String [] currentRecord;
    private static RawData input;
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public Main">
    
    
    //****************************** PUBLIC Main *******************************
    public static void main(String[] args) throws IOException
    {    
        //initialize RawData Object
        input = new RawData();

        //initialize CodeIndex Object
        CodeIndex output = new CodeIndex(true);

        //loop til EOF of RawData file
        read();
        while(!input.isRawDataReadingDone())
        {
            output.insert1Country(currentRecord);
            read();
        }
        
        //close all files
        input.close();
        output.close();
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
    private static void read()
    {
        //gets one line from RawData
        currentRecord = input.read1Line().split(",");
    }
    
    
    //</editor-fold>
}
