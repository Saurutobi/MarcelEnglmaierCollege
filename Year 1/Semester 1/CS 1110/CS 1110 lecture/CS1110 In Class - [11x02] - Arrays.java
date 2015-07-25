package arrays;

import java.util.*;

/**
 *
 * @author Marcel Englmaier
 */
public class Arrays {

    public static void main(String[] args) {
        /*
        double [] student = new double[28000];
        int ia = 0;
        Scanner s = new Scanner(System.in);
        while(ia < 28000)
        {
            student[ia] = s.nextDouble();
            ia++;
        }
        
        double [] big = new double[320000000];
        int ib;
        for(ib=0; ib < big.length; ib++)
        {
            
        }
        */
        String [] aos = new String[6];
        String ans;
        int ic;
        Scanner key = new Scanner(System.in);
        for(ic = 0; ic < 6; ic++)
        {
            System.out.print("Enter String:");
            aos[ic] = key.nextLine();
        }
        for(ic = 0; ic < 6; ic++)
        {
            System.out.print(aos[ic] + " ");
            System.out.println("");
        }
        System.out.print("enter string to search for");
        ans = key.nextLine();
        ic = 0;
        while(ic < 6 && ans.equalsIgnoreCase(aos[ic]))
        {
            ic++;
        }
        if(ic < 6)
        {
            System.out.println("found at: " + ic);
        }
        else
        {
            System.out.println("Not Found");
        }
    }
}
