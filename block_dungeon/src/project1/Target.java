package project1;

import org.newdawn.slick.Input;

public class Target extends Sprite {
	
	private boolean hasStone = false;
	
	public Target(float x, float y) {
		super("res/Target.png", x, y);
	}
	public Target(Sprite sprite)
	{
		super(sprite);
		Target temp = (Target)sprite;
	    this.hasStone = temp.hasStone;
	}
	
	
	@Override
	public void update(Input input, int delta) {
		hasStone = Location.isPushable(getX(), getY());
	}
	
	public boolean hasStone() {
		return hasStone;
	}
}
