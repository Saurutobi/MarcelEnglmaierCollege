/* PROJECT: WorldDataProject #3         CLASS: UserInterface
 * Authored By: Quincy Campbell
 * Edited By: Marcel Englmaier
 * FILES ACCESSED:
 * DESCRIPTION:
 *******************************************************************************/
package OOPClasses;

import java.io.*;
import java.util.*;

public class UserInterface {
    
    //<editor-fold defaultstate="collapsed" desc="Public Declarations">
    
    
    //**************************** PUBLIC DECLARATIONS ************************
    //None
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Declarations">
    
    
    //**************************** PRIVATE DECLARATIONS ************************
    private PrintWriter logger;
    private BufferedReader transDataReader;
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public Constructor(s)">
    
    
    //**************************** PUBLIC CONSTRUCTOR(S) ***********************
    public UserInterface() throws IOException
    {
        logger = new PrintWriter(new FileWriter(new File("LogSession.txt"), true));
        transDataReader = new BufferedReader(new FileReader(new File("TransData.txt")));
    }
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public Get/Set Methods">
    
    
    //**************************** PUBLIC GET/SET METHODS **********************
    //None
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public Service Methods">
    
    
    //**************************** PUBLIC SERVICE METHODS **********************
    public void writeToLog(String message) throws IOException
    {
        logger.println(message);
        System.out.println("Logged|| " + message);
    }

    // closes log file
    public void close() throws IOException
    {
        transDataReader.close();
        logger.close();
    }
     
    public String read1Trans() throws IOException
    {
         return transDataReader.readLine();
    }
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Methods">
    
    
    //**************************** PRIVATE METHODS *****************************
    //None
    
    
    //</editor-fold>
}
