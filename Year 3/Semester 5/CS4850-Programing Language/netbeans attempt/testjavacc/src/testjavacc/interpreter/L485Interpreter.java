package testjavacc.interpreter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

import testjavacc.parser.L485Parser;
import testjavacc.parser.ParseException;


/**
 * @author carr
 *
 */

public class L485Interpreter {
	
	private static String getUserInput() throws IOException {
		String curLine = ""; // Line read from standard in
		
		InputStreamReader converter = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(converter);

		curLine = in.readLine();
		
		return curLine;
	}
	
	private static void repl()
	{
	    System.out.print( "====> ");
	    try {
			processCode(getUserInput());
		} catch (IOException e) {
			System.out.println("Error reading input");
		} catch (Error e) {
			System.out.println("Uncaught Interpreter Error: "+e);
		} catch (Exception e) {
			System.out.println("Uncaught Interpreter Exception: "+e);
		}
	    repl();
	}
	
	public static void processCode(String code)
	{
	    try {
	    	L485Parser.ReInit(new StringReader(code));
	    	L485Parser.program();
	    }
	    catch(ParseException e) {
	    	System.out.println("Syntax Error: "+e);
	    }
	}
	

	/**
	 * @param args
	 */
	public static void main(String args []) throws ParseException
	{
		L485Parser parser = new L485Parser(System.in);
		repl();
	}
}
