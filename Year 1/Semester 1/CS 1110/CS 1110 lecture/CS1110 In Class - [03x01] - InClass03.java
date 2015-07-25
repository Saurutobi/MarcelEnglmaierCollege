package inclass03;

/**
 *
 * @author Marcel Englmaier
 */
public class InClass03
{

    public static void main(String[] args)
	{
		Name bob = new Name("Robert", "Glenn", "Hardin", "bob");
		System.out.println(bob.getFormal());
		Name sam = new Name("Samual", "Author", "John", "Sam");
		System.out.println(sam.getFirstLast());
    }
}
