package project1;

import org.newdawn.slick.Input;

public class Rogue extends Sprite implements Enemy{
	
	private static boolean playerHasMoved = false;
	
	private boolean left = true; // Direction moving
	
	// For determining if wall has been hit
	private float pastX;
	private float pastY;
	
	// Pushable blocks refer to this for direction pushed
	private static int lastMove;
	
	public Rogue(float x, float y) {
		super("res/rogue.png", x, y);
	}
	
	public Rogue(Sprite sprite) {
		super(sprite);
	}
	
	
	// Override
	public void update(Input input, int delta) {
		if(playerHasMoved)
		{
			pastX = getX();
			pastY = getY();
			if(left)
			{
				moveToDest(Player.DIR_LEFT);
				lastMove = Player.DIR_LEFT;
			}
			else {
				moveToDest(Player.DIR_RIGHT);
				lastMove = Player.DIR_RIGHT;

			}
			
			setRogueCoords(getX(),getY()); //So pushables can adjust
			
			// If it has hit a wall, change direction
	    	if(Location.sameLocation(pastX, pastY, getX(), getY())) {
				left ^= true;
			}
			playerHasMoved = false;
		}

	}
	
	public static void undo() {
		// Dummy values so blocks can move back
		setRogueCoords(23432, 32312);
	} 
	
	
	public static void playerMoved() {
		playerHasMoved  = true;
	}
	
	public static int getLastMove() {
		return lastMove;
	}
}
