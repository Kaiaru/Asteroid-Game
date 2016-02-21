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
		
		height = h;
		width = w;

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
	
	void printScreen(int height) {

		System.out.println(line);
		for (int j = height-1; j > 0; j--)
			System.out.println(screen[j]);
		System.out.println(line);
	}

	void updateScreen(SpaceObj ship, ArrayList<SpaceObj> rocks) {
		
		this.resetScreen(height, width);
		
		for(SpaceObj rock : rocks) {
			screen[rock.getYcor()].deleteCharAt(rock.getXcor());
			screen[rock.getYcor()].insert(rock.getXcor(), rock.getPrintOut());
		}
		
		screen[ship.getYcor()].deleteCharAt(ship.getXcor());
		screen[ship.getYcor()].insert(ship.getXcor(), ship.getPrintOut());
		
	}
	
	void paint(SpaceObj ship, ArrayList<SpaceObj> rocks) {
		this.updateScreen(ship, rocks);
		this.printScreen(height);
	}

}
