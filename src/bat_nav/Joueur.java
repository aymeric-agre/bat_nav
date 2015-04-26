package bat_nav;

import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Joueur extends JPanel {
	//Contient le score, historique, les bateaux qu'il reste
	private String name;
	private Integer score;
	private JLabel nomJoueur;
	
	Joueur(String newName){
		this.nomJoueur = new JLabel(this.name);
		add("North", nomJoueur);
		changePlayer(newName);
	}
	
	public void changePlayer(String newName){
		this.name = newName;
		this.score = 0;
		this.nomJoueur.setText(newName);
		System.out.println("Création d\'un nouveau joueur: " + newName);
	}

	public void paintComponent(Graphics g){
	}
}

