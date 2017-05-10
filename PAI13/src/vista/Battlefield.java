package vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.util.*;
import modelo.*;

public class Battlefield extends JPanel {
	public static final int VIRTUAL_HEIGHT = 600;
	public static final int VIRTUAL_WIDTH = 1200;
	private ArrayList<Bola> bolas;
	private Bola proyectil;
	private Cannon cannon;
	
	public Battlefield(){
		setCannon(new Cannon(VIRTUAL_WIDTH/2, 0));
		setProyectil(getCannon().getBola());
		generateLevel();
		this.addMouseListener(new Mouselist());
	}
	
	public void generateLevel(){
		ArrayList<Bola> temp = new ArrayList<Bola>();
		int nbolas = VIRTUAL_WIDTH / (Bola.RADIUS*2);
		for(int i = 0; i< nbolas; i++){
			Bola b = new Bola(Bola.RADIUS + Bola.RADIUS*i*2, VIRTUAL_HEIGHT - Bola.RADIUS);
			temp.add(b);
		}
		setBolas(temp);
	}
	
	public ArrayList<Bola> getBolas() {
		return bolas;
	}
	public void setBolas(ArrayList<Bola> bolas) {
		this.bolas = bolas;
	}
	public Bola getProyectil() {
		return proyectil;
	}
	public void setProyectil(Bola proyectil) {
		this.proyectil = proyectil;
	}
	public Cannon getCannon() {
		return cannon;
	}
	public void setCannon(Cannon cannon) {
		this.cannon = cannon;
	}
	
	public int virtualX(int x){
		double xscale = (double)this.getWidth() / (double)VIRTUAL_WIDTH;
		
		return (int)(x*xscale);
	}
	
	public int virtualY(int y){
		double yscale = (double)this.getHeight() / (double)VIRTUAL_HEIGHT;
		return (int)((this.getHeight()) - (y*yscale));
	}
	
	public class Mouselist implements MouseListener {
		
		public void mouseClicked(MouseEvent e) {
			setProyectil(getCannon().fire());
		}

		@Override
		public void mouseEntered(MouseEvent arg0){}

		@Override
		public void mouseExited(MouseEvent arg0) {}

		@Override
		public void mousePressed(MouseEvent arg0) {}

		@Override
		public void mouseReleased(MouseEvent arg0) {}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//this.setSize(900, 600);
		Graphics2D g2 = (Graphics2D) g;
		double yscale = (double)this.getHeight() / (double)VIRTUAL_HEIGHT;
		double xscale = (double)this.getWidth() / (double)VIRTUAL_WIDTH;
		try{
			boolean hit = false;
			Bola hitted = null;
			for(Bola b : getBolas()){
					g.setColor(b.getColor());
					int xpos = virtualX(b.getX()-b.RADIUS);
					int ypos = virtualY(b.getY() + b.RADIUS);
					g.fillOval(xpos, ypos, (int)(b.RADIUS * 2  * xscale),(int)( b.RADIUS * 2  * yscale));
					if(b.isTouching(getProyectil()) && hit == false){
						hitted = b;
						hit = true;
					}
					else if(b.isTouching(getProyectil()) && hit == true){
						hit = false;
					}
			}
			
			if(hit == false && hitted != null){
				File soundFile = new File("assets/wrong.wav");
		        AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);              
		        Clip clip = AudioSystem.getClip();
		        clip.open(audioIn);
		        clip.start();
				setProyectil(getCannon().getBola());
			}
			if(hit == true && hitted != null){
				File soundFile = new File("assets/right.wav");
		        AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);              
		        Clip clip = AudioSystem.getClip();
		        clip.open(audioIn);
		        clip.start();
				setProyectil(getCannon().getBola());
				setProyectil(getCannon().getBola());
				getBolas().remove(hitted);
			}
			
			AffineTransform transform = g2.getTransform();
			int centerY = virtualY(getCannon().getY());
			int centerX = virtualX(getCannon().getX());
			int mouseY = MouseInfo.getPointerInfo().getLocation().y;
	        int mouseX = MouseInfo.getPointerInfo().getLocation().x;            
			double angle = Math.atan2(centerY - mouseY, centerX - mouseX) + Math.PI/2;
			
			getCannon().setAngle(angle);

			((Graphics2D)g).rotate(angle, centerX, centerY);

			g.setColor(Color.BLACK);
			g.fillRect(centerX-30, centerY, 60, 200); // draw your rectangle

			g2.setTransform(transform);
			
			g.setColor(getProyectil().getColor());
			getProyectil().actualizePos(FrameGame.TIME);
			int xpos = virtualX(getProyectil().getX()-Bola.RADIUS);
			int ypos = virtualY(getProyectil().getY() + Bola.RADIUS);
			g.fillOval(xpos, ypos, (int)(getProyectil().RADIUS * 2  * xscale),(int)( getProyectil().RADIUS * 2  * yscale));
			
			Bola loaded = getCannon().getBola();
			g.setColor(loaded.getColor());
			xpos = virtualX(loaded.getX()-Bola.RADIUS);
			ypos = virtualY(loaded.getY() + Bola.RADIUS);
			g.fillOval(xpos, ypos, (int)(loaded.RADIUS * 2  * xscale),(int)( loaded.RADIUS * 2  * yscale));
			
		}
		catch(Exception e){
			System.out.println("Error cargando la imagen");
			System.exit(1);
		}
	}
}
