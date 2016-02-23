package game;

import java.util.Random;

public class Bomb extends SpaceObj{
//rock is a SpaceObj that represents the asteroids

	Bomb(int height, int width) {
		printOut = "@";
		Random rand = new Random();
		xcor = rand.nextInt(width - 2) + 1;
		ycor = height-1;
	}
	

}