package app;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class FoodCircle extends Parent{
	private Circle foodBody;
	
	public FoodCircle(Vector2 center) {
		foodBody = new Circle(center.get_x(), center.get_y(), 5);
		foodBody.setFill(Color.GREEN);
		foodBody.setStroke(Color.BLACK);
		this.getChildren().add(foodBody);
	}
	
	public Circle get_foodBody() {
		return foodBody;
	}
}

