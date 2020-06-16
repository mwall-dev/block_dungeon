package project1;

import org.newdawn.slick.Input;
import org.newdawn.slick.Graphics;


public class Player extends Sprite {
	
	private static int moveCount = 0;
	
	// Pushable blocks refer to this for direction pushed
	private static int lastMove;
	
	// Flag for enemy contact
	private static boolean enemyContact = false;
	
	public Player(float x, float y) {
		super("res/player_left.png", x, y);
	}
	
	// Copy constructor 
	public Player(Sprite sprite) {
		super(sprite);
	}

	@Override
	public void update(Input input, int delta) {
		
		int dir = DIR_NONE;

		if (input.isKeyPressed(Input.KEY_LEFT)) {
			dir = DIR_LEFT;
			lastMove = dir;
			moveCount++;

		}
		else if (input.isKeyPressed(Input.KEY_RIGHT)) {
			dir = DIR_RIGHT;
			lastMove = dir;
			moveCount++;

		}
		else if (input.isKeyPressed(Input.KEY_UP)) {
			dir = DIR_UP;
			lastMove = dir;
			moveCount++;

		}
		else if (input.isKeyPressed(Input.KEY_DOWN)) {
			dir = DIR_DOWN;
			lastMove = dir;
			moveCount++;
		}
		
		// Move to our destination
		moveToDest(dir);
		
		// Set Coords that pushables can access
		setPlayerCoords(getX(),getY());
		
		// If player is on an enemy, this will trigger a restart
		if(Location.isEnemy(getX(), getY())) {
			enemyContact = true;
		}
	}
	@Override
	public void render(Graphics g) {
		super.render(g);
		g.drawString("Moves = " + moveCount, 0,0);
	}
	
	public static void undo() {
		if(moveCount>0){
			moveCount--;
		}
		// Dummy values so blocks can move back
		setPlayerCoords(10000, 10000);

	} 
	
	public static void restart() {
		moveCount = 0;
		enemyContact = false;
		// Dummy values so blocks can move back
		setPlayerCoords(10000, 10000);
	}
	
	
	// Accessor Methods
	public static int getLastMove() {
		return lastMove;
	}
	
	public static boolean touchedEnemy() {
		return enemyContact;
	}
	
	public static int getMoveCount() {
		return moveCount;
	}
	
	
}
