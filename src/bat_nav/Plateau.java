package bat_nav;

import java.awt.*;

import javax.swing.*;

public class Plateau extends JPanel{
	//Contient le placement des bateaux
	//Contient les coups joués précédemment

	int[][] plateau;
	int[][] viseur;
	int taille;
	int absShot;
	int ordShot;
	
	Plateau(int t)
	{
		super();
		taille=t;
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
		int marge = 20;
		super.paintComponent(g);
		int base=this.getHeight()/taille;
		
		for(int i = 1; i<taille; i++)
		{
			g.drawLine(base*i+marge, marge, base*i+marge, base*taille+marge);
			g.drawLine(marge, base*i+marge, base*taille+marge, base*i+marge);
		}
		
		/*g.drawRect(50, 50, 200, 200);
		
		g.drawLine(50, 150, 250, 150);
		
		
		g.drawRect(50, 350, 200, 200);
		g.drawLine(150, 350, 150, 550);
		g.drawLine(50, 450, 250, 450);*/
		
		//this.updateUI();
		System.out.println("Dessin.");
	}


}
