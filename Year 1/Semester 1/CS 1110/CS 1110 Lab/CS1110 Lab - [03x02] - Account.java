package Main;

/**
 *
 * @author Marcel Englmaier
 */
public class Account {
    private double balance;
    private boolean insufficientFunds;
    private String test;
    
    public Account(double bal)
    {
		balance = bal;
    }
	
    public void deposit(double bal)
    {
        balance = balance + bal;
    }
	
    public boolean withdraw(double withdraw)
    {
        if(balance - withdraw <= 0)
        {
            return false;
        }
        else
        {
            balance = balance - withdraw;
            return true;
        }
    }
    
    public double getBalance()
    {
        return balance;

    }
}
