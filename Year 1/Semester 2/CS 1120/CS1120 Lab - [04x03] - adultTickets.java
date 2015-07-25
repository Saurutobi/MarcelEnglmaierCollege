package la4_englmaier;

/**
 *
 * @author Marcel Englmaier
 */
public class adultTickets  implements RevenueCalculator{

    int adultTicketAmount;
    double adultTicketPrice;
    double adultGrossRevenue;
    double adultNetRevenue;
    
    public adultTickets(int tickets, double price)
    {
        adultTicketAmount = tickets;
        adultTicketPrice = price;
    }
    
    public double calculateGrossRevenue()
    {
        adultGrossRevenue = adultTicketAmount * adultTicketPrice;
        return adultGrossRevenue;
    }
    
    public double calculateNetRevenue()
    {
        if(adultTicketAmount <= 100)
        {
            adultNetRevenue = adultGrossRevenue * .2;
        }
        else
        {
            adultNetRevenue = adultGrossRevenue * .1;
        }
        return adultNetRevenue;
    }
}