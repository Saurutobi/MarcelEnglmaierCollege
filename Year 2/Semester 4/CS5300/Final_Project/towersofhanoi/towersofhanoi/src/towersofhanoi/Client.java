
package towersofhanoi;


public class Client {
	public static void main(String[] args) {
		int[] startA = {3, 2, 1}; // A[0] is at the bottom A[2] at the top
		int[] startB = {0, 0, 0};
		int[] startC = {0, 0, 0};
		
		int[] goalA = {0, 0, 0};
		int[] goalB = {0, 0, 0};
		int[] goalC = {3, 2, 1};
		
		State Start =new State(startA,startB,startC);
		State Goal =new State(goalA,goalB,goalC);
		Control Controler = new Control(Start,Goal);
		Controler.Naviage();
		
		
	}

}
