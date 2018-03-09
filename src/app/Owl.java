package app;
public abstract class Owl implements Runnable {
	
	//Classe abstraite pour les piafs, permet aussi de g�rer le threading de chaque classe.
	
	
	private long cycle_interval= (long) 0.5; // intervalle de temps (en ms) entre 2 appels d'IA.
	
	protected int speed;
	protected int taille;
	protected Thread t;
	protected Vector2 pos;//la position du piaf en question.
	protected Board b; //le board.
	protected String name;//nom du thread et de l'oiseau, principalement utilis� pour le d�bug.
	
	public Owl(Vector2 pos, String thread_name, Board b) {
		this.pos=pos;
		this.name=thread_name;
		this.b=b;
		t=new Thread(this,thread_name);
	}

	public void run(){
		do {
			try {
				Thread.sleep(cycle_interval);
			} catch (InterruptedException e) {
				System.err.println("Le sommeil de l\'oiseau: " + name + " a �t� interrompu! �a ne devrait pas arriver! :o");
				e.printStackTrace();
			}
			owl_ia();//Jouer son tour
		}while (true);// VERS L'INFINI ET AU DELA!
	}
	
	private void owl_ia() {
		synchronized(b.get_food()) {
			if (b.get_food().size()>0) {
				float min_magnitude=99999, m;
				int min_index=-1,i=0;
				for (i=0;i<b.get_food().size();i++) {//On cherche la nourriture la plus proche.
					m=pos.minus(b.get_food().get(i).get_pos()).magnitude();
					if (m<min_magnitude) {
						min_magnitude=m;
						min_index=i;
					}
				}
				if (min_magnitude<speed) {// Si la nourriture est a port�e, on se jette dessus pour la d�vorer
					this.pos=b.get_food().get(min_index).get_pos();
					if (b.get_food().get(min_index).isFresh()) {// On v�rifie quand m�me qu'elle soit fraiche.
						b.get_food().remove(min_index);
					}
				} else {//Si la nourriture n'est pas a port�e.
					move_towards(b.get_food().get(min_index).get_pos());
				}	
			}
		}//On lib�re le verrou
	}

	private void move_towards(Vector2 fpos) {
		Vector2 mv=(fpos.minus(pos)).normalize().times(speed);//On calcule le vecteur parcouru
		mv=mv.plus(pos);//On calcule la nouvelle position
		pos=new Vector2((float)Math.floor(mv.get_x()),(float)Math.floor(mv.get_y()));//  On applique le d�placement.
	}
	
	
}