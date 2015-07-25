package passingobjects;

/**
 *
 * @author Marcel Englmaier
 */
public class Passingobjects
{

    public static void main(String[] args)
	{
        int i = 23;
        System.out.println(demo(i));
        
    }
	
    public static double demo(int i)
    {
        return (double) i/0.5;
    }
}
