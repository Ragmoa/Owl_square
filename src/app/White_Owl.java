package app;

import java.util.Random;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public class White_Owl extends Owl {
	
	//Y'a besoin d'un nom de thread, on peut juste lui filer un numéro qu'on va incrémenter au fur et a mesure.
	public White_Owl(Vector2 pos, String thread_name, Board b) {
		super(pos,thread_name,b);//ces valeurs-là sont set indépendemment du type d'oiseau.
		Random rand=new Random();
		this.speed= rand.nextInt((12 - 6) + 1) + 6;
		this.taille=20;
		//createCircle(pos, this.taille, Color.WHITE);
		t.start();// On démarre le thread du piaf, ça lance Owl.run(). Je voulais le mettre dans le constructeur de Owl, mais je suis obligé de l'apeller en permier... Alors que le démarrage du thread c'est le truc a faire en dernier.
	}
}