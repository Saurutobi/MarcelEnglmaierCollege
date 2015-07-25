package Game;

/**
 *
 * @author Marcel Englmaier
 */
public class Board {
    private int boardRadius;
    private int bullsEyeRadius;
    private int numberOfTries = 0;
    private int xPoint;
    private int yPoint;
    private int hit = 0;
    private int distanceToRadius = 0;
    
    public Board(int radius, int bullsEye)
    {
        boardRadius = radius;
        bullsEyeRadius = bullsEye;
    }
	
    public Board(int x, int y, int distance)
    {
        numberOfTries++;
        xPoint = x;
        yPoint = y;
        distanceToRadius = distance;
        if(distanceToRadius <= bullsEyeRadius)
        {
            hit = 1;
        }
    }
	
    public int getRadius()
    {
        return boardRadius;
    }
	
    public int getbullsEyeRadius()
    {
        return bullsEyeRadius;
    }
	
    public int hit()
    {
        return hit;
    }
}
