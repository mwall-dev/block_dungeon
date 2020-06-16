package project1;

public class Floor extends Sprite {
	public Floor(float x, float y) {
		super("res/floor.png", x, y);
	}
	public Floor(Sprite sprite)
	{
		super(sprite);
	}
}
