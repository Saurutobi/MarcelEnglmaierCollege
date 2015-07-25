
package towersofhanoi;

import java.util.ArrayList;
import java.util.Arrays;


public class Control {
	State workingMemory;
	State goal;
	Rules rule;
	ArrayList<Integer> hueristics;
	public Control(State start, State Goal){
		workingMemory = start;
		rule = new Rules(start);
		goal = Goal;
		printWorkingMemory();
	}
	public void Naviage(){ //Main control
		ArrayList<Integer> legalRules = new ArrayList<Integer>();
		State testRule;
		while(!(Arrays.equals(workingMemory.getA(), goal.getA()) //While Working Memory is not the goal
		   && Arrays.equals(workingMemory.getB(), goal.getB()) 
		   && Arrays.equals(workingMemory.getC(), goal.getC())))
		{
			hueristics = new ArrayList<Integer>();
			legalRules = new ArrayList<Integer>(); // Get the legal rules
			legalRules = rule.ruleSelection(workingMemory);
		for(int i=0; i<legalRules.size();i++){
			if(legalRules.get(i)==1){  //Fire the rule
				//workingMemory = rule.rule1(workingMemory, true);
				testRule = rule.rule1(workingMemory, false);
				heuristic(testRule);
			}
			if(legalRules.get(i)==2){
				//workingMemory = rule.rule2(workingMemory, true);
				testRule = rule.rule2(workingMemory, false);
				heuristic(testRule);
			}
			if(legalRules.get(i)==3){
				//workingMemory = rule.rule3(workingMemory, true);
				testRule = rule.rule3(workingMemory, false);
				heuristic(testRule);
			}
			if(legalRules.get(i)==4){
				//workingMemory = rule.rule4(workingMemory, true);
				testRule = rule.rule4(workingMemory, false);
				heuristic(testRule);
			}
			if(legalRules.get(i)==5){
				//workingMemory = rule.rule5(workingMemory, true);
				testRule = rule.rule5(workingMemory, false);
				heuristic(testRule);
			}
			if(legalRules.get(i)==6){
				//workingMemory = rule.rule6(workingMemory, true);
				testRule = rule.rule6(workingMemory, false);
				heuristic(testRule);
			}
		}
			sortByHeuristic(legalRules);
			
			if(legalRules.get(0)==1){  //Fire the rule
				System.out.println("Rule 1 Fired");
				workingMemory = rule.rule1(workingMemory, true);
			}
			if(legalRules.get(0)==2){
				System.out.println("Rule 2 Fired");
				workingMemory = rule.rule2(workingMemory, true);
			}
			if(legalRules.get(0)==3){
				System.out.println("Rule 3 Fired");
				workingMemory = rule.rule3(workingMemory, true);
			}
			if(legalRules.get(0)==4){
				System.out.println("Rule 4 Fired");
				workingMemory = rule.rule4(workingMemory, true);
			}
			if(legalRules.get(0)==5){
				System.out.println("Rule 5 Fired");
				workingMemory = rule.rule5(workingMemory, true);

			}
			if(legalRules.get(0)==6){
				System.out.println("Rule 6 Fired");
				workingMemory = rule.rule6(workingMemory, true);
			}
			printWorkingMemory();
			
		}
		System.out.println("Goal!!!");
	}
	private void printWorkingMemory(){ //Prints the Current Working Memory
		int[] A = workingMemory.getA();
		int[] B = workingMemory.getB();
		int[] C = workingMemory.getC();
		for(int i=2; i>-1; i--){
			System.out.println(A[i] + " " + B[i] + " " + C[i]);
		}
		System.out.println("A B C");
		System.out.println();
	}
	private void heuristic(State testRule){
		int[] A = testRule.getA();
		int[] B = testRule.getB();
		int[] C = testRule.getC();
		int h =0;
		//For every ring on top of 2 and 3 add 1, for every ring not in C add 1
		if(C[0] !=3){
			h++;
		}
		//If 3 is on the bottom of ring C and 1 is on top add 1
		else if(C[1] ==1){
			h++;
		}
		for(int i=0; i<3; i++){
			if(A[i]==3){
				if(i<2){ //If there is space about ring size 3
					if(A[i+1]>0){ // if it has a ring above it
						h++;
						if(i==0){ //if ring size 3 is at the bottom of the peg
							if(A[i+2]>0){ //if it has 2 rings above it
								h++;
							}
						}
					}
				}
			}
			if(B[i]==3){
				if(i<2){ //If there is space about ring size 3
					if(B[i+1]>0){ // if it has a ring above it
						h++;
						if(i==0){ //if ring size 3 is at the bottom of the peg
							if(B[i+2]>0){ //if it has 2 rings above it
								h++;
							}
						}
					}
				}
			}
			
		}
		hueristics.add(h);
		//System.out.println("H: " + h);
	}
	private ArrayList<Integer> sortByHeuristic(ArrayList<Integer> legalRules){
		int smallest = 99;
		int smallestIndex =-1;
		for(int i=0; i<hueristics.size();i++){
			if(smallest>hueristics.get(i)){
				smallest = hueristics.get(i);
				smallestIndex = i;
			}
		}
		legalRules.set(0, legalRules.get(smallestIndex));
		System.out.println("H: "+hueristics.get(smallestIndex));
		
		return legalRules;
	}

}