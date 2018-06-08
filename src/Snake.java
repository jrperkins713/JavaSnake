import java.util.*;
import java.awt.Color;
public class Snake{
	public static void main(String[] args){
		Scanner vReader = new Scanner(System.in);
		List<int[]> playerPos = new ArrayList<int[]>();
		int[] nextPos = {5,5}, lastPos;
		playerPos.add(nextPos);
		int code, score = 2;
		String dir = "none";
		Grid grid = new Grid(20,20);
		grid.setColor(5,5,Color.red);
		while (true){
			code = grid.getCode();
			try {
				Thread.sleep(175);
			}
			catch(Exception e) {

			}
			switch(code){
				case 87:
					dir="up";
					break;
				case 65:
					dir="left";
					break;
				case 83:
					dir="down";
					break;
				case 68:
					dir="right";
					break;
			}
			if(dir.equals("up"))
				nextPos[1]--;
			if(dir.equals("down"))
				nextPos[1]++;
			if(dir.equals("left"))
				nextPos[0]--;
			if(dir.equals("right"))
				nextPos[0]++;
			playerPos.add(nextPos);
			printArray(playerPos);

			if(playerPos.size()>=score+3){
				lastPos = playerPos.remove(0);
				grid.setColor(lastPos[1],lastPos[0], Color.white);
			}
			printArray(playerPos);

			grid.setColor(nextPos[1],nextPos[0], Color.red);

			nextPos = new int[2];
			nextPos[0] = playerPos.get(playerPos.size()-1)[0];
			nextPos[1] = playerPos.get(playerPos.size()-1)[1];



		}

	}
	public static void printArray(List<int[]> arr){
		for(int x=0;x<arr.size();x++)
			System.out.println(arr.get(x)[1]+", "+arr.get(x)[0]);
		System.out.println();
	}

}
