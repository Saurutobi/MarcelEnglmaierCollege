package lab04;

/**
 *
 * @author Marcel Englmaier
 */
public class Rectangle {
    private int length = 10;
    private int width = 30;
    private String name = "";
    
    public int getLength()
    {
        return length;
    }
	
    void setLength(int length)
    {
        this.length = length;
    }
	
    public int getWidth()
    {
        return width;
    }
	
    public void setWidth(int width)
    {
        this.width = width;
    }
	
    public int getArea()
    {
        return  length * width;
    }
	
    @Override
    public String toString()
    {
        return "Length = " + length + " & Width = " + width;
    }
	
    public String getName()
	{
        return name;
    }
	
    public void setName(String name)
	{
        this.name = name;
    }
}
