package puzzle8;

import java.util.ArrayList;

//Author: Marcel Englmaier
class puzzle
{
    public static void main(String[] args)
    {
	//create goal and start arrays
	int goal[][] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
	int start[][] = {{3, 0, 6}, {2, 1, 7}, {5, 4, 8}};
	int[][] state;
	ArrayList<board> path;
        int HSelect = 2; //this determines speed
	
	boardNav Nav = new boardNav(goal, start, HSelect); //Initialize navigation
	path = Nav.returnPath(); //get the path from start -> goal
	for(int i = 0; i < path.size(); i++)
        {
            state = path.get(i).getPuzzle(); //get the array for each board
            for(int j = 0; j < 3; j++)
            {//print board
		for(int k = 0; k < 3; k++)
                {
                    System.out.print(state[j][k]);
		}
		System.out.println();
            }
            System.out.println();
	}	
    }
}
