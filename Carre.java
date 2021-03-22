import java.io.*;
public class Carre implements Serializable{
	/** Les coordonnées du carré*/
	private int x,y;
	
	/**
	Constructeur de Carre prenant des coordonnées
	@param x X du carré
	@param y Y du carré
	*/
	public Carre(int x,int y){
		this.x=x;
		this.y=y;
	}
	/**
	Constructeur de Carre faisant une copie d'un autre Carre
	@param car Carre que l'on veut copier
	*/
	public Carre(Carre car){
		this.x=car.x;
		this.x=car.y;
	}
	
	public boolean equals(Carre c){
		return c.x==this.x && c.y==this.y;
	}
	
	public void setX(int x){
		this.x=x;
	}
	public void setY(int y){
		this.y=y;
	}
	public void setXY(int x,int y){
		this.x=x;
		this.y=y;
	}
	
	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
	}
	
	public String toString(){
		return "("+this.x+","+this.y+")";
	}
}
