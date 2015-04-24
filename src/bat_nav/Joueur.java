package bat_nav;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Joueur {
	//Contient le score, historique, les bateaux qu'il reste
	private String name;
	private Integer score;
	
	public Joueur(String nom){
		name = nom;
		score = 0;
		System.out.println("Création d\'un nouveau joueur: " + name);
	}		
}

