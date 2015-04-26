package bat_nav;

import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Joueur extends JPanel {
	//Contient le score, historique, les bateaux qu'il reste
	private String name;
	private Integer score;
	
	Joueur(String newName){
		changePlayer(newName);
	}
	
	public void changePlayer(String newName){
		this.name = newName;
		this.score = 0;
		System.out.println("Création d\'un nouveau joueur: " + newName);
	}

	public void paintComponent(Graphics g){
		JLabel nomJoueur = new JLabel(this.name);
		add("North", nomJoueur);
	}
}

