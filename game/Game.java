package game;

import java.util.concurrent.*;
import jline.*;

import java.io.*;
import java.util.*;
import java.util.zip.*;

public class Game {

	final static int HEIGHT = 24;
	final static int WIDTH = 30;

	private ArrayList<SpaceObj> rockList;
	private ArrayList<SpaceObj> bombList;
	private Painter gamePainter;
	private SpaceObj playerShip;
	private int score;

	private int gameDifficulty = 1;
	private double timePerTick = 0.1;
	private int ticksTillAdd = 3;
	private int tickCounter = ticksTillAdd; // adds rocks immediately
	private int numRocksToAdd = 12;

	private char[] allowed = {'1','2','3'};
	
	private boolean running;
	private double timeOne;
	private double timeTwo;
	
	private backThread bThread;
	private Thread t;
	
	private ConsoleReader reader;
	
	public static void main(String args[]) throws IOException {
		// initial setup and then calls the mainLoop();
		Game cGame = new Game();
		
		cGame.rockList = new ArrayList<SpaceObj>();
		cGame.bombList = new ArrayList<SpaceObj>();
		cGame.gamePainter = new Painter(HEIGHT, WIDTH);
		cGame.score = 0;
		cGame.running = true;

		cGame.playerShip = new Ship(WIDTH);
		
		//gets game difficulty
		cGame.reader = new ConsoleReader(System.in, new PrintWriter(System.out));
		int i = 0;   
		i = cGame.reader.readCharacter(cGame.allowed);		
		cGame.gameDifficulty = Character.getNumericValue(i);
		
		System.out.println(i);
		
		//sets all of the var relative to gameDifficulty
		cGame.timePerTick = 0.1 * cGame.gameDifficulty;
		cGame.ticksTillAdd = cGame.gameDifficulty * 3;
		cGame.tickCounter = cGame.ticksTillAdd; // adds rocks immediately
		cGame.numRocksToAdd = 12 / cGame.gameDifficulty;
		
		System.out.println(cGame.timePerTick + " " + cGame.ticksTillAdd+ " " + cGame.tickCounter+ " " + cGame.numRocksToAdd);
		
		
		cGame.timeOne = (double) System.nanoTime() / 1000000000; // convert to seconds
		cGame.gamePainter.paint(cGame.playerShip, cGame.rockList, cGame.bombList);
		
		//makes a thread for the consoleReader
		cGame.bThread = new backThread(cGame.playerShip, HEIGHT, WIDTH, cGame.rockList);
		cGame.t = new Thread(cGame.bThread);
		cGame.t.start();

		cGame.mainLoop();
		
		cGame.bThread.quit();

	}
	
	public void updateDynamicLogic(double passedTime){
		// Dynamic logic based on timePassed if ever needed
    }

	public void updateFixedLogic(){
		// All Fixed time logic
		
		score += 100;
		
		//System.out.println(playerShip.xcor + " " + playerShip.ycor);
		

		if ((tickCounter % (ticksTillAdd + 1)) == ticksTillAdd) { // adds rocks each ticksTillAdd
			for (int x = 0; x < numRocksToAdd; x++) {
				rockList.add(new Rock(HEIGHT, WIDTH));
			}
			
		}
		
		if(tickCounter == ticksTillAdd * 15){
			bombList.add(new Bomb(HEIGHT, WIDTH));
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
		for (SpaceObj bomb : bombList) {
			if (bomb.getYcor() >= 1){
				bomb.setYcor(bomb.getYcor() - 1);
			}
			else{
				bomb = null; //deletes the rock if it hits the bottom
			}
		}
		
		//collision code
		for (SpaceObj rock : rockList){
			if (rock.getXcor() == playerShip.getXcor() && rock.getYcor() == playerShip.getYcor()){
				running = false;
			}
		}
		for (SpaceObj bomb : bombList){
			if (bomb.getXcor() == playerShip.getXcor() && bomb.getYcor() == playerShip.getYcor()){
				playerShip.setBomb(true);
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
				gamePainter.paint(playerShip, rockList, bombList);
			}


			deltaTime = getTimePassedAndResetTimer();
			timePassed += deltaTime;

		}
		
		System.out.println("Your ship and crew was forever lost, cast throughout the asteroid field...");
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
