package bat_nav_serveur;

import java.rmi.*;

import bat_nav.Client;

interface IOS extends Remote {
	public int register(Client client) throws RemoteException;
	public void pret(int joueur) throws RemoteException;
	public int joueCoup(int joueur, int x, int y) throws RemoteException;
}