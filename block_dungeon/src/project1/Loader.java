package project1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
/**
 * Loads game worlds from file.
 * Saves states of game world into a stack for undoing.
 * Has current state of game being used (blocks).
 * and all previous game states (stack).
*/
public class Loader {
	private static ArrayList<Sprite> blocks;	
	
	private static Stack stack; //Stack holds all previous states of sprites
	
	private static int world_width;
	private static int world_height;
	private static int offset_x;
	private static int offset_y;
	
	/**
	 * Create the appropriate sprite given a name and location.
	 * @param name	The name of the sprite
	 * @param x		The x position
	 * @param y		The y position
	 * @return		The sprite object
	 */
	private static Sprite createSprite(String name, float x, float y) {
		switch (name) {
			case "wall":
				return new Wall(x, y);
			case "floor":
				return new Floor(x, y);
			case "stone":
				return new Stone(x, y);
			case "target":
				return new Target(x, y);
			case "ice":
				return new Ice(x, y);
			case "explosion":
				return new Explosion(x, y);
			case "tnt":
				return new TNT(x, y);
			case "player":
				return new Player(x, y);
			case "skeleton":
				return new Skeleton(x, y);
			case "rogue":
				return new Rogue(x, y);
			case "cracked":
				return new Cracked(x, y);
			case "switch":
				return new Switch(x, y);
			case "door":
				return new Door(x, y);
			
			
		}
		return null;
	}
	
	// Copy constructors 
	private static Sprite createSprite(Sprite sprite) {
		if(sprite instanceof Player){
			return new Player(sprite);
		}
		if(sprite instanceof Wall){
			return new Wall(sprite);
		}
		if(sprite instanceof Floor){
			return new Floor(sprite);
		}
		if(sprite instanceof Stone){
			return new Stone(sprite);
		}
		if(sprite instanceof Target){
			return new Target(sprite);
		}
		if(sprite instanceof Ice){
			return new Ice(sprite);
		}
		if(sprite instanceof Explosion){
			return new Explosion(sprite);
		}
		if(sprite instanceof TNT){
			return new TNT(sprite);
		}
		if(sprite instanceof Skeleton){
			return new Skeleton(sprite);
		}
		if(sprite instanceof Rogue){
			return new Rogue(sprite);
		}
		if(sprite instanceof Cracked){
			return new Cracked(sprite);
		}
		if(sprite instanceof Switch) {
			return new Switch(sprite);
		}
		if(sprite instanceof Door) {
			return new Door(sprite);
		}
		return null;		
	} 
	
	// Load previous state of sprites. (For undo)
	public static ArrayList<Sprite> getPreviousState() {
		blocks = (ArrayList<Sprite>)stack.pop();
		Player.undo();
		Rogue.undo();
		return blocks;
	}
	
	/** Each time player moves, save state of all sprites. */
	public static void setPreviousState() {
		ArrayList<Sprite> previousState = new ArrayList<>();
		
		for(Sprite sprite : blocks) {
			previousState.add(createSprite(sprite));
		}
		
		for(Sprite sprite: previousState) {
			if(sprite instanceof Player) {
				Sprite.setPlayerCoords(sprite.getX(), sprite.getY());
			}
		}
		
		// Save state onto stack
		stack.push(previousState);
		
	}
		
	// Load sprites from file
	public static ArrayList<Sprite> loadSprites(String filename) {
		blocks = new ArrayList<>();
		
		// Open the file
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line;
			
			// Find the world size
			line = reader.readLine();
			String[] parts = line.split(",");
			world_width = Integer.parseInt(parts[0]);
			world_height = Integer.parseInt(parts[1]);
			
			// Calculate the top left of the tiles so that the level is
			// centred
			offset_x = (App.SCREEN_WIDTH - world_width * App.TILE_SIZE) / 2;
			offset_y = (App.SCREEN_HEIGHT - world_height * App.TILE_SIZE) / 2;

			// Loop over every line of the file
			while ((line = reader.readLine()) != null) {
				String name;
				float x, y;
				
				// Split the line into parts
				parts = line.split(",");
				name = parts[0];
				x = Integer.parseInt(parts[1]);
				y = Integer.parseInt(parts[2]);
				
				// Adjust for the grid
				x = offset_x + x * App.TILE_SIZE;
				y = offset_y + y * App.TILE_SIZE;
				//System.out.println("(" + x + "," + y + ")");
				// Create the sprite
				blocks.add(createSprite(name, x, y));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return blocks;
	}

	public static void createStack() {
		stack = new Stack();
	}
	
	public static ArrayList<Sprite> getSprites() {
		return blocks;
	}
	


}

