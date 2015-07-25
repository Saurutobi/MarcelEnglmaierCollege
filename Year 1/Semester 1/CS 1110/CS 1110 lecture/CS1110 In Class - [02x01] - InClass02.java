package inclass02;

/**
 *
 * @author Marcel Englmaer
 */
public class InClass02 {

    public static void main(String[] args)
	{
        Triangle T1, T2;
        T1 = overRun("T1", 23, 23);
        T2 = overRun("T2", 16, 56);
        printTriangle("T1" , T1);
        printTriangle("T2" , T2);
    }
    
    public static Triangle overRun(String Name, int base, int height)
	{
        Triangle T = new Triangle(base, height);
        System.out.println(Name + " base is " + T.GetBase());
        System.out.println(Name + " Height is " + T.GetHeight());
        System.out.println(Name + " Area is " + T.GetArea());
        return T;
    }
    
    public static void printTriangle(String Name, Triangle T)
	{
        System.out.println(Name + " base is " + T.GetBase());
        System.out.println(Name + " Height is " + T.GetHeight());
        System.out.println(Name + " Area is " + T.GetArea());
    }
}
