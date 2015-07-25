package la4_englmaier;

/**
 *
 * @author Marcel Englmaier
 */
public class childTickets implements RevenueCalculator
{
    int childTicketAmount;
    double childTicketPrice;
    double childGrossRevenue;
    double childNetRevenue;
    
    public childTickets(int tickets, double price)
    {
        childTicketAmount = tickets;
        childTicketPrice = price;
    }
    
    public double calculateGrossRevenue()
    {
        childGrossRevenue = childTicketAmount * childTicketPrice;
        return childGrossRevenue;
    }
    
    public double calculateNetRevenue()
    {
            childNetRevenue = childGrossRevenue * .25;
        
        return childNetRevenue;
    }
}