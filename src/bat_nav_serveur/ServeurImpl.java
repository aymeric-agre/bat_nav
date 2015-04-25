package bat_nav_serveur;

import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;

public class ServeurImpl {
	public static void main(String[] args) {
		try {
			try {
				LocateRegistry.createRegistry(2005);
			} catch (RemoteException e) {
				LocateRegistry.getRegistry(2005);
			}
			OS serveur = new OS();
			try {
				Naming.rebind("//:2005/batnav", serveur);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (RemoteException e) {
			System.out.println("Fail server: " + e.getMessage());
		}
		System.out.println("Serveur installe");
	}
}