package modelo;

public class Cannon {
	public static int SPEED = 1500;
	private double angle;
	private Bola bola;
	private int x;
	private int y;
	
	public Cannon(){
		setAngle(0);
		setX(0);
		setY(0);
		reload();
	}
	public Cannon(int x, int y){
		setAngle(0);
		setX(x);
		setY(y);
		reload();
	}
	
	public double getAngle() {
		return angle;
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}
	public Bola getBola() {
		return bola;
	}
	public void setBola(Bola bola) {
		this.bola = bola;
	}
	
	public void reload(){
		setBola(new Bola(getX(), getY()));
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

	public Bola fire(){
		Bola b = getBola();
		b.setAngle(getAngle());
		b.setSpeed(SPEED);
		reload();
		return b;
	}
}
