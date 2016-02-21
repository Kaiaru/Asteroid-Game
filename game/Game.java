package game;

import java.util.concurrent.*;
import jline.*;

import java.io.*;
import java.util.*;
import java.util.zip.*;

public class Game {

	final static int HEIGHT = 25;
	final static int WIDTH = 30;
	

	static ArrayList<SpaceObj> rockList;
	static Painter gamePainter;
	static SpaceObj playerShip;
	static int score;

	static double timePerTick = 0.2;
	static int ticksTillAdd = 3;
	static int tickCounter = 3; // adds rocks immediately
	static int numRocksToAdd = 6;

	static boolean running;
	static double timeOne = (double) System.nanoTime() / 1000000000; // convert to seconds
	static double timeTwo;

	public static void main(String args[]) throws IOException {

		// initial setup and then calls the mainLoop();
		rockList = new ArrayList<SpaceObj>();
		gamePainter = new Painter(HEIGHT, WIDTH);
		score = 0;
		running = true;

		playerShip = new Ship(WIDTH);
		gamePainter.paint(playerShip, rockList);
		
		//makes a thread for the consoleReader
		backThread bThread = new backThread(playerShip);
		Thread t = new Thread(bThread);
		t.start();

		mainLoop();
		
		bThread.quit();

	}

	public static void updateDynamicLogic(double passedTime){
		// Dynamic logic based on timePassed if ever needed
    }

	public static void updateFixedLogic(){
		// All Fixed time logic
		
		score += 100;
		
		//System.out.println(playerShip.xcor + " " + playerShip.ycor);
		

		if (tickCounter == ticksTillAdd) { // adds rocks each ticksTillAdd
			for (int x = 0; x < numRocksToAdd; x++) {
				rockList.add(new Rock(HEIGHT, WIDTH));
			}
			tickCounter = 0;
		}

		//moves rocks down
		for (SpaceObj rock : rockList) {
			if (rock.ycor >= 1){
				rock.ycor -= 1;
			}
			else{
				rock = null;
			}
		}
		
		//collision code
		for (SpaceObj rock : rockList){
			if (rock.xcor == playerShip.xcor && rock.ycor == playerShip.ycor){
				running = false;
			}
		}

	}

	public static void mainLoop() {
		
		double timePassed = 0;
		double deltaTime = 0;

		while (running) {

			updateDynamicLogic(deltaTime);

			while (timePassed >= timePerTick) {
				updateFixedLogic();
				tickCounter += 1;
				timePassed -= timePerTick;
				gamePainter.paint(playerShip, rockList);
			}


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
