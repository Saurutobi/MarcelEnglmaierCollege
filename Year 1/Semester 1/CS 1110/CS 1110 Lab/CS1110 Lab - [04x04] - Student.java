package lab04quiz;

/**
 *
 * @author Marcel Englmaier
 */
public class Student {
    private String name = "";
    private final int SID;
    private float score1;
    private float score2;
    private float highScore;
    
    public Student(int studentID)
	{
        SID = studentID;
    }
	
    public int getStudentID()
	{
        return SID;
    }
	
    public String getName()
	{
        return name;
    }
	
    public void setName(String name)
	{
        this.name = name;
    }
	
    public float getAverage()
	{
        return (score1 + score2)/2;
    }
	
    @Override
    public String toString()
	{
        return "Name: " + name + " SID:" + SID + " test 1 score:" + score1 + " test 2 score:" + score2;
    }
	
    public void setScore(int whichTest, float testScore)
	{
        if(whichTest == 1)
        {
            score1 = testScore;
        }
        else if(whichTest == 2)
        {
            score2 = testScore;
        }
    }
	
    public float getHighScore()
	{
        if(score1 > score2)
        {
            highScore = score1;
        }
        else if(score2 > score1)
        {
            highScore = score2;        
        }         
        return highScore;
    }    
}
