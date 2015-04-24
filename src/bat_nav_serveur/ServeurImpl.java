package bat_nav_serveur;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

import bat_nav.Client;

interface Serveur extends Remote {
	public int register(Client client) throws RemoteException;
	public void pret(int joueur) throws RemoteException;
	public int joueCoup(int joueur, int x, int y) throws RemoteException;
}

@SuppressWarnings("serial")
public class ServeurImpl extends UnicastRemoteObject implements Serveur {
	Client joueur1;
	Client joueur2;
	int[] pret = new int[2];
	int premier_joueur;
	
	public ServeurImpl() throws RemoteException {
		super();
		try {
			Naming.bind("rmi://localhost/bat_nav", this);
			System.out.println("Serveur installe");
		} catch (Exception e) {
			System.out.println("Fail serveur: " + e.getMessage());
		}
		Random rand = new Random();
		premier_joueur = rand.nextInt(2)+1;
	}

	@Override
	public int register(Client client) throws RemoteException {
		if (joueur1 == null) {
			joueur1 = client;
			return 1;
		} else if (joueur2 == null) {
			joueur2 = client;
			
			// on a deux joueurs: on place les bateaux
			joueur1.placeBateaux();
			joueur2.placeBateaux();
			
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

	@Override
	public int joueCoup(int joueur, int x, int y) throws RemoteException {
		if (joueur==1) {
			return joueur2.recoitCoup(x, y);
		} else {
			return joueur1.recoitCoup(x, y);
		}
	}

	@Override
	public void pret(int joueur) throws RemoteException {
		this.pret[joueur-1] = 1;
		if (this.pret[0]==1 && this.pret[1]==1) {
			joueur1.commencePartie(premier_joueur);
			joueur2.commencePartie(premier_joueur);
		}
	}
}