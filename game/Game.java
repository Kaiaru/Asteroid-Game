package game;

import java.util.*;

public class Game {

	final static int WIDTH = 74;
	final static int HEIGHT = 75;

	static ArrayList<SpaceObj> rockList;
	static Painter gamePainter;
	static SpaceObj playerShip;
	static int score;

	static double timePerTick = 0.25;
	static int ticksTillAdd = 5;
	static int tickCounter = 5; // adds rocks immediately
	static int numRocksToAdd = 6;

	static boolean running;
	static double timeOne = (double) System.nanoTime() / 1000000000; // convert
																		// to
																		// double
																		// seconds
	static double timeTwo;

	public static void main(String args[]) {

		// initial setup and then calls the mainLoop();
		rockList = new ArrayList<SpaceObj>();
		gamePainter = new Painter(HEIGHT, WIDTH);
		score = 0;
		running = true;

		playerShip = new Ship(WIDTH);
		gamePainter.paint(playerShip, rockList);

		mainLoop();

	}

	public static void updateDynamicLogic(double passedTime) {
		// Dynamic scoring based on the time passed
		score += (int) Math.ceil(passedTime) * 100;
	}

	public static void updateFixedLogic() {
		// movement and collision detection

		if (tickCounter == ticksTillAdd) { // adds rocks each ticksTillAdd
			for (int x = 0; x < numRocksToAdd; x++) {
				rockList.add(new Rock(HEIGHT, WIDTH));
			}
			tickCounter = 0;
		}

		for (SpaceObj rock : rockList) {
			rock.ycor -= 1;
		}

	}

	public static void mainLoop() {
		
		double timePassed = 0;
		double deltaTime = 0;

		while (running) {

			updateDynamicLogic(deltaTime);

			while (timePassed >= timePerTick) {
				updateFixedLogic();
				timePassed -= timePerTick;
			}

			gamePainter.paint(playerShip, rockList);

			deltaTime = getTimePassedAndResetTimer();
			timePassed += deltaTime;

		}

		System.out.println("Game Over");
		System.out.println("Your Score: " + score);

	}

	public static double getTimePassedAndResetTimer() {
		timeTwo = (double) System.nanoTime() / 1000000000;
		double deltaTime = timeTwo - timeOne;
		timeOne = timeTwo;
		return deltaTime;
	}

}
