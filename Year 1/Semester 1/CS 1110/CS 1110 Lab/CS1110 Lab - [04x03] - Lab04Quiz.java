package lab04quiz;

/**
 *
 * @author Marcel Englmaier
 */
public class Lab04Quiz {

    public static void main(String[] args)
	{
        Student std1 = new Student(100);
        std1.setName("Bill");
        std1.setScore(1, 95);
        std1.setScore(2, 65);
        
        System.out.println(std1.toString());
        
        System.out.println("-------------------");
        System.out.println("Best Scoring : Average");
        System.out.println(std1.getHighScore() + "\t\t" + std1.getAverage());
    }
}
