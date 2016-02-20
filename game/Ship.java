
import java.lang.Math;

public class Ship extends SpaceObj {

	Ship(int width) {
		printOut = "^";
		xcor = (int) Math.floor(width/2);
		ycor = 1;
	}

}
