package threadedintegersorter;

import java.util.ArrayList;
import java.util.List;

/*
 * @author Saurutobi
 */
public class MergingThread extends Thread
{
    private int [] top;
    private int [] bottom;
    protected int [] sortedArray;
    
    public MergingThread(int [] top, int [] bottom)
    {
        this.top = top;
        this.bottom = bottom;
        sortedArray = new int[top.length + bottom.length];
    }
    
    @Override
    public void run()
    {
        //merge the two results into the one array
        for(int i = 0; i < sortedArray.length/2; i++)
        {
            sortedArray[i] = top[i];
            sortedArray[i + sortedArray.length / 2] = bottom[i];
        }
        sortedArray[sortedArray.length - 1] = bottom[bottom.length - 1];
        
        //Modified Bubble Sort just for the bottom half to merge
        for(int i = sortedArray.length/2; i < sortedArray.length; i++)
        {
            for(int y = 1; y < sortedArray.length; y++)
            {
                if(sortedArray[y-1] > sortedArray[y])
                {
                    int temp = sortedArray[y-1];
                    sortedArray[y-1] = sortedArray[y];
                    sortedArray[y] = temp;
                }
            }
        }
    }
}