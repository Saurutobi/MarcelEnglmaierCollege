/* PROJECT: WorldDataProject         CLASS: RawData
 * AUTHOR: Original: Quincy Campbell, mods: Marcel Englmaier
 * FILES ACCESSED:
 *       INPUT:  ?RawData.csv (class user specifies fileNamePrefix)
 * FILE STRUCTURE:  Serial file (i.e., record are in no particular order).
 *       An ASCII TEXT file.
 *       A "Comma Separated Values" ("csv") file.
 * DESCRIPTION: All file and record handling for MainData file is handled in
 *       this class.  The actual program (Setup) does NOT know that the RawData
 *       is coming from a FILE, nor what file structure is used.  
 *******************************************************************************/
package OOPClasses;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class RawData {
    //**************************** PRIVATE DECLARATIONS ************************

    BufferedReader fileReader;
    String theLine;
    private boolean done;

    //**************************** PUBLIC GET/SET METHODS **********************
    /**
    return done;
    }   * @return the done
     */
    public boolean isDone() {
        return done;
    }

    //**************************** PUBLIC CONSTRUCTOR(S) ***********************
    public RawData(String prefix) throws FileNotFoundException {
        fileReader = new BufferedReader(new FileReader(prefix + "RawData.csv"));
        done = false;
    }

    //**************************** PUBLIC SERVICE METHODS **********************
    public String read1Country() {
        try {
            theLine = fileReader.readLine();
                if (theLine != null) {
                    return theLine;
                }
        } catch(IOException ex) {
            done = true;
        }
        return "";
        
    }
    
    public void closeFile() throws IOException {
        fileReader.close();
    }
    //**************************** PRIVATE METHODS *****************************
}
