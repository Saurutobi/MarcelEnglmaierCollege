package la3gui;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author #Tamwyn Eopia
 */
public class Book extends LibraryItems{

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
    public String printSpecs()
    {
        String specs = "";
        specs = ("--------------------------------\nBook Title: " + title + "\nAuthor: " + author + "\nGenre: " + genre + "\nCall Number: " + callNumber + "\nChecked Cut: " + itemIsCheckedOut + "\n");

        if(itemIsCheckedOut)
        {
            specs = specs + (String.format("Date Checked Out: %tD\n", dateCheckedOut)) + String.format("Date Due: %tD\n", dateDue);
        }
        specs = specs + "\n--------------------------------";
        return specs;
    }
    
    @Override
    public String checkOut()
    {
        dateCheckedOut = new GregorianCalendar();
        dateDue = (GregorianCalendar)dateCheckedOut.clone();
        dateDue.add(Calendar.DAY_OF_YEAR, 21);
        itemIsCheckedOut = true;
        return printSpecs();
    }
}