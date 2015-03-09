package bat_nav;

import java.util.Random;

public class Reseau {
	
	Plateau plateau;
	//Plateau/Socket joueur_distant;
	int joueur;
	int premier_joueur;
	
	//on essaye de se connecter à telle personne
	//client = joueur 1
	//serveur = joueur 2
	//il faut se mettre d'accord
	
	public Reseau(Plateau p) {
		plateau = p;
		//connecter au joueur distant ici
		//determiner le numero des joueurs ici + leurs noms
		Random rand = new Random();
		premier_joueur = rand.nextInt(2)+1;
	}
	
	//appelé par nous quand on veut jouer un coup sur la grille du joueur distant
	public int joueCoup(int x, int y) {
		//le joueur joue un coup à telle coordonnée,
		//on transmet sur le réseau
		//on recoit
		//0: plouf
		//1: touché
		//2: coulé
		int resultat = 0;
		
		//ecrire sur le socket
		//resultat = joueur_distant.recoitCoup(x, y);
		return resultat;
	}
	
	//appelé par le joueur distant quand il veut jouer un coup sur notre grille
	public int recoitCoup(int x, int y) {
		int resultat = plateau.coupJoue(x, y);
		return resultat;
	}
}
