
package towersofhanoi;
import java.util.ArrayList;
import java.util.Arrays;


public class Rules {
	private ArrayList<State> Visited;
	public Rules(State start){
		Visited = new ArrayList<State>();
		Visited.add(start);	
		}
	private ArrayList<Integer> prunePreviousState(ArrayList<Integer> availableMoves, State WorkingMemory)//Test if state has already been visited
	{ 
		State testState = null;
		Boolean visited=false; 
		for(int i=0; i< availableMoves.size();i++){
			//Get what the state will be for each rule fired
			if(availableMoves.get(i)==1){
				testState =	rule1(WorkingMemory, false);
			}
			if(availableMoves.get(i)==2){
				testState =	rule2(WorkingMemory, false);
			}
			if(availableMoves.get(i)==3){
				testState =	rule3(WorkingMemory, false);
			}
			if(availableMoves.get(i)==4){
				testState =	rule4(WorkingMemory, false);
			}
			if(availableMoves.get(i)==5){
				testState =	rule5(WorkingMemory, false);
			}
			if(availableMoves.get(i)==6){
				testState =	rule6(WorkingMemory, false);
			}
			//if its one of the state already visited remove rule from list
			for(int j=0;j<Visited.size();j++){
				
				if(Arrays.equals(testState.getA(), Visited.get(j).getA()) 
				&& Arrays.equals(testState.getB(), Visited.get(j).getB()) 
				&& Arrays.equals(testState.getC(), Visited.get(j).getC()))
				{
					visited=true;
					
				}
				
				
			}
			//if the state has been visited remove it
			if(visited == true){
				availableMoves.remove(i);
				visited =false;
			}
		}
		
		
		
		return availableMoves;
	}
	public ArrayList<Integer> ruleSelection(State WorkingMemory)
	{
		int [] A;
		int [] B;
		int [] C;
		int aIndex=2, bIndex=2, cIndex=2; //Location of the highest ring on each peg
		ArrayList<Integer> availableMoves = new ArrayList<Integer>();
		
		//Get the peg data
		A = WorkingMemory.getA();
		B = WorkingMemory.getB();
		C = WorkingMemory.getC();
		//Find where the top of the peg is
		while(A[aIndex] == 0 && aIndex > 0){
			aIndex--;
		}
		while(B[bIndex] ==0 && bIndex > 0){
			bIndex--;
		}
		while(C[cIndex] ==0 && cIndex > 0){
			cIndex--;
		}
		//Find legal moves
		//A
		if(A[aIndex]>0){ //A peg is not empty
			if(A[aIndex] < C[cIndex] || C[cIndex]==0) //if A's peg is smaller then C's or C is empty 
			{
				availableMoves.add(1);
			}
		
			if(A[aIndex] < B[bIndex] || B[bIndex]==0)//if A's peg is smaller then B's or B is empty 
			{
				availableMoves.add(2);
			}
		}
		//B
		if(B[bIndex]>0){ //B peg is not empty
			if(B[bIndex] < C[cIndex] || C[cIndex] ==0){ //if B's peg is smaller then C's or C is empty 
				availableMoves.add(3);
			}
			if(B[bIndex] < A[aIndex] || A[aIndex] ==0){//if B's peg is smaller then A's or A is empty 
				availableMoves.add(4);
			}
			
		}
		//C
		if(C[cIndex]>0){ //C peg is not empty
			if(C[cIndex] < A[aIndex] || A[aIndex] ==0){//if C's peg is smaller then A's or A is empty 
				availableMoves.add(5);
			}
			if(C[cIndex] < B[bIndex] || B[bIndex] ==0){//if A's peg is smaller then B's or B is empty
				availableMoves.add(6);
			}
			
		}
		
		//Remove any thats leads to a state already visited
		prunePreviousState(availableMoves,WorkingMemory);
		//Return available moves
		return availableMoves;
		
		
	}
	public State rule1(State WorkingMemory, Boolean fireRule) // A->C
	{

		int [] A = (int[])WorkingMemory.getA().clone();
		int [] C = (int[])WorkingMemory.getC().clone();
		int aIndex=2, cIndex=2;
		State nextState;
		//Find top of A peg
		while(A[aIndex] == 0 && aIndex > 0){
			aIndex--;
		}
		//Find top of C peg
		while(C[cIndex] ==0 && cIndex > 0){
			cIndex--;
		}
		if(C[cIndex] == 0){
			cIndex--;
		}
		//Move from Top of A to Top of C
		C[cIndex+1] = A[aIndex];
		A[aIndex] = 0;
		
		nextState = new State(A,WorkingMemory.getB(),C);
		if(fireRule == true){
			Visited.add(nextState);
		}
		
		return nextState;
		
	}
	public State rule2(State WorkingMemory, Boolean fireRule) // A->B
	{
		int [] A = (int[])WorkingMemory.getA().clone();
		int [] B = (int[])WorkingMemory.getB().clone();
		int aIndex=2, bIndex=2;
		State nextState;
		//Find top of A peg
		while(A[aIndex] == 0 && aIndex > 0){
			aIndex--;
		}
		//Find top of B peg
		while(B[bIndex] ==0 && bIndex > 0){
			bIndex--;
		}
		if(B[bIndex] == 0){
			bIndex--;
		}
		//Move from Top of A to Top of B
		B[bIndex+1] = A[aIndex];
		A[aIndex] = 0;
		
		nextState = new State(A,B,WorkingMemory.getC());
		if(fireRule == true){
			Visited.add(nextState);
		}
		
		
		return nextState;
		
	}
	public State rule3(State WorkingMemory, Boolean fireRule)// B->C
	{
		int [] B = WorkingMemory.getB().clone();
		int [] C = WorkingMemory.getC().clone();
		int bIndex=2, cIndex=2;
		State nextState;
		//Find top of B peg
		while(B[bIndex] == 0 && bIndex > 0){
			bIndex--;
		}
		//Find top of C peg
		while(C[cIndex] ==0 && cIndex > 0){
			cIndex--;
		}
		if(C[cIndex] == 0){
			cIndex--;
		}
		//Move from Top of B to Top of C
		C[cIndex+1] = B[bIndex];
		B[bIndex] = 0;
		
		nextState = new State(WorkingMemory.getA(),B,C);
		if(fireRule == true){
			Visited.add(nextState);
		}
		
		return nextState;
		
	}
	public State rule4(State WorkingMemory, Boolean fireRule)// B->A
	{
		int [] B = WorkingMemory.getB().clone();
		int [] A = WorkingMemory.getA().clone();
		int bIndex=2, aIndex=2;
		State nextState;
		//Find top of B peg
		while(B[bIndex] == 0 && bIndex > 0){
			bIndex--;
		}
		//Find top of A peg
		while(A[aIndex] ==0 && aIndex > 0){
			aIndex--;
		}
		if(A[aIndex] == 0){
			aIndex--;
		}
		//Move from Top of B to Top of A
		A[aIndex+1] = B[bIndex];
		B[bIndex] = 0;
		
		nextState = new State(A,B,WorkingMemory.getC());
		if(fireRule == true){
			Visited.add(nextState);
		}
		
		return nextState;
	}

