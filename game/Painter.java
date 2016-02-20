import java.util.concurrent.*;
import static java.lang.Math.*;
import java.util.*;

public class Painter {

	static StringBuilder[] screen;
	static StringBuilder line;
	static StringBuilder blank;

	// final static int WIDTH = 74;
	// final static int HEIGHT = 60;

	Painter(int height, int width) {

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
			screen[i] = new StringBuilder(blank); // WHY?

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

	void updateScreen(Ship ship, ArrayList<SpaceObj> rocks) {
		
		this.resetScreen(74, 60);
		
		for(SpaceObj rock : rocks) {
			screen[rock.ycor].deleteCharAt(rock.xcor);
			screen[rock.ycor].insert(rock.xcor, rock.printOut);
		}
		
		screen[ship.ycor].deleteCharAt(ship.xcor);
		screen[ship.ycor].insert(ship.xcor, ship.printOut);
		
	}
	
	void paint(Ship ship, ArrayList<SpaceObj> rocks) {
		this.updateScreen(ship, rocks);
		this.printScreen(74);
	}
	
	public static void main(String[] args) {
		Ship s = new Ship(60);
		SpaceObj r = new Rock(74, 60);
		ArrayList<SpaceObj> rs = new ArrayList<SpaceObj>();
		rs.add(r);
		
		Painter p = new Painter(74, 60);
		
		
		/* TESTS */
		p.paint(s, rs); // paint screen
		
		s.xcor = s.xcor + 1; // move ship one unit to the right
		
		p.paint(s, rs); // repaint screen
		
		r.ycor = r.ycor - 1; // move rock one unit down
		
		p.paint(s, rs); // repaint screen
		
		
	}

}
