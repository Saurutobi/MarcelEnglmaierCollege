package Main;

import java.util.*;
/**
 *
 * @author Marcel Englmaier
 */
public class Rocketship {
    private static int count = -1;
    private static double cost = 0;
    Random randy = new Random(236787);
    
    private String name;
    private int numberOfShields;
    private int numberOfGuns;
    private int numberOfEngines;
    private int numberOfProtocolDroids = 0;
    private int numberOfProtocolDroidsRandom = 0;
    
    public Rocketship(String setNameOfRocketship, int setNumberOfShields, int setNumberOfGuns, int setNumberOfEngines)
    {
        count ++;
        this.name = setNameOfRocketship;
        this.numberOfShields = setNumberOfShields;
        this.numberOfGuns = setNumberOfGuns;
        this.numberOfEngines = setNumberOfEngines;
    }
    
    public static int getCount()
	{
        return count;
    }
    
    public String getName()
	{
        return name;
    }
    
    public int getNumberOfShields()
	{
        return numberOfShields;
    }

    public int getNumberOfGuns()
	{
        return numberOfGuns;
    }

    public int getNumberOfEngines()
	{
        return numberOfEngines;
    }

    public int getNumberOfProtocolDroids()
	{
        numberOfProtocolDroidsRandom = randy.nextInt(10) + 1;
        switch(numberOfProtocolDroidsRandom)
        {
            case 1: 
                numberOfProtocolDroids = 0;
                break;
            case 2:
                numberOfProtocolDroids = 0;
                break;
            case 3:
                numberOfProtocolDroids = 0;
                break;
            case 4:
                numberOfProtocolDroids = 0;
                break;
            case 5:
                numberOfProtocolDroids = 0;
                break;
            case 6:
                numberOfProtocolDroids = 1;
                break;
            case 7:
                numberOfProtocolDroids = 1;
                break;
            case 8:
                numberOfProtocolDroids = 2;
                break;
            case 9:
                numberOfProtocolDroids = 2;
                break;
            case 10:
                numberOfProtocolDroids = 3;
                break;
        }
        return numberOfProtocolDroids;
    }
}
