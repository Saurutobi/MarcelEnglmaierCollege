package runClass;

import java.text.DecimalFormat;

/**
 * class Restaurant contains the information (name, type, rating, location) of a restaurant
 * @author Marcel Englmaier/Thomas Ekema/Bobby Wojo
 */
public class Restaurant implements Comparable<Restaurant>
{
    private String nameOfRestaurant;
    private String typeOfCuisine;
    private int numberOfStars;
    private int xCoordinate;
    private int yCoordinate;
    private double distance;
    
    /**
     * the constructor initializes the variables using parameter input
     * @param name The name of the restaurant
     * @param cuisine The type of cuisine
     * @param stars The rating/number of stars of the restaurant
     * @param x The x coordinate of the restaurant's location
     * @param y The y coordinate of the restaurant's location
     */
    public Restaurant(String name, String cuisine, int stars, int x, int y)
    {
        nameOfRestaurant = name;
        typeOfCuisine = cuisine;
        numberOfStars = stars;
        xCoordinate = x;
        yCoordinate = y;
    }
    
    /**
     * method getDistance calculates distance to target from restaurant
     * @param restaurant Takes in a restaurant object
     * @param x The x coordinate of the target's location
     * @param y The y coordinate of the target's location
     */
    public void setDistance(int x, int y)
    {
        distance = Math.sqrt(Math.pow(x - xCoordinate, 2) + Math.pow(y - yCoordinate, 2));
    }
    
    /**
     * method getDistance returns the distance to target
     * @return double the distance to target
     */
    public double getDistance()
    {
        return distance;
    }
    
    /**
     * method getName returns the name
     * @return String the name
     */
    public String getName()
    {
        return nameOfRestaurant;
    }
    
    /**
     * method getType returns the type
     * @return String the type
     */
    public String getType()
    {
        return typeOfCuisine;
    }
    
    /**
     * method getX returns the x coordinate
     * @return xCoordinate the integer that is returned
     */
    public int getX()
    {
        return xCoordinate;
    }
    
    /**
     * method getY returns the y coordinate
     * @return yCoordinate the integer that is returned
     */
    public int getY()
    {
        return yCoordinate;
    }
    
    /**
     * method getStars returns the number of stars
     * @return numberOfStars the integer that is returned
     */
    public int getStars()
    {
        return numberOfStars;
    }
    
    /**
     * method compareTo returns the state of insertion
     * @return int the integer that determines insertion
     * if state is positive, it gets inserted above, if it is negative, below, if equal, it returns a 0
     */
    @Override
    public int compareTo(Restaurant in)
    {
        
        if(in.getStars() > numberOfStars)
        {
            return 1;
        }
        else if(in.getStars() == numberOfStars && in.getDistance() < distance)
        {
            return 1;
        }
        else if(in.getStars() == numberOfStars && in.getDistance() == distance)
        {
            return 0;
        }
        else if(in.getStars() == numberOfStars && in.getDistance() > distance)
        {
            return -1;
        }
        else
        {
            return -1;
        }
    }
    
    /**
     * method toString returns a description of the restaurant
     * @return String description of restaurant
     */
    @Override
    public String toString()
    {
        DecimalFormat formatMyDouble = new DecimalFormat("0.0");
        return nameOfRestaurant + "(" + typeOfCuisine + "), " + numberOfStars + " Stars, " + formatMyDouble.format(distance) + " miles";
    }
}
