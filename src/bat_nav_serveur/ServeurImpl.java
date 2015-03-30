package bat_nav_serveur;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

import bat_nav.Client;
import bat_nav.Plateau;

interface Serveur extends Remote {
	public int register(Client client) throws RemoteException;
	public int joueCoup(int x, int y) throws RemoteException;
	public int recoitCoup(int x, int y) throws RemoteException;
}

@SuppressWarnings("serial")
public class ServeurImpl extends UnicastRemoteObject implements Serveur {
	Client joueur1;
	Client joueur2;
	int premier_joueur;
	
	public ServeurImpl() throws RemoteException {
		super();
		try {
			Naming.bind("rmi://localhost/bat_nav", this);
			System.out.println("Serveur installe");
		} catch (Exception e) {
			System.out.println("Fail serveur: " + e.getMessage());
		}
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

	@Override
	public int register(Client client) throws RemoteException {
		if (joueur1 == null) {
			joueur1 = client;
			return 1;
		} else if (joueur2 == null) {
			joueur2 = client;
			return 2;
		} else {
			System.out.println("Un troisieme joueur essaye de se connecter");
			return 0;
		}
	}
	
	public static void main(String[] args) {
		try {
			new ServeurImpl();
		} catch (RemoteException e) {
			System.out.println("Fail serveur: " + e.getMessage());
		}
	}
}