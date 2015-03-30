package bat_nav;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

import bat_nav_serveur.ServeurImpl;

@SuppressWarnings("serial")
public class Reseau implements Client {
	
	Plateau plateau;
	//Plateau/Socket joueur_distant;
	int joueur;
	int premier_joueur;
	
	//on essaye de se connecter à telle personne
	//client = joueur 1
	//serveur = joueur 2
	//il faut se mettre d'accord
	
	public Reseau(Plateau p) {
		try {
			UnicastRemoteObject.exportObject(this);
			ServeurImpl serveur = (ServeurImpl) Naming.lookup("rmi:///bat_nav");
			joueur = serveur.register(this);
		} catch (Exception e) {
			System.out.println("Fail connection au serveur: " + e.getMessage());
		}
		plateau = p;
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

/* Dans main:
 * try {
 * 	new Reseau();
 * } catch (RemoteException e) {
 * 	System.out.println("Fail :" + e.getMessage());
 * }
 */
