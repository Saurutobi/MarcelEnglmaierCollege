package inclass02;

/**
 *
 * @author Marcel Englmaier
 */
public class Circle
{
    private double r;
	
    public void SetRadius(double radius)
    {
        r = radius;
    }
	
    public double GetArea()
    {
        return Math.PI * Math.pow(r, 2);
    }
}
