/* PROJECT: WorldDataProject         PROGRAM: ShowFilesUtility
* AUTHOR: Ekema
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
    public static final short SIZE_OF_HEADER_REC = 4;
    public static final short SIZE_OF_DATA_REC = 61;
    public static void main(String[] args)
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
        
        //boolean for looping, strings for output, shorts for header record and RRN
        boolean hasNext = true;
        String header;
        String[] outputString = new String[9];
        short n, maxID, rrn;
        int byteOffset;
        
        //try-catch block to open,read,and write
        try
        {
            binfile = new File(fileNamePrefix + "MainData.bin");
            inputFile = new RandomAccessFile(binfile, "r");
            outputFile = new PrintWriter(new FileWriter("LogSessionFile.txt", true));
            outputFile.println("Opening ShowFiles.txt and LogSessionFile.txt");
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
                        rrn = Short.parseShort(outputString[0]);     //id
                        outputString[1] = ReadCode();           //code
                        outputString[2] = ReadName();           //name
                        outputString[3] = ReadContinent();      //continent
                        outputString[4] = ReadRegion();         //region
                        outputString[5] = inputFile.readInt() + "";  //surface area
                        outputString[6] = inputFile.readShort() + "";//year of indep
                        outputString[7] = inputFile.readLong() + ""; //population
                        outputString[8] = inputFile.readFloat() + "";//life expect.

                        //print fields
                        outputFile.printf("%-8s%-8s%-8s%-24s%-16s%-16s%-16s%-8s%-16s%-8s", "[" + formatter.format(rrn)
                                 + "] ", outputString[0], outputString[1], outputString[2], outputString[3], 
                                outputString[4], outputString[5], outputString[6], outputString[7], outputString[8]);
                        outputFile.println();
                    }
                    else
                    {
                        outputFile.println("EMPTY");
                        // just seek ahead the appropriate number of bits.
                        inputFile.seek(inputFile.getFilePointer() + SIZE_OF_DATA_REC - Short.SIZE / Byte.SIZE);
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
    }
    public static String ReadCode() throws IOException
    {
        String temp = "";
        for(int i = 0; i < 3; i++)
        {
            temp += (char)inputFile.readByte();
        }
        return temp;
    }
    public static String ReadName() throws IOException
    {
        String temp = "";
        for(int i = 0; i < 17; i++)
        {
            temp += (char)inputFile.readByte();
        }
        return temp;
    }
    public static String ReadContinent() throws IOException
    {
        String temp = "";
        for(int i = 0; i < 11; i++)
        {
            temp += (char)inputFile.readByte();
        }
        return temp;
    }
    public static String ReadRegion() throws IOException
    {
        String temp = "";
        for(int i = 0; i < 10; i++)
        {
            temp += (char)inputFile.readByte();
        }
        return temp;
    }
}