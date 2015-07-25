package test01;
import javax.swing.JOptionPane;

 /*
 * @author Marcel Englmaier
 */
public class Test01 {

    public static void main(String[] args) {

        double d;
        String str;
        str = JOptionPane.showInputDialog("enter a double");
        System.out.println("you entered " + str);
        d=Double.parseDouble(str);
        System.out.println("as a bouble " + d);
        
        System.exit(0);
    }
}
