/* PROJECT: WorldDataProject #4         PROGRAM: TempUserApp
 * Written By: Marcel Englmaier
 * PROJECT CLASSES USED: CodeIndex, UserInterface
 * FILES ACCESSED: MainData.txt, TransData?.txt, LogSession.txt
 * DESCRIPTION: This Class takes in data, and sends commands to CodeIndex accordingly
 * TRANSACTION HANDLERS: QC
 *******************************************************************************/
package TempUserApp;

import OOPClasses.CodeIndex;
import java.io.*;

public class TempUserApp 
{

    //<editor-fold defaultstate="collapsed" desc="Public Declarations">
    
    
    //**************************** PUBLIC DECLARATIONS ************************
    //None
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Declarations">
    
    
    //**************************** PRIVATE DECLARATIONS ************************
    //files
    private static PrintWriter logger;
    private static BufferedReader transDataReader;
    private static RandomAccessFile raf;
    
    //CodeIndex
    private static CodeIndex CI;
    
    //finals
    private static final int RECORD_SIZE = 25;
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public Main">
    
    
    //****************************** PUBLIC Main *******************************
    public static void main(String[] args) throws IOException
    {
        logger = new PrintWriter(new FileWriter(new File("LogSession.txt"), true));
        doOne("1");
        doOne("2");
        doOne("3");
        logger.close();
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
    private static void doOne(String fileName) throws IOException
    {
        transDataReader = new BufferedReader(new FileReader(new File("TransData" + fileName + ".txt")));
        CI = new CodeIndex(fileName);
        raf = new RandomAccessFile(new File("MainData" + fileName + ".txt"), "rw");
        
        writeToLog("+ + + + + + + + + + + + + + + + + + + THE START OF TRANS + + + + + + + + + + + + + + + + + + + + +\n");
        
        // initialize transaction counter
        int transProcessed = 0;

        // core algorithm described above
        String transaction = transDataReader.readLine();
        while(transaction != null)
        {
            CI.reset();
            //writeToLog(transaction + "");
            queryByCode(transaction.split(" ")[1]);
            transProcessed++;
            transaction = transDataReader.readLine();
        }
        if (transProcessed > 0)
        {
            writeToLog("\n----" + transProcessed + " Transactions processed----");
            writeToLog("+ + + + + + + + + + + + + + + + + + + THE END OF TRANS + + + + + + + + + + + + + + + + + + + + + +\n\n");
        }
        
        closeOut();
    }
    
    private static void queryByCode(String id) throws IOException
    {
        CI.setCode(id);
        int result = CI.queryByCode();
        if(result == -1)
        {
            writeToLog("QC " + id + " >>> INVALID COUNTRY CODE [" + CI.getNodesVisited() + " nodes visited]");
        }
        else
        {
            raf.seek((result - 1) * RECORD_SIZE);
            writeToLog("QC " + id + " >>> " + raf.readLine() + " [" + CI.getNodesVisited() + " nodes visited]");
        }
    }
    
    private static void closeOut() throws IOException
    {
        transDataReader.close();
        CI.close();
        raf.close();
    }
    
    private static void writeToLog(String message) throws IOException
    {
        logger.println(message);
        System.out.println(message);
    }
    
    //</editor-fold>
}