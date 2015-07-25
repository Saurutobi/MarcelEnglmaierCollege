/* PROJECT: WorldDataProject         CLASS: UserInterface
* AUTHOR:
* FILES ACCESSED:
*       INPUT:  ?TransData.txt (class user specifies fileNamePrefix)
*       OUTPUT:  LogSession.txt (only a SINGLE file for whole project, even
*                   though programs are run multiple times)
*                   NOTE:  File is always opened in APPEND MODE.  In order to
*                           get a fresh copy, either the AutoTesterUtility will
*                           delete the file, or the developer will do it
*                           manually before running the project programs.
* DESCRIPTION: All file and record handling for the above 2 files is handled in
*       this class.  The actual programs (Setup & UserApp & ShowFilesUtility)
*       do NOT know that the transactions come from a FILE, nor that messages
*       to be written are logged to a FILE.
*******************************************************************************/

package OOPClasses;

import java.io.*;
import java.util.Scanner;

public class UserInterface
{
    
    //**************************** PRIVATE DECLARATIONS ************************
    private PrintWriter logSessionFile;
    private Scanner transDataScanner;
    private String fileName;
    
    
    //**************************** PUBLIC CONSTRUCTOR(S) ***********************
    public UserInterface(String prefix, boolean readTrans) throws IOException
    {
        //opens log
        openLog();
        //checks which parent program is running it, and if it should also be reading ?TransData.txt
        if(readTrans)
        {
            //tries to read TransData
            //creates filename for TransData
            fileName = prefix + "TransData.txt";
            
            //creates log message and opens TransData
            transDataScanner = new Scanner(new File(fileName));
            writeToLog("Opening  " + fileName);
        }
    }
    
    
    //**************************** PUBLIC SERVICE METHODS **********************
    public String [] readTransData() throws IOException
    {
        //reads one line of Trans Data
        return transDataScanner.nextLine().split(" ");
    }
    
    public boolean transHasNextLine()
    {
        //checks if another line can be read and returns the appropriate boolean
        if(transDataScanner.hasNextLine())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public void closeLog() throws IOException
    {
        //closes the LogSession
        writeToLog("**** Closing Log Session\n");
        logSessionFile.close();
    }
    
    public void writeToLog(String message)
    {
        
            //writes to LogSession.txt
            logSessionFile.println(message + "\n");
            //prints to System.out
            System.out.println(message);
    }
    
    
    //**************************** PRIVATE METHODS *****************************
    private void openLog() throws IOException
    {
        //opens the LogSession
        logSessionFile = new PrintWriter(new FileWriter("logSessionFile.txt", true));
        writeToLog("Opening Log Session");
    }
    
    
    //**************************** PUBLIC GET/SET METHODS **********************
    
}
