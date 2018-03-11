package app;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public class Board {
	
	private List<Food> food;
	private List<Food> expiredFood;
	private List<Owl> owls;
	private List<OwlCircle> owlsCircles;
	private float mix,max,miy,may;// set aux coordonnées max et min de x et y pour le tableau.
	private Group root;
	private float default_freshness=50;
	private Danger danger;
	
	public Board(float mix, float max, float miy, float may, Group root) {
		food=new ArrayList<Food>();
		expiredFood=new ArrayList<Food>();
		owls=new ArrayList<Owl>();	
		owlsCircles=new ArrayList<OwlCircle>();
		this.mix=mix;
		this.max=max;
		this.miy=miy;
		this.may=may;
		this.root=root;
		init_board(5,2,1);
		DangerDraw rectangle=new DangerDraw(rd_pos(mix, max, miy, may));
		root.getChildren().add(rectangle);
		danger=new Danger(mix,max,miy,may,root,rectangle);
	}
	
	public void addFood(Vector2 pos, Group root) {
		synchronized(food) {//On lock la liste de nourriture.
			food.add(new Food(pos,default_freshness));
			return;// La liste est unlock ici.
		}
	}
	
	private void init_board(int owl1_number,int owl2_number,int owl3_number) {// les trois types d'oiseaux.
		synchronized(food){ // pour éviter que les oiseaux cherchent a lire dans la nourriture avant qu'on ait finit de les créer, on lock
			int i=0,j=0;
			for (i=0;i<owl1_number;i++) {
				owls.add(new White_Owl(rd_pos(mix,max,miy,may),new String("["+j+"] : White_Owl_"+i) ,this));
				owlsCircles.add(new OwlCircle(owls.get(j).get_pos(),owls.get(j).get_size(), Color.WHITE));
				j++;
			}
			for (i=0;i<owl2_number;i++) {
				owls.add(new Ashy_Faced_Owl(rd_pos(mix,max,miy,may),new String("["+j+"] : Ashy_Faced_"+i) ,this, root));
				owlsCircles.add(new OwlCircle(owls.get(j).get_pos(),owls.get(j).get_size(), Color.DARKGOLDENROD));
				j++;
			}
			for (i=0;i<owl3_number;i++) {
				owls.add(new Golden_Masked_Owl(rd_pos(mix,max,miy,may),new String("["+j+"] : Golden_Masked_"+i) ,this, root));
				owlsCircles.add(new OwlCircle(owls.get(j).get_pos(),owls.get(j).get_size(), Color.GOLD));
				j++;
			}
			
			for (OwlCircle owlCircle : owlsCircles) {
				root.getChildren().add(owlCircle);
			}
		}
	}
	
	private Vector2 rd_pos(float mix, float max, float miy, float may) {//Genère une position Random
		float x=(float)(Math.random()*max+mix);// 
		float y=(float)(Math.random()*may+miy);
		return new Vector2(x,y);
	}
	
	//Getters and Setters
	
	public List<Food> get_food() {
		return food;
	}
	
	public List<Owl> get_owl() {
		return owls;
	}
	
	public List<OwlCircle> get_owls_circles() {
		return owlsCircles;
	}
	
	public Danger get_danger() {
		return danger;
	}
	public float[] get_bounds() {
		float [] res={mix,max,miy,may};
		return res;
	}
	public List<Food> get_expired(){
		return expiredFood;
	}
}
