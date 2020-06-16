package project1;

import org.newdawn.slick.Input;

public class Ice extends Sprite implements Pushable {
	
	private int time = 0;
	private boolean moving = false;
	private int dir;
	
	private float pastX;
	private float pastY;
	
	public Ice(float x, float y) {
		super("res/ice.png", x, y);
	}
	public Ice(Sprite sprite)
	{
		super(sprite);
	}
	
	// OVERRIDE
	public void update(Input input, int delta) {
		
		// If player has pushed
    	if(Location.sameLocation(getPlayerX(), getPlayerY(), getX(), getY()))
		{
				pastX = getX();
				pastY = getY();
				
				moveToDest(Player.getLastMove());
				dir = Player.getLastMove();
				
				moving = true;
		}
		// If rogue has pushed 
    	if(Location.sameLocation(getRogueX(), getRogueY(), getX(), getY()))
		{
				pastX = getX();
				pastY = getY();
				
				moveToDest(Rogue.getLastMove());
				dir = Rogue.getLastMove();
				
				moving = true;
		}
		
		// Movement
		if(moving)
		{
			if(time < 250)
			{
				time += delta;
			}
			else {
				pastX = getX();
				pastY = getY();
				moveToDest(dir);
				// If has stopped moving.
				if(Location.sameLocation(pastX, getX(), pastY, getY()))
				{
					moving = false;
				}
				time = 0;
			}
		}
	
	}
	

}
