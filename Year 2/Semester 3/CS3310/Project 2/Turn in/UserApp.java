/* PROJECT: WorldDataProject         PROGRAM: UserApp
 * AUTHOR: Original: Quincy Campbell, mods: Marcel Englmaier
 * COURSE INFO:  CS3310 (MWF) Fall 2012 - Asgn 1
 * PROJECT CLASSES USED:  UserInterface, DataStorage, NameIndex
 *       [and for Asgn2 onwards,UserInterface, DataStorage also:  CodeIndex, NameIndex] 
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

import OOPClasses.DataStorage;
import OOPClasses.DupRecException;
import OOPClasses.CodeIndex;
import OOPClasses.UserInterface;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UserApp {

    private static DataStorage mainData;
    private static CodeIndex indexOfCode;
    private static UserInterface ui;

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // Detect whether Setup is being run by AutoTesterUtility, or being run
        // manually by the user, and assign the 2 file name prefixes accordingly
        String mainPrefix, transPrefix;
        if (args.length > 1) {
            mainPrefix = args[0];
            transPrefix = args[1];
        } else {
            mainPrefix = "A2Z";
            transPrefix = "A2Z";
        }

        ui = new UserInterface(transPrefix);
        ui.log("***** UserApp PROGRAM started");

        mainData = new DataStorage(mainPrefix);
        ui.log("***** " + mainPrefix + "MainData.bin FILE opened");
        indexOfCode = new CodeIndex(true, mainPrefix);
        ui.log("***** " + mainPrefix + "IndexBackup.bin FILE opened");
        ui.log("");

        // initialize transaction counter
        int transProcessed = 0;

        // core algorithm described above
        String transaction;
        while (ui.hasMoreTrans()) // loop til NO_MORE_TRANS
        {
            transaction = ui.read1Trans();  //     get 1 transaction
            ui.log(transaction);
//            call appropriate handler method (in this program)
//            based on tranCode (QI, SI, IN, DI, UP(LI?) [more for Asgn 2] 
            switch (transaction.split(" ")[0]) {
                case "QI": // query by id
                    queryById(transaction.split(" ")[1]);
                    break;
                case "QC": //query, but use code
                    queryByCode(transaction.split(" ")[1]);
                    break;
                case "IN": // insert at id
                    insert(transaction.substring(3));
                    break;
                case "DE": // delete at id
                    deleteById(transaction.split(" ")[1]);
                    break;
                case "LI":
                    listAllById();
                    break;
                case "LC": //list out the BST in order
                    listAllByCode();
                    break;
            }
            transProcessed++;
        }

        if (transProcessed > 0) {
            ui.log("- - -");
            ui.log("+ + + + + + + + + + + + + + + + + + + THE END OF DATA + + + + + + + + + + + + + + + + + + + + + +");
            ui.log("");
        }

        ui.log("***** " + mainPrefix + "MainData.bin FILE closed");
        mainData.closeFile();
        
        ui.log("***** " + mainPrefix + "IndexBackup.bin FILE closed");
        indexOfCode.finishUp();

        ui.log("***** UserApp Program ended - " + transProcessed + " transactions processed");
        ui.endLog();
    }
//------------------------------------------------- 5 TRANS HANDLER METHODS

    private static void queryById(String id) throws IOException {
        String record = mainData.readOneRecord(Integer.parseInt(id));
        if (!record.equals("")) {
            ui.log("  " + record);
        } else {
            ui.log("  ERROR – no country with that id");
        }
    }

    private static void listAllById() throws IOException
    {
        //Print Header
        ui.log("  ID " + DataStorage.fixedSizeString("CODE", 4) + " " +
                       DataStorage.fixedSizeString("NAME", DataStorage.SIZE_OF_NAME) + '\t' +
                       DataStorage.fixedSizeString("CONTINENT", DataStorage.SIZE_OF_CONT) + '\t' +
                       DataStorage.fixedSizeString("REGION", DataStorage.SIZE_OF_REGION) + '\t' +
                       DataStorage.fixedSizeString("AREA", 12) + '\t' +
                       DataStorage.fixedSizeString("INDEP", 6) + '\t' +
                       DataStorage.fixedSizeString("POPULATION", 12) + '\t' +
                       DataStorage.fixedSizeString("L.EXP", 8));
        String record;
        for (int i = 1; i <= mainData.getMaxId(); i++) {
            record = mainData.readOneRecord(i);
            if (!record.equals("")) {
                ui.log("  " + record);
            }
        }
    }

    private static void insert(String record) throws IOException {
        try {
            int rrn = mainData.writeCountry(record.split(","));
            ui.log("  OK, country inserted");
            //insert to Code Index aswell
            String [] temp = record.split(",");
            indexOfCode.insertCode(temp[0], (short)rrn);
            
            ui.log("  OK, code inserted");
            ui.log("  >>>" + indexOfCode.getNodesVisited() + " Nodes Visited");
        } catch (DupRecException ex) {
            ui.log(ex.getMessage());
        } catch (IOException ex) {
            ui.log("  ERROR - invalid record");
        }
    }

    private static void deleteById(String id) throws IOException {
        String record = mainData.readOneRecord(Integer.parseInt(id));
        if (!record.equals("")) {
            ui.log("  OK, country deleted - " + record.substring(7, 7 + DataStorage.SIZE_OF_NAME));
        } else {
            ui.log("  ERROR – no country with that id");
        }
        ui.log("  >>>" + indexOfCode.getNodesVisited() + " Nodes Visited");
    }
    
    private static void listAllByCode() throws IOException 
    {
        //Print Header
        ui.log("  ID " + DataStorage.fixedSizeString("CODE", 4) + " " +
                       DataStorage.fixedSizeString("NAME", DataStorage.SIZE_OF_NAME) + '\t' +
                       DataStorage.fixedSizeString("CONTINENT", DataStorage.SIZE_OF_CONT) + '\t' +
                       DataStorage.fixedSizeString("REGION", DataStorage.SIZE_OF_REGION) + '\t' +
                       DataStorage.fixedSizeString("AREA", 12) + '\t' +
                       DataStorage.fixedSizeString("INDEP", 6) + '\t' +
                       DataStorage.fixedSizeString("POPULATION", 12) + '\t' +
                       DataStorage.fixedSizeString("L.EXP", 8));
        
        short [] temp = indexOfCode.getAllRRN();
        //print all items in order 
        for(int i = 0; i <temp.length; i++)
        {
            String record = mainData.readOneRecord(temp[i]);
            if (!record.equals("")) 
            {
                ui.log("  " + record);
            } else {
                ui.log("  ERROR – no country with that id");
            }
        }
    }
    
    private static void queryByCode(String id) throws IOException
    {
        //searches for the id given
        String record = mainData.readOneRecord(indexOfCode.query(id));
        if (!record.equals("")) 
        {
            ui.log("  " + record);
        } else {
            ui.log("  ERROR – no country with that id");
        }
        ui.log("  >>>" + indexOfCode.getNodesVisited() + " Nodes Visited");
    }
}
