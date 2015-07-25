package la3_englmaier;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Marcel Englmaier
 */
public class Periodical extends LibraryItems
{
    private String volumeNumber;
    private String issueNumber;
    private String subject;
    private boolean itemIsCheckedOut;
    private GregorianCalendar dateCheckedOut;
    private GregorianCalendar dateDue;
    
    public Periodical(String number, String titleIn, String volumeIn, String issueIn, String subjectIn)
    {
        callNumber = number;
        title = titleIn;
        volumeNumber = volumeIn;
        issueNumber = issueIn;
        subject = subjectIn;
    }
    
    @Override
    public void printSpecs()
    {
        System.out.println("--------------------------------");
        System.out.println("Periodical Title: " + title);
        System.out.println("Volume: " + volumeNumber);
        System.out.println("Issue: " + issueNumber);
        System.out.println("Subject: " + subject);
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
        dateDue.add(Calendar.DAY_OF_YEAR, 7);
        itemIsCheckedOut = true;
        printSpecs();
    }    
}