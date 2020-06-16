package project1;

public class Explosion extends Sprite {
	public Explosion(float x, float y) {
		super("res/explosion.png", x, y);
	}
	public Explosion(Sprite sprite)
	{
		super(sprite);
	}

}
