/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package towersofhanoi;


public class State {
	//Pegs A-C
	private int[] A;
	private int[] B;
	private int[] C;
	
	public State(int[] a, int[] b, int[] c){
		A = a;
		B = b;
		C = c;
	}
	public int[] getA(){
		return A;
	}
	public int[] getB(){
		return B;
	}
	public int[] getC(){
		return C;
	}

}
