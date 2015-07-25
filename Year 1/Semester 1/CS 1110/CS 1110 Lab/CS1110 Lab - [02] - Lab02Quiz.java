package lab02quiz;

import java.util.Scanner;
/**
 *
 * @author Marcel Englmaier
 */
public class Lab02Quiz {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Application which calculates an investment worth after specified amount of years:");
        System.out.print("Please enter the original investment($): ");
        double principal = Float.parseFloat(scanner.next());
        System.out.print("Please enter the Interest rate(%): ");
        float interestrate = Float.parseFloat(scanner.next());
        System.out.print("Please enter the number of years invested: ");
        int yearsinvested = Integer.parseInt(scanner.next());
        
        double finalworth;
        float powerfunctionvar;     //is x in Math.pow(x,y);
        
        powerfunctionvar = (1 + (interestrate / 100));
        finalworth = principal * (Math.pow(powerfunctionvar, yearsinvested));
        
        System.out.println("");
        System.out.println("Investing $" + principal + " at an interest rate of " + interestrate + "% for " + yearsinvested + " years will have a final worth of $" + finalworth + ".");
                
        System.exit(0);
    }
}
