package lab06quiz;

import java.util.*;

/**
 *
 * @author Marcel Englmaier
 */
public class Diamond {

    public static void main(String[] args) {
        
        int character = 0;
        int drawSize = 0;
        char drawingCharacter = 'a';
        Scanner scanner = new Scanner(System.in);
        
        while(!(character == 5))
        {
            System.out.println("Let us make a Diamond.");
            System.out.println("Please define the character you would like to use.");
            System.out.println("    0=default character('o'), 1= '*' , 2= 'x' , 3= '.' , 4= '+' ");
            System.out.println("        Press 5 to exit program");
            character = scanner.nextInt();
            if(character == 0)
            {
                drawingCharacter = 'o';
            }
            if(character == 1)
            {
                drawingCharacter = '*';
            }
            if(character == 2)
            {
                drawingCharacter = 'x';
            }
            if(character == 3)
            {
                drawingCharacter = '.';
            }
            if(character == 4)
            {
                drawingCharacter = '+';
            }
            if(character == 5)
            {
                break;
            }
            
            System.out.println("Please define the size of your diamond.");
            System.out.println("    0=default size, 1= create a random size, 2-20= the size of your pyramid");
            drawSize = scanner.nextInt();
            Draw drawPyramid = new Draw(drawingCharacter, drawSize);
        }
        
        System.out.println("Thank you\nBye!");
        
        System.exit(0); 
    }
}
