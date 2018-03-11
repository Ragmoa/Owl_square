package app;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class OwlCircle extends Parent{
	private Vector2 center;
	private int radius;
	private Color color;
	private Circle owlBody;
	
	public OwlCircle(Vector2 center, int radius, Color color) {
		this.center=center;
		this.radius=radius;
		this.color=color;
		
		this.owlBody = new Circle(center.get_x(), center.get_y(), radius);
		owlBody.setFill(color);
		owlBody.setStroke(Color.BLACK);
		this.getChildren().add(owlBody);
	}
	
	public Circle get_owlBody() {
		return owlBody;
	}
}
