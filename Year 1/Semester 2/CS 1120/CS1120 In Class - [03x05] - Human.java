package inlabpractice;

/**
 *
 * @author Marcel Englmaier
 */
public class Human implements Talkative
{
    private String message;
    
    public Human(String msg)
    {
        message = msg;
    }
    
    public String speak()
    {
        return message;  
    }
}


