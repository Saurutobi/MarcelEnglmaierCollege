package inclassweek4la3;

/**
 *
 * @author Marcel Englmaier
 */
public class ComputerClub extends StudentOrganization
{
    //Fields
    private double numberOfRobots;
    private double robotsBudget;

    //Constructor
    public ComputerClub(int numStud, double rate, double robots)
	{
        super(numStud, rate);
        numberOfRobots = robots;
        robotsBudget = numberOfRobots * 100;
    }

    //Methods
    public double calcBudget()
	{
        return super.calcBudget() + robotsBudget;
    }
}
