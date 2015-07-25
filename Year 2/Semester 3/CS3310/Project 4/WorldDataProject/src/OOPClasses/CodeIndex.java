/* PROJECT: WorldDataProject #4         CLASS: CodeIndex
* Written By: Marcel Englmaier
* FILES ACCESSED: CodeIndex?.bin
* FILE STRUCTURE: OOP
* INDEX STRUCTURE: B-Tree  
* DESCRIPTION: Creates a hash table and makes    
*******************************************************************************/

package OOPClasses;

import java.io.*;

public class CodeIndex 
{
    //<editor-fold defaultstate="collapsed" desc="Public Declarations">
    
    
    //**************************** PUBLIC DECLARATIONS ************************
    //None
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Declarations">
    
    
    //**************************** PRIVATE DECLARATIONS ************************
    //3 Parallel arrays
    private static short [] TP;
    private static String [] KV;
    private static short [] DRP;
    
    //Integers
    private static int nodesVisited;
    private static short m;
    private static short rootPTR;
    private static short n;
    
    //String
    private String code;
    
    //saveFile
    private RandomAccessFile file;
    
    //finals
    private int RECORD_SIZE;
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public Constructor(s)">
    
    
    //**************************** PUBLIC CONSTRUCTOR(S) ***********************
    public CodeIndex(String fileName)  throws IOException
    {
        //initialize saveFile for reading/writing
        file = new RandomAccessFile(new File("CodeIndex" + fileName + ".bin"), "rw");
        nodesVisited = 0;
        startUp();
        RECORD_SIZE = ((10 * m - 10) + 2);
    }
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public Get/Set Methods">
    
    
    //**************************** PUBLIC GET/SET METHODS **********************
    public void setCode(String id)
    {
        code = id;
    }
    public void reset()
    {
        //resets the count
        nodesVisited = 0;
    }
    
    public int getNodesVisited()
    {
        //returns number of nodes visited
        return nodesVisited;
    }
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public Service Methods">
    
    
    //**************************** PUBLIC SERVICE METHODS **********************
    public int queryByCode() throws IOException
    {
        int DRP = 0;
        DRP = searchOneNode(rootPTR);
        return DRP;
    }
    
//******************************************************************************
    
    public void close() throws IOException
    {
        //close the file
        file.close();
    }
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Methods">
    
    
    //**************************** PRIVATE METHODS *****************************
    private void startUp() throws IOException
    {
        file.seek(0);
        m = file.readShort();
        rootPTR = file.readShort();
        n = file.readShort();
    }
    
    //******************************************************************************
    
    private void readOneNode(int RRN) throws IOException
    {
        file.seek(6 + ((RRN - 1) * RECORD_SIZE));
        TP = new short[m];
        KV = new String[m-1];
        DRP = new short[m-1];
        for(int i = 0; i < m-1; i++)
        {
            TP[i] = file.readShort();
            KV[i] = String.valueOf(file.readChar()) + String.valueOf(file.readChar()) + String.valueOf(file.readChar());
            DRP[i] = file.readShort();
        }
        TP[m-1] = file.readShort();
        nodesVisited++;
    }
    
    //******************************************************************************
    
    private int searchOneNode(int ptr) throws IOException
    {
        readOneNode(ptr);
        for(int i = 0; i < m-2; i++)
        {
            if(code.equalsIgnoreCase(KV[i]))
            {
                return DRP[i];
            }
            else if(code.compareToIgnoreCase(KV[i]) < 0)
            {
                if(TP[i] == -1)
                {
                    return -1;
                }
                else
                {
                    return searchOneNode(TP[i]);
                }
            }
        }
        if(TP[m-1] == -1)
        {
            return -1;
        }
        else
        {
            return searchOneNode(TP[m-1]);
        }
    }
    
    
    //</editor-fold>
}
