/* PROJECT: WorldDataProject         PROGRAM: ShowFilesUtility
* AUTHOR: Marcel Englmaier, Thomas Ekema, Quincy Campbell
* COURSE INFO:  CS3310 (MWF) Fall 2012 - Asgn 1
* PROJECT CLASSES USED:  none (this does not use the OOP paradigm)
* FILES ACCESSED:  (all files handled by THIS program DIRECTLY)
*       INPUT:  ?MainData.bin
*       INPUT:  for Asgn2:  ?IndexBackup.bin
*       OUTPUT: LogSession.txt
*       where ? is the appropriate fileNamePrefix.
* DESCRIPTION:  This is a utility program for the developer.  As such, it's just
*       a quick non-OOP program which accesses the files directly.  It dumps
*       the ?MainData file (and for Asgn2, also the ?IndexBackup file) to the
*       LogSession file in an easy-to-read format, with appropriate labelling.
* CORE ALGORITHM:  Traditional sequential file processing - i.e.,
*       loop til EOF on ?MainData file
*                   (or N sent in as program parameter, whichever is sooner)
*       {   read 1 record
*           format & print it to LogSession file                  
*       }
*       [Asgn 2:    read & format/print headerRec data from ?IndexBackup file
*                   for loop using appropriate N from headerRec for CodeIndex
*                       {read & format/print record}
*                   for loop using appropriate N from headerRec for NameIndex
*                       {read & format/print record}]
*******************************************************************************/

package ShowFilesUtility;

import java.io.*;
import java.text.DecimalFormat;
import OOPClasses.DataStorage;

