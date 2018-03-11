package app;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public class Golden_Masked_Owl extends Owl{
	public Golden_Masked_Owl(Vector2 pos, String thread_name, Board b, Group root) {
		super(pos,thread_name,b,root);//ces valeurs-là sont set indépendemment du type d'oiseau.
		this.speed=5;
		this.taille=50;
		createCircle(pos, this.taille, Color.GOLD);
		t.start();// On démarre le thread du piaf, ça lance Owl.run(). Je voulais le mettre dans le constructeur de Owl, mais je suis obligé de l'apeller en permier... Alors que le démarrage du thread c'est le truc a faire en dernier.
	}
}