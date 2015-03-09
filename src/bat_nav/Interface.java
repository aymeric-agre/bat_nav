package bat_nav;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Interface {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Fenetre fenetre = new Fenetre();
		
		
		fenetre.setVisible(true);
		
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//Ferme le programme à la fermeture de la fenêtre
	}
}

class Fenetre extends JFrame implements ActionListener{
	private JFrame f;
	private JPanel p;
	private JPanel infoJoueur;
	private JPanel boutons;
	private JButton newGame;
	private JButton exit;
	
	
	public Fenetre(){
		setTitle("Bataille Navale");
		setSize(1000,600);		
		getContentPane().setLayout(new FlowLayout());
		
		p = (JPanel) getContentPane();
		
		infoJoueur = new JPanel();
		boutons = new JPanel();
		newGame = new JButton("Nouvelle partie");
		exit = new JButton("Quitter");
		
		exit.addActionListener(this);
		
		boutons.add("West", newGame);
		boutons.add("East", exit);
		
		p.add("East", infoJoueur);
		p.add("South", boutons);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ae = e.getSource();
		if(ae == exit){
			System.out.println("Traitement de FIN");
			System.exit(0);
		}
	}
}