package app;

public class Vector2 {
	private float x,y;
	
	Vector2(float x, float y){
	this.x=x;
	this.y=y;
	}
	
	public float magnitude() {// renvoie la longeur du vecteur.
		float m;
		m=(x*x)+(y*y);
		return (float) Math.sqrt(m);
	}

	public Vector2 minus(Vector2 v) {//realise l'opération this-v, et retorune le vecteur obtenu.
		return new Vector2(this.x-v.x,this.y-v.y);
	}
	
	public Vector2 normalize() {// retourne un Vector2 de longeur 1 selon la direction de this
		return new Vector2(this.x/this.magnitude(),this.y/this.magnitude());
	}
	
	public Vector2 times(float f) { //retourne le vecteur multiplié par f
		return new Vector2 (this.x*f,this.y*f);
	}
	public Vector2 plus (Vector2 v) {//ajoute les deux Vecteurs
		return new Vector2(this.x+v.x,this.y+v.y);
	}
	
	//Getters and Setters
	
	public float get_x() {
		return x;
	}
	
	public float get_y() {
		return y;
	}
}
