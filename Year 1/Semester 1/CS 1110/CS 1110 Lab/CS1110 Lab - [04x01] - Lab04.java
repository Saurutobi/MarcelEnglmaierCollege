package lab04;

/**
 *
 * @author Marcel Englmaier
 */
public class Lab04 {

    public static void main(String[] args) 
	{
		//Test the rectangle method.
        Rectangle rect = new Rectangle();
        //practice toString() Override
        System.out.println(rect);
        
        int area = rect.getArea();
        
        System.out.println("Area = " + area);
    }
}
