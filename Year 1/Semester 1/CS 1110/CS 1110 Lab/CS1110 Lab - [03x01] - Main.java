// Program: <Lab 03> File: <Main.java; Account.java>
//
// Problem: <The program's purpose is to handle two accounts, input money, and deposit money, and to check whether this works.>
//
// Programmer(s): <Marcel Englmaier>
// Course: CS1110 <Fall 2011> <M/W 4:30-5:45>
// Lab: <M 12:30-2:20>

// Problem Requirement Analysis (Step 1 - What?) 
//
// Ref.: <List short references to algorithms, program segments, etc.>
// Revision History:
// Released: <10/9/11> <ME>
// Reason: due date
// <9/27/11, Marcel Englmaier, Entire program, Began Project>
// <10/9/11, Marcel Englmaier, Documentation, lab requirements, finished progran>

// Input:   <double, Account.deposit to "balance">
//          <double, Account.withdraw to "balance">
// Output:  <string, gives summary of transaction>
//          <double, the balance is outputted>

// Functions, Methods, Operations, Tasks: 
// <Addition, two numbers being added>
// <Subtraction, tww numbers being subtracted>
// <true/false tests, tests if withdrawal is possible>


// Problem Design – Quick Algorithm (Step 2 - How?) 
// 
// 1. <program makes two objects>
// 2. <deposits and withdraws (transactions)>
// 3. <outputs results of transactions>

package Main;

/**
 *
 * @author Marcel Englmaier
 */
public class Main {

    
    public static void main(String[] args) {
        
        //initial identifier
        System.out.println("CS1110 Fall 2011, Lab03, Marcel Englmaier.\n");
        
        //create the empty checking account
        Account checking = new Account(0);
        
        //create the savings account with $3.00 deposit
        Account savings = new Account(3.00);
        
        //print intitial balances
        System.out.println("Checking: " + checking.getBalance() + "\nSavings: " + savings.getBalance());
        
        //deposit some money
        checking.deposit(5.00);
        
        //Print transaction with balance
        System.out.println("deposit $5.00 into checking.\nNew Balance: " + checking.getBalance());
        
        //deposit some money
        savings.deposit(12.00);
        
        //Print transaction with balance
        System.out.println("deposit $12.00 into savings.\nNew Balance: " + savings.getBalance());
        
        //withdraw some money and print it
        if(checking.withdraw(3))
        {
            System.out.println("withdraw $3.00 from savings.\nNew Balance:" + checking.getBalance());
        }
        else
        {
            System.out.println("withdraw $3.00: insufficient funds in account");
        }
        
        //withdraw some money and print it
        if(checking.withdraw(1234567))
        {
            System.out.println("withdraw $1234567 from savings.\nNew Balance:" + checking.getBalance());
        }
        else
        {
            System.out.println("withdraw $1234567: insufficient funds in account");
        }
        
        System.out.println("\nend of output");
    }
}
