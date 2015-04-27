package bat_nav_serveur;

import java.rmi.*;
import java.rmi.server.*;
import java.util.Random;

import bat_nav.Client;

@SuppressWarnings("serial")
class OS extends UnicastRemoteObject implements IOS {
	Client joueur1;
	Client joueur2;
	int[] pret = new int[2];
	int premier_joueur;
	
	OS() throws RemoteException {
		super();
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
			return 2;
		} else {
			System.out.println("Un troisieme joueur essaye de se connecter");
			return 0;
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