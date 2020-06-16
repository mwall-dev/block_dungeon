package project1;

import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
/**
 * Updates and renders the game world.
*/
public class World {
	private ArrayList<Sprite> sprites;
	
	public World(int levelNumber) {
		Loader.createStack();
		sprites = Loader.loadSprites("res/levels/" + levelNumber + ".lvl");
	}
	
	// Load previous state of all sprites
	public void undo() {
		sprites = Loader.getPreviousState();
	}
	
	
	public void update(Input input, int delta) {
		for (Sprite sprite : sprites) {
			if (sprite != null) {
				sprite.update(input, delta);
			}
		}
	}
	
	public void render(Graphics g) {
		for (Sprite sprite : sprites) {
			if (sprite != null) {
				sprite.render(g);
			}
		}
	}
	
	
	// Checks if all targets have blocks on them 
	public boolean isLevelFinished() {
		for (Sprite sprite : sprites) {
			if (sprite != null && sprite instanceof Target) {
				Target temp = (Target)sprite;
				if(temp.hasStone() == false)
				{
					return false;
				}
			}
		}
		return true;
	}
	
			
	
}

