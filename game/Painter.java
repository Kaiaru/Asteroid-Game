//package game;

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
		
		// prints the start screen
		printInitialScreen();

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
		
	}

	void printInitialScreen() {
		// displays start screen
		System.out.print("INSTRUCTIONS:\nManeuver your ship to avoid asteroids. You die if you run into an asteroid or any of the walls. Collect bombs to clear the screen\nCONTROLS:\nw: up\ns: down\na: left\nd: right\nspace bar: bomb!\n\n CHOOSE YOUR DIFFICULTY:\n1: Hard\n2: Normal\n3: Easy");
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

	void updateScreen(SpaceObj ship, ArrayList<SpaceObj> rocks) {
		
		// clears the screen
		this.resetScreen(height, width);
		
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
	void paint(SpaceObj ship, ArrayList<SpaceObj> rocks) {
		this.updateScreen(ship, rocks);
		this.printScreen(height);
	}

}
