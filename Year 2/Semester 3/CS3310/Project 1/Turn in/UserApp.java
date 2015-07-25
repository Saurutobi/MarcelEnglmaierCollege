/* PROJECT: WorldDataProject         PROGRAM: UserApp
* AUTHOR:
* COURSE INFO:  CS3310 (MWF) Fall 2012 - Asgn 1
* PROJECT CLASSES USED:  UserInterface, DataStorage
*       [and for Asgn2 onwards, also:  CodeIndex, NameIndex] 
* FILES ACCESSED: (only indirectly through other classes)
*       INPUT:  ?Trans.txt (handled by UserInterface class)
*       OUTPUT: LogSession.txt (handled by UserInterface class)
*       INPUT/OUTPUT: ?MainData.bin (handled by DataStorage class)
*       INPUT/OUTPUT: for Asgn2:  ?IndexBackup.bin
*                       (handled by CodeIndex & NameIndex classes)]
*       where ? is the appropriate fileNamePrefix.
* DESCRIPTION:  This program processes the transactions to do:
*               query, showAll, insert, delete, update
*       to retrieve or change the main data (and for Asgn2, also the 2 internal
*       indexes as well).
* CORE ALGORITHM:  Traditional sequential transaction processing - i.e.,
*       [Asgn 2:  load backup indexes into 2 internal indexes]
*       loop til NO_MORE_TRANS (as indicated by UserInterface)
*       {   get 1 transaction
*           call appropriate handler method (in this program)
*               based on tranCode (QI, SI, IN, DI, UP) [more for Asgn 2]                  
*       }
*       [Asgn 2:  dump internal indexes to external backup storage]
* TRANSACTION HANDLERS:  5 separate handler methods, 1 per tranCode
*       [more for Asgn 2]
*******************************************************************************/

package UserApp;

import OOPClasses.CodeIndex;
import OOPClasses.DataStorage;
import OOPClasses.NameIndex;
import OOPClasses.UserInterface;
import java.io.*;

public class UserApp 
{
    
    public static DataStorage dStorage;
    public static UserInterface UI;
            
    public static void main(String[] args) throws IOException
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
        
        UI = new UserInterface(fileNamePrefix, true);
        UI.writeToLog("OK, starting UserApp");
        dStorage = new DataStorage(fileNamePrefix);
        while(UI.transHasNextLine())
        {
            String [] transMethods = UI.readTransData();
                switch(transMethods[0])
                {
                    case "QI" : query(transMethods);
                        break;
                    case "LI" : list();
                        break;
                    case "IN" : write(transMethods);
                        break;
                    case "DE" : delete(transMethods);
                        break;
                }
        }
        UI.writeToLog("**** Closing " + fileNamePrefix + "MainData.bin");
        dStorage.closeMainData();
        UI.writeToLog("**** Closing UserApp - " + dStorage.getProcessed() + " items Processed");
        UI.closeLog();
    } 
    
    public static void query(String [] trans) throws IOException
    {
        String [] temp = new String[2];
        boolean willBeNeg = false;
        if(Short.valueOf(trans[1]) == 0)
        {
            willBeNeg = true;
        }
        else
        {
            temp = dStorage.read1Country(Short.valueOf(trans[1]));
        }
        String output = "";
        
        if(temp[1] == null || willBeNeg)
        {
            output = "No Country with that ID";
        }
        else
        {
            //if there is no error, format the output for viewing
            output = formatSize(temp[1], 3) + " " + 
                     formatSize(temp[2], 17) + " " + 
                     formatSize(temp[3], 11) + " " + 
                     formatSize(temp[4], 10) + " " + 
                     formatSize(temp[5], 4) + " " + 
                     formatSize(temp[6], 2) + " " + 
                     formatSize(temp[7], 8) + " " + 
                     formatSize(temp[8], 4);
        }
        UI.writeToLog("QI: " + Short.valueOf(trans[1]) + "\n      " + output);
        
    }
    
    public static void list() throws IOException
    {
        //reads one line and prints it in the correct format, then reads the next line until no more lines
        String [] writeThis;
        String output = "";
        UI.writeToLog("LI: ");
        UI.writeToLog("    Code   Name                 Continent      Region        Area       Indep   Population  L.exp");
        for(int i = 1; i < dStorage.getMaxID(); i++)
        {
            writeThis = dStorage.read1Country(i);
            if(!(writeThis[0] == null || writeThis[1] == null || writeThis[2] == null))
            {
                UI.writeToLog("    " + formatSize(writeThis[1], 3) + "    " +  
                                       formatSize(writeThis[2], 17) + "    " +  
                                       formatSize(writeThis[3], 11) + "    " +  
                                       formatSize(writeThis[4], 10) + "    " +  
                                       formatSize(writeThis[5], 7) + "    " +  
                                       formatSize(writeThis[6], 4) + "    " +  
                                       formatSize(writeThis[7], 8) + "    " +  
                                       formatSize(writeThis[8], 4));
            }
        }
    }
    
    public static void write(String [] trans) throws IOException
    {
        //writes one country
        int result = dStorage.insertCountry(trans[1].split(","));
        if(result == 1)
        {
            UI.writeToLog("Country inserted");
        }
        else if(result == 0)
        {
            String [] errors = dStorage.getTypeOfErrors();
            UI.writeToLog(errors[dStorage.getNumOfErrors() - 1]);
        }
    }
    
    public static void delete(String [] trans) throws IOException
    {
        //deletes one country by overwritting the space with 0s
        UI.writeToLog(dStorage.deleteByID(Short.valueOf(trans[1])));
    }
    
     private static String formatSize(String input, int length)
    {
        //formats input String to a given size and returns it, either clipping it for too long Strings, or adding spaces for too short Strings
        char [] output = new char[length];
        for(int i = 0; i < length; i++)
        {
            if(i < input.length())
            {
                output[i] = input.charAt(i);
            }
            else
            {
                output[i] = ' ';
            }
        }
        return new String(output);
    }
}
