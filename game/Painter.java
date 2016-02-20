package game;

import java.util.concurrent.*;
import static java.lang.Math.*;
import java.util.*;

public class Painter {

	static StringBuilder[] screen;
	static StringBuilder line;
	static StringBuilder blank;

	int height;
	int width;

	Painter(int h, int w) {
		
		height = h;
		width = w;

		screen = new StringBuilder[height];

		blank = new StringBuilder("||");
		for (int i = 0; i < width - 2; i++)
			blank.insert(1, ' ');

		for (int i = 0; i < height; i++)
			screen[i] = new StringBuilder(blank); // WHY?

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
			screen[rock.ycor].deleteCharAt(rock.xcor);
			screen[rock.ycor].insert(rock.xcor, rock.printOut);
		}
		
		screen[ship.ycor].deleteCharAt(ship.xcor);
		screen[ship.ycor].insert(ship.xcor, ship.printOut);
		
	}
	
	void paint(SpaceObj ship, ArrayList<SpaceObj> rocks) {
		this.updateScreen(ship, rocks);
		this.printScreen(height);
	}

}
