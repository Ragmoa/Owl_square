package app;

import javafx.animation.FillTransition;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class FoodCircle extends Parent{
	private Circle foodBody;
	private Vector2 center;
	
	public FoodCircle(Vector2 center) {
		this.center=center;
		foodBody = new Circle(center.get_x(), center.get_y(), 5);
		foodBody.setFill(Color.GREEN);
		foodBody.setStroke(Color.BLACK);
		this.getChildren().add(foodBody);
	}
	
	public Circle get_foodBody() {
		return foodBody;
	}
}

