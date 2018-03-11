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
	private Board b;

	public Food(Vector2 pos, float f, Group root, Board b) {
		this.pos=pos;
		this.freshness=f;
		this.root=root;
		this.b=b;
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
		t.stop();
	}
	
	public void getting_older() {
		this.freshness--;
		if(!this.isFresh()) {
			circle.get_foodBody().setFill(Color.BROWN);
//			FillTransition ft = new FillTransition(Duration.millis(1000), circle.get_foodBody(), Color.GREEN, Color.BROWN);
//			ft.setAutoReverse(true);
//			ft.play();
		}
		if (this.freshness<-5.0) {
			synchronized(b.get_food()) {
			int i=0;
			for (i=0;i<b.get_food().size();i++) {
				if (b.get_food().get(i).get_pos().get_x()==pos.get_x() && b.get_food().get(i).get_pos().get_y()==pos.get_y() ) {
					b.get_food().remove(i);
				}
			}
			removeFoodCircle();
			}
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
