package app;
public class White_Owl extends Owl {
	
	// Un exemple de piaf
	
	
	//Y'a besoin d'un nom de thread, on peut juste lui filer un num�ro qu'on va incr�menter au fur et a mesure.
	public White_Owl(Vector2 pos, String thread_name, Board b) {
		super(pos,thread_name,b);//ces valeurs-l� sont set ind�pendemment du type d'oiseau.
		this.speed=8;
		this.taille=1;
		t.start();// On d�marre le thread du piaf, �a lance Owl.run(). Je voulais le mettre dans le constructeur de Owl, mais je suis oblig� de l'apeller en permier... Alors que le d�marrage du thread c'est le truc a faire en dernier.
	}

}