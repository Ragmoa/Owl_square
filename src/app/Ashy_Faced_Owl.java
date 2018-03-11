package app;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public class Ashy_Faced_Owl extends Owl{
	public Ashy_Faced_Owl(Vector2 pos, String thread_name, Board b, Group root) {
		super(pos,thread_name,b,root);//ces valeurs-là sont set indépendemment du type d'oiseau.
		this.speed=15;
		this.taille=30;
		createCircle(pos, this.taille, Color.DARKGOLDENROD);
		t.start();// On démarre le thread du piaf, ça lance Owl.run(). Je voulais le mettre dans le constructeur de Owl, mais je suis obligé de l'apeller en permier... Alors que le démarrage du thread c'est le truc a faire en dernier.
	}
}
