/*******************************************************************************
 * ShowIndexFileUtility.java
 * Written by: Marcel Englmaier
 * 
 * External procedural utility for displaying the contents of IndexBackup.bin.
 ******************************************************************************/
package ShowIndexFilesUtility;

import java.io.*;

public class ShowIndexFileUtility
{
    
	public static void main(String[] args)
        {
            try
            {
                //initialize reader and logger for use
                RandomAccessFile indexBackup = new RandomAccessFile(new File("IndexBackup.bin"), "rw");
                BufferedWriter logger = new BufferedWriter(new FileWriter("LogSession.txt", true));
		
                //read header
                //nHomeRec
                String line = "[nHomeRec]: " + indexBackup.readInt() + "\n";
                logger.write(line);
                System.out.print(line);
                //nCollRec
                int collisions = indexBackup.readInt();
                line = "[nCollRec]: " + collisions + "\n";
                logger.write(line);
                System.out.print(line);
                //MAX_N_HOME_LOC
                int home = indexBackup.readInt();
                line = "[MAX_N_HOME_LOC]: " + home + "\n\n";
                logger.write(line);
                System.out.print(line);
                
                //print header for table
                line = "[Index]  [CODE]  [DRP]  [LINK]\n";
                logger.write(line);
                System.out.print(line);
		
                int temp = home + collisions;
                
		//for total indexes, this reads one Index and writes it
                for(int i = 0; i < temp; i++)
                {
                    line = "   " + i + "      " + 
                                     indexBackup.readChar() + 
                                     indexBackup.readChar() + 
                                     indexBackup.readChar() + "      " + 
                                     indexBackup.readInt() + "     " +  
                                     indexBackup.readInt() + "\n";
                    logger.write(line);
                    System.out.print(line);
                }
                //close up the file
                logger.write("\n\n");
                indexBackup.close();
                logger.close();
            }
            catch(EOFException e)
            {
                String line = "End Of File Error\n";
                System.out.println(line);
            }
            catch (IOException e)
            {
                System.out.append("\n\n\nThere was some sort of problem running ShowIndexFilesUtility\n\n\n");
                e.printStackTrace();
            }
	}
}
