
package puzzle8;

import java.util.ArrayList;
import java.lang.Math;

//Author: Marcel Englmaier
public class boardNav
{
    private ArrayList<board> openStates = new ArrayList<board>(); //list of open states
    private ArrayList<board> closeStates = new ArrayList<board>(); //list of closed states
	
    private board Goal; //the goal
    private board State; //the current working states
	
    private int HSelect;
	
    public boardNav(int[][] goal, int[][] start, int hselect)
    {
        Goal = new board(goal, 0); 
	State = new board (start, 0);
	openStates.add(State);//add first element to open
	HSelect = hselect;
    }

    public void getChildren()
    {
        ArrayList<board> children = new ArrayList<board>(); //children of state
	children = State.getChildren();//gets all posible children of state
	closeStates.add(State); // add current working state to closed
	openStates.remove(State);//remove current working state from open
	int[][] closedtest; //test potential children
	int[][] closedboards;//test closedboard vs potential children
	int duplicates = 0;//counts the number of duplicates
	boolean different; //true if child state is inclosed array
	for(int i = 0; i < children.size(); i++) 
	{
            different = true;//start with them being differnt
            closedtest = children.get(i).getPuzzle(); //gets the puzzle of one child
            for(int j = 0; j < closeStates.size(); j++)//loop thought each closed puzzle
            {
                duplicates = 0;
		closedboards = closeStates.get(j).getPuzzle();//gets single closed puzzle
		for(int k = 0; k < 3; k++) // loop though each location
		{
                    for(int l = 0; l < 3; l++)
                    {
                        if(closedtest[k][l] == closedboards[k][l])
                        {
                            duplicates++;//if they are they same add to duplicates
			}
                    }
		}
		if(duplicates == 9)
                { //if there are 9 duplicates then its the same board as one that is already closed
                    different = false;
		}
            }
            if(different == true)//other wise the two boards are different
            {
                openStates.add(children.get(i));//add all children to open states
            }
	}
	getPath();//Continue to find a path
    }
    
    public int H(board state)
    {
        int h = 0;
	int [][] Currentboard = state.getPuzzle();//get current state
	int [][] Goalboard = Goal.getPuzzle(); //get goal state
	
        /*
        if(HSelect==1)
        {
            for(int i=0;i<3;i++)
            {
                for(int j=0;j<3;j++)
                {
                    if(Currentboard[i][j]!=Goalboard[i][j])
                    {
                        h++; //counts the number of spots out of place
                    }
		}
            }
	}
        */
        
	if(HSelect == 2)
        {
            int Value; //value of one coordinate in Currentboard
            int k = 0; 
            int l = 0;
            for(int i = 0; i < 3; i++)
            {
                for(int j = 0; j < 3; j++)
                {
                    Value = Currentboard[i][j];
                    l = 0;
                    k = 0;
                    while(Value != Goalboard[k][l - 3 * k])
                    { //search for the location of each value in the goal state
                        l++;
			k = l / 3;
                    }
                    h += Math.abs(i - k) + Math.abs(j - (l - k * 3)); // the distance from the goal to current state
		}
            }
	}
	return h;//return the hurestic
    }

    public int G(board state)
    {
        return state.getG();
    }
    
    public int F(board state)
    {
	return G(state) + H(state);
    }
    
    public void getPath()
    {
        ArrayList<Integer> f = new ArrayList<Integer>();
	int Smallest; //find the the smallest f() value
	int SmallIndex = 0;//the index of the smallest f value
	int [][] current; //the current puzzle
	int [][] goal = Goal.getPuzzle();//the goal puzzle
	int duplicates = 0;//number of duplicates
	boolean goalfound = false; //test if one of the open states is the goal
	for(int i = 0; i < openStates.size(); i++)
        { //loop though each open state
            duplicates = 0;
            current = openStates.get(i).getPuzzle();//get the one of the open puzzles
            for(int j = 0; j < 3; j++)
            {//test if its the goal state
                for(int k = 0; k < 3; k++)
                {
                    if(current[j][k] == goal[j][k])
                    {
                        duplicates++;
                    }
		}
            }
            if(duplicates == 9)
            {//if they are the same then the goal is found
                goalfound = true;
                State = new board(current, 0);
		closeStates.add(State);//add the goal state to closedStates
            }
            f.add(F(openStates.get(i)));//get the f value for each open state
        }
	Smallest = f.get(0); //find the state with the smallest f value
	for(int i = 1; i < f.size(); i++)
        {
            if(f.get(i) < Smallest)
            {
                Smallest = f.get(i); //test for smallest fvalue
                SmallIndex = i;//the index for the board with the smallest fvalue
            }	
        }
	if(goalfound == false)//if the goal is not in the opensates get mre children
        {
            State = openStates.get(SmallIndex);//current state is now the open state with the smallest fvalue
            getChildren();//get the children of the new state
	}	
    }

    public ArrayList<board> returnPath()
    {
        getPath();
	return closeStates;//return the states that we navigated to
    }	
}
