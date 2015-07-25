package Randy;

import java.util.*;
        
/**
 *
 * @author Marcel Englmaier
 */
public class Randy {

    public static void main(String[] args) {
        Random randy = new Random(10);
        Random r2 = new Random(10);
        int i, j;
        i = randy.nextInt(6) + 1;
        j = r2.nextInt(6) + 1;
        System.out.println(i + "" + j);
        
    }
}
