package bat_nav;

import java.util.Random;

public class Reseau {
	
	Plateau plateau;
	int joueur;
	int premier_joueur;
	
	//on essaye de se connecter à telle personne
	//client = joueur 1
	//serveur = joueur 2
	
	public Reseau(Plateau p) {
		plateau = p;
		Random rand = new Random();
		premier_joueur = rand.nextInt(2)+1;
	}
	
	public int joueCoup(int x, int y) {
		//le joueur joue un coup à telle coordonnée,
		//on transmet sur le réseau
		//on recoit
		//0: plouf
		//1: touché
		//2: coulé
		
		//ecrire sur le socket
		int resultat = ...(x, y);
		//p.joueCoupSurSaGrille(x, y); // c'est fait par l'interface?
		return resultat;
	}
	
	//socket callback
	public void recoitCoup() {
		//read le socket pour définir x,y
		int x,y;
		int resultat = p.joueCoupSurMaGrille(x, y);
		//socket pour renvoyer resultat
	}
}
