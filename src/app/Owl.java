package app;
public abstract class Owl implements Runnable {
	
	//Classe abstraite pour les piafs, permet aussi de gérer le threading de chaque classe.
	
	
	private long cycle_interval= (long) 0.5; // intervalle de temps (en ms) entre 2 appels d'IA.
	
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

	}

	public void run(){
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
			if (b.get_food().size()>0) {
				float min_magnetude=99999;
				int min_index=-1,i=0;
				
			}

		}
		//TODO:Chercher de la nourriture
		//Si on en trouve on se déplace vers la plus proche.
		//Si on est dessus, on la mange, si elle est encore fraiche.
		//Sinon on dort	
	}
	
	
}
