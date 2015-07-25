/* PROJECT: WorldDataProject         PROGRAM: AutoTesterUtility
* AUTHOR: Ekema/Kaminski
* COURSE INFO:  CS3310 (MWF) Fall 2012 - Asgn 1
* PROJECT CLASSES USED: none (this does not use the OOP paradigm)
* FILES ACCESSED: LogSession.txt, ?MainData.bin, ?IndexBackup.bin
*       (where ? is the appropriate prefix).
* DESCRIPTION: This is a utility program for the developer.  It allows the
*       developer to automate the testing of the WorldDataProject programs with
*       various test data sets.  It deletes files from previous runs and
*       executes the 3 programs: Setup, UserApp and ShowFilesUtility, providing
*       file name prefixes and N's as parameters when calling those programs'
*       main methods.
*******************************************************************************/

package AutoTesterUtility;

import java.io.*;
import Setup.Setup;
import UserApp.UserApp;
import ShowFilesUtility.ShowFilesUtility;

public class AutoTesterUtility 
{
    public static void main(String[] args) throws IOException
    {
        System.out.println("OK, starting AutoTesterUtilty");
        
        // 3 parallel arrays (all strings) with hard-coded parameters to 
        // be used when calling the other 3 programs:
        String mainFilesPrefix[] = {"A2Z",  "A2Z",  "All", "Dup"};
        String transFilePrefix[] = {"A2Z1", "A2Z2", "All", "All"};
        String nRecToShow[] =      {"All",   "10",   "30",   "All"}; 
        
        //Delete the single LogSession.txt file (if it exists)
        DeleteFile("LogSessionFile.txt");
        
        for (int i=0;i<mainFilesPrefix.length;i++)
        {                        
            //Delete *MainData.bin (if it exists)
            DeleteFile(mainFilesPrefix[i] + "MainData.bin");
            //Delete *IndexBackup.bin (if it exists)
            DeleteFile(mainFilesPrefix[i] + "IndexBackup.bin");
        
            //Run the other 3 programs
            Setup.main(new String[]{mainFilesPrefix[i]});
            UserApp.main(new String[]{mainFilesPrefix[i],
                                      transFilePrefix[i]});
            ShowFilesUtility.main(new String[]{mainFilesPrefix[i], nRecToShow[i]});
        }                
    }
    
    //**************************** PRIVATE METHOD ******************************
    // This method checks if a file exists, and deletes it if it does.  
    // Returns: 
    //  - boolean True if file exists and is deleted.
    //  - boolean False if file does not exist, OR fails to be deleted.
    //**************************************************************************
    private static boolean DeleteFile(String fileName)
    {
        boolean delete = false;
        File f = new File(fileName);
        if (f.exists())
        {
            delete = f.delete();
        }
        return delete;
    }
}
