package bat_nav;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;

import javax.swing.*;

@SuppressWarnings("serial")
public class Plateau extends JPanel{
	//Contient le placement des bateaux
	//Contient les coups jou駸 pr馗馘emment

	Fenetre fenetre;
	int[][] plateau; // -1 si c'est possible comme deuxième clic de positionnement de bateau, 1, si il y a une case de bateau, 0 si rien
	public int taille;
	int phaseDeJeu; // placement de bateaux (0) ou en cours de partie (1)
	int choix; // on a cliqué une fois pour commencer à placer un bateau (1) ou non (0)
	int peut_jouer;
	int base;
	int nb_bateaux;
	int marge;
	int absShot;
	int ordShot;
	
	Plateau(int taille, Fenetre fenetre, int type)
	{
		// type: 0 pour la notre, 1 pour celle du joueur distant
		super();
		this.fenetre = fenetre;
		if (type == 0)
			this.addMouseListener(new TraitementMaGrille(this));
		else
			this.addMouseListener(new TraitementSaGrille(this));
		this.taille=taille;
		phaseDeJeu = 0;
		choix = 0;
		plateau = new int[taille][taille];
		peut_jouer = 0;
		nb_bateaux = 0;// nombre de bateaux placés
		this.repaint();
	}
	
	// condition pour dire qu'on est prêt
	public boolean pret()
	{
		return nb_bateaux == 5;
	}
	
	// la partie commence, on n'ajoute plus de bateaux
	public void commencer()
	{
		phaseDeJeu = 1;
	}
	
	// est-on autorisé à jouer
	public void jePeuxJouer(int jouer)
	{
		peut_jouer = jouer;
	}
	
	public void changerTaille(int ta)
	{
		taille = ta;
		plateau = new int[taille][taille];
		this.repaint();
	}
	
	// renvoie 0 si pas de bateau,
	// 1 s'il y a un bateau à cette case
	// 2 sinon
	public int coupJoue(int a, int b)
	{
		if(plateau[a][b]==1)
		{
			plateau[a][b] = -1;
		}
		// vérifier qu'on a encore des cases "en vie"
		boolean vivant = false;
		for(int i = 0; i<taille; i++)
		{
			for(int j = 0; j<taille; j++)
			{
				if(plateau[i][j]==1)
				{
					vivant = true;
					break; // ca break pas des deux loops mais bon
				}
			}
		}
		if (vivant){
			return -plateau[a][b];
		} else {
			this.fenetre.gagne();
			return 2;
		}
	}
	
	public void joueCoup(int a, int b) throws RemoteException
	{
		System.out.println("Je joue");
		int resultat = this.fenetre.reseau.joueCoup(a, b);
		if (resultat == 0) {
			System.out.println("Dans le vide");
			plateau[a][b] = -1;
		} else if (resultat == 1){
			System.out.println("Touche");
			plateau[a][b] = 1;
		} else if (resultat == 2){
			plateau[a][b] = 1;
			this.fenetre.perdu();
		}
		this.repaint();	
	}
	
	public void paintComponent(Graphics g){
		marge = 20;
		super.paintComponent(g);
		base=(this.getHeight()-4*marge)/taille;
		
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
	}
}
// si c'est la grille de l'adversaire
class TraitementSaGrille implements MouseListener
{
	Plateau jeu;
	TraitementSaGrille(Plateau j){jeu=j;}
	@Override
	public void mouseClicked(MouseEvent e) {
		// la partie doit etre commencée  et ca doit etre à nous de jouer
		if (jeu.phaseDeJeu == 0 || jeu.peut_jouer == 0) {
			System.out.println("Pas a moi de jouer");
			return;
		};
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		x=(x-jeu.marge)/jeu.base;
		y=(y-jeu.marge)/jeu.base;
		System.out.println(x+" "+y);
		
		if(jeu.plateau[x][y] == 0) // on a jamais cliqué ici
		{
			try {
				jeu.joueCoup(x,  y);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
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
// si c'est la grille où on place nos bateaux
class TraitementMaGrille implements MouseListener 
{
	Plateau jeu;
	TraitementMaGrille(Plateau j){ jeu=j; }
	@Override
	public void mouseClicked(MouseEvent e) {
		// Premier clic pour l'origine
		// Deuxième clic pour la direction
		int x = e.getX();
		int y = e.getY();
		x=(x-jeu.marge)/jeu.base;
		y=(y-jeu.marge)/jeu.base;
		System.out.println(x+" "+y);
		
		if(jeu.phaseDeJeu==0&&jeu.choix==0&&jeu.nb_bateaux<5&&jeu.plateau[x][y]==0)
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

				jeu.nb_bateaux++;
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