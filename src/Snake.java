import java.util.*;
import java.awt.Color;
public class Snake{
	public static void main(String[] args){
		Scanner vReader = new Scanner(System.in);
		List<int[]> playerPos = new ArrayList<int[]>();
		int[] nextPos = {5,10}, lastPos, coinPos = randPos();
		playerPos.add(nextPos);
		int code, score = 0;
		String dir = "none";
		Grid grid = new Grid(20,20);
		grid.setColor(coinPos[1],coinPos[0],Color.yellow);
		grid.setColor(nextPos[1],nextPos[0],Color.red);
		while (true){
			code = grid.getCode();
			try {
				Thread.sleep(175);
			}
			catch(Exception e) {

			}
			switch(code){
				case 87:
					if(!dir.equals("down"))
						dir="up";
					break;
				case 65:
					if(!dir.equals("right"))
						dir="left";
					break;
				case 83:
					if(!dir.equals("up"))
						dir="down";
					break;
				case 68:
					if(!dir.equals("left"))
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

			if(playerPos.size()>=score+2){
				lastPos = playerPos.remove(0);
				grid.setColor(lastPos[1],lastPos[0], Color.white);
			}
			try{
				grid.setColor(nextPos[1],nextPos[0], Color.red);
			}
			catch(Exception e){
				break;
			}
			if(samePos(nextPos,coinPos)){
				System.out.println("Event");
				score++;
				printArray(playerPos);
				System.out.println(coinPos[1]+" "+coinPos[0]);
				while(inArray(playerPos,coinPos)){
					coinPos = randPos();
					System.out.println("Move!");
				}
				System.out.println("passed");
				grid.setColor(coinPos[1],coinPos[0],Color.yellow);
			}

			nextPos = new int[2];
			nextPos[0] = playerPos.get(playerPos.size()-1)[0];
			nextPos[1] = playerPos.get(playerPos.size()-1)[1];



		}
		System.out.println("Game Over. Your score was "+score);

	}
	public static void printArray(List<int[]> arr){
		for(int x=0;x<arr.size();x++)
			System.out.println(arr.get(x)[1]+", "+arr.get(x)[0]);
		System.out.println();
	}

	//not working at the moment
	public static boolean inArray(List<int[]> player, int[] next){
		for(int x=0;x<player.size()-1;x++){
			System.out.println("in");
			System.out.println(player.get(x)[1]+" "+player.get(x)[0]);
			System.out.println(next[1]+" "+next[0]);
			if(samePos(player.get(x),next)){
				return true;
			}
		}
		return false;
	}

	public static boolean samePos(int[] play, int[] thing){
			if(play[0] ==thing[0]&&play[1]==thing[1])
				return true;
			else return false;
	}

	public static int[] randPos(){
		Random rand = new Random();
		int[] temp = {rand.nextInt(5),rand.nextInt(5)};
		return temp;
	}
}
