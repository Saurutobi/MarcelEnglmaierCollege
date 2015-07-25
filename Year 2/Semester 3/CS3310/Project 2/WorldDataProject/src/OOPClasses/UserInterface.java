/* PROJECT: WorldDataProject         CLASS: UserInterface
 * AUTHOR: Original: Quincy Campbell, mods: Marcel Englmaier
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class UserInterface {
    //**************************** PRIVATE DECLARATIONS ************************

    private PrintWriter logger;
    private Scanner transDataReader;
    private String transPrefix;
    private boolean hasTransData;

    //**************************** PUBLIC GET/SET METHODS **********************
    //**************************** PUBLIC CONSTRUCTOR(S) ***********************
    public UserInterface() throws IOException {
        logger = new PrintWriter(new FileWriter(new File("LogSession.txt"), true));
        log("***** LogSession.txt FILE opened");
        hasTransData = false;
    }
    
    public UserInterface(String filePrefix) throws FileNotFoundException, IOException {
        transPrefix = filePrefix;
        logger = new PrintWriter(new FileWriter(new File("LogSession.txt"), true));
        log("***** LogSession.txt FILE opened");
        transDataReader = new Scanner(new File(filePrefix + "TransData.txt"));
        log("***** " + filePrefix + "TransData FILE opened");
        
        hasTransData = true;
    }
    
    //**************************** PUBLIC SERVICE METHODS **********************    
    // writes a message to the log file
    public void log(String message) throws IOException {
        logger.println(message);
        System.out.println("Logged|| " + message);
    }

    // closes log file
    public void endLog() throws IOException {
        if (hasTransData) {
            log("***** " + transPrefix + "TransData.txt FILE closed");
        }
        log("***** LogSession.txt FILE closed");
        log("");
            logger.close();
    }
    
    public String read1Trans() throws NoSuchElementException {
         return transDataReader.nextLine();
    }
    
    public boolean hasMoreTrans() {
        try {
            return transDataReader.hasNextLine();
        } catch(IllegalStateException ex) {
            return false;
        }
    }

    //**************************** PRIVATE METHODS *****************************

}
