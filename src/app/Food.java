package app;

import javafx.animation.FillTransition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Food implements Runnable {
	
	private Vector2 pos;
	private float freshness;
	protected Thread t;
	private FoodCircle circle;
	private Group root;

	public Food(Vector2 pos, float f, Group root) {
		this.pos=pos;
		this.freshness=f;
		this.root=root;
		createCircle();
		t=new Thread(this,"food"+pos.get_x());
		t.start();
	}
	
	public void run() {
		System.out.println("Food créee");
		do {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				System.err.println("Le sommeil de la food a été interrompu! Ça ne devrait pas arriver! :o");
				e.printStackTrace();
			}
			this.getting_older();
		}while (true);// VERS L'INFINI ET AU DELA!
	}
	
	public boolean isFresh() {
		if (freshness>0) {
			return true;
		} else {
			return false;
		}
	}
	
	private void createCircle() {
		circle=new FoodCircle(pos);
		root.getChildren().add(circle);
	}
	
	public void removeFoodCircle() {
		//root.getChildren().remove(circle);
		circle.get_foodBody().setOpacity(0);
	}
	
	public void getting_older() {
		if(this.freshness>0) this.freshness--;
		if(!this.isFresh()) {
			circle.get_foodBody().setFill(Color.BROWN);
//			FillTransition ft = new FillTransition(Duration.millis(1000), circle.get_foodBody(), Color.GREEN, Color.BROWN);
//			ft.setAutoReverse(true);
//			ft.play();
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
