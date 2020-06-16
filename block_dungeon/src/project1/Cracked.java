package project1;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Cracked extends Sprite {
	private int time = 0;
	private boolean wallBroke = false;
	
	
	public Cracked(float x, float y) {
		super("res/cracked_wall.png", x, y);
	}
	public Cracked(Sprite sprite)
	{
		super(sprite);
	}
	
	public void update(Input input, int delta)
	{		
    	if(wallBroke)
    	{
    		try {
    			explode();
    		} catch (SlickException e) {
    			e.printStackTrace();
    		}
    		
    		if(time<400)
    		{
    			time += delta;
    		}
    		else {
    			try {
    				clearImage();
    			}
    			catch (SlickException e) {
    				e.printStackTrace();
    			}
    			wallBroke = false;
    		}
    		
    	}
    	
  
    }
	public void breakWall()
	{
		wallBroke = true;
	}
    
    public void explode() throws SlickException
    {
    	changeImage("res/explosion.png");	
    }
	
  
	
	
	
}
