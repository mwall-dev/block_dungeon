package project1;

import org.newdawn.slick.Input;

public class Skeleton extends Sprite implements Enemy {
	
	private float pastX;
	private float pastY;
	private boolean up = true;
	private int time = 0;
	
	public Skeleton(float x, float y) {
		super("res/skull.png", x, y);
	}
	public Skeleton(Sprite sprite)
	{
		super(sprite);
	}
	
	public void update(Input input, int delta)
	{
		// Movement 
		if(time >= 1000)
		{
			pastX = getX();
			pastY = getY();
			if(up)
			{
				moveToDest(Sprite.DIR_UP);
			}
			else {
				moveToDest(Sprite.DIR_DOWN);
			}
			// If stopped moving, change direction
	    	if(Location.sameLocation(pastX, pastY, getX(), getY()))
			{
				up ^= true;
			}
			time = 0;
		}
		
		else {
			time += delta;
		}
		
	}

}
