package bat_nav_serveur;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.*;
import java.util.Random;

import bat_nav.Client;

@SuppressWarnings("serial")
public class OS extends UnicastRemoteObject implements IOS {
	Client joueur1;
	Client joueur2;
	int[] pret = new int[2];
	int premier_joueur;
	
	public OS() throws RemoteException {
		super();
		try {
			try{
				LocateRegistry.createRegistry(2004);
			}catch (Exception e){
				LocateRegistry.getRegistry(2004);
			}
			Naming.rebind("//:2004/", this);
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