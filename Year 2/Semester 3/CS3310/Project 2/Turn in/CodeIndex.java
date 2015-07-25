/* PROJECT: WorldDataProject         CLASS: CodeIndex
* AUTHOR: Marcel Englmaier
* FILES ACCESSED:
*       INPUT & OUTPUT:  ?IndexBackup.bin (class user specifies fileNamePrefix)
*               NOTE:  DumpIndexToFile will open this in TRUNCATE MODE (for
*                       output), so it's always the latest version of the
*                       internal index.
* FILE STRUCTURE:  Serial file - just a copy of the internal index.
*       A BINARY file (not a text file).
* INDEX STRUCTURE:
*       * * * * * NOT USED UNTIL Asgn 2.  Details specified at that time.
* DESCRIPTION: 
*       * * * * * NOT USED UNTIL Asgn 2.  Details specified at that time.
*******************************************************************************/

package OOPClasses;

import java.io.*;

public class CodeIndex 
{
    //**************************** PRIVATE DECLARATIONS ************************
    private short [] leftPointer;
    private String [] keyValue;
    private short [] drp;
    private short [] rightPointer;
    private short [] allRRN;
    private RandomAccessFile saveFile;
    private String fileName;
    private short rootPointer;
    private short nextEmpty;
    private int nodesVisited;
    
    
    //**************************** PUBLIC GET/SET METHODS **********************
    public int getNodesVisited()
    {
        //returns nodes visited for every operation
        return nodesVisited;
    }
    
    public short [] getAllRRN()
    {
        //creates an array of rrns in the correct order for printing
        listAll((short)0);
        return allRRN;
    }
    
    //**************************** PUBLIC CONSTRUCTOR(S) ***********************
    public CodeIndex(boolean load, String fileNamePrefix) throws IOException
    {
        //creates filename and initializes variables
        fileName = fileNamePrefix + "IndexBackup.bin";
        nodesVisited = 0;
        
        //determines if setup or userApp calls CodeIndex and does appropriate stuff
        if(load)
        {
            load();
        }
        else
        {
            nextEmpty = 0;
            rootPointer = -1;
        }
    }
    
    
    //**************************** PUBLIC SERVICE METHODS **********************
    public short query(String code)
    {
        //sets total nodes visited this operation to 0, then searches for the specific code in the BST
        nodesVisited = 0;
        return search(code, rootPointer);
    }
    
    public void insertCode(String code, short rrn)
    {
        //expands the entire tree
        expandBST();
        //writes initial data to arrays
        leftPointer[nextEmpty] = -1;
        keyValue[nextEmpty] = code;
        drp[nextEmpty] = rrn;
        rightPointer[nextEmpty] = -1;
         
        //organizes the newly added data
        nodesVisited = 0;
        if (!fixPointer(code, rootPointer)) {
            rootPointer = nextEmpty;
        }
        nextEmpty++;
    }
    
    public void finishUp() throws IOException
    {
        //Saving is done in the following lines
        saveFile = new RandomAccessFile(new File(fileName), "rw");
        int i = 0;
        
        //write the arrays to file
        saveFile.writeShort(nextEmpty);
        saveFile.writeShort(rootPointer);
        for(i = 0; i < nextEmpty; i++)
        {
                saveFile.writeShort(leftPointer[i]);
                saveFile.writeBytes(keyValue[i]);
                saveFile.writeShort(drp[i]);
                saveFile.writeShort(rightPointer[i]);
        }
        saveFile.close();
    }
    
    
    //**************************** PRIVATE METHODS *****************************
    private void load() throws IOException
    {
        //opens file for reading
        saveFile = new RandomAccessFile(new File(fileName), "r");
        
        nextEmpty = saveFile.readShort();
        rootPointer = saveFile.readShort();
        //initializes the BST for reading
        leftPointer = new short[nextEmpty];
        keyValue = new String[nextEmpty];
        drp = new short[nextEmpty];
        rightPointer = new short[nextEmpty];
        //reads all data to BST
        for(int i = 0; i < nextEmpty; i++)
        {
                leftPointer[i] = saveFile.readShort();
                keyValue[i] = nextBytes(saveFile, 3);
                drp[i] = saveFile.readShort();
                rightPointer[i] = saveFile.readShort();
        }
        saveFile.close();
    }
    
