package bat_nav;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Joueur {
	//Contient le score, historique, les bateaux qu'il reste
	private String name;
	
	public Joueur(String name, int score)
	{
		this.name = CreationJoueur.Joueur;
		score = 0;
		
		System.out.println("On crée les objets Navire pour "+this.name);
	}
		
}



class CreationJoueur extends JFrame{  // A voir ou mettre
	
	static String Joueur = JOptionPane.showInputDialog(null, "Veuillez entrer un nom de joueur.", "Nom du joueur", JOptionPane.QUESTION_MESSAGE);
	
	}
	
	

