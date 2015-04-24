package bat_nav;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class Plateau extends JPanel{
	//Contient le placement des bateaux
	//Contient les coups joués précédemment

	int[][] plateau;
	int[][] viseur;
	public int taille;
	int phaseDeJeu;
	int choix;
	int base;
	int marge;
	int absShot;
	int ordShot;
	Traitement t;
	
	Plateau(int ta)
	{
		super();
		Traitement t = new Traitement(this);
		this.addMouseListener(t);
		taille=ta;
		phaseDeJeu = 0;
		choix = 0;
		plateau = new int[taille][taille];
		viseur = new int[taille][taille];
		this.repaint();
	}
	
	public void changerTaille(int ta)
	{
		taille = ta;
		plateau = new int[taille][taille];
		viseur = new int[taille][taille];
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
		marge = 30;
		super.paintComponent(g);
		base=(this.getHeight()-marge)/taille;
		
		for(int i = 0; i<taille+1; i++)
		{
			g.drawLine(base*i+marge, marge, base*i+marge, base*taille+marge);
			g.drawLine(marge, base*i+marge, base*taille+marge, base*i+marge);
		}
		
		for(int i = 0; i<taille; i++)
		{
			for(int j = 0; j<taille; j++)
			{
				if(plateau[i][j]==-1)
				{
					g.setColor(Color.blue);
					g.fillRect(base*i+marge, base*j+marge, base, base);
				}
				else if(plateau[i][j]==1)
				{
					g.setColor(Color.red);
					g.fillRect(base*i+marge, base*j+marge, base, base);
				}
			}
		}
		
		System.out.println("Dessin.");
	}

}

class Traitement implements MouseListener 
{
	Plateau jeu;
	
	Traitement(Plateau j){ jeu=j; }

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		x=(x-jeu.marge)/jeu.base;
		y=(y-jeu.marge)/jeu.base;
		System.out.println(x+" "+y);
		
		if(jeu.phaseDeJeu==0&&jeu.choix==0)
		{
			if(x>=0&&x<jeu.taille&&y>=0&&y<jeu.taille)
			{
				if(y-2>=0&&jeu.plateau[x][y-1]==0&&jeu.plateau[x][y]==0&&jeu.plateau[x][y-2]==0)
					jeu.plateau[x][y-1]=-1;
				if(y+2<jeu.taille&&jeu.plateau[x][y+1]==0&&jeu.plateau[x][y]==0&&jeu.plateau[x][y+2]==0)
					jeu.plateau[x][y+1]=-1;
				if(x-2>=0&&jeu.plateau[x][y]==0&&jeu.plateau[x-1][y]==0&&jeu.plateau[x-2][y]==0)
					jeu.plateau[x-1][y]=-1;
				if(x+2<jeu.taille&&jeu.plateau[x][y]==0&&jeu.plateau[x+1][y]==0&&jeu.plateau[x+2][y]==0)
					jeu.plateau[x+1][y]=-1;
				
				jeu.absShot=x;
				jeu.ordShot=y;
				jeu.choix=1;
			}
		}
		else if(jeu.phaseDeJeu==0&&jeu.choix==1)
		{
			if(jeu.plateau[x][y]==-1)
			{
				for(int i = 0; i<3; i++)
				{
					jeu.plateau[jeu.absShot+(x-jeu.absShot)*i][jeu.ordShot+(y-jeu.ordShot)*i]=1;
				}
			}
			for(int i=0; i<jeu.taille; i++)
			{
				for(int j=0; j<jeu.taille; j++)
				{
					if(jeu.plateau[i][j]==-1)
						jeu.plateau[i][j]=0;
				}
			}
			jeu.choix = 0;
		}
		
		jeu.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
