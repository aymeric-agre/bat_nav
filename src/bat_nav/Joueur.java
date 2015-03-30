package bat_nav;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Joueur {
	//Contient le score, historique, les bateaux qu'il reste
	private String name;
	
	public Joueur(int score)
	{
		score = 0;
		
		System.out.println("On crée les objets Navire pour "+this.name);
	}
		
}



class CreationJoueur extends JFrame{ // A voir ou mettre
	
	String Joueur = JOptionPane.showInputDialog(null, "Veuillez un nom de joueur.", "Nom du joueur", JOptionPane.QUESTION_MESSAGE);
	
	}
	
	

