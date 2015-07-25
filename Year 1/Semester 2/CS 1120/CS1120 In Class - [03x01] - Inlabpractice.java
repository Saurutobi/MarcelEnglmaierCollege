package inlabpractice;

/**
 *
 * @author Marcel Englmaier
 */

public class Inlabpractice
{

    public static void main(String[] args) {
        Talkative [] objects = new Talkative[10];
        objects[0] = new Human("I LOVE JAVA!1");
        objects[1] = new Dog();
        objects[2] = new Human("I LOVE JAVA!2");
        objects[3] = new Dog();
        objects[4] = new Human("I LOVE JAVA!3");
        objects[5] = new Dog();
        objects[6] = new DialUpModem();
        objects[7] = new DialUpModem();
        objects[8] = new DialUpModem();
        objects[9] = new DialUpModem();
        int i = 0;
        for(i = 0; i< objects.length; i++)
        {
            System.out.println(objects[i].speak());
        }
    }
}
