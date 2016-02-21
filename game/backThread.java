package game;

import java.util.concurrent.*;
import jline.*;

import java.io.*;
import java.util.*;
import java.util.zip.*;

public class backThread implements Runnable{
	//backThread class for the jline consoleReader in order to have continuous reading of inputs
	
		private boolean running = true;
		
		//the movement booleans
		private boolean leftMove = false;
		private boolean rightMove = false;
		private boolean upMove = false;
		private boolean downMove = false;
		private boolean useBomb = false;
		
		private int width;
		private int height;
		
		private ConsoleReader reader;
		
		private SpaceObj playerShip;
		private ArrayList<SpaceObj> rocklList;
		
		private char[] allowed = {'w','a','s','d', ' '};
	
		
		public backThread(SpaceObj pShip, int h, int w) throws IOException{
			//contructor, takes in the playerShip; makes a ConsoleReader
			height = h;
			width = w;
			playerShip = pShip;
			reader = new ConsoleReader(System.in, new PrintWriter(System.out));
		}
		
		public backThread(SpaceObj pShip, int h, int w, ArrayList<SpaceObj> rocks) throws IOException{
			//contructor, takes in the playerShip; makes a ConsoleReader
			height = h;
			width = w;
			playerShip = pShip;
			rockList = rocks;
			reader = new ConsoleReader(System.in, new PrintWriter(System.out));
		}
		
		@Override 
		public void run(){
			while(running){
				//reads in the character from allowed and set the movement boolean
				try {
					int i = 0;
					i = reader.readCharacter(allowed);
					processChar(i);
				} 	
				catch (IOException e) {
					e.printStackTrace();
				}
				
				if(rightMove){
					playerShip.setXcor(playerShip.getXcor() + 1);
					rightMove = false;
				}
		
				else if(leftMove){
					playerShip.setXcor(playerShip.getXcor() - 1);
					leftMove = false;
				}
		
				else if(upMove){
					playerShip.setYcor(playerShip.getYcor() + 1);
					upMove = false;
				}
		
				else if(downMove){
					playerShip.setYcor(playerShip.getYcor() - 1);
					downMove = false;
				}
				
				else if(useBomb){
					if(pship.getBomb){
					rockList.clear();
					pship.setBomb(false);
					}
					useBomb = false
					
					
				}
				
				
				//ship hits a wall
				if(playerShip.getXcor() == width || playerShip.getXcor() == 0){
					quit();
				}
				if(playerShip.getYcor() == height || playerShip.getYcor() == 0){
					quit();
				}
				
			}
		}
		
		public void quit() {
			//to quit the thread
			running = false;
		}
		
		public void processChar(int i){
		//process the character input
			switch(i){
				case 'w':
					upMove = true;
					break;
				case 's':
					downMove = true;
					break;
				case 'a':
					leftMove = true;
					break;
				case 'd':
					rightMove = true;
					break;
				case ' ':
					useBomb = true;
					break;
		}
    }
	}
