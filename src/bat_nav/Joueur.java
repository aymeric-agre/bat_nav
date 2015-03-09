package bat_nav;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Joueur {
	//Contient le score, historique, les bateaux qu'il reste
	
	Joueur(int score)
	{
		
	}
		
}



class CreationJoueur extends JFrame{ // A voir ou mettre
	private JLabel label;
	private JTextField t;
	
	private JPanel buildContentPane(){		// Creation TextBox
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
 
		t = new JTextField();
 
		panel.add(t);
 
		label = new JLabel("Rien pour le moment");
 
		panel.add(label);
 
		JButton bouton = new JButton(new GetAction(this, "Ok"));
 
		panel.add(bouton);
 
		return panel;
	}
	
	public JTextField getTextField(){
		return t;
	}
 
	public JLabel getLabel(){
		return label;
	}

}


class GetAction extends AbstractAction {		//Recuperation name
	private CreationJoueur fenetre;
	private String name;
 
	public GetAction(CreationJoueur fenetre, String texte){
		super(texte);
 
		this.fenetre = fenetre;
	}
 
	public void actionPerformed(ActionEvent e) { 
		name = fenetre.getTextField().getText();
		fenetre.getLabel().setText(name);
	} 
}


