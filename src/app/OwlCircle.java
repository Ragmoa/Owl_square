package app;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class OwlCircle extends Parent{
	private Vector2 center;
	private int radius;
	private Color color;
	
	public OwlCircle(Owl chouette) {
		this.center=chouette.get_pos();
		this.radius=chouette.get_size();
		
		Circle owlBody = new Circle(25, 25, 20);
		owlBody.setFill(Color.LIGHTBLUE);
		owlBody.setStroke(Color.BLACK);
		this.getChildren().add(owlBody);
	}
}