    private void expandBST()
    {
        //this method takes all arrays in the BST and makes them 1 bigger
        short [] tempShort;
        int i = 0;
        
        //if leftPointer is not empty, expand by one
        if(leftPointer != null)
        {
            tempShort = leftPointer;
            leftPointer = new short[tempShort.length + 1];
            for(i = 0; i < tempShort.length; i++)
            {
                leftPointer[i] = tempShort[i];
            }
        }
        //otherwise initialize
        else
        {
            leftPointer = new short[1];
        }
        
        //if keyValue is not empty, expand by one
        if(keyValue != null)
        {
            String [] tempString = keyValue;
            keyValue = new String[tempString.length + 1];
            for(i = 0; i < tempString.length; i++)
            {
                keyValue[i] = tempString[i];
            }
        }
        //otherwise initialize
        else
        {
            keyValue = new String[1];
        }
        
        //if drp is not empty, expand by one
        if(drp != null)
        {
            tempShort = drp;
            drp = new short[tempShort.length + 1];
            for(i = 0; i < tempShort.length; i++)
            {
                drp[i] = tempShort[i];
            }
        }
        //otherwise initialize
        else
        {
            drp = new short[1];
        }
        
        //if rightPointer is not empty, expand by one
        if(rightPointer != null)
        {
            tempShort = rightPointer;
            rightPointer = new short[tempShort.length + 1];
            for(i = 0; i < tempShort.length; i++)
            {
                rightPointer[i] = tempShort[i];
            }
        }
        //otherwise initialize
        else
        {
            rightPointer = new short[1];
        }
    }
    
    private boolean fixPointer(String code, short pointer)
    {
        //add one to nodes visited
        nodesVisited++;
        //in case there are no nodes, return false
        if(pointer == -1)
        {
            return false;
        }
        //if the code is less than 0
        if(code.compareTo(keyValue[pointer]) < 0)
        {
            //recurse and add code to left slot, if it goes there
            if(!fixPointer(code, leftPointer[pointer]))  
            {
                leftPointer[pointer] = nextEmpty;
            }
            return true;
        }
        //if code is greater than 0
        else if (code.compareTo(keyValue[pointer]) > 0) 
        {
            //recurse and add code to right slot, if it goes there
            if(!fixPointer(code, rightPointer[pointer]))
            {
                rightPointer[pointer] = nextEmpty;
            }
            return true;
        }
        return true;
    }
    
    private short search(String code, short pointer)
    {
        //add one to total nodes visited
        nodesVisited++;
        
        //checks to see if the pointer even exists
        if(pointer == -1)
        {
            return -1;
        }
        //if the pointer is less than 0
        if(code.compareTo(keyValue[pointer]) < 0)
        {
            //recurse and return that solution
            return search(code, leftPointer[pointer]);
        }
        //if the pointer is greater than 0
        else if(code.compareTo(keyValue[pointer]) > 0)
        {
            //recurse and return that solution
            return search(code, rightPointer[pointer]);
        }
        else
        {
            //if it finds the solution, return it
            return drp[pointer];
        }
        
    }
    
    private void listAll(short pointer)
    {
        //check to make sure pointer is greater than 0
        if(pointer >= 0)
        {
            //recurse to the left child
            listAll(leftPointer[pointer]);
            
            //expand allRRN
            short [] tempShort;
            if(allRRN != null)
            {
                tempShort = allRRN;
                allRRN = new short[tempShort.length + 1];
                for(int i = 0; i < tempShort.length; i++)
                {
                    allRRN[i] = tempShort[i];
                }
            }
            //otherwise initialize
            else
            {
                allRRN = new short[1];
            }
            //add current pointer to allRRN
            allRRN[allRRN.length - 1] = drp[pointer];
            //recurse for the right child
            listAll(rightPointer[pointer]);
        }
    }
    
    // returns the next n bytes of the mainData file as a string
    //this method is copied from ShowFilesUtility because it is the way bytes were read
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
}
