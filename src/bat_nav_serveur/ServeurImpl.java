package bat_nav_serveur;

import java.rmi.registry.LocateRegistry;
import java.rmi.Naming;

class ServeurImpl {
	public static void main(String[] args) {
		try {
			try {
				LocateRegistry.createRegistry(2002);
			} catch(Exception e){
				System.out.println(e.getMessage());
				System.out.println("R�cup�ration du port d�fini");
				LocateRegistry.getRegistry(2002);				
			}			
			OS serveur= new OS(); 
			Naming.rebind("//:2002/transformeur",serveur);
		}
		catch(Exception e){
			System.out.println("Exception au lancement du serveur");
			System.out.println(e.getMessage());
		}
		System.out.println("Serveur installe");
	}
}