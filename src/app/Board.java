package app;

import java.util.ArrayList;
import java.util.List;

public class Board {// La Board aura probablement son propre thread aussi? ou alors un pour l'affichage
	
	private List<Food> food;
	private List<Owl> owls;//la liste des oiseaux, sert pour le random scare.
	private float mix,max,miy,may;//TODO: set aux coordonnées max et min de x et y pour le tableau.
	
	private float default_freshness;
	
	public Board(float mix, float max, float miy, float may) {
		food=new ArrayList<Food>();
		owls=new ArrayList<Owl>();
		init_board(3,0,0);
		this.mix=mix;
		this.max=max;
		this.miy=miy;
		this.may=may;
	}
	
	public void addFood(Vector2 pos) {
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
				j++;
			}
			// TODO: Faire de même pour les deux autres types de chouettes.
		}
	}
	
	private Vector2 rd_pos(float mix, float max, float miy, float may) {//Genère une position Random
		float x=(float)(Math.random()*max+mix);// 
		float y=(float)(Math.random()*may+miy);
		System.out.println(x + " " + y);
		return new Vector2(x,y);
	}
		//Getters and Setters
	
	public List<Food> get_food() {
		return food;
	}
	
	public List<Owl> get_owl() {
		return owls;
	}
}
