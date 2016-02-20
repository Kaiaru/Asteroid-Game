package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class Controller implements KeyListener{

	public HashMap<Integer, Key> keyBindings = new HashMap<Integer, Key>();
	
	Controller(){
		bind(KeyEvent.VK_W, Key.right);
	    bind(KeyEvent.VK_A, Key.left);
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void bind(Integer keyCode, Key key){
	    keyBindings.put(keyCode, key);
	}

}
