package game;

import java.util.Random;

public class Rock extends SpaceObj{

	Rock(int height, int width) {
		printOut = "#";
		Random rand = new Random();
		xcor = 10;
		ycor = height-1;
	}
	

}
