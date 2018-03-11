package app;

import java.util.Random;

import javafx.scene.Group;

public class Golden_Masked_Owl extends Owl{
	public Golden_Masked_Owl(Vector2 pos, String thread_name, Board b, Group root) {
		super(pos,thread_name,b);//ces valeurs-là sont set indépendemment du type d'oiseau.
		Random rand=new Random();
		this.speed= rand.nextInt((7 - 3) + 1) + 3;
		this.taille=50;
		t.start();// On démarre le thread du piaf, ça lance Owl.run(). Je voulais le mettre dans le constructeur de Owl, mais je suis obligé de l'apeller en permier... Alors que le démarrage du thread c'est le truc a faire en dernier.
	}
}