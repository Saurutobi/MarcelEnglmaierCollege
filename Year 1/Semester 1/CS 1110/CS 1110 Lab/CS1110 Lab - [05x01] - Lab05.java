// Program: <Lab 05> File: <Lab05.java; Rocketship.java>
//
// Problem: <The program's purpose is to simulate the interaction between a robot selling spaceships and the human buying the spaceship.>
//
// Programmer(s): <Marcel Englmaier>
// Course: CS1110 <Fall 2011> <M/W 4:30-5:45>
// Lab: <M 12:30-2:20>

// Problem Requirement Analysis (Step 1 - What?) 
//
// Ref.: <List short references to algorithms, program segments, etc.>
// Revision History:
// Released: <10/23/11> <ME>
// Reason: due date
// <10/10/11, Marcel Englmaier, Documentation, lab requirements, code outline with important code commented due to incompletion, began project>
// <10/20/11, Marcel Englmaier, Documentation, lab requirements, completed everything but found a loop issue with the booleans>
// <10/23/11, Marcel Englmaier, Documentation, lab requirements, finished program, fixed loop issue by switching from booleans to integers>

// Input:   <string, keyboard input for rocketship name>
//          <int, keyboard input for rocketship number of engines>
//          <int, keyboard input for rocketship number of shields>
//          <int, keyboard input for rocketship number of weapons>
// Output:  <string, gives summary of transaction>
//          <double, the cost of the rocketship>
//          <rocketship properties, outputted to summarize transaction

// Functions, Methods, Operations, Tasks: 
// <if fuction to test for answer>
// <while loop to sell rocketship>
// <if, switch case, and while functions to test if user input is correct and to assign values to variables>
// <if function to determine cost>
// <switch case function to assign protocol droids>
// <if function to test for answer to repeat while loop>

// Problem Design – Quick Algorithm (Step 2 - How?) 
// 
// 1. <program makes variables to use>
// 2. <interactions between the 'robot' and the user to choose rocketship properties>
// 3. <program makes a rocketship according to properties>
// 4. <outputs results of transactions>


package Main;

import java.util.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
/**
 *
 * @author Marcel Englmaier
 */
public class Lab05 {

    
    public static void main(String[] args){
        //Initial statement:
        System.out.println("CS1110 Fall 2011, Lab05, Marcel Englmaier.\n");
        
        //Makes a scanner
        Scanner scanner = new Scanner(System.in);
        
        //variable declarations
        int purchaseAnotherRocketship = 0;     //integer is used because for some unknown reason a boolean did not work
        String rocketshipName = "";
        int numberOfShields = 0;
        int numberOfWeapons = 0;
        int numberOfEngines = 0;
        double cost;
        Random randomMoney = new Random(23567542);
        char quote = '"';
        String shouldIMakeARocketship;
        
        //initial rocketship created
        Rocketship rocketship = new Rocketship(rocketshipName, numberOfShields, numberOfWeapons, numberOfEngines);
        
        //tests for user input to make a rocketship
        System.out.println("Would you like to buy a rocket ship? (Y/N)");
        shouldIMakeARocketship = scanner.nextLine(); 
        if(shouldIMakeARocketship.equalsIgnoreCase("Yes") || shouldIMakeARocketship.equalsIgnoreCase("Y"))
        {
            purchaseAnotherRocketship = 1;
        }
        
        //begin rocketship creation
        while(purchaseAnotherRocketship == 1)
            {   
                //asks for and assigns basic rocketship properties
                System.out.println("What would you like to name your Rocketship?");
                rocketshipName = scanner.next();
                System.out.println("How many shields would you like your rocketship to have?(1, 2, or 0)");
                numberOfShields = scanner.nextInt();
                if(!(numberOfShields == 1) && !(numberOfShields == 2) && !(numberOfShields == 0))
                {
                    System.out.println("Since you did not enter a 1, 2, or 0, we will give your rocketship 1 shield");
                    numberOfShields = 1;
                }
                System.out.println("How many weapons would you like your rocketship to have?(1, 2, or 0)");
                numberOfWeapons = scanner.nextInt();
                if(!(numberOfWeapons == 1) && !(numberOfWeapons == 2) && !(numberOfWeapons == 0))
                {
                    System.out.println("Since you did not enter a 1, 2, or 0, we will give your rocketship 1 gun");
                    numberOfWeapons = 1;
                }
                System.out.println("How many engines would you like your rocketship to have(1 or 2)");
                numberOfEngines = scanner.nextInt();
                while(!(numberOfEngines == 1) && !(numberOfEngines == 2))
                {
                    System.out.println("Please enter a 1, 2");
                    numberOfEngines = scanner.nextInt();
                }
                
                //makes a rocketship and gives properties
                rocketship = new Rocketship(rocketshipName, numberOfShields, numberOfWeapons, numberOfEngines);
                
                //Protocol Droids assignment
                switch(rocketship.getNumberOfProtocolDroids())
                {
                  case 1:
                       System.out.println("We're giving your rocketship an extra protocol droid for good luck");
                       break;
                  case 2:
                       System.out.println("We're giving your rocketship two extra protocol droids for good luck");
                       break;
                
                  case 3:
                       System.out.println("We're giving your rocketship three extra protocol droids for good luck");
                       break;
                }
                
                //gives summary of Rocketship and its properties
                System.out.println("Rocketship: " + quote + rocketship.getName() + quote + " has:");
                System.out.println(rocketship.getNumberOfShields() + " shield(s) and");
                System.out.println(rocketship.getNumberOfGuns() + " guns(s) and ");
                System.out.println(rocketship.getNumberOfEngines() + " engine(s)");
                switch(rocketship.getNumberOfProtocolDroids())
                {
                    case 1: 
                        System.out.println("It also has 1 extra protocol droid");
                        break;
                    case 2: 
                        System.out.println("It also has 2 extra protocol droid");
                        break;
                    case 3: 
                        System.out.println("It also has 3 extra protocol droid");
                        break;
                }
                
                //assigns a random cost from 0.0d to 1.0d and then multiplies by 100 to make range 0.0d to 100.0d
                cost = randomMoney.nextDouble() * 100; 
                
                //declares cost variable formatter
                NumberFormat priceFormatter = new DecimalFormat("#00.00");
                
                //determines cost of rocketship on random principles and formats cost during output
                if(cost > 20.0)
                {
                    cost = 20.0;
                    System.out.println("Rocketship " + quote + rocketship.getName() + quote + " costs " + priceFormatter.format(cost) + " Billion space credits");
                }
                else if(cost < 3)
                {
                    cost = 3.0;
                    System.out.println("Rocketship " + quote + rocketship.getName() + quote + " costs " + priceFormatter.format(cost) + " Billion space credits");
                }
                else
                {
                    System.out.println("Rocketship " + quote + rocketship.getName() + quote + " costs " + priceFormatter.format(cost) + " Billion space credits");
                }
                
                //Asks for another rocketship
                System.out.println("Would you like to purchase another Rocketship(Y/N)");
                shouldIMakeARocketship = scanner.next();
                if(shouldIMakeARocketship.equalsIgnoreCase("No") || shouldIMakeARocketship.equalsIgnoreCase("N"))
                {
                    purchaseAnotherRocketship = 0;
                }
            }
        
        //thank you statements and end of output
        System.out.println("Thank you for visiting the Rocketship Emporium");
        System.out.println("You have purchased " + Rocketship.getCount() + " rocketship(s) today.");
        System.out.println("Have a great day\n\nEnd of Output");
    }
}
