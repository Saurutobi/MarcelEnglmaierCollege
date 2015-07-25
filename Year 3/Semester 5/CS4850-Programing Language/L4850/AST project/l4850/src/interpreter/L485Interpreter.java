package interpreter;
import java.io.*;
import java.util.*;

import parser.L485Parser;
import parser.ParseException;


/**
 * @author carr & Marcel Englmaier
 *
 */

public class L485Interpreter
{
	//This method now returns an array for quicker testing
	private static String [] getDirectoryInput() throws IOException
	{
		List<String> retVal = new ArrayList<String>();
		System.out.println("Enter the full file path of your directory of input");
		Scanner scan = new Scanner(System.in);
		System.out.print( "====> ");
		String dirName = scan.nextLine();
		try
		{
			File directory = new File(dirName);
			File [] contents = directory.listFiles();
			for(File currentFile : contents)
			{
				BufferedReader buff = new BufferedReader(new FileReader(currentFile.getAbsolutePath()));
				String curLine;
				while((curLine = buff.readLine()) != null)
				{
					retVal.add(curLine);
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("It didn't work, try again");
		}
		return retVal.toArray(new String[retVal.size()]);
	}
	
	private static String getUserInput() throws IOException
	{
		Scanner scan = new Scanner(System.in);
		return scan.nextLine();
	}

	private static void repl()
	{
		//This section is modified to ask for 2 types of input: a single line input, or a directory path for batch processing
	    System.out.println("1 for single input\n2 for directory input");
	    Scanner operation = new Scanner(System.in);
	    System.out.print( "====> ");
	    String op = operation.nextLine();
	    try 
	    {
			//Either read in from StdIn
	    	if(op.equals("1"))
			{
	    		System.out.print( "====> ");
	    		String [] temp = new String[1];
	    		temp[0] = getUserInput();
				processCode(temp);
			}
			//Or from the directory
	    	if(op.equals("2"))
	    	{
	    		System.out.print( "====> ");
	    		processCode(getDirectoryInput());
	    	}
		}
	    catch (IOException e)
	    {
			System.out.println("Error reading input");
		}
		catch (Error e)
		{
			System.out.println("Uncaught Interpreter Error: "+e);
		}
		catch (Exception e)
		{
			System.out.println("Uncaught Interpreter Exception: "+e);
		}
	    repl();
	}
	
	//Changed the input to be an array
	public static void processCode(String [] code)
	{
		//for each line of input, do all the work. allows for easy batch processing
		for(String currentInput : code)
		{
			//modified try-catch to run inside rather than outside foreach loop, so all items will get processed
			try
			{
				System.out.println("Working on: " + currentInput);
				L485Parser.ReInit(new StringReader(currentInput));
				L485Parser.program();
				System.out.println("Success!");
			}
			catch(ParseException e)
			{
				System.out.println("----------------------------------------------------Syntax Error: "+e);
			}
		}
	}

	public static void main(String args []) throws ParseException
	{
		L485Parser parser = new L485Parser(System.in);
		repl();
	}
}
