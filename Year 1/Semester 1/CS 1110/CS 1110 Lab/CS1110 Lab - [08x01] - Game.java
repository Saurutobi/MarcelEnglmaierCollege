package Game;

import java.util.*;
import java.io.*;
import java.lang.*;

/**
 *
 * @author Marcel Englmaier
 */
public class Game {

    public static void main(String[] args) throws IOException
	{
        Scanner input = new Scanner("rsc\\input.txt");
        Scanner keyboardInput = new Scanner(System.in);
        String playAGame = "";
        int playTheGame = 0;
        int boardRadius;
        int bullsEyeRadius;
        double distanceToRadius;
		
        System.out.println("please enter board radius");
        boardRadius = keyboardInput.nextInt();
        System.out.println("please enter bulls-eye radius");
        bullsEyeRadius = keyboardInput.nextInt();
        
        Board gameBoard = new Board(boardRadius, bullsEyeRadius);
        Dart dart = new Dart(boardRadius);
        
        System.out.println("Board Radius: " + gameBoard.getRadius() + "     Bulls-Eye Radius: " + gameBoard.getbullsEyeRadius());
        System.out.println("Would you like to play a game of darts? y/n");
        playAGame = keyboardInput.next();
        if((playAGame.equalsIgnoreCase("y")) || (playAGame.equalsIgnoreCase("yes")))
        {
            playTheGame = 1;
        }
		
        while(playTheGame == 1)
        {
            playTheGame = 0;
            dart = new Dart();
            distanceToRadius = Math.sqrt(Math.pow((double)(gameBoard.getRadius() - dart.getX()), 2) + Math.pow((double)(gameBoard.getRadius() - dart.getY()), 2));            
            
            gameBoard = new Board(dart.getX(), dart.getY(), (int)distanceToRadius);
            
            System.out.print("Would You like to play again?");
            playAGame = keyboardInput.next();
            if((playAGame.equalsIgnoreCase("y")) || (playAGame.equalsIgnoreCase("yes")))
            {
                playTheGame = 1;
            }
            if(gameBoard.hit() == 1)
            {
                playTheGame = 0;
                System.out.println("You hit the Bulls-Eye. Congratulations!");
            }
        }
        
        System.out.println("Thank you for playing. Good bye!");
    }
}
