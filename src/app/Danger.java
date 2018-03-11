package app;

import java.util.Random;

import javafx.scene.Group;

public class Danger implements Runnable{
	protected Vector2 pos;
	protected Group root;
	protected Thread t;
	protected boolean isActive=false;
	protected DangerDraw rectangle;
	private float mix,max,miy,may;
	
	public Danger (float mix, float max, float miy, float may, Group root, DangerDraw rectangle) {
		this.mix=mix+20;
		this.max=max-20;
		this.miy=miy+20;
		this.may=may-20;
		this.root=root;
		this.rectangle=rectangle;
		t=new Thread(this,"danger");
		
		pos=rd_pos(mix, max, miy, may);
		t.start();
	}
	
	public void run() {
		do {
			try {
				Thread.sleep(2500);
			} catch (InterruptedException e) {
				System.err.println("Le sommeil du danger a été interrompu! Ça ne devrait pas arriver! :o");
				e.printStackTrace();
			}
			randomPop();//
		}while (true);// VERS L'INFINI ET AU DELA!		
	}
	
	private void randomPop() {
		Random rand=new Random();
		int randomNum = rand.nextInt((3 - 0) + 1) + 0;
		if(randomNum==0) { //25% chance
			isActive=true;
			rectangle.getRectangle().setOpacity(1);
			System.out.println("Danger !");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				System.err.println("Le sommeil du randomPop du danger a été interrompu! Ça ne devrait pas arriver! :o");
				e.printStackTrace();
			}
			isActive=false;
			System.out.println("Fin danger !");
			rectangle.getRectangle().setOpacity(0);
			pos=rd_pos(mix,max,miy,may);
			rectangle.updateRectangle(pos);
		}
		else {
			System.out.println("Pas de danger");
		}
	}
	
	private Vector2 rd_pos(float mix, float max, float miy, float may) {//Genère une position Random
		float x=(float)(Math.random()*max+mix);// 
		float y=(float)(Math.random()*may+miy);
		return new Vector2(x,y);
	}
	
	public boolean getIsActive() {
		return isActive;
	}
	
	public Vector2 getPos() {
		return pos;
	}
}
