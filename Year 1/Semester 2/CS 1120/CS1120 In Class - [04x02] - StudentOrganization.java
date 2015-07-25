package inclassweek4la3;

/**
 *
 * @author Marcel Englmaier
 */
public class StudentOrganization
{
    //Fields
    int numStudents;
    double perStudentBudgetRate;

    //Constructor
    public StudentOrganization(int numStud, double rate)
	{
        numStudents = numStud;
        perStudentBudgetRate = rate;
    }

    //Methods
    public double calcBudget()
	{
        return numStudents * perStudentBudgetRate;
    }
}
