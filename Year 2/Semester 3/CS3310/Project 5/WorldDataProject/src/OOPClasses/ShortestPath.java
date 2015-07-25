/* PROJECT: WorldDataProject #5         CLASS: shortestPath
* Written By: Marcel Englmaier
* FILES ACCESSED: none
* FILE STRUCTURE: OOP
* DESCRIPTION: calculates the map path using dijkstra's algorithm
*******************************************************************************/

package OOPClasses;

import java.util.ArrayList;


public class shortestPath
{
    //<editor-fold defaultstate="collapsed" desc="Public Declarations">
    
    
    //**************************** PUBLIC DECLARATIONS ************************
    //None
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Declarations">
    
    
    //**************************** PRIVATE DECLARATIONS ************************
    private Map map;
    private boolean [] included;
    private int [] distance;
    private int [] path;
    private int i;
    private int n;
    private int minSubscript;
    private int minDistance;
    private int numberOfTargets;
    ArrayList<String> tracePathArray = new ArrayList<String>();
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public Constructor(s)">
    
    
    //**************************** PUBLIC CONSTRUCTOR(S) ***********************
    public shortestPath(Map map)
    {
        this.map = map;
        n = map.getN();
    }
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public Get/Set Methods">
    
    
    //**************************** PUBLIC GET/SET METHODS **********************
    public int [] getDistanceArray()
    {
        return distance;
    }
    public int [] getPathArray()
    {
        return path;
    }
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public Service Methods">
    
    
    //**************************** PUBLIC SERVICE METHODS **********************
    public String findPath(int [] numbers)
    {
        Initialize(numbers[0]);
        Search(numbers[0], numbers[1]);
        return ReportAnswer(numbers[0], numbers[1]) + "\n" + traceOfTargets();
    }
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Methods">
    
    
    //**************************** PRIVATE METHODS *****************************
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
            distance[i] = map.getRoadDistance(firstCityNumber, i);
            if(distance[i] != Integer.MAX_VALUE && distance[i] != 0)
                path[i] = firstCityNumber;
            else
                path[i] = -1;
        }
        included[firstCityNumber] = true;
        
    }
    private void Search(int firstCityNumber, int secondCityNumber)
    {
        tracePathArray = new ArrayList<String>();
        numberOfTargets = 0;
        
        //the main Search part
        //special case
        if(firstCityNumber == secondCityNumber)
        {
            distance[secondCityNumber] = map.getRoadDistance(firstCityNumber, 
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
                tracePathArray.add(map.getCityName(minSubscript) + " ");
                numberOfTargets++;
                for(i = 0; i < n; i++)
                {
                    if(!included[i])
                    {
                        if(map.getRoadDistance(minSubscript, i) 
                                != Integer.MAX_VALUE 
                                && map.getRoadDistance(minSubscript, i) != 0)
                        {
                            if((distance[minSubscript] 
                                    + map.getRoadDistance(minSubscript, i)) 
                                    < distance[i])
                            {
                                distance[i] = distance[minSubscript] 
                                        + map.getRoadDistance(minSubscript, i);
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
        String ret = "DISTANCE: " + distance[secondCityNumber] + "\nPATH: ";
        ArrayList<String> pathArray = new ArrayList<String>();

        //store backwards path in array
        pathArray.add(map.getCityName(secondCityNumber));
        int current = secondCityNumber;
        while(path[current] != -1)
        {
            pathArray.add(map.getCityName(path[current]));
            current = path[current];
        }
        
        //store forwards path in string retval
        for(i = pathArray.size() - 1; i >= 0; i--)
        {
            if(i != 0)
                ret += pathArray.get(i) + " > ";
            else
                ret += pathArray.get(i);
        }
        ret += "\n";
        
        //return answer
        return ret;
    }
    
    private String traceOfTargets()
    {
        String ret = "TRACE OF TARGETS: ";
        for(i = 0; i < tracePathArray.size(); i++)
        {
            ret += tracePathArray.get(i);
        }
        ret += "\n# TARGETS: " + numberOfTargets + "\n";
        
        //return trace of targets
        return ret;
    }
    
    
    
    //</editor-fold>
}