/* PROJECT: WorldDataProject         CLASS: DataStorage
 * AUTHOR: Original: Quincy Campbell, mods: Marcel Englmaier
 * FILES ACCESSED:
 *       INPUT/OUTPUT:  ?MainData.bin (class user specifies fileNamePrefix)
 *               NOTE:  If Setup PROGRAM requests opening this file, delete prior
 *                       copies of the file (by open in TRUNCATE MODE or . . .).
 * FILE STRUCTURE:  Random Access (using Direct Address) on id field.
 *       A BINARY file (not a text file).
 * DESCRIPTION: All file and record handling for MainData file is handled in
 *       this class.  The actual programs (Setup & UserApp) do NOT know that
 *       the DataStorage is actually in a FILE, nor what file structure is used.
 *******************************************************************************/
package OOPClasses;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.FileAlreadyExistsException;
import java.text.DecimalFormat;
import java.util.logging.Formatter;

public class DataStorage {
    //**************************** PRIVATE DECLARATIONS ************************

    public static final int SIZE_OF_HEADER_REC = 32 / Byte.SIZE;
    public static final int SIZE_OF_ID = Short.SIZE / Byte.SIZE;
    public static final int SIZE_OF_CODE = 3;
    public static final int SIZE_OF_NAME = 17;
    public static final int SIZE_OF_CONT = 11;
    public static final int SIZE_OF_REGION = 10;
    public static final int SIZE_OF_SURFAR = Integer.SIZE / Byte.SIZE;
    public static final int SIZE_OF_YROFINDEP = Short.SIZE / Byte.SIZE;
    public static final int SIZE_OF_POPULATION = Long.SIZE / Byte.SIZE;
    public static final int SIZE_OF_LIFEEXPECT = Float.SIZE / Byte.SIZE;
    public static final int SIZE_OF_RECORD =
            SIZE_OF_ID
            + SIZE_OF_CODE
            + SIZE_OF_NAME
            + SIZE_OF_CONT
            + SIZE_OF_REGION
            + SIZE_OF_SURFAR
            + SIZE_OF_YROFINDEP
            + SIZE_OF_POPULATION
            + SIZE_OF_LIFEEXPECT;
    private RandomAccessFile mainData;
    private short rrn, maxId, n;

    //**************************** PUBLIC GET/SET METHODS **********************
    public short getN() {
        return n;
    }

    public short getMaxId() {
        return maxId;
    }

    //**************************** PUBLIC CONSTRUCTOR(S) ***********************
    public DataStorage(String prefix) throws FileNotFoundException {
        mainData = new RandomAccessFile(new File(prefix + "MainData.bin"), "rws");
        readHeader();
    }

    //**************************** PUBLIC SERVICE METHODS **********************
    public short writeCountry(String[] record) throws IOException, DupRecException {
        // get rrn
        rrn = Short.parseShort(record[0]);

        // if not a duplicate, write record
        if (readOneRecord(rrn).equals("")) {
            // update maxId
            if (rrn > maxId) {
                maxId = rrn;
            }

            // increment number of records
            n++;

            // write data
            mainData.seek(byteOffset(rrn));
            mainData.writeShort(rrn);     // write id
            mainData.writeBytes(fixedSizeString(record[1], SIZE_OF_CODE));  // write code
            mainData.writeBytes(fixedSizeString(record[2], SIZE_OF_NAME));  // write name
            mainData.writeBytes(fixedSizeString(record[3], SIZE_OF_CONT));  // write continent
            mainData.writeBytes(fixedSizeString(record[4], SIZE_OF_REGION));// write region
             if (!record[5].equalsIgnoreCase("null"))
             {
                 mainData.writeInt(Integer.parseInt(record[5]));         // write surface area
            } else {
                mainData.writeShort(0);
            }
            if (!record[6].equalsIgnoreCase("null")) {
                mainData.writeShort(Short.parseShort(record[6]));   // write year of independence
            } else {
                mainData.writeShort(0);
            }
            mainData.writeLong(Long.parseLong(record[7]));          // write population
            if (!record[8].equalsIgnoreCase("null")) {
                mainData.writeFloat(Float.parseFloat(record[8]));   // write life expectancy
            } else {
                mainData.write(new byte[Float.SIZE / Byte.SIZE]);
            }
        } else {
            mainData.seek(byteOffset(rrn) + SIZE_OF_ID + SIZE_OF_CODE);
            throw new DupRecException("  ERROR - duplicate id for " + record[2] + " (not inserted) - id " + rrn + " is " + nextBytes(SIZE_OF_NAME));
        }
        
        return rrn;
    }

    public String readOneRecord(int rrn) {
        try {
            mainData.seek(byteOffset(rrn));
            return readOneRecord();
        } catch (IOException ex) {
            return "";
        }
    }

    public String readOneRecord() {
        try {
        Short id = mainData.readShort();

        if (id != 0) {

            String retVal = "";
            retVal +=
                    rrnFormat.format(id) + ' '
                    + fixedSizeString(nextBytes(SIZE_OF_CODE), SIZE_OF_CODE) + ' '      // code
                    + fixedSizeString(nextBytes(SIZE_OF_NAME), SIZE_OF_NAME) + '\t'     // name
                    + fixedSizeString(nextBytes(SIZE_OF_CONT), SIZE_OF_CONT) + '\t'     // continent
                    + fixedSizeString(nextBytes(SIZE_OF_REGION), SIZE_OF_REGION) + '\t' // region
                    + fixedSizeString(String.valueOf(mainData.readInt()), 12) + '\t'    // surface area
                    + fixedSizeString(String.valueOf(mainData.readShort()), 6) + '\t'   // year of independence
                    + fixedSizeString(String.valueOf(mainData.readLong()), 12) + '\t'   // population
                    + fixedSizeString(String.valueOf(mainData.readFloat()), 8);         // life expectancy
            return retVal;
        }
        return "";
        } catch(IOException ex) {
            return "";
        }
    }

    public void closeFile() throws IOException {
        // write header record
        mainData.seek(0);
        mainData.writeShort(n);
        mainData.writeShort(maxId);

        mainData.close();
    }
    
    public static String fixedSizeString(String s, int size) {
        array = new char[size];
        for (int i = 0; i < size; i++) {
            if (i < s.length()) {
                array[i] = s.charAt(i);
            } else {
                array[i] = ' ';
            }
        }
        return new String(array);
    }
    
    
    //**************************** PRIVATE METHODS *****************************
    private static char[] array;

    private long byteOffset(int rrn) {
        return SIZE_OF_HEADER_REC + ((rrn - 1) * SIZE_OF_RECORD);
    }

    // returns the next n bytes of the mainData file as a string
    private String nextBytes(int n) {
        char[] retVal = new char[n];
        for (int i = 0; i < n; i++) {
            try {
                retVal[i] = (char) mainData.readByte();
            } catch (IOException ex) {
                retVal[i] = ' ';
            }
        }

        return new String(retVal);
    }

    private void readHeader() {
        try {
            mainData.seek(0);
            n = mainData.readShort();
            maxId = mainData.readShort();
        } catch (IOException ex) {
            n = 0;
            maxId = 0;
        }
    }
    DecimalFormat rrnFormat = new DecimalFormat("#000");
}
