package practicepmt;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Marcel Englmaier
 */
public class PracticePMT
{

    public static int numberOfItems;
    private static ArrayList<String>  fullInput = new ArrayList<String>();
    public static int [] missing;
    
    public static void main(String[] args) throws IOException
	{
        readFile();
        sort();
        for(int i = 0; i < numberOfItems; i++)
        {
            System.out.println(fullInput.get(i) + "    Missing: " + missing[i]);
        }
    }
    
    public static void readFile() throws IOException
    {
        File inputFile = new File("Input.txt");
        Scanner scanLeFilesOfInput = new Scanner(inputFile);
        int i = 0;
        String itemLineInput = "";
        
        //read line one to find how many items there are(public variable)
        numberOfItems = scanLeFilesOfInput.nextInt();
        scanLeFilesOfInput.nextLine();
        //loop for how many items there are
		for(i = 0; i < numberOfItems; i++)
        {
            //read in the item
            itemLineInput = scanLeFilesOfInput.nextLine();
            fullInput.add(itemLineInput);
        }
        missing = new int[numberOfItems];
    }
    
    public static void checkArray(int [] array, int errorAt)
    {
        int i = 0;
        for(i = 0; i < array.length - 1; i++)
        {
            if(!((array[i] + 1 )== array[i+1]))
            {
                missing[errorAt] = array[i] + 1;
            }
        }
        if(missing[errorAt] == 0)
        {
            if(array[0] == 1)
            {
                missing[errorAt] = array[array.length - 1] + 1;
            }
            else
            {
                missing[errorAt] = 1;
            }
        }
    }
    
    public static void sort()
    {
        int outer = 0;
        for(outer = 0; outer < fullInput.size(); outer++)
        {
            String [] lineArray = fullInput.get(outer).split(";");
            int [] array = new int[lineArray.length];
            for(int two = 0; two < array.length; two++)
            {
                array[two] = Integer.parseInt(lineArray[two]);
            }
            int i, j,t=0;
            for(i = 0; i < array.length; i++)
            {
                for(j = 1; j < (array.length-i); j++)
                {
                    if(array[j-1] > array[j])
                    {
                        t = array[j-1];
                        array[j-1]=array[j];
                        array[j]=t;
                    }
                }
            }
            checkArray(array, outer);
        }
    }
}
