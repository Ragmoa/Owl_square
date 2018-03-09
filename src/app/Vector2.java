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

}
