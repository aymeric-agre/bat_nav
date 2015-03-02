package bat_nav;

import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Joueur extends JFrame{
	//Contient le score, historique, les bateaux qu'il reste
	private String name;
	private JLabel label;
	private JTextField t;
	
	private JPanel buildContentPane(){
		JPanel IN = new JPanel();
		IN.setLayout(new FlowLayout());
		
		t = new JTextField();
		
		label = new JLabel("Rien pour le moment");
		
		return IN;
	}
	
	public JTextField getTextField(){
		return t;
	}
 
	public JLabel getLabel(){
		return label;
	}

}


public class GetAction extends AbstractAction {
	private FenetreSaisie fenetre;
 
	public GetAction(FenetreSaisie fenetre, String texte){
		super(texte);
 
		this.fenetre = fenetre;
	}
 
	public void actionPerformed(ActionEvent e) { 
		String texteUtilisateur = fenetre.getTextField().getText();
		fenetre.getLabel().setText(texteUtilisateur);
	} 
}
