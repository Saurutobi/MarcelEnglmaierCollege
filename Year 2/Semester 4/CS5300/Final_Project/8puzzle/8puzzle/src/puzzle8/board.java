package puzzle8;

import java.util.ArrayList;

//Author: Marcel Englmaier
public class board
{
    private int Puzzle[][];//the current puzzle position
    private int G;//the gvalue ie:  how deep the search is
    public board(int puzzle[][], int g)
    {
        Puzzle = puzzle;
        G = g;
    }
    
    public int[][] getPuzzle()
    {
        return Puzzle;
    }
    
    public int getG()
    {
        return G;
    }
    
    public ArrayList<board> getChildren()
    {
        ArrayList<board> Children = new ArrayList<board>();
        int[][] Upstate = new int[3][3]; //the state if you moved up
        int[][] Downstate = new int[3][3];//the state if you moved down
        int[][] Leftstate = new int[3][3]; //the state if you moved left
        int[][] Rightstate = new int[3][3];//the state if you move right
        board tempstate;
        int temp;
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                if(Puzzle[i][j] == 0)
                {
                    if(i < 2)
                    {
                        temp = Puzzle[i + 1][j];//store the value above the 0
                        for(int q = 0; q < 3; q++)
                        { //copy the puzzle to its child 
                            for(int w = 0; w < 3; w++)
                            {
                                Upstate[q][w] = Puzzle[q][w];
                            }
                        }
                        Upstate[i + 1][i] = 0; // change the value above 0 to 0
                        Upstate[i][j] = temp;
                        tempstate = new board(Upstate, G + 1);//create a new board that is one deeper
                        Children.add(tempstate); //add the new board to children
                    }
                    if(i > 0)
                    {
                        //move down
                        temp = Puzzle[i - 1][j];//store the value above the 0
                        for(int q = 0; q < 3; q++)
                        {//copy the puzzle to its child 
                            for(int w = 0; w < 3; w++)
                            {
                                Downstate[q][w] = Puzzle[q][w];
                            }
                        }
                        Downstate[i - 1][j] = 0;// change the value above 0 to 0
                        Downstate[i][j] = temp;
                        tempstate = new board(Downstate, G + 1);//create a new board that is one deeper
                        Children.add(tempstate);//add the new board to children
                    }
                    if(j > 0)
                    {
                        //move left
                        temp = Puzzle[i][j - 1];//store the value above the 0
                        for(int q = 0; q < 3; q++)
                        {
                            for(int w = 0; w < 3; w++)
                            {
                                Leftstate[q][w] = Puzzle[q][w];
                            }
                        }
                        Leftstate[i][j - 1] = 0;// change the value above 0 to 0
                        Leftstate[i][j] = temp;
                        tempstate = new board(Leftstate, G + 1);//create a new board that is one deeper
                        Children.add(tempstate);//add the new board to children
                    }
                    if(j < 2)
                    {
                        //move right
                        temp = Puzzle[i][j + 1];//store the value above the 0
                        for(int q = 0; q < 3; q++)
                        {//copy the puzzle to its child 
                            for(int w = 0; w < 3; w++)
                            {
                                Rightstate[q][w] = Puzzle[q][w];
                            }
                        }
                        Rightstate[i][j + 1] = 0;// change the value above 0 to 0
                        Rightstate[i][j] = temp;
                        tempstate = new board(Rightstate, G + 1);//create a new board that is one deeper
                        Children.add(tempstate);//add the new board to children
                    }
                }
            }
        }
        return Children; //return each child of this state
    }
}
