package bat_nav;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

import bat_nav_serveur.IOS;

@SuppressWarnings("serial")
public class Reseau implements Client {
	
	Plateau plateau;
	IOS serveur;
	int joueur; // notre numéro de joueur
	int premier_joueur; //qui commence à jouer
	
	public Reseau(Plateau p) {
		try {
			UnicastRemoteObject.exportObject(this);
			serveur = (IOS) Naming.lookup("rmi:///bat_nav");
			joueur = serveur.register(this);
			if (joueur == 0) {
				System.out.println("Trop de joueurs connectes");
				System.exit(0);
			}
		} catch (Exception e) {
			System.out.println("Fail connection au serveur: " + e.getMessage());
		}
		plateau = p;
	}
	
	//appelé par nous quand on veut jouer un coup sur la grille du joueur distant
	public int joueCoup(int x, int y) throws RemoteException {
		return serveur.joueCoup(joueur, x, y);
	}
	
	//appelé par le joueur distant quand il veut jouer un coup sur notre grille
	public int recoitCoup(int x, int y) {
		int resultat = plateau.coupJoue(x, y);
		return resultat;
	}
	
	public void placeBateaux() {
		//plateau.placeBateaux();
	}
	
	public void jeSuisPret() throws RemoteException {
		serveur.pret(joueur);
	}
	
	public void commencePartie(int joueur) {
		if (this.joueur == joueur) {
			//c'est a nous de commencer
		}
		// la partie commence
	}
}

/* Dans main:
 * try {
 * 	new Reseau();
 * } catch (RemoteException e) {
 * 	System.out.println("Fail :" + e.getMessage());
 * }
 */
