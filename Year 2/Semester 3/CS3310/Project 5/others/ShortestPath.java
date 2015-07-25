/* PROJECT: Shortest Path         CLASS: ShortestPath
* AUTHOR: Ekema
* COURSE INFO:  CS3310 (MWF) Fall 2012 - Asgn 5
* Search Method: Dijkstra's Minimum Cost Path Algorithm
*******************************************************************************/

package OOPClasses;

import java.util.ArrayList;

public class ShortestPath 
{
    /************************ Private Declarations ****************************/
    ArrayList<String> tracePathArray = new ArrayList<String>();
    private Map map = null;
    private String[] answers;
    private boolean[] included;
    private int[] distance, path;
    private int i, n, numberOfTargets, minSubscript, minDistance;
    
    /************************ Public Getters/Setters **************************/
    public int[] GetDistanceArray()
    {
        return distance;
    }
    
    //--------------------------------------------------------------------------
    public int[] GetPathArray()
    {
        return path;
    }
    
    /************************ Constructor *************************************/
    public ShortestPath(Map map)
    {
        this.map = map;
        n = map.GetN();
    }
    
    /************************ Public Service Methods **************************/
    public String[] FindPath(int firstCityNumber, int secondCityNumber)
    {
        Initialize(firstCityNumber);
        Search(firstCityNumber, secondCityNumber);
        answers = new String[2];
        answers[0] = ReportAnswer(firstCityNumber, secondCityNumber);
        answers[1] = ReportTraceOfTargets();
        return answers;
    }
    
    /************************ Private Methods *********************************/
    private void Initialize(int firstCityNumber)
    {
        //Initialize the 3 supplemental arrays
        included = new boolean[n];
        distance = new int[n];
        path = new int[n];
        
        //fill with beginning values
        for(i = 0; i < n; i++)
        {
            included[i] = false;
            distance[i] = map.GetRoadDistance(firstCityNumber, i);
            if(distance[i] != Integer.MAX_VALUE && distance[i] != 0)
                path[i] = firstCityNumber;
            else
                path[i] = -1;
        }
        included[firstCityNumber] = true;
    }
    
    //--------------------------------------------------------------------------
    private void Search(int firstCityNumber, int secondCityNumber)
    {
        tracePathArray = new ArrayList<String>();
        numberOfTargets = 0;
        
        //the main Search part
        //special case
        if(firstCityNumber == secondCityNumber)
        {
            distance[secondCityNumber] = map.GetRoadDistance(firstCityNumber, 
                    secondCityNumber);
        }
        //normal case
        else
        {
            while(!included[secondCityNumber])
            {
                minDistance = Integer.MAX_VALUE;
                for(i = 0; i < n; i++)
                {
                    if(distance[i] != 0 && distance[i] < minDistance 
                            && !included[i])
                    {
                        minDistance = distance[i];
                        minSubscript = i;
                    }
                }
                included[minSubscript] = true;
                tracePathArray.add(map.GetCityName(minSubscript) + " ");
                numberOfTargets++;
                for(i = 0; i < n; i++)
                {
                    if(!included[i])
                    {
                        if(map.GetRoadDistance(minSubscript, i) 
                                != Integer.MAX_VALUE 
                                && map.GetRoadDistance(minSubscript, i) != 0)
                        {
                            if((distance[minSubscript] 
                                    + map.GetRoadDistance(minSubscript, i)) 
                                    < distance[i])
                            {
                                distance[i] = distance[minSubscript] 
                                        + map.GetRoadDistance(minSubscript, i);
                                path[i] = minSubscript;
                            }
                        }
                    }
                }
            }
        }
    }
    
    //--------------------------------------------------------------------------
    private String ReportAnswer(int firstCityNumber, int secondCityNumber)
    {     
        String retval = "DISTANCE: " + distance[secondCityNumber] + "\nPATH: ";
        ArrayList<String> pathArray = new ArrayList<String>();

        //store backwards path in array
        pathArray.add(map.GetCityName(secondCityNumber));
        int current = secondCityNumber;
        while(path[current] != -1)
        {
            pathArray.add(map.GetCityName(path[current]));
            current = path[current];
        }
        
        //store forwards path in string retval
        for(i = pathArray.size() - 1; i >= 0; i--)
        {
            if(i != 0)
                retval += pathArray.get(i) + " > ";
            else
                retval += pathArray.get(i);
        }
        retval += "\n";
        
        //return answer
        return retval;
    }
    
    //--------------------------------------------------------------------------
    private String ReportTraceOfTargets()
    {
        String retval = "TRACE OF TARGETS: ";
        for(i = 0; i < tracePathArray.size(); i++)
        {
            retval += tracePathArray.get(i);
        }
        retval += "\n# TARGETS: " + numberOfTargets + "\n";
        
        //return trace of targets
        return retval;
    }
}
