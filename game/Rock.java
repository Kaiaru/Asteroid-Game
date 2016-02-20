package game;

import java.util.Random;

public class Rock extends SpaceObj{

	Rock(int height, int width) {
		printOut = "#";
		Random rand = new Random();
		xcor = rand.nextInt((width) + 1);;
		ycor = height;
	}
	

}
