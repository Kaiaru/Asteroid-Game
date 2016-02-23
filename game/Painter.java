package game;

import java.util.concurrent.*;
import static java.lang.Math.*;
import java.util.*;

public class Painter {

	private static StringBuilder[] screen;
	private static StringBuilder line;
	private static StringBuilder blank;

	private int height;
	private int width;

	Painter(int h, int w) {
		
		height = h; // height of the screen
		width = w; // width of the screen

		// creates the game screen
		screen = new StringBuilder[height];

		blank = new StringBuilder("||");
		for (int i = 0; i < width - 2; i++)
			blank.insert(1, ' ');

		for (int i = 0; i < height; i++)
			screen[i] = new StringBuilder(blank);

		line = new StringBuilder("");
		for (int i = 0; i < width; i++)
			line.append('_');
		
		// prints the start screen
		printInitialScreen();
	}

	void printInitialScreen() {
		// displays start screen
		System.out.println(line);
		System.out.println(line);
		System.out.print("***ASTEROID FALLS***\n\nINSTRUCTIONS:\nCaptain! We've entered an asteroid field, maneuver our ship to safety! \nThe ship cannot sustain "
		+ "an asteroid collision or the \ndimensional distortion of the wormhole walls! \nCollect other ship fragments for bombs to clear asteroids!"
		+ "\n\nCONTROLS:\nw: up\ns: down\na: left\nd: right\nspace bar: bomb!\n\n CHOOSE YOUR DIFFICULTY:\n1: Hell\n2: Normal\n3: Easy\n");
		System.out.println(line);
		System.out.println(line);
	}
	
	void resetScreen(int height, int width) {
		screen = new StringBuilder[height];

		blank = new StringBuilder("||");
		for (int i = 0; i < width - 2; i++)
			blank.insert(1, ' ');

		for (int i = 0; i < height; i++)
			screen[i] = new StringBuilder(blank);

		line = new StringBuilder("");
		for (int i = 0; i < width; i++)
			line.append('_');
	}
	
	// prints the walls 
	void printScreen(int height) {

		System.out.println(line);
		for (int j = height-1; j > 0; j--)
			System.out.println(screen[j]);
		System.out.println(line);
	}

	void updateScreen(SpaceObj ship, ArrayList<SpaceObj> rocks, ArrayList<SpaceObj> bombs) {
		
		// clears the screen
		this.resetScreen(height, width);
		
		for(SpaceObj bomb : bombs) {
			screen[bomb.getYcor()].deleteCharAt(bomb.getXcor());
			screen[bomb.getYcor()].insert(bomb.getXcor(), bomb.getPrintOut());
		}
		
		// adds the rocks to the StringBuilder
		for(SpaceObj rock : rocks) {
			screen[rock.getYcor()].deleteCharAt(rock.getXcor());
			screen[rock.getYcor()].insert(rock.getXcor(), rock.getPrintOut());
		}
		
		// adds ship to the StringBuilder
		screen[ship.getYcor()].deleteCharAt(ship.getXcor());
		screen[ship.getYcor()].insert(ship.getXcor(), ship.getPrintOut());
		
	}
	
	// updates the screen and prints it
	void paint(SpaceObj ship, ArrayList<SpaceObj> rocks, ArrayList<SpaceObj> bombs) {
		this.updateScreen(ship, rocks, bombs);
		this.printScreen(height);
	}

}
