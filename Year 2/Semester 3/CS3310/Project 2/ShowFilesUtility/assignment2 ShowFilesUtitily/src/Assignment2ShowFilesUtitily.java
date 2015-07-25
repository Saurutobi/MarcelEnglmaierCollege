/* PROJECT: WorldDataProject         PROGRAM: Assignment2ShowFilesUtility
* AUTHOR: Saurutobi
* COURSE INFO:  CS3310 (MWF) Fall 2012 - Asgn 1
* PROJECT CLASSES USED:  none (this does not use the OOP paradigm)
* FILES ACCESSED:  (all files handled by THIS program DIRECTLY)
*       INPUT:  ?IndexBackup.bin
*       OUTPUT: LogSession.txt
*       where ? is the appropriate fileNamePrefix.
* DESCRIPTION:  This is a utility program for the developer.  As such, it's just
*       a quick non-OOP program which accesses the files directly.  It dumps
*       the ?IndexBackup file to the LogSession file in an easy-to-read format, 
*       with appropriate labelling.
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
* METHODS:  Main-does the work
*           formatSize-formats strings for appropriate output
*           printInformation-prints out the information, to the System.out and 
*               the outputFile
*******************************************************************************/
/*
 * Note from the Author: there might be a few places where you need to modify the code
 * 
 * If you find a bug or a problem, please email Saurutobi@lbkstudios.net, and I
 * will fix is asap(or you can send me a fixed version) so Dr. Kaminski can upload it.
 * 
 * I have many comments in this code to make it understandable. The variable names
 * all make sense, and the comments are clear. If you do not understand how this
 * program works, you may email me and ask.
 * 
 * This code is As-Is. I do not guarantee it works in every situation or to spec(it 
 * was thrown together in an hour), but it should.
 */

import java.io.*;

public class Assignment2ShowFilesUtitily
{
    //general declarations for file writing and reading
    private static RandomAccessFile inputFile;
    private static PrintWriter outputFile;
    //length of a record in this program is 9 bytes: 3 Shorts(2 bytes each), 3 chars(2 bytes each)
    public static final short SIZE_OF_INDEXBACKUP_RECORD = 12;
    public static final short SIZE_OF_MAINDATA_RECORD = 61;
    
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
        }
        
        // <editor-fold defaultstate="collapsed" desc="?MainData.bin">
        //usable variables being declared and initialized
        String[] outputString = new String[9];
        short n;
        short maxID;
        //try-catch block to open,read,and write
        try
        {
            //initializing files and printing startup msg to file
            inputFile = new RandomAccessFile(new File(fileNamePrefix + "MainData.bin"), "r");
            outputFile = new PrintWriter(new FileWriter("LogSession.txt", true));
            printInformation("OK, starting ShowFilesUtility");
            
            //read header record
            n = inputFile.readShort();
            maxID = inputFile.readShort();
            
            //print header record and formating outputs
            printInformation(fileNamePrefix + "Main Data Storage - N is " + n + ", MaxID is " + maxID);
            printInformation(formatSize("[RRN]",8) + 
                             formatSize("ID",8) + 
                             formatSize("Code",8) + 
                             formatSize("Name",24) + 
                             formatSize("Continent",16) + 
                             formatSize("Region",16) + 
                             formatSize("Area",16) + 
                             formatSize("Indep",8) + 
                             formatSize("Population",16) + 
                             formatSize("L.Exp",8));
            printInformation("");
            printInformation("------------------------------------------"
                    + "---------------------------------------------------"
                    + "---------------------------------------------------");
            for(int i = 0; i < maxID; i++)
            {
                //inner try-catch block do reading
                try 
                {     
                    //begin reading. read first field
                    outputString[0] = inputFile.readShort() + "";
                    //if the field is empty, print according info and advance pointer
                    if(Short.parseShort(outputString[0]) != 0)
                    {
                        outputString[1] = String.valueOf(inputFile.readChar() + inputFile.readChar() + inputFile.readChar());          //code
                        outputString[2] = ReadName();           //name
                        outputString[3] = ReadContinent();      //continent
                        outputString[4] = ReadRegion();         //region
                        outputString[5] = inputFile.readInt() + "";  //surface area
                        outputString[6] = inputFile.readShort() + "";//year of indep
                        outputString[7] = inputFile.readLong() + ""; //population
                        outputString[8] = inputFile.readFloat() + "";//life expect.

                        //print fields
                        printInformation(formatSize(outputString[0],8) + 
                                         formatSize(outputString[0],8) + 
                                         formatSize(outputString[1],8) + 
                                         formatSize(outputString[2],24) + 
                                         formatSize(outputString[3],16) + 
                                         formatSize(outputString[4],16) + 
                                         formatSize(outputString[5],16) + 
                                         formatSize(outputString[6],8) + 
                                         formatSize(outputString[7],16) + 
                                         formatSize(outputString[8],8));
                        printInformation("");
                    }
                    else
                    {
                        //writes that the first field is empty, and advances pointer accordingly
                        printInformation("EMPTY");
                        inputFile.seek(inputFile.getFilePointer() + SIZE_OF_MAINDATA_RECORD - Short.SIZE / Byte.SIZE);
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
            //prints the end of data notification, and then attempts to close the files
            printInformation("- - -");
            printInformation("* * * * * * * * * * * * * * * * * * * * * * * * * * * THE END OF DATA * * * * * * * * * * * * * * * * * * * * * * * * * * * * * ");
            printInformation("");
            try 
            {
                inputFile.close();
                outputFile.close();
            } 
            catch (IOException ex) 
            {
                //if file won't close, print that
                System.out.println("IOException, inputFile or outputFile could not be closed. May not have been initialized");
            }
        }
        // </editor-fold>
        
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
            printInformation(formatSize("Index", 7) + formatSize("LChPTr", 7) + formatSize("KeyValue", 8) + formatSize("DRP", 7) + formatSize("RChPtr", 7));
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
                        outputString[1] = String.valueOf(inputFile.readChar() + inputFile.readChar() + inputFile.readChar());
                        //read third field
                        outputString[2] = String.valueOf(inputFile.readShort());
                        //read fourth field
                        outputString[3] = String.valueOf(inputFile.readShort());
                        //outputs all the data including index, and formats the size, padding between strings by a two space minimum
                        printInformation(formatSize(String.valueOf(i), 7) + formatSize(outputString[0], 7) + formatSize(outputString[1], 8) + formatSize(outputString[2], 7) + formatSize(outputString[3], 7));
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
    
    //</editor-fold>
}





