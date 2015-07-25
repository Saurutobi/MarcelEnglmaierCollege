package inlabpractice;

import java.util.*;

/**
 *
 * @author Marcel Englmaier
 */
public class Dog implements Talkative
{
    
    public String speak()
    {
        Random randy = new Random();
        int one = randy.nextInt(3);
        switch(one)
        {
            case 0: return "Bark! Bark!";
            case 1: return "HOWL";
            case 2: return "Woof!";
            default: return "";
        }
    }
}