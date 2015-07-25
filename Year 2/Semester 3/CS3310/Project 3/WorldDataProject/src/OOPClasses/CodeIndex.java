/* PROJECT: WorldDataProject #3         CLASS: CodeIndex
* Written By: Marcel Englmaier
* FILES ACCESSED:
* FILE STRUCTURE:  
* INDEX STRUCTURE:      
* DESCRIPTION:       
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
    //Finals
    private final int MAX_ARRAY_SIZE = 300;
    private final int MAX_N_HOME_LOC = 20;
    
    //3 Parallel arrays
    private static String [] keyValue;
    private static int [] DRP;
    private static int [] link;
    
    //2 Counters
    private static int nHomeRec;
    private static int nCollRec;
    private static int nodesVisited;
    
    //saveFile
    private RandomAccessFile saveFile;
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public Constructor(s)">
    
    
    //**************************** PUBLIC CONSTRUCTOR(S) ***********************
    public CodeIndex(boolean setup)  throws IOException
    {
        //initialize saveFile for reading/writing
        saveFile = new RandomAccessFile(new File("IndexBackup.bin"), "rw");
        //if setup calls CodeIndex
        if(setup)
        {
            //initialize arrays and counters
            keyValue = new String[MAX_ARRAY_SIZE];
            DRP = new int[MAX_ARRAY_SIZE];
            link = new int[MAX_ARRAY_SIZE];
            nHomeRec = 0;
            nCollRec = 0;
            for(int i = 0; i < MAX_ARRAY_SIZE; i++)
            {
                keyValue[i] = "";
                DRP[i] = -1;
                link[i] = -1;
            }
            nodesVisited = 0;
        }
        //else UserApp must be running CodeIndex, so load up the .bin
        else
        {
            nodesVisited = 0;
            load();
        }
    }
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public Get/Set Methods">
    
    
    //**************************** PUBLIC GET/SET METHODS **********************
    public void reset()
    {
        //resets the count
        nodesVisited = 0;
    }
    
//******************************************************************************
    
    public int getNHomeRec()
    {
        //returns number of home locations written to
        return nHomeRec;
    }
    
//******************************************************************************
    
    public int getNCollRec()
    {
        //returns number of collisions
        return nCollRec;
    }
    
//******************************************************************************
    
    public int getNodesVisited()
    {
        //returns number of nodes visited
        return nodesVisited;
    }
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public Service Methods">
    
    
    //**************************** PUBLIC SERVICE METHODS **********************
    public boolean insert1Country(String [] record)
    {
        //finds home address through hashFunction
        int indexHomeAddress = hashFunction(record[1], MAX_N_HOME_LOC);
        //checks home location for tombstone or emptiness
        if(keyValue[indexHomeAddress] == "" || keyValue[indexHomeAddress] == "---")
        {
            //insert the information, making sure to leave the pointer untouched
            keyValue[indexHomeAddress] = record[1];
            DRP[indexHomeAddress] = Integer.parseInt(record[0]);
            //advance the counts
            nHomeRec++;
            nodesVisited++;
            return true;
        }
        //if home address is taken, go to collision zone
        else
        {
            //loop through collision zone until next empty node
            for(int i = MAX_N_HOME_LOC - 1; i < MAX_ARRAY_SIZE;i++)
            {
                //advance count
                nodesVisited++;
                //if location is empty, insert information making sure to fix pointers
                if(keyValue[i] == "" || keyValue[i] == "---")
                {
                    keyValue[i] = record[1];
                    DRP[i] = Integer.parseInt(record[0]);
                    link[i] = link[indexHomeAddress];
                    link[indexHomeAddress] = i;
                    nCollRec++;
                    return true;
                }
            }
        }
        return false;
    }
    
//******************************************************************************
    
    public boolean deleteByCode(String code)
    {
        boolean notFound = true;
        int homeIndex = hashFunction(code, MAX_N_HOME_LOC);
        int previousIndex = 0;
        //check homeAddress 
        if(keyValue[homeIndex].equalsIgnoreCase(code))
        {
            //if it's there, advance nodesVisited and return the DRP
            nodesVisited++;
            keyValue[homeIndex] = "---";
            DRP[homeIndex] = -1;
            return true;
        }
        //if it's not there follow pointers on to collision area
        else
        {
            previousIndex = homeIndex;
            while(notFound)
            {
                //advance count
                nodesVisited++;
                //if the last item in chain had been visited
                if(link[previousIndex] == -1)
                {
                    //return tombstone or empty identifier
                    return false;
                }
                //if the item is there, return the DRP
                else if(keyValue[link[previousIndex]].equalsIgnoreCase(code))
                {
                    previousIndex = link[previousIndex];
                    keyValue[previousIndex] = "---";
                    DRP[previousIndex] = -1;
                    return true;
                }
                //if it's not at the end of the chain and not there, advance chain
                else
                {
                    previousIndex = link[previousIndex];
                }
            }
        }
        return false;
    }
    
//******************************************************************************
    
    public int queryByCode(String code)
    {
        boolean notFound = true;
        int homeIndex = hashFunction(code, MAX_N_HOME_LOC);
        int previousIndex = 0;
        //check homeAddress 
        if(keyValue[homeIndex].equalsIgnoreCase(code))
        {
            //if it's there, advance nodesVisited and return the DRP
            nodesVisited++;
            return DRP[homeIndex];
        }
        //if it's not there follow pointers on to collision area
        else
        {
            previousIndex = homeIndex;
            while(notFound)
            {
                //advance count
                nodesVisited++;
                //if the last item in chain had been visited
                if(link[previousIndex] == -1)
                {
                    //return tombstone or empty identifier
                    return -1;
                }
                //if the item is there, return the DRP
                else if(keyValue[link[previousIndex]].equalsIgnoreCase(code))
                {
                    return DRP[link[previousIndex]];
                }
                //if it's not at the end of the chain and not there, advance chain
                else
                {
                    previousIndex = link[previousIndex];
                }
            }
        }
        return -1;
    }
    
//******************************************************************************
    
    public void close() throws IOException
    {
        saveFile.seek(0);
        //write header
        saveFile.writeInt(nHomeRec);
        saveFile.writeInt(nCollRec);
        saveFile.writeInt(MAX_N_HOME_LOC);
        int temp = MAX_N_HOME_LOC + nCollRec;
        //write all the info
        for(int i = 0; i < temp; i++)
        {
            //if the info is empty, write a tombstone
            if(keyValue[i] == "")
            {
                saveFile.writeChar('-');
                saveFile.writeChar('-');
                saveFile.writeChar('-');
                saveFile.writeInt(-1);
                saveFile.writeInt(-1);
            }
            //otherwise write the good information
            else
            {
                saveFile.writeChar(keyValue[i].charAt(0));
                saveFile.writeChar(keyValue[i].charAt(1));
                saveFile.writeChar(keyValue[i].charAt(2));
                saveFile.writeInt(DRP[i]);
                saveFile.writeInt(link[i]);
            }
        }
        //close the file
        saveFile.close();
    }
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Methods">
    
    
    //**************************** PRIVATE METHODS *****************************
    private int hashFunction(String code, int maxHome)
    {
        //hashFunction
        return (int)(code.charAt(0) * code.charAt(1) * code.charAt(2))%MAX_N_HOME_LOC;
    }
    
//******************************************************************************
    
    private void load() throws IOException
    {
        saveFile.seek(0);
        //initialize arrays
        keyValue = new String[MAX_ARRAY_SIZE];
        DRP = new int[MAX_ARRAY_SIZE];
        link = new int[MAX_ARRAY_SIZE];
        for(int i = 0; i < MAX_ARRAY_SIZE; i++)
        {
            keyValue[i] = "";
            DRP[i] = -1;
            link[i] = -1;
        }
        //read header
        nHomeRec = saveFile.readInt();
        nCollRec = saveFile.readInt();
        int temp = saveFile.readInt();
        //read all the info
        for(int i = 0; i < (MAX_N_HOME_LOC + nCollRec); i++)
        {
            keyValue[i] = String.valueOf(saveFile.readChar()) + 
                          String.valueOf(saveFile.readChar()) + 
                          String.valueOf(saveFile.readChar());
            DRP[i] = saveFile.readInt();
            link[i] = saveFile.readInt();
        }
    }
    
    
    //</editor-fold>
}
