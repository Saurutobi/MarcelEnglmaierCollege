package inclass10;

import java.util.*;

/**
 *
 * @author Marcel Englmaier
 */
public class Inclass10
{

    public static void main(String[] args)
	{
        String Name = "Made, Up";
        Date birth = new Date(1992, 0, 6);
        Person me = new Person(Name, birth);
        Person you = new Person(Name, 1992, 0, 6);
        System.out.println(me);
        System.out.println(you);
        System.exit(0);
    }
}