package app;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DangerDraw extends Parent{
	protected Vector2 pos;
	private Rectangle dangerBody;
	
	public DangerDraw(Vector2 pos) {
		this.dangerBody = new Rectangle(pos.get_x(),pos.get_y(),50,50);
		dangerBody.setFill(Color.RED);
		dangerBody.setStroke(Color.BLACK);
		dangerBody.setOpacity(0);
		this.getChildren().add(dangerBody);
	}
	
	public void updateRectangle(Vector2 pos) {
		dangerBody.setX(pos.get_x());
		dangerBody.setY(pos.get_y());
	}
	
	public Rectangle getRectangle() {
		return this.dangerBody;
	}
}
