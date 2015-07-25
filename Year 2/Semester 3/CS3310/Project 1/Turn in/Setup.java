/* PROJECT: WorldDataProject         PROGRAM: Setup
* AUTHOR: 
* COURSE INFO:  CS3310 (MWF) Fall 2012 - Asgn 1
* PROJECT CLASSES USED:  RawData, UserInterface, DataStorage
*       [and for Asgn2 onwards, also:  CodeIndex, NameIndex]
* FILES ACCESSED: (only indirectly through other classes)
*       INPUT:  ?RawData.csv (handled by RawData class)
*       OUTPUT: LogSession.txt (handled by UserInterface class)
*       OUTPUT: ?MainData.bin (also INPUT to be able to check for "empty"'s)
*                       (handled by DataStorage class)
*       OUTPUT: for Asgn2:  ?IndexBackup.bin
*                       (handled by CodeIndex & NameIndex classes)]
*       where ? is the appropriate fileNamePrefix.
* DESCRIPTION:  This program stores the raw data (from RawData file) in the main
*       data storage location (the MainData file).  [Asgn2: it also creates the
*       CodeIndex and NameIndex as internal indexes, dumping them to external
*       backup storage (the IndexBackup file) after creation's completed].
* CORE ALGORITHM:  Traditional sequential-file processing - i.e., 
*       loop til EOF of RawData file
*       {   read 1 record from RawData file
*           use that data to construct & write 1 record to MainData file
*                   (returning RRN where the record was stored)
*           [Asgn2, also insert appropriate data for that record into 
*                   appropriate indexes, using the KeyField and RRN]
*       }
*       [Asgn 2:  dump internal indexes to external backup storage]
*******************************************************************************/

package Setup;

import OOPClasses.CodeIndex;
import OOPClasses.DataStorage;
import OOPClasses.NameIndex;
import OOPClasses.RawData;
import OOPClasses.UserInterface;
import java.io.*;

public class Setup 
{    
    
    public static void main(String[] args)  throws IOException
    {
        
        
        // Detect whether Setup is being run by AutoTesterUtility, or being run
        // manually by the user, and assign "fileNamePrefix" accordingly.
        String fileNamePrefix;      // for ?RawData, ?MainData,
        //                          // (& for Asgn2 onwards, ?IndexBackup)
        if (args.length > 0)
        {
            fileNamePrefix = args[0];
        }
        else
        {
            fileNamePrefix = "A2Z";
        }
        
        // declare new instances of UI, RawData, and DataStorage
        UserInterface UI = new UserInterface(fileNamePrefix, false);
        UI.writeToLog("Setup Program Started");
        RawData RDobject = new RawData(fileNamePrefix + "RawData.csv");
        UI.writeToLog("**** Opening " + fileNamePrefix + "RawData.csv");
        //check if nothing went wrong yet
        if(!RDobject.failed)
        {
            //open ?MainData.bin and create the according DataStorage object
            DataStorage DSobject = new DataStorage(fileNamePrefix);
            UI.writeToLog("**** Opening " + fileNamePrefix + "MainData.bin");
            //read in the RawData file so long as it isn't at the EOF
            while(!RDobject.endOfFileCheck())
            {
                //pull a line from RawData
                String [] temp = RDobject.getLine();
                //check to make sure that line didn't set off the endOfFile boolean
                if(!RDobject.endOfFileCheck())
                {
                    //pass RawData's line to DataStorage
                    DSobject.write1Country(temp, true);
                }
            }
            //close Rawdata and MainData and log both
            UI.writeToLog("**** Closing " + fileNamePrefix + "RawData.csv");
            RDobject.closeRawData();
            UI.writeToLog("**** Closing " + fileNamePrefix + "MainData.bin");
            DSobject.closeMainData();
            //log summary of jobs completed
            UI.writeToLog(Integer.toString(DSobject.getResults()) + " items written, " + Integer.toString(DSobject.getNumOfErrors()) + " errors");
            //checks if errors happened. if yes, logs them
            if(DSobject.getNumOfErrors() != 0)
            {
                UI.writeToLog("Errors:");
                for(int i = 0; i < DSobject.getTypeOfErrors().length -1; i++)
                {
                    UI.writeToLog("     " + DSobject.getTypeOfErrors()[i]);
                }
            }
        }
        else
        {
            //logs original RawData fault
            UI.writeToLog("Reading the RawData failed");
        }
        //logs and closes setup
        UI.writeToLog("**** Closing Setup");
        UI.closeLog();
    }
}
