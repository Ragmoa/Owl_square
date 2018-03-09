package app;
public class Food {
	
	private Vector2 pos;
	private float freshness;

	public Food(Vector2 pos, float f) {
		this.pos=pos;
		this.freshness=f;
	}
	
	public boolean isFresh() {
		if (freshness>0) {
			return true;
		} else {
			return false;
		}
	}
}
