package pathtriangle;

import java.util.Random;

/**
 *
 * @author Tamwyn Eopia
 */
public class Person {
    int [] position = new int[2];
    Random randy = new Random();
    int meh = 0;
    
    public Person()
    {
        position[0] = 0;
        position[1] = 0;
    }
    
    public int x()
    {
        return position[0];
    }
    
    public int y()
    {
        return position[1];
    }
    
    public void zero()
    {
        position[0] = 0;
        position[1] = 0;
    }
    
    public void move()
    {
        meh = randy.nextInt(4);
        switch(meh)
        {
            case 0: position[0] = position[0] + 1;
                break;
            case 1: position[1] = position[1] + 1;
                break;
            case 2: position[0] = position[0] - 1;
                break;
            case 3: position[1] = position[1] - 1;
                break;
        }
    }
}
