package app;

public abstract class Owl implements Runnable {
	
	//Classe abstraite pour les piafs, permet aussi de gérer le threading de chaque classe.
	
	
	private long cycle_interval= (long) 50; // intervalle de temps (en ms) entre 2 appels d'IA.
	
	protected int speed;
	protected int taille;
	protected Thread t;
	protected Vector2 pos;//la position du piaf en question.
	protected Board b; //le board.
	protected String name;//nom du thread et de l'oiseau, principalement utilisé pour le débug.
	
	public Owl(Vector2 pos, String thread_name, Board b) {
		this.pos=pos;
		this.name=thread_name;
		this.b=b;
		t=new Thread(this,thread_name);
		//this.root=root;
	}

	public void run(){
		System.out.println(name + " crée!");
		do {
			try {
				Thread.sleep(cycle_interval);
			} catch (InterruptedException e) {
				System.err.println("Le sommeil de l\'oiseau: " + name + " a été interrompu! Ça ne devrait pas arriver! :o");
				e.printStackTrace();
			}
			owl_ia();//Jouer son tour
		}while (true);// VERS L'INFINI ET AU DELA!
	}
	
	private void owl_ia() {
		synchronized(b.get_food()) {
			if(b.get_danger().getIsActive()) {//danger
				move_away(b.get_danger().getPos());
			}
			else if (b.get_food().size()>0) {
				float min_magnitude=99999, m;
				int min_index=-1,i=0;
				for (i=0;i<b.get_food().size();i++) {//On cherche la nourriture la plus proche.	
					m=pos.minus(b.get_food().get(i).get_pos()).magnitude();
					if (m<min_magnitude) {//Une nourriture pas fraiche n'est pas attirante
						min_magnitude=m;
						min_index=i;
					}
				}

				if (min_magnitude<speed) {// Si la nourriture est a portée, on se jette dessus pour la dévorer
					this.pos=b.get_food().get(min_index).get_pos();
					System.out.println(name + " veut manger un truc.");
					if (b.get_food().get(min_index).isFresh()) {// On vérifie quand même qu'elle soit fraiche.
						b.get_food().get(min_index).removeFoodCircle();
						b.get_food().remove(min_index);
						System.out.println(name + " a mangé un truc vert.");
					}
					else {
						b.get_expired().add(b.get_food().get(min_index));
						b.get_food().remove(min_index);						
						System.out.println("C'est périmé ! " + name + " ne mange pas de la nourriture avariée.");
					}
				} else {//Si la nourriture n'est pas a portée.
					move_towards(b.get_food().get(min_index).get_pos());
				}	
			} else {
				//System.out.println(name + " dort pour ce tour!");
			}
		}//On libère le verrou
	}

	private void move_towards(Vector2 fpos) {
		Vector2 mv=(fpos.minus(pos)).normalize().times(speed);//On calcule le vecteur parcouru
		mv=mv.plus(pos);//On calcule la nouvelle position
		pos=new Vector2((float)Math.floor(mv.get_x()),(float)Math.floor(mv.get_y()));//  On applique le déplacement.
		this.stay_inside();
		//b.checkOwlPosition();
		//updateCircle();
	}
	
	private void move_away(Vector2 fpos) {
		Vector2 mv=(pos.minus(fpos)).normalize().times(speed);//On calcule le vecteur parcouru
		mv=mv.plus(pos);//On calcule la nouvelle position
		pos=new Vector2((float)Math.floor(mv.get_x()),(float)Math.floor(mv.get_y()));//  On applique le déplacement.
		this.stay_inside();

	}
	
	//Getters and Setters
	public Vector2 get_pos(){
		return this.pos;
	}
	
	public int get_size() {
		return this.taille;
	}
	
	public void stay_inside() {
		if (pos.get_x()<b.get_bounds()[0]) {
			pos= new Vector2(b.get_bounds()[0],pos.get_y());
		}
		else if (pos.get_x()>b.get_bounds()[1]) {
			pos= new Vector2(b.get_bounds()[1],pos.get_y());
		}
		if (pos.get_y()<b.get_bounds()[2]) {
			pos= new Vector2(pos.get_x(),b.get_bounds()[2]);
		}
		else if (pos.get_y()>b.get_bounds()[3]) {
			pos= new Vector2(pos.get_x(),b.get_bounds()[3]);
		}
	}
}
