package game;

import java.util.concurrent.*;
import jline.*;

import java.io.*;
import java.util.*;
import java.util.zip.*;

public class backThread implements Runnable{
	//backThread class for the jline consoleReader in order to have continuous reading of inputs
	
		boolean running = true;
		boolean leftMove = false;
		boolean rightMove = false;
		boolean upMove = false;
		boolean downMove = false;
		
		ConsoleReader reader;
		
		SpaceObj playerShip;
		
		char[] allowed = {'w','a','s','d'};
	
		
		public backThread(SpaceObj pShip) throws IOException{
			playerShip = pShip;
			reader = new ConsoleReader(System.in, new PrintWriter(System.out));
		}
		
		@Override 
		public void run(){
			while(running){
				try {
					int i = 0;
					i = reader.readCharacter(allowed);
					processChar(i);
				} 	
				catch (IOException e) {
					e.printStackTrace();
				}
				
				if(rightMove){
					playerShip.xcor += 1;
					rightMove = false;
				}
		
				else if(leftMove){
					playerShip.xcor -= 1;
					leftMove = false;
				}
		
				else if(upMove){
					playerShip.ycor += 1;
					upMove = false;
				}
		
				else if(downMove){
					playerShip.ycor -= 1;
					downMove = false;
				}
				
			}
		}
		
		public void quit() {
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
		}
    }
	}
