package game;

public class Key {

	public boolean isDown;
	
	public static Key left = new Key();
	public static Key right = new Key();

	/* toggles the keys current state */
	public void toggle() {
		isDown = !isDown;
	}
}
