package project1;

import java.util.ArrayList;
/**
 * Utility class for game.
 * Compares locations of Sprites.
 * Gets currently loaded state of all blocks from Loader.
*/
public final class Location {
	
		/** Determines if the location is blocked. 
		 * Other 'is' functions have similar structure.
		 * @param x  X coordinate
		 * @param y  Y coordinate
		 * @return boolean Whether position is blocked
		 */
		public static boolean isBlocked(float x, float y){
			ArrayList<Sprite> blocks = Loader.getSprites();
			for (Sprite sprite : blocks) {
				if( ((sprite instanceof Wall || sprite instanceof Cracked || sprite instanceof Door) 
						&& Location.sameLocation(sprite.getX(), sprite.getY(), x, y) )) {
					return true;
				}

			}
			return false;
		}
		
		// Returns true if that location is an enemy tile
		public static boolean isEnemy(float x, float y) {
			ArrayList<Sprite> blocks = Loader.getSprites();
			for (Sprite sprite : blocks) {
				if( (sprite instanceof Enemy) && 
						Location.sameLocation(sprite.getX(), sprite.getY(), x, y) ) {
					return true;
				}
			}
			return false;
			
		}
		
		
		// Returns true if that location is a TNT tile
		public static boolean isTNT(float x, float y) {
			ArrayList<Sprite> blocks = Loader.getSprites();
			for (Sprite sprite : blocks) {
				if( (sprite instanceof TNT) && 
						Location.sameLocation(sprite.getX(), sprite.getY(), x, y) ) {
					return true;
				}
			}
			return false;
		} 
		
		// Returns true if that location is a Cracked tile
		public static boolean isCracked(float x, float y){
			ArrayList<Sprite> blocks = Loader.getSprites();
			
			for (Sprite sprite : blocks) {
				if( (sprite instanceof Cracked) && 
						Location.sameLocation(sprite.getX(), sprite.getY(), x, y) ) {
					return true;
				}
			}
			return false;
		}
		
		// Returns true if that location is a pushable tile
		public static boolean isPushable(float x, float y) {
			ArrayList<Sprite> blocks = Loader.getSprites();
			for (Sprite sprite : blocks) {
				if( (sprite instanceof Pushable) && 
						Location.sameLocation(sprite.getX(), sprite.getY(), x, y) ) {
					return true;
				}
			}
			return false;
		}
		
		/**
		 * Method finds which cracked wall the TNT Block 
		 * is on and breaks this specific cracked wall. 
		 * @param x  X coordinate
		 * @param y  Y coordinate
		 */
		// incase level has mutiple TNT blocks and multiple cracked walls
		public static void breakCrackedWall(float x, float y) {
			ArrayList<Sprite> blocks = Loader.getSprites();
			for (Sprite sprite : blocks) {
				if( (sprite instanceof Cracked) && 
						Location.sameLocation(sprite.getX(), sprite.getY(), x, y) ) {
					
					Cracked temp = (Cracked)sprite;
					temp.breakWall();
				}
			}	
		}
		
		/** Location compare function
		 * @param x  X coordinate
		 * @param y  Y coordinate
		 * @return boolean Whether locations equal
		 */
		public static boolean sameLocation(float x1, float y1, float x2, float y2)
		{
			if(Float.compare(x1, x2) == 0 && Float.compare(y1, y2) == 0)
			{
				return true;
			}
			return false;		
		}

}