	public State rule5(State WorkingMemory, Boolean fireRule) //C->A
	{
		int [] C = WorkingMemory.getC().clone();
		int [] A = WorkingMemory.getA().clone();
		int cIndex=2, aIndex=2;
		State nextState;
		//Find top of C peg
		while(C[cIndex] == 0 && cIndex > 0){
			cIndex--;
		}
		//Find top of A peg
		while(A[aIndex] ==0 && aIndex > 0){
			aIndex--;
		}
		if(A[aIndex] == 0){
			aIndex--;
		}
		//Move from Top of C to Top of A
		A[aIndex+1] = C[cIndex];
		C[cIndex] = 0;
		
		nextState = new State(A,WorkingMemory.getB(),C);
		if(fireRule == true){
			Visited.add(nextState);
		}
		
		return nextState;
	}
	public State rule6(State WorkingMemory, Boolean fireRule) //C->B
	{
		int [] C = WorkingMemory.getC().clone();
		int [] B = WorkingMemory.getB().clone();
		int cIndex=2, bIndex=2;
		State nextState;
		//Find top of C peg
		while(C[cIndex] == 0 && cIndex > 0){
			cIndex--;
		}
		//Find top of B peg
		while(B[bIndex] ==0 && bIndex > 0){
			bIndex--;
		}
		if(B[bIndex] == 0){
			bIndex--;
		}
		//Move from Top of C to Top of B
		B[bIndex+1] = C[cIndex];
		C[cIndex] = 0;
		
		nextState = new State(WorkingMemory.getA(),B,C);
		if(fireRule == true){
			Visited.add(nextState);
		}
		
		return nextState;
		
	}
	
}
