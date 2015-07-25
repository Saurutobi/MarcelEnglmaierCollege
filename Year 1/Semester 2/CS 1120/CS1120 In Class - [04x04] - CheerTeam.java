package inclassweek4la3;

/**
 *
 * @author Marcel Englmaier
 */
public class CheerTeam extends StudentOrganization
{
    //Fields
    private double pompomBudget;

    //Constructor
    public CheerTeam(int numStud, double rate, double pompom)
	{
        super(numStud, rate);
        pompomBudget = pompom;
    }

    //Methods
    public double calcBudget()
	{
        return super.calcBudget() + pompomBudget;
    }
}
