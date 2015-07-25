package threadedintegersorter;

/*
 * @author Saurutobi
 */
public class SortingThread extends Thread
{
    protected int [] arrayToSort;
    
    public SortingThread(int[] arrayToSort)
    {
        this.arrayToSort = arrayToSort;
    }
    
    @Override
    public void run()
    {
        //Full Bubble sorting happens here
        for(int i = 0; i < arrayToSort.length; i++)
        {
            for(int y = 1; y < arrayToSort.length; y++)
            {
                if(arrayToSort[y-1] > arrayToSort[y])
                {
                    int temp = arrayToSort[y-1];
                    arrayToSort[y-1] = arrayToSort[y];
                    arrayToSort[y] = temp;
                }
            }
        }
    }
    
    public int [] sortedArray()
    {
        return arrayToSort;
    }
}
