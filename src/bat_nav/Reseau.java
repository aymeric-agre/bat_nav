package bat_nav;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

import bat_nav_serveur.IOS;

@SuppressWarnings("serial")
public class Reseau implements Client {
	
	Plateau plateau1, plateau2;
	IOS serveur;
	int joueur; // notre numéro de joueur
	int premier_joueur; //qui commence à jouer
	
	public Reseau(Plateau p1, Plateau p2) {
		try {
			UnicastRemoteObject.exportObject(this);
			serveur = (IOS) Naming.lookup("//:2002/bat_nav");
			joueur = serveur.register(this);
			if (joueur == 0) {
				System.out.println("Trop de joueurs connectes");
				System.exit(0);
			}
		} catch (Exception e) {
			System.out.println("Fail connection au serveur: " + e.getMessage());
		}
		plateau1 = p1;
		plateau2 = p2;
	}
	
	//appelé par nous quand on veut jouer un coup sur la grille du joueur distant
	public int joueCoup(int x, int y) throws RemoteException {
		return serveur.joueCoup(joueur, x, y);
	}
	
	//appelé par le joueur distant quand il veut jouer un coup sur notre grille
	public int recoitCoup(int x, int y) {
		int resultat = plateau1.coupJoue(x, y);
		return resultat;
	}
	
	public void jeSuisPret() throws RemoteException {
		serveur.pret(joueur);
	}
	
	public void commencePartie(int joueur) {
		System.out.println("La partie commence");
		if (this.joueur == joueur) {
			System.out.println("Je commence");
			plateau2.jePeuxJouer(1);
		}
		// la partie commence
		plateau2.commencer();
	}
}

/* Dans main:
 * try {
 * 	new Reseau();
 * } catch (RemoteException e) {
 * 	System.out.println("Fail :" + e.getMessage());
 * }
 */
