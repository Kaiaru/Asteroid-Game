//package game;

import java.util.concurrent.*;
import jline.*;

import java.io.*;
import java.util.*;
import java.util.zip.*;

public class Game {

	final static int HEIGHT = 24;
	final static int WIDTH = 30;

	private ArrayList<SpaceObj> rockList;
	private Painter gamePainter;
	private SpaceObj playerShip;
	private int score;

	private int gameDifficulty = 1;
	private double timePerTick = 0.2 * gameDifficulty;
	private int ticksTillAdd = gameDifficulty * 3;
	private int tickCounter = ticksTillAdd; // adds rocks immediately
	private int numRocksToAdd = 12 / gameDifficulty;

	private char[] allowed = {'1','2','3'};
	
	private boolean running;
	private double timeOne = (double) System.nanoTime() / 1000000000; // convert to seconds
	private double timeTwo;
	
	private backThread bThread;
	private Thread t;
	
	public static void main(String args[]) throws IOException {
		// initial setup and then calls the mainLoop();
		Game cGame = new Game();
		
		cGame.rockList = new ArrayList<SpaceObj>();
		cGame.gamePainter = new Painter(HEIGHT, WIDTH);
		cGame.score = 0;
		cGame.running = true;

		cGame.playerShip = new Ship(WIDTH);
		
		//gets game difficulty
		reader = new ConsoleReader(System.in, new PrintWriter(System.out));
		int i = 0;   
		i = reader.readCharacter(allowed);		
		cGame.processChar(i);
		
		cGame.gamePainter.paint(cGame.playerShip, cGame.rockList);
		
		//makes a thread for the consoleReader
		cGame.bThread = new backThread(cGame.playerShip, HEIGHT, WIDTH);
		cGame.t = new Thread(cGame.bThread);
		cGame.t.start();

		cGame.mainLoop();
		
		cGame.bThread.quit();

	}

	public void processChar(int i){
		//process the character input
			gameDifficulty = i;
		}
	
	public void updateDynamicLogic(double passedTime){
		// Dynamic logic based on timePassed if ever needed
    }

	public void updateFixedLogic(){
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
			if (rock.getYcor() >= 1){
				rock.setYcor(rock.getYcor() - 1);
			}
			else{
				rock = null; //deletes the rock if it hits the bottom
			}
		}
		
		//collision code
		for (SpaceObj rock : rockList){
			if (rock.getXcor() == playerShip.getXcor() && rock.getYcor() == playerShip.getYcor()){
				running = false;
			}
		}

	}

	public void mainLoop() {
	//mainLoop for the game
	
		double timePassed = 0;
		double deltaTime = 0;

		while (running && t.isAlive()) {

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

	public double getTimePassedAndResetTimer() {
	//gets the amount of time passed and returns it
		timeTwo = (double) System.nanoTime() / 1000000000;
		double deltaTime = timeTwo - timeOne;
		timeOne = timeTwo;
		return deltaTime;
	}
	
}
