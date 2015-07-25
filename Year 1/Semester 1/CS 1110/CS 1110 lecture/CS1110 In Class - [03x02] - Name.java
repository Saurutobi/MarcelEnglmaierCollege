package inclass03;

/**
 *
 * @author Marcel Englmaier
 */
public class Name
{
    private String First, Middle, Last, nickname;
	
    public Name(String F, String M, String L, String K)
    {
        First = F; Middle = M; Last = L; nickname = K;
    }
	
    public String getFormal()
    {
        String retval = "";
        retval = Last + ", ";
        retval = retval + First + " ";
        retval = retval + Middle;
        return retval;
    }
	
    public String getFirstLast()
    {
        String retval = "";
        retval = First + " " + Last;
        return retval;
    }
}
