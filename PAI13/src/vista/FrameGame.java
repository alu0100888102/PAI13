package vista;

import javax.swing.*;
import javax.swing.Timer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class FrameGame extends JFrame{
	private Battlefield panel;
	private Timer fps;
	public static int TIME = 100;
	
	public FrameGame(){
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(900, 700);

		setFps(new Timer(10, new TimeListener()));
		
		setPanel(new Battlefield());
		this.add(getPanel(), BorderLayout.CENTER);
		
		JButton info = new JButton();
		JPanel buttons = new JPanel();
		buttons.add(info);
		this.add(buttons, BorderLayout.SOUTH);
		info.addActionListener(new EmpezarListener());
		info.setIcon(new ImageIcon("assets/info.png"));
		info.setBounds(0, 0, 100, 100);
		info.setMargin(new Insets(0, 0, 0, 0));
		info.setBorder(null);
		getFps().start();
	}

	public void start(){
		
	}
	
	public Timer getFps() {
		return fps;
	}

	public void setFps(Timer fps) {
		this.fps = fps;
	}

	public Battlefield getPanel() {
		return panel;
	}

	public void setPanel(Battlefield panel) {
		this.panel = panel;
	}
	
	private class EmpezarListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			InfoFrame info = new InfoFrame();
			info.setVisible(true);
			
		}
		
	}
	
	private class TimeListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			getPanel().repaint();
		}
	}	
}
