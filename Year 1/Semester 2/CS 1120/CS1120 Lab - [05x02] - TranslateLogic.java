package la5_englmaier;

import java.io.*;
import java.util.*;

/**
 *
 * @author Marcel Englmaier
 */
public class TranslateLogic
{
    //general public declarations
    File inputFile;
    String translatedText = "";
    Scanner scanLeFilesOfInput;
    String [][] text;
    String currentLine = "";
    int numberOfWords = 0;
    int word = 0;
    
    /**
     * empty constructor
     */
    public TranslateLogic()
    {
        
    }
    
    /**
     * this method checks whether the file exists, and then calls readFile()
     * -if any error occurs, it returns true
     * @param fileName Specifies name of file to be used.
     * @return a boolean which tells the main class whether or not it
     *         should show an error message for improper input.
     */
    public boolean fileDoesNotExist(String fileName)
    {
        //original try statement in case of IOException
        try
        {
            inputFile = new File(fileName);
            scanLeFilesOfInput = new Scanner(inputFile);
            //if the file exists, attempt to read it in
            try
            {
                readFile();
                return false;
            }
            catch(Exception e)
            {
                return true;
            }
        }
        catch(IOException e)
        {
            return true;
        }
    }
    
    /**
     * reads file into 2D array, prepares for translation
     */
    public void readFile()
    {
        //reads how many words are in corpus
        numberOfWords = scanLeFilesOfInput.nextInt();
        text = new String[numberOfWords][2];
        scanLeFilesOfInput.nextLine();
        
        word = 0;
        String [] lineArray;
        
        //read in file into 2D array
        for(word = 0; word < numberOfWords; word++)
        {
            currentLine = scanLeFilesOfInput.nextLine();
            lineArray = currentLine.split(",");
            text[word][0] = lineArray[0];
            text[word][1] = lineArray[1];
        }
    }
    
    /**
     * this method does the actual translation, the checking for capitalization
     *      and the punctuation checking
     * @param textToTranslate Specifies text that is to be translated.
     * @return a string that is the fully translated version of the input string
     */
    public String translate(String textToTranslate)
    {
        //attempts to translate, but if there is any error, returns an error message
        try
        {
            boolean translate = true;
            boolean addPunctuation = false;
            char punctuation = 0;
            char [] lastWordOfInput;
            char [] firstWordOfInput;
            char [] translatedTextCharArray;
            word = 0;
            int i = 0;
            
            String [] inputTextArray = textToTranslate.split(" ");
            
            //assignments needed for capitalization or punctuation
            lastWordOfInput = inputTextArray[inputTextArray.length- 1].toCharArray();
            firstWordOfInput = inputTextArray[0].toCharArray();
            punctuation = lastWordOfInput[lastWordOfInput.length - 1];
            
            //if there is punctuation, remove it before translating
            if(punctuation >= 33 && punctuation <= 47)
            {
                lastWordOfInput = inputTextArray[inputTextArray.length- 1].toCharArray();
                inputTextArray[inputTextArray.length - 1] = "";
                for(i = 0; i < lastWordOfInput.length - 1; i++)
                {
                    inputTextArray[inputTextArray.length - 1] = inputTextArray[inputTextArray.length - 1] + lastWordOfInput[i];
                }
                addPunctuation = true;
            }
            
            //actual translation
            for(word = 0; word < inputTextArray.length; word++)
            {
                for(i = 0; i < numberOfWords; i++)
                {
                    if(translate)
                    {
                        if(inputTextArray[word].equalsIgnoreCase(text[i][0]))
                        {
                            //translation occurs here
                            translate = false;
                            translatedText = translatedText + " " + text[i][1];
                        }
                    }
                }
                //if the word doesn't exist in corpus, use word from input
                if(translate)
                {
                    translatedText = translatedText + " " + inputTextArray[word];
                }
                translate = true;
            }
            
            //check for and fix punctuation
            if(addPunctuation)
            {
                translatedText = translatedText + punctuation;
            }
            
            //dissasembly of string into char array needed to provide proper capitalization
            translatedTextCharArray = translatedText.toCharArray();
            
            //check for and fix capitalization
            if(firstWordOfInput[0] >= 65 && firstWordOfInput[0] <= 90)
            {
                System.out.println(translatedTextCharArray[1]);
                translatedTextCharArray[1] = (char)(translatedTextCharArray[1] - 32);
            }
            
            translatedText = "";
            
            //reassembly of string from char array
            for(i = 0; i < translatedTextCharArray.length; i++)
            {
                translatedText = translatedText + translatedTextCharArray[i];
            }
            
            return translatedText;
        }
        catch(Exception e)
        {
            return "Error while Translating";
        }
    }
    
    /**
     * this method sets all variables to 0, or the equivalent of 0(null or empty)
     */
    public void reset()
    {
        //resets all settings
        inputFile = null;
        translatedText = "";
        scanLeFilesOfInput = null;
        text = null;
        currentLine = "";
        numberOfWords = 0;
        word = 0;
    }
}
