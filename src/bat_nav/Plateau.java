package bat_nav;

import java.awt.Graphics;
import javax.swing.JPanel;

public class Plateau extends JPanel{
	//Contient le placement des bateaux
	//Contient les coups joués précédemment

	int[][] plateau;
	int[][] viseur;
	String joueur;
	int taille;
	int absShot;
	int ordShot;
	
	Plateau(int t, String joueur)
	{
		super();
		taille=t;
		this.joueur=joueur;
		plateau = new int[taille][taille];
		viseur = new int[taille][taille];
		this.setSize(400,400);
		this.repaint();
	}
	
	public int coupJoue(int a, int b)
	{
		if(plateau[a][b]==1)
		{
			plateau[a][b] = -1;
		}
		return -plateau[a][b];
		
	}
	
	public void resultatTir(int a)
	{
		viseur[absShot][ordShot] = a;
	}
	
	public void cible (int a, int b)
	{
		absShot = a;
		ordShot = b;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.drawRect(50, 50, 200, 200);
		g.drawLine(150, 50, 150, 250);
		g.drawLine(50, 150, 250, 150);
		g.drawLine(50, 50, 250, 250);
		g.drawLine(50, 250, 250, 50);
		
		g.drawRect(50, 350, 200, 200);
		g.drawLine(150, 350, 150, 550);
		g.drawLine(50, 450, 250, 450);
		g.drawLine(50, 350, 250, 550);
		g.drawLine(50, 550, 250, 350);
	}


}
