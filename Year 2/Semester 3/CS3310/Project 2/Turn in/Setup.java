/* PROJECT: WorldDataProject         PROGRAM: Setup
 * AUTHOR: Original: Quincy Campbell, mods: Marcel Englmaier
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
import OOPClasses.DupRecException;
import OOPClasses.NameIndex;
import OOPClasses.RawData;
import OOPClasses.UserInterface;
import java.io.IOException;


public class Setup {

    public static void main(String[] args) throws IOException {

        // initialize UserInterface, open log file
        UserInterface ui = new UserInterface();
        ui.log("***** Setup PROGRAM started");

        // Detect whether Setup is being run by AutoTesterUtility, or being run
        // manually by the user, and assign "fileNamePrefix" accordingly.
        String fileNamePrefix;      // for ?RawData, ?MainData,
        //                          // (& for Asgn2 onwards, ?IndexBackup)
        if (args.length > 0) {
            fileNamePrefix = args[0];
        } else {
            fileNamePrefix = "A2Z";
        }

        // initize record counters
        int itemsProcessed = 0, itemsOk = 0, itemsDuped = 0;
        
        // open rawdata
        RawData input = new RawData(fileNamePrefix);
        ui.log("***** " + fileNamePrefix + "RawData.csv FILE opened");

        // open maindata
        DataStorage output = new DataStorage(fileNamePrefix);
        ui.log("***** " + fileNamePrefix + "MainData.bin FILE opened");
        
        // initialize code index
        CodeIndex codeIndex = new CodeIndex(false, fileNamePrefix);

//       loop til EOF of RawData file
        String[] record;
        do {
//       read 1 record from RawData file
            
            record = input.read1Country().split(",");
            if (record.length > 0 && !record[0].equals("")) {
                try {
                    // use that data to construct & write 1 record to MainData file
                    //                   (returning RRN where the record was stored)
                    short rrn = output.writeCountry(record);
                    
                    // add to code index
                    codeIndex.insertCode(record[1], rrn);
                    
                    itemsOk++;
                } catch (DupRecException ex) {
                    ui.log(ex.getMessage());
                    itemsDuped++;
                } finally {
                    itemsProcessed++;
                }
            }
        } while (record.length > 0 && !record[0].equals(""));

        ui.log("***** " + fileNamePrefix + "MainData.bin FILE closed");
        output.closeFile();

        ui.log("***** " + fileNamePrefix + "RawData.bin FILE closed");
        input.closeFile();
        
        ui.log("***** Saving CodeIndex to " + fileNamePrefix + "IndexBackup.bin FILE");
        codeIndex.finishUp();
        
        ui.log("***** Setup PROGRAM ended - " + itemsProcessed + " items processed - " + itemsOk + " OK - " + itemsDuped + " DUPLICATES");
        ui.endLog();
    }
}