public class ShowFilesUtility 
{
    //declare DataInputStream and PrintWriter objects and outputString var
    private static File binfile;
    private static RandomAccessFile inputFile;
    private static PrintWriter outputFile;
    private static DecimalFormat formatter;
    public static final short SIZE_OF_HEADER_REC = 8;
    public static final short SIZE_OF_DATA_REC = 102;
    public static final short SIZE_OF_INDEXBACKUP_RECORD = 12;
    public static void main(String[] args) throws IOException
    {
        System.out.println("OK, starting ShowFilesUtility");
                
        // Detect whether Setup is being run by AutoTesterUtility, or being run
        // manually by the user, and assign file name prefixes and N's
        // accordingly.
        String fileNamePrefix;
        int nRecToShow;
        if (args.length > 0)
        {
            fileNamePrefix = args[0];
            if(args[1].equalsIgnoreCase("all"))
            {
                nRecToShow = 239;
            }
            else
            {
                nRecToShow = Integer.parseInt(args[1]);
            }
        }
        else
        {
            fileNamePrefix = "A2Z";
            nRecToShow = 239;
        }        
        
        // <editor-fold defaultstate="collapsed" desc="?MainData.bin">
        //boolean for looping, strings for output, shorts for header record and RRN
        boolean hasNext = true;
        String header;
        String[] outputString = new String[9];
        short n, maxID, id;
        int byteOffset;
        
        //try-catch block to open,read,and write
        try
        {
            binfile = new File(fileNamePrefix + "MainData.bin");
            inputFile = new RandomAccessFile(binfile, "r");
            outputFile = new PrintWriter(new FileWriter("LogSession.txt", true));
            outputFile.println("Ok, opened inputFile and outputFile");
            formatter = new DecimalFormat("#000");
            
            //read header record
            n = inputFile.readShort();
            maxID = inputFile.readShort();
            
            //print logsession.txt header
            outputFile.println();
            outputFile.println(fileNamePrefix + "Main Data Storage - N is " + n
                    + ", MaxID is " + maxID);
            outputFile.printf("%-8s%-8s%-8s%-24s%-16s%-16s%-16s%-8s%-16s%-8s",
                    "[RRN]", "ID", "CODE", "NAME", "CONTINENT", "REGION", 
                    "AREA", "INDEP", "POPULATION", "L.EXP");
            outputFile.println();
            outputFile.println("------------------------------------------"
                    + "---------------------------------------------------"
                    + "---------------------------------------------------");
            
            for(int i = 0; i < nRecToShow; i++)
            {
                //inner try-catch block to build string
                try 
                {     
                    //store read fields as strings
                    outputString[0] = inputFile.readShort() + "";    //ID
                    if(Short.parseShort(outputString[0]) != 0)
                    {
                        id = Short.parseShort(outputString[0]);     //id
                        outputString[1] = ReadCode();           //code
                        outputString[2] = ReadName();           //name
                        outputString[3] = ReadContinent();      //continent
                        outputString[4] = ReadRegion();         //region
                        outputString[5] = inputFile.readInt() + "";  //surface area
                        outputString[6] = inputFile.readShort() + "";//year of indep
                        outputString[7] = inputFile.readLong() + ""; //population
                        outputString[8] = inputFile.readFloat() + "";//life expect.

                        //print fields
                        outputFile.printf("%-8s%-8s%-8s%-24s%-16s%-16s%-16s%-8s%-16s%-8s", "[" + formatter.format(i + 1)
                                 + "] ", formatter.format(id), outputString[1], outputString[2], outputString[3], 
                                outputString[4], outputString[5], outputString[6], outputString[7], outputString[8]);
                        outputFile.println();
                    }
                    else
                    {
                        outputFile.println("[" + formatter.format(i+1) + "]   EMPTY");
                        // just seek ahead the appropriate number of bits.
                        inputFile.seek(inputFile.getFilePointer() + DataStorage.SIZE_OF_RECORD - Short.SIZE / Byte.SIZE);
                    }
                }
                catch (EOFException e)
                {
                    System.out.println("EOFEXCEPTION");
                    break;
                }
            }
        }
        catch (FileNotFoundException e)
        {
             System.out.println("File not found.");       
        }
        catch (IOException e)
        {
            System.out.println("Error creating file. Please try a different "
                    + "name or location.");
        }
        finally
        {
            outputFile.println("- - -");
            outputFile.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * THE END OF DATA * * * * * * * * * * * * * * * * * * * * * * * * * * * * * ");
            outputFile.println();
            outputFile.close();
            try 
            {
                inputFile.close();
            } 
            catch (IOException ex) 
            {
                System.out.println("IOException, inputFile could not be + "
                        + "closed. May not have been initialized");
            }
        }
        //</editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="?IndexBackup.bin">
        //usable variables being declared and initialized
        outputString = new String[4];
        short nextEmptySlot;
        short rootPTR;
        //try-catch block to open,read,and write
        try
        {
            //initializing files and printing startup msg to file
            inputFile = new RandomAccessFile(new File(fileNamePrefix + "IndexBackup.bin"), "r");
            outputFile = new PrintWriter(new FileWriter("LogSession.txt", true));
            printInformation("OK, starting ShowFilesUtility");
            
            //read header record
            nextEmptySlot = inputFile.readShort();
            rootPTR = inputFile.readShort();
            
            //print header record and formating outputs
            printInformation("");
            printInformation("The Next empty slot is: " + nextEmptySlot);
            printInformation("The Root Pointer is: " + rootPTR);
            printInformation("");
            printInformation(formatSize("Index", 7) + formatSize("LChPTr", 7) + formatSize("KeyValue", 9) + formatSize("DRP", 7) + formatSize("RChPtr", 7));
            printInformation("------------------------------------");
            
            for(int i = 0; i < nextEmptySlot; i++)
            {
                //inner try-catch block do reading
                try 
                {     
                    //begin reading. read first field
                    outputString[0] = inputFile.readShort() + "";
                    //if the field is empty, print according info and advance pointer
                    if(Short.parseShort(outputString[0]) != 0)
                    {
                        //read second field(3 chars)
                        outputString[1] = nextBytes(inputFile, 3);
                        //read third field
                        outputString[2] = String.valueOf(inputFile.readShort());
                        //read fourth field
                        outputString[3] = String.valueOf(inputFile.readShort());
                        //outputs all the data including index, and formats the size, padding between strings by a two space minimum
                        printInformation(formatSize(String.valueOf(i), 7) + formatSize(outputString[0], 7) + formatSize(outputString[1], 9) + formatSize(outputString[2], 7) + formatSize(outputString[3], 7));
                    }
                    else
                    {
                        //writes that the first field is empty, and advances pointer accordingly
                        printInformation("EMPTY");
                        inputFile.seek(inputFile.getFilePointer() + SIZE_OF_INDEXBACKUP_RECORD - Short.SIZE / Byte.SIZE);
                    }
                }
                catch (EOFException e)
                {
                    //prints the end of file notification
                    System.out.println("EOFEXCEPTION");
                    break;
                }
            }
        }
        catch (FileNotFoundException e)
        {
             System.out.println("File not found.");       
        }
        catch (IOException e)
        {
            System.out.println("Error creating file. Please try a different name or location.");
        }
        finally
        {
            //prints the end of data notification, and then attempts to close the file
            printInformation("------------------------------------");
            printInformation(" * * * * * THE END OF DATA * * * * *");
            printInformation("");
            printInformation("");
            try 
            {
                inputFile.close();
                outputFile.close();
            } 
            catch (IOException ex) 
            {
                //if file won't closwe, print that
                System.out.println("IOException, inputFile could not be closed. May not have been initialized");
            }
        }
        // </editor-fold>
    }
    
    // <editor-fold defaultstate="collapsed" desc="private methods">
    private static String ReadCode() throws IOException
    {
        String temp = "";
        for(int i = 0; i < 3; i++)
        {
            temp += (char)inputFile.readByte();
        }
        return temp;
    }
    
    private static String ReadName() throws IOException
    {
        String temp = "";
        for(int i = 0; i < 17; i++)
        {
            temp += (char)inputFile.readByte();
        }
        return temp;
    }
    
    private static String ReadContinent() throws IOException
    {
        String temp = "";
        for(int i = 0; i < 11; i++)
        {
            temp += (char)inputFile.readByte();
        }
        return temp;
    }
    
    private static String ReadRegion() throws IOException
    {
        String temp = "";
        for(int i = 0; i < 10; i++)
        {
            temp += (char)inputFile.readByte();
        }
        return temp;
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
     
    private static void printInformation(String toPrint) throws IOException
    {
        //prints information to file and System.out
        outputFile.println(toPrint);
        System.out.println(toPrint);
    }
    
    // returns the next n bytes of the file as a string
    private static String nextBytes(RandomAccessFile file, int n) 
    {
        char[] retVal = new char[n];
        for (int i = 0; i < n; i++) {
            try {
                retVal[i] = (char) file.readByte();
            } catch (IOException ex) {
                retVal[i] = ' ';
            }
        }

        return new String(retVal);
    }
    //</editor-fold>
}
