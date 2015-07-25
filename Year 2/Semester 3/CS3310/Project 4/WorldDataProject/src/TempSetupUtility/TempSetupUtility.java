/* PROJECT: WorldDataProject #4         PROGRAM: TempSetupUtility
 * Written By: Marcel Englmaier
 * PROJECT CLASSES USED: 
 * FILES ACCESSED: CodeIndex?.txt, CodeIndex?.bin, LogSession.txt
 * DESCRIPTION: this class gets one line from RawData, and passes it to CodeIndex, then prints CodeIndex
 ******************************************************************************/
package TempSetupUtility;

import java.io.*;

public class TempSetupUtility
{

    //<editor-fold defaultstate="collapsed" desc="Public Declarations">
    
    
    //**************************** PUBLIC DECLARATIONS ************************
    //None
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Declarations">
    
    
    //**************************** PRIVATE DECLARATIONS ************************
    //private files
    private static BufferedReader input;
    private static RandomAccessFile output;
    
    //private boolean
    private static boolean readingIsDone;
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public Main">
    
    
    //****************************** PUBLIC Main *******************************
    public static void main(String[] args) throws IOException
    {
        loadCodeIndex("CodeIndex1");
        loadCodeIndex("CodeIndex2");
        loadCodeIndex("CodeIndex3");
        
        //Read CodeIndex1.bin and print it
        logCodeIndex("CodeIndex1.bin");
        //Read CodeIndex2.bin and print it
        logCodeIndex("CodeIndex2.bin");
        //Read CodeIndex3.bin and print it
        logCodeIndex("CodeIndex3.bin");
    }
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public Get/Set Methods">
    
    
    //**************************** PUBLIC GET/SET METHODS **********************
    //None
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public Service Methods">
    
    
    //**************************** PUBLIC SERVICE METHODS **********************
    //None
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Methods">
    
    
    //**************************** PRIVATE METHODS *****************************
    private static void loadCodeIndex(String fileName) throws IOException
    {
        input = new BufferedReader(new FileReader(fileName + ".txt"));
        output = new RandomAccessFile(new File(fileName + ".bin"), "rw");
        String [] currentLine = input.readLine().split(" ");
        int m = Integer.parseInt(currentLine[0]);
        output.writeShort(m);
        output.writeShort(Integer.parseInt(currentLine[1]));
        output.writeShort(Integer.parseInt(currentLine[2]));
        String temp = input.readLine();
        int tracking = 0;
        while(temp != null)
        {
            currentLine = temp.split(" ");
            tracking = 0;
            for(int i = 0; i < m - 1; i++)
            {
                output.writeShort(Integer.parseInt(currentLine[tracking]));
                output.writeChar(currentLine[tracking + 1].charAt(0));
                output.writeChar(currentLine[tracking + 1].charAt(1));
                output.writeChar(currentLine[tracking + 1].charAt(2));
                output.writeShort(Integer.parseInt(currentLine[tracking + 2]));
                tracking = tracking + 3;
            }
            output.writeShort(Integer.parseInt(currentLine[currentLine.length - 1]));
            temp = input.readLine();
        }
        
        //close input then log the CodeIndex
        input.close();
    }
    
    private static void logCodeIndex(String fileName)
    {
        try
        {
            //initialize reader and logger for use
            RandomAccessFile indexBackup = new RandomAccessFile(new File(fileName), "rw");
            BufferedWriter logger = new BufferedWriter(new FileWriter("LogSession.txt", true));
	
            String line = "";
            //read header
            int m = indexBackup.readShort();
            int rootPTR = indexBackup.readShort();
            int n = indexBackup.readShort();
            line = ("+ + + + + + + + + + + + + + + + + + THE START OF SETUP + + + + + + + + + + + + + + + + + + + + + +\n\n");
            //line = formatSize("------------------------------------------------------------"
            //        + "------------------------------------------------------", (4 * m) + 8 + (8 * (m - 1))) + "\n";
            logger.write(line);
            System.out.print(line);
            line = fileName + "     M is " + m + ", rootPTR is " + rootPTR + ", N is " + n + "\n\n";
            logger.write(line);
            System.out.print(line);
	
            line = formatSize("---------------------------------------------------", ((4 * m)/2) - 3) + 
                   m + " TP's" + 
                   formatSize("---------------------------------------------------", ((4 * m)/2) - 3) + 
                   " |  " + 
                   formatSize("---------------------------------------------------", ((4 * (m - 1))/2) - 3) + 
                   (m-1) + " KV's" + 
                   formatSize("---------------------------------------------------", ((4 * (m - 1))/2) - 3) + 
                   " | " + 
                   formatSize("---------------------------------------------------", ((4 * (m - 1))/2) - 3) + 
                   (m-1) + " DRPs" + 
                   formatSize("---------------------------------------------------", ((4 * (m - 1))/2) - 3) + "\n";
            logger.write(line);
            System.out.print(line);
            short [] TP = new short[m];
            String [] KV = new String[m-1];
            short [] DRP = new short[m-1];
            for(int i = 0; i < n; i++)
            {
                for(int j = 0; j < m - 1; j++)
                {
                    TP[j] = indexBackup.readShort();
                    KV[j] = String.valueOf(indexBackup.readChar()) + String.valueOf(indexBackup.readChar()) + String.valueOf(indexBackup.readChar());
                    DRP[j] = indexBackup.readShort();
                }
                TP[m-1] = indexBackup.readShort();
                line = "";
                for(int x = 0; x < m; x++)
                {
                    line = line + formatSize(String.valueOf(TP[x]), 3) + " ";
                }
                line = line + " |  ";
                for(int x = 0; x < m - 1; x++)
                {
                    line = line + formatSize(KV[x], 3) + " ";
                }
                line = line + " |  ";
                for(int x = 0; x < m - 1; x++)
                {
                    line = line + formatSize(String.valueOf(DRP[x]), 3) + " ";
                }
                line = line + "\n";
                logger.write(line);
                System.out.print(line);
            }
            
            line = ("\n+ + + + + + + + + + + + + + + + + + THE END OF SETUP + + + + + + + + + + + + + + + + + + + + + +\n\n\n");
                
            //line = line + formatSize("------------------------------------------------------------"
            //        + "------------------------------------------------------", (4 * m) + 4 + (4 * (m - 1)) + 4 + (4 * (m - 1))) + "\n\n";
            logger.write(line);
            System.out.print(line);
             
            //close up the file
            logger.write("\n\n");
            indexBackup.close();
            logger.close();
        }
        catch(EOFException e)
        {
            String line = "End Of File Error-IF THIS HAPPENS SOMETHING'S VERY, VERY WRONG!!!!!!!!!1!1\n";
            System.out.println(line);
        }
        catch (IOException e)
        {
            System.out.append("\n\n\nThere was some sort of problem running ShowIndexFilesUtility\n\n\n");
            e.printStackTrace();
        }
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
    
    //</editor-fold>
}
