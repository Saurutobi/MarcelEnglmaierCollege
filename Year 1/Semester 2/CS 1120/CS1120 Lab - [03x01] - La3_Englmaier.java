package la3_englmaier;

import java.util.*;
import java.io.*;

/**
 *
 * @author Marcel Englmaier
 */
public class La3_Englmaier
{
    
    public static Scanner scanLeKeyboard = new Scanner(System.in);
    public static int numberOfItems = 0;
    public static LibraryItems [] libraryCatalog;
    
    public static void main(String[] args)  throws FileNotFoundException, IOException
	{
        readFile();
        boolean quit = false;
        int selection = 0;
        while(!quit)
        {
            selection = acquireUserSelection();
            if(selection == 1)
            {
                printItems();
            }
            else if(selection == 2)
            {
                checkOutItems();
            }
            else if(selection == 3)
            {
                quit = true;
            }
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
        //create new Library array
        libraryCatalog = new LibraryItems[numberOfItems];
        scanLeFilesOfInput.nextLine();
        //loop for how many items there are
		for(i = 0; i < numberOfItems; i++)
        {
            //read in the item
            itemLineInput = scanLeFilesOfInput.nextLine();
            String [] lineArray = itemLineInput.split(",");
            if(lineArray[0].equalsIgnoreCase("B"))
            {
                libraryCatalog[i] = new Book(lineArray[1], lineArray[2], lineArray[3], lineArray[4]);
            }
            if(lineArray[0].equalsIgnoreCase("P"))
            {
                libraryCatalog[i] = new Periodical(lineArray[1], lineArray[2], lineArray[3], lineArray[4], lineArray[5]);
            }
        }
    }
    
    public static int acquireUserSelection()
    {
        int selection = 0;
        
        //print ALL the Menus
        System.out.println("------------- Menu -------------");
        System.out.println("1) Display collection");
        System.out.println("2) Check out materials");
        System.out.println("3) Quit");
        System.out.println("--------------------------------");
        System.out.print("Please choose an option: ");
        
        selection = scanLeKeyboard.nextInt();
        while(!(selection == 1 || selection == 2 || selection == 3))
        {
            System.out.println("please only enter 1,2, or 3");
            selection = scanLeKeyboard.nextInt();
        }
        return selection;
    }
    
    public static void printItems()
    {
        int i = 0;
        for(i = 0; i < numberOfItems; i++)
        {
            libraryCatalog[i].printSpecs();
        }
    }
    
    public static void checkOutItems()
    {
        String callNumber = "";
        int i = 0;
        boolean itemDoesNotExist = true;
        System.out.println("Enter the call Number: ");
        do
        {
            callNumber = scanLeKeyboard.next();
            for(i = 0; i < numberOfItems; i++)
            {
                if(libraryCatalog[i].callNumberExists(callNumber))
                {
                    libraryCatalog[i].checkOut();
                    itemDoesNotExist = false;
                }
            }
            if(itemDoesNotExist)
            {
                System.out.println("This Call Number Does Not Exist. Please Enter a valid Call Number");
            }
        }while(itemDoesNotExist);
    }
}