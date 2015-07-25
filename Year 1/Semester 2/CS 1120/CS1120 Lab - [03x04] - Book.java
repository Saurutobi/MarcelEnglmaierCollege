package la3_englmaier;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Marcel Englmaier
 */
public class Book extends LibraryItems
{
    private String author;
    private String genre;
    private boolean itemIsCheckedOut;
    private GregorianCalendar dateCheckedOut;
    private GregorianCalendar dateDue;
    
    public Book(String number, String titleIn, String authorIn, String genreIn)
    {
        callNumber = number;
        title = titleIn;
        author = authorIn; 
        genre = genreIn;
    }
    
    @Override
    public void printSpecs()
    {
        System.out.println("--------------------------------");
        System.out.println("Book Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Genre: " + genre);
        System.out.println("Call Number: " + callNumber);
        System.out.println("Checked Out: " + itemIsCheckedOut);
        if(itemIsCheckedOut)
        {
            System.out.print(String.format("Date Checked Out: %tD\n", dateCheckedOut));
            System.out.print(String.format("Date Due: %tD\n", dateDue));
        }
        System.out.println("--------------------------------");
    }
    
    @Override
    public void checkOut()
    {
        dateCheckedOut = new GregorianCalendar();
        dateDue = (GregorianCalendar)dateCheckedOut.clone();
        dateDue.add(Calendar.DAY_OF_YEAR, 21);
        itemIsCheckedOut = true;
        printSpecs();
    }
}