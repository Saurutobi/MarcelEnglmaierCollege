/* PROJECT: WorldDataProject         CLASS: DataStorage
* AUTHOR: 
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

import java.io.*;

public class DataStorage 
{
    
    //**************************** PRIVATE DECLARATIONS ************************
    //general declarations for things
    private String fileName;
    private short rrn;
    private short maxID;
    private int headerRecordSize = 4;
    private static RandomAccessFile mainDataFile;
    private short id;
    private int numOfErrors;
    private String [] typeOfErrors;
    private int goodWrites;
    private int processed = 0;
    
    // size of a record is:
    // id = 2 bytes, cc = 3 bytes, name = 17 bytes, continent = 11 bytes,
    //   region = 10 bytes, surface area = 4 bytes, year of independence = 2 bytes,
    //   population = 8 bytes, life exp = 4 bytes
    private final int recordSize = 61;
    
    
    //**************************** PUBLIC CONSTRUCTOR(S) ***********************
    public DataStorage(String fileNamePrefix) throws IOException
    {
        // add the prefix and creat the file name
        fileName = fileNamePrefix + "MainData.bin";
        //make the file/writer
        mainDataFile = new RandomAccessFile(fileName, "rw");
        mainDataFile.seek(0);
        try
        {
            goodWrites = mainDataFile.readShort();
            maxID = mainDataFile.readShort();
            mainDataFile.seek(0);
        }
        catch(EOFException e)
        {
        
        }
        //initialize error stuff here or errors pop up when getting them and resets them
        typeOfErrors = new String[1];
        numOfErrors = 0;
        goodWrites = 0;
    }
    
    
    //**************************** PUBLIC GET/SET METHODS **********************
    public int getNumOfErrors()
    {
        //gets the total number of errors at the time
        return numOfErrors;
    }
    public int getResults()
    {
        //returns how many successful writes occured at this time
        return goodWrites;
    }
    public String [] getTypeOfErrors()
    {
        //returns an array which contains all the error messages throughout DataStorage operation
        return typeOfErrors;
    }
    public int getMaxID()
    {
        return maxID;
    }
    public int getProcessed()
    {
        return processed;
    }
    
    
    //**************************** PUBLIC SERVICE METHODS **********************
    
    
    // recieves 1 full record from Setup and writes it to the file
    public void write1Country(String [] inputLine, boolean setup) throws IOException
    {
            // get rrn from the country id
            rrn = Short.parseShort(inputLine[0]);
        
            //make a short used for duplicate checking
            short IDReset = 0;
            id = 0;
            //Seek to location 
            mainDataFile.seek(byteOffsetCalc(rrn));
        
            //checks if write1Country is being called by setup. without this, there would be an EOF error every time
            if(!setup)
            {
                //ReadRec at RRN (duplicate checking)
                id = mainDataFile.readShort();
                mainDataFile.seek(byteOffsetCalc(rrn));
            }
        
            //checks to see if location is empty, then writes data
            if (id == 0 && Short.valueOf(inputLine[0]) != id)
            {
                mainDataFile.seek(byteOffsetCalc(rrn));
                mainDataFile.writeShort(rrn);
                mainDataFile.writeBytes(formatSize(inputLine[1], 3));
                mainDataFile.writeBytes(formatSize(inputLine[2], 17));
                mainDataFile.writeBytes(formatSize(inputLine[3], 11));
                mainDataFile.writeBytes(formatSize(inputLine[4], 10));
                mainDataFile.writeInt(Integer.valueOf(inputLine[5]));
                try
                {
                    mainDataFile.writeShort(Short.valueOf(inputLine[6]));
                }
                catch(NumberFormatException e)
                {
                    mainDataFile.writeShort(0);
                }
                
                mainDataFile.writeLong(Long.valueOf(inputLine[7]));
                try
                {
                    mainDataFile.writeFloat(Float.parseFloat(inputLine[8]));
                }
                catch(NumberFormatException e)
                {
                    mainDataFile.writeFloat(0);
                }
                goodWrites++;
                processed++;
                if(rrn > maxID)
                {
                    maxID = rrn;
                }
            }
            else
            {
                //if there's an id at the location, read it in
                String [] countryAtLocation = read1Country(rrn);
                //temp is used to increase typeOfErrors size
                String [] temp = typeOfErrors;
                typeOfErrors = new String[temp.length+1];
                for(int i = 0; i< temp.length; i++)
                {
                    typeOfErrors[i] = temp[i];
                }
                //writes the country already there into typeOfErrors for error reporting
                typeOfErrors[numOfErrors] = "Error writing Counrty " + inputLine[1] + countryAtLocation[1] + " is already there";
                numOfErrors++;
                processed++;
            }
    }
    
    public String [] read1Country(int location) throws IOException
    {
        mainDataFile.seek(byteOffsetCalc(location));
        processed++;
        return read1Country();
    }
    
    public String [] read1Country() throws IOException
    {
        String [] output = new String[9];
        try
        {
            output[0] = String.valueOf(mainDataFile.readShort());
            if(Short.valueOf(output[0]) != 0)
            {
                output[1] = readItem(3) + "";
                output[2] = readItem(17) + "";
                output[3] = readItem(11) + "";
                output[4] = readItem(10) + "";
                output[5] = mainDataFile.readInt() + "";
                output[6] = mainDataFile.readShort() + "";
                output[7] = mainDataFile.readLong() + "";
                output[8] = mainDataFile.readFloat() + "";
                return output;
            }
        }
        catch(IOException e)
        {
        }
        return output;
    }
    
    public int insertCountry(String [] data) throws IOException
    {
        //uses numOfErrors for error reporting, and then writes the country passed to it
        int check = numOfErrors;
        write1Country(data, false);
        if(check == numOfErrors)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
    
    public String deleteByID(Short ID) throws IOException
    {
        //currently a dummy method, but will later delete items by overwriting them with no information
        processed++;
        return "DeleteByID not yet implemented";
    }
    
    public void closeMainData() throws IOException
    {
        mainDataFile.seek(0);
        mainDataFile.writeShort(goodWrites);
        mainDataFile.writeShort(maxID);
        //closes mainDataFile
        mainDataFile.close();
    }
    
    
    //**************************** PRIVATE METHODS *****************************
    private static String readItem(int byteSize) throws IOException
    {
        //read1Country uses this method to read the file
        String temp = "";
        for(int i = 0; i < byteSize; i++)
        {
            temp += (char)mainDataFile.readByte();
        }
        return temp;
    }
    
    private int byteOffsetCalc(int rrn)
    {
        return headerRecordSize + ((rrn-1) * recordSize);
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
