package project1;

import org.newdawn.slick.Input;

public class Stone extends Sprite implements Pushable {
	public Stone(float x, float y) {
		super("res/stone.png", x, y);
	}
	public Stone(Sprite sprite)
	{
		super(sprite);
	}
	
	// OVERRIDE
	public void update(Input input, int delta) {
		// If player has pushed
    	if(Location.sameLocation(getPlayerX(), getPlayerY(), getX(), getY()))
		{
			moveToDest(Player.getLastMove());
		}
		// If rogue has pushed
    	if(Location.sameLocation(getRogueX(), getRogueY(), getX(), getY()))
		{
			moveToDest(Rogue.getLastMove());
		}
		
	}
}
