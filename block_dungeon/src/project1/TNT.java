package project1;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class TNT extends Sprite implements Pushable  {
	
	
	private int time = 0;
	private boolean tntBlown = false;
	public TNT(float x, float y) {
		super("res/tnt.png", x, y);
	}
	public TNT(Sprite sprite)
	{
		super(sprite);
	}
	
    public void update(Input input, int delta)
    {
    	// Enables block to be pushed by player and rogue
    	if(Location.sameLocation(getPlayerX(), getPlayerY(), getX(), getY()))
		{
			moveToDest(Player.getLastMove());
		}
    	if(Location.sameLocation(getRogueX(), getRogueY(), getX(), getY()))
		{
			moveToDest(Rogue.getLastMove());
		}
    	
    	// If pushed onto cracked tile, explode and disappear
    	if(Location.isCracked(getX(), getY()))
    	{
    		Location.breakCrackedWall(getX(), getY());
    		
    		try {
				clearImage();
			}
			catch (SlickException e) {
				e.printStackTrace();
			}
    	}
  
    }

}
