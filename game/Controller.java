package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class Controller implements KeyListener {

	public HashMap<Integer, Key> keyBindings = new HashMap<Integer, Key>();
	public static boolean other[] = new boolean[256];

	Controller() {
		bind(KeyEvent.VK_W, Key.right);
		bind(KeyEvent.VK_A, Key.left);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		other[e.getExtendedKeyCode()] = true;
		keyBindings.get(e.getKeyCode()).isDown = true;

	}

	@Override
	public void keyReleased(KeyEvent e) {
		other[e.getExtendedKeyCode()] = false;
		keyBindings.get(e.getKeyCode()).isDown = false;

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Empty, not needed
	}

	public boolean isKeyBinded(int extendedKey) {
		return keyBindings.containsKey(extendedKey);
	}

	public void bind(Integer keyCode, Key key) {
		keyBindings.put(keyCode, key);
	}

	public void releaseAll() {
		for (Key key : keyBindings.values()) {
			key.isDown = false;
		}
	}

}
