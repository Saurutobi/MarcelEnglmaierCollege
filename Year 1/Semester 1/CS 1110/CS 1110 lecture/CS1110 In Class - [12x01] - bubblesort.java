package bubblesort;

/**
 *
 * @author Marcel Englmaier
 */
public class bubblesort
{

    public static void main(String[] args)
	{
        
    }
	
    public static void bubblesort(int[] x)
    {
        int n = x.length, pass, i, temp;
        for(pass = 1; pass < n; pass++)
        {
            for(i = 0; i < n - pass; i++)
            {
                if(x[i] > x[i+1])
                {
                    temp = x[i];
                    x[i] = x[i+1];
                    x[i+1] = temp;
                }
            }
        }
    }
}
