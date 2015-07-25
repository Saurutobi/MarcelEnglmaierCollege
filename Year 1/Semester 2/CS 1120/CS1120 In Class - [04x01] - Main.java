package inclassweek4la3;

/**
 *
 * @author Marcel Englmaier
 */
public class Main
{
    public static void main(String[] args)
	{
		int students = 3;
		int i = 0;
		double rate = 57;
		double robots = 4;
		double pompoms = 24;
    
		StudentOrganization [] orgs = new StudentOrganization[6];
    
		CheerTeam pinkHearts = new CheerTeam(students, rate, pompoms);
		CheerTeam bigTitsPinkPomPoms = new CheerTeam(students, rate, pompoms);
		CheerTeam hottiesInPanties = new CheerTeam(students, rate, pompoms);
		ComputerClub chips = new ComputerClub(students, rate, robots);
		ComputerClub hardwareMastas = new ComputerClub(students, rate, robots);
		ComputerClub driveSynths = new ComputerClub(students, rate, robots);
    
		orgs[0] = pinkHearts;
		orgs[1] = chips;
		orgs[2] = hardwareMastas;
		orgs[3] = bigTitsPinkPomPoms;
		orgs[4] = hottiesInPanties;
		orgs[5] = driveSynths;
    
		for(i = 0; i < orgs.length; i++)
		{
			System.out.println(orgs[i].calcBudget());
		}
    }
}
