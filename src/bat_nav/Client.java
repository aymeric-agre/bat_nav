package bat_nav;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Client extends Remote {
	public int joueCoup(int x, int y) throws RemoteException;
	public int recoitCoup(int x, int y) throws RemoteException;
	public void placeBateaux() throws RemoteException;
	public void jeSuisPret() throws RemoteException;
	public void commencePartie(int joueur) throws RemoteException;
}