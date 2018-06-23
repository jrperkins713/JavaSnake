import java.util.*;
import java.awt.Color;
public class Snake{

	private static int GameLoopMillis = 120;
	private static int InputSleepMillis = GameLoopMillis / 10;

	private static void SafeSleep(int millis) {
		try { Thread.sleep(millis); }
		catch(Exception ignore) {}
	}

	public static void main(String[] args){
		Scanner vReader = new Scanner(System.in);
		List<int[]> playerPos = new ArrayList<int[]>();
		int[] nextPos = {5,10}, lastPos, coinPos = randPos();
		playerPos.add(nextPos);
		int code, score = 0;
		boolean lose= false;
		String dir = "none";
		Grid grid = new Grid(20,20,700);
		grid.setColor(coinPos[1],coinPos[0],Color.yellow);
		grid.setColor(nextPos[1],nextPos[0],Color.red);
		while (true){

			int loopTimeMillis = 0;
			code = 0;
			while (code == 0 && loopTimeMillis < GameLoopMillis) {
				code = grid.getCode();
				SafeSleep(InputSleepMillis);
				loopTimeMillis += InputSleepMillis;
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
				case 38:
					if(!dir.equals("down"))
						dir="up";
					break;
				case 37:
					if(!dir.equals("right"))
						dir="left";
					break;
				case 40:
					if(!dir.equals("up"))
						dir="down";
					break;
				case 39:
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
			if(samePos(nextPos,coinPos)){
				score++;
				while(inArray(playerPos,coinPos)){
					coinPos = randPos();
				}
				grid.setColor(coinPos[1],coinPos[0],Color.yellow);
			}
			if(playerPos.size()>3){
				for(int i=0;i<playerPos.size()-1;i++){
					if(samePos(playerPos.get(i),nextPos))
						lose = true;
				}
			}
			if(lose){
				grid.setColor(nextPos[1],nextPos[0], Color.gray);
				break;
			}


			if(playerPos.size()>=2*score +1){
				lastPos = playerPos.remove(0);
				grid.setColor(lastPos[1],lastPos[0], Color.white);
			}
			try{
				grid.setColor(nextPos[1],nextPos[0], Color.red);
			}
			catch(Exception e){
				int square = playerPos.size()-2;
				try{
					grid.setColor(playerPos.get(square)[1],playerPos.get(square)[0],Color.gray);
				}
				catch(Exception x){
				}
				break;
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


	public static boolean inArray(List<int[]> player, int[] next){
		for(int x=1;x<player.size();x++){
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
		int[] temp = {rand.nextInt(20),rand.nextInt(20)};
		return temp;
	}
}
