/* PROJECT: WorldDataProject #3         PROGRAM: UserApp
 * Written By: Marcel Englmaier
 * PROJECT CLASSES USED:CodeIndex, UserInterface
 * FILES ACCESSED:none
 * DESCRIPTION:This Class takes in data, and sends commands to CodeIndex accordingly
 * TRANSACTION HANDLERS:DC, QC, IN
 *******************************************************************************/
package UserApp;

import OOPClasses.CodeIndex;
import OOPClasses.UserInterface;
import java.io.*;

public class UserApp {
    
    private static CodeIndex indexOfCode;
    private static UserInterface ui;

    public static void main(String[] args) throws FileNotFoundException, IOException
    {
 
        ui = new UserInterface();
        
        indexOfCode = new CodeIndex(false);

        // initialize transaction counter
        int transProcessed = 0;

        // core algorithm described above
        String transaction = ui.read1Trans();
        while(transaction != null)
        {
            indexOfCode.reset();
            switch (transaction.split(" ")[0])
            {
                case "QC": //query, but use code
                    queryByCode(transaction.split(" ")[1]);
                    break;
                case "DC":
                    deleteByCode(transaction.split(" ")[1]);
                    break;
                case "IN": // insert at id
                    insert(transaction.substring(3));
                    break;
            }
            transProcessed++;
            transaction = ui.read1Trans();
        }

        if (transProcessed > 0)
        {
            ui.writeToLog("+ + + + + + + + + + + + + + + + + + + THE END OF DATA + + + + + + + + + + + + + + + + + + + + + +\n\n");
        }
        
        indexOfCode.close();
        ui.close();
    }
//------------------------------------------------- 3 TRANS HANDLER METHODS-----

    

    private static void insert(String record) throws IOException
    {
        indexOfCode.insert1Country(record.split(","));
        ui.writeToLog("IN " + record);
        ui.writeToLog("    >>> OK, " + record.split(",")[1] + " inserted [" + indexOfCode.getNodesVisited() + " nodes Visited]");
    }
    
    private static void queryByCode(String id) throws IOException
    {
        if(indexOfCode.queryByCode(id) == -1)
        {
            ui.writeToLog("QC " + id + " >>> INVALID COUNTRY CODE [" + indexOfCode.getNodesVisited() + " nodes visited]");
        }
        else
        {
            ui.writeToLog("QC " + id + " >>> " + indexOfCode.queryByCode(id) + " [" + indexOfCode.getNodesVisited() + " nodes visited]");
        }
        
    }
    
    private static void deleteByCode(String id) throws IOException
    {
        if(indexOfCode.deleteByCode(id))
        {
            ui.writeToLog("DC " + id + " >>> OK, " + id + " deleted [" + indexOfCode.getNodesVisited() + " nodes Visited]");
        }
        else
        {
            ui.writeToLog("DC " + id + " >>> INVALID COUNTRY CODE [" + indexOfCode.getNodesVisited() + " nodes Visited]");
        }
    }
}
