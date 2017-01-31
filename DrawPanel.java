package Monopoly;


import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class DrawPanel extends JPanel{
	
	private ArrayList<Joueur> joueursOnThis = new ArrayList<Joueur>();
	private int[][] ovalPositions = {{1,11,1,11,1,11},{1,8,16,24,32}};
	
	public DrawPanel() {
		this.setOpaque(false); 
		this.setBackground( new Color(255, 0, 0, 0) );
	}
	  
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(joueursOnThis.size()>0){
			int index = 0;
			for (Joueur joueur : joueursOnThis){
				g.setColor(joueur.getColor());
				g.fillOval(ovalPositions[1][index],ovalPositions[0][index], 10, 10);
				index++;
			}
		}
	}
	
	public void addJoueur(Joueur joueur){
		this.joueursOnThis.add(joueur);
	}
	
	public void removeJoueur(Joueur joueur){
		ArrayList<Joueur> newList = new ArrayList<Joueur>();
		for(Joueur joueurOn : joueursOnThis){
			if(joueurOn != joueur){
				newList.add(joueurOn);
			}
		}
		joueursOnThis = newList;
		this.repaint();
		this.revalidate();
	}
}
