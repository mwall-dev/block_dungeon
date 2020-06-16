package project1;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
/**
 * Superclass for each block in game.
 * Each block has a location and Image. 
 * Handles movement logic of sprites. 
*/
public class Sprite {
	
	// Used to decide what direction an object is moving
	public static final int DIR_NONE = 0;
	public static final int DIR_LEFT = 1;
	public static final int DIR_RIGHT = 2;
	public static final int DIR_UP = 3;
	public static final int DIR_DOWN = 4;
	
	private Image image = null;
	private float x;
	private float y;
	
	
	/* Entities able to push blocks have coordinates 
	  here for the pushable blocks to use */ 
	private static float playerX;
	private static float playerY;
	private static float rogueX;
	private static float rogueY;
	
	
	// Constructor for loading from file
	public Sprite(String image_src, float x, float y) {
		try {
			image = new Image(image_src);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		this.x = x;
		this.y = y;
		snapToGrid();
	}
	
	// Copy Constructor for saving states of game 
	public Sprite(Sprite sprite) {
		this.x = sprite.x;
		this.y = sprite.y;
		this.image = sprite.image;
	}
	
	public void update(Input input, int delta) {
		
	}
	
	// Draw Sprites
	public void render(Graphics g) {
		image.drawCentered(x, y);
	}
	
	// Forces this sprite to align to the grid
	public void snapToGrid() {
		x /= App.TILE_SIZE;
		y /= App.TILE_SIZE;
		x = Math.round(x);
		y = Math.round(y);
		x *= App.TILE_SIZE;
		y *= App.TILE_SIZE;
	}
	
	/** Function for all movement logic. 
	 * Updates a blocks coordinates if the move is legal.
	 * <p>
	 * Another approach would be to override 
	 * this method in each movable blocks class and provide a different 
	 * implementation for each class, but this condensed approach was used instead.
	 * 
	 * @param dir Direction of movement being attempted 
	 */
	public void moveToDest(int dir) {
		float speed = 32;
		// Translate the direction to an x and y displacement
		float delta_x = 0,
			  delta_y = 0;
		
		switch (dir) {
			case DIR_LEFT:	
				delta_x = -speed;
				break;
			case DIR_RIGHT:
				delta_x = speed;
				break;
			case DIR_UP:
				delta_y = -speed;
				break;
			case DIR_DOWN:
				delta_y = speed;
				break;
		}
		
		// Player has moved so save the state of all sprites.
		// Also tell Rogue and Mage to move.
		if(this instanceof Player && dir!= DIR_NONE) {
			Loader.setPreviousState();
			Rogue.playerMoved();
		}
		
		// For explosion
		if(this instanceof TNT && Location.isCracked(x + delta_x, y + delta_y)) {
			x += delta_x;
			y += delta_y;
			return;
		}
		
		// So pushable blocks are treated like walls for Skeleton and Mage
		if( (this instanceof Skeleton) 
				&& Location.isPushable(x + delta_x, y + delta_y) ) {
			return;
		}
		
		// Make sure the position isn't a wall.
		if(Location.isBlocked(x + delta_x, y + delta_y)) {
			return;
		}
		// Make sure ice block stops on contact with another pushable.
		if(this instanceof Pushable && Location.isPushable(x + delta_x, y + delta_y) ) {
			return;
		}
		
		// Player/Rogue interaction with block 
		if (Location.isPushable(x + delta_x, y + delta_y)) {
			// Pushing block into block
			if(Location.isPushable(x + (2*delta_x), y + (2*delta_y))) {
				return;
			}
			// If trying to push TNT block onto cracked wall	
			if(Location.isTNT(x + delta_x, y + delta_y) 
			   && Location.isCracked(x + (2* delta_x), y + (2 * delta_y))) {
				x += delta_x;
				y += delta_y;
				return;
			}
			
			// Pushing block into wall
			if(Location.isBlocked(x + (2*delta_x), y + (2*delta_y))) {
				return;
			}
					
		}
			// If reaches here then block is free to move		
			x += delta_x;
			y += delta_y;	
	}
	
	
	// Various Accessor methods and Mutator methods
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}

	public float getPlayerX() {
		return playerX;
	}
	public float getPlayerY() {
		return playerY;
	}
	public float getRogueX() {
		return rogueX;
	}
	public float getRogueY() {
		return rogueY;
	}
	public static void setRogueCoords(float x, float y) {
		rogueX = x;
		rogueY = y;
	}
	public static void setPlayerCoords(float x, float y) {
		playerX = x;
		playerY = y;
	}
	
	public void changeImage(String filename) throws SlickException {
		image = new Image(filename);
	}
	public void clearImage() throws SlickException {
		// Just move sprite off the screen
		x = 100000;
		y = 100000;
	}
	public Image getImage() {
		return image;
	}
	
	public void setCoords(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	
}
