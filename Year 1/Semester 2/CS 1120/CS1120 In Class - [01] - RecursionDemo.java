package recursiondemo;

/**
 *
 * @author Marcel Englmaier
 */
public class RecursionDemo
{
    static int [] searchArray;
    
    public static void main(String[] args)
	{
        //tribonacci recursion
        System.out.println(tribonacci(5));
        //binary search recursion
        searchArray = new int[7];
        searchArray[0] = 3;
        searchArray[1] = 6;
        searchArray[2] = 12;
        searchArray[3] = 25;
        searchArray[4] = 47;
        searchArray[5] = 49;
        searchArray[6] = 51;
        int target = 6;
        System.out.println(binarySearch(0, searchArray.length, target));
    }
    
    public static int tribonacci(int n)
    {
        if(n <= 2)
        {
            return 0;
        }
        else if(n == 3)
        {
            return 1;
        }
        else
        {
            int a = tribonacci(n - 1);
            int b = tribonacci(n - 2);
            int c = tribonacci(n - 3);
            return a + b + c;
        }
    }
    
    public static int binarySearch(int start, int end, int target)
    {
        int midpoint = (end + start) / 2;
        if(start > end)
        {
            return -1;
        }
        if(target == searchArray[midpoint])
        {
            return midpoint;
        }
        else if(target < searchArray[midpoint])
        {
            return binarySearch(start, (midpoint - 1), target);
        }
        else
        {
            return binarySearch((midpoint + 1), end, target);
        }
    }
}
