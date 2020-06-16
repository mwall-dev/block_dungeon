package project1;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Door extends Sprite {
	
	
	// Used for when switch is turned on and off
	// Set when level is loaded.
	// Static so they don't change when undoing moves.
	private static float startingX;
	private static float startingY;
	
	
	public Door(float x, float y) {
		super("res/door.png", x, y);
		startingX = this.getX();
		startingY = this.getY();
	}
	
	public Door(Sprite sprite) {
		super(sprite);
	}
	
	public void update(Input input, int delta)
    { 
    	// If switch is pressed than open door
    	if(Switch.isPressed()) {
    		try {
				clearImage();
			}
			catch (SlickException e) {
				e.printStackTrace();
			}
    	}
    	// If switch isn't pressed than display it
    	else {
    		setCoords(startingX, startingY);
    	}
    }

}
