package game;

import java.lang.Math;

public class Ship extends SpaceObj {
//Ship is a SpaceObj that represents the player's ship

	private boolean hasBomb;

	Ship(int width) {
		printOut = "^";
		xcor = width/2;
		ycor = 1;
		hasBomb = false;
	}
	
	public boolean getBomb(){
		return hasBomb;
	}
	
	public void setBomb(boolean x){
		hasBomb = x;
	}

}
