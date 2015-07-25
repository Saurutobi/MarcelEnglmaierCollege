package inclass02;

/**
 *
 * @author Marcel Englmaier
 */
public class Triangle
{
    private int base;
    private int height;
    private int area;
	
    public Triangle(int b, int h)
    {
        base = b; height = h;
    }
	
    public void SetBase(int b)
    {
        base = b;
    }
	
    public int GetBase()
    {
        return base;
    }
	
    public void SetHeight(int h)
    {
        height = h;
    }
	
    public int GetHeight()
    {
        return height;
    }
	
    public double GetArea()
    {
        return (double)(base * height) / 2.0;
    }
}
