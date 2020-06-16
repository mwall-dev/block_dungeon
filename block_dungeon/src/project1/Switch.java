package project1;

import org.newdawn.slick.Input;

public class Switch extends Sprite{
	
	private static boolean pressed = false;
	
	public Switch (float x, float y) {
		super("res/switch.png", x, y);
	}
	
	public Switch (Sprite sprite) {
		super(sprite);
	}
	public void update(Input input, int delta)
	{
		// If switch has a block on it
		if(Location.isPushable(getX(), getY())) {
			pressed = true;
		}
		else {
			pressed = false;
		}
	}
	
	
	public static boolean isPressed() {
		return pressed;
	}
	

}
