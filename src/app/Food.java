package app;

import javafx.animation.FillTransition;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Food extends Parent{
	
	private Vector2 pos;
	private float freshness;
	private Circle foodBody;

	public Food(Vector2 pos, float f) {
		this.pos=pos;
		this.freshness=f;
		
		this.pos=pos;
		foodBody = new Circle(pos.get_x(), pos.get_y(), 5);
		foodBody.setFill(Color.GREEN);
		foodBody.setStroke(Color.BLACK);
		this.getChildren().add(foodBody);
		System.out.println("Food crée.");
	}
	
	public boolean isFresh() {
		if (freshness>0) {
			return true;
		} else {
			return false;
		}
	}

	public void removeFoodCircle() {
		foodBody.setOpacity(0);
	}
	
	public void getting_older() {
		this.freshness--;
		if(!this.isFresh()) {
			FillTransition ft = new FillTransition(Duration.millis(1000), foodBody, Color.GREEN, Color.BROWN);
			ft.setAutoReverse(true);
			ft.play();
		}
		if (this.freshness<-500.0) {
			removeFoodCircle();
		}
	}

	//Getters and Setters
	
	public Vector2 get_pos() {
		return pos;
	}
	
	public float get_freshness() {
		return freshness;
	}
}
