package Game;

import java.util.*;

/**
 *
 * @author Marcel Englmaier
 */
public class Dart
{
    Random randyX = new Random();
    Random randyY = new Random();
    private int count = 0;
    private int xPoint;
    private int yPoint;
    private int radius;
    
    public Dart(int radius)
    {
        count++;
        radius = radius;
    }
	
    public Dart()
    {
        count++;
    }
	
    public int getX()
    {
        xPoint = randyX.nextInt(radius);
        return xPoint;
    }
	
    public int getY()
    {
        yPoint = randyY.nextInt(radius);
        return yPoint;
    }
}
