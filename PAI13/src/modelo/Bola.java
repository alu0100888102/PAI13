package modelo;

import java.awt.Color;
import java.util.Random;

public class Bola {
	public static final int RADIUS = 30;
	private int x;
	private int y;
	private double angle;
	private int speed;
	private Color color;
	
	public Bola(){
		setX(0);
		setY(0);
		setAngle(0);
		setSpeed(0);
		setColor(randomColor());
	}
	public Bola(int x, int y){
		setX(x);
		setY(y);
		setAngle(0);
		setSpeed(0);
		setColor(randomColor());
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public double getAngle() {
		return angle;
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void changeColor(){
		Color newcolor = randomColor();
		while(newcolor == getColor()){
			newcolor = randomColor();
		}
		setColor(newcolor);
	}
	
	public void actualizePos(int time){
		setY( (int) (getY() - Math.cos( getAngle() ) * getSpeed() / time ));
		setX( (int) (getX() - Math.sin( getAngle() ) * getSpeed() / time ));
	}
	
	public boolean isTouching(Bola b){
		double distance = Math.sqrt((this.getX() - b.getX()) * (this.getX() - b.getX()) + (this.getY() - b.getY()) * (this.getY() - b.getY()));
		if(distance <= RADIUS * 2)
			return true;
		else
			return false;
	}
	
	public Color randomColor(){
		Random randomGenerator = new Random();
		int temp = randomGenerator.nextInt(6);
    	switch(temp){
    	case 0: return Color.BLUE;
    	case 1: return Color.RED;
    	case 2: return Color.CYAN;
    	case 3: return Color.GREEN;
    	case 4: return Color.PINK;
    	case 5: return Color.YELLOW;
    	}
    	return Color.YELLOW;
	}

}
