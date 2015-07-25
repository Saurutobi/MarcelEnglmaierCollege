package pathtriangle;

/**
 *
 * @author Tamwyn Eopia
 */
public class Pathtriangle {

    public static void main(String[] args) {  
        Person one = new Person();
        Person two = new Person();
        Person three = new Person();
        double perim = 0;
        double all = 0;
        int x = 1000000;
        for(int i = 0; i < x; i++)
        {
            for(int j = 0; j < 20; j++)
            {
                one.move();
                two.move();
                three.move();
            }
            perim = Math.sqrt(Math.pow((two.y() - one.y()), 2) + Math.pow((two.x() - one.x()), 2));
            perim = perim + Math.sqrt(Math.pow((three.y() - two.y()), 2) + Math.pow((three.x() - two.x()), 2));
            perim = perim + Math.sqrt(Math.pow((one.y() - three.y()), 2) + Math.pow((one.x() - three.x()), 2));
            all += perim;
            one.zero();
            two.zero();
            three.zero();
        }
        all = all/x;
        System.out.println(all);
    }
}
