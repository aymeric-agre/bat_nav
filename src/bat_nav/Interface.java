package bat_nav;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Interface {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CreationJoueur preFenetre = new CreationJoueur(); 
		
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
	private Plateau plateau;
	
	
	public Fenetre(){
		f = this;
		f.setTitle("Bataille Navale");
		f.setSize(1000,600);		
		//getContentPane().setLayout(new FlowLayout());
		p = new JPanel();
		p.setLayout(new BorderLayout());
		f.setContentPane(p);
		
		infoJoueur = new JPanel();
		boutons = new JPanel();
		plateau = new Plateau(7);
		
		
		newGame = new JButton("Nouvelle partie");
		newGame.addActionListener(this);
		
		exit = new JButton("Quitter");
		exit.addActionListener(this);
		
		boutons.add("West", newGame);
		boutons.add("East", exit);
		
		p.add("Center",plateau);
		p.add("East", infoJoueur);
		p.add("South", boutons);
		
		//p.repaint();
		plateau.setSize(500, 500);
		//plateau.updateUI();
		f.setVisible(true);
		
		System.out.println(plateau.getX()+"  "+plateau.getY());
		System.out.println(plateau.getHeight()+"  "+plateau.getWidth());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ae = e.getSource();
		if(ae == exit){
			System.out.println("Arrêt du jeu.");
			System.exit(0);
		}else if(ae == newGame){
			System.out.println("Nouvelle partie.");
			//this.plateau = new Plateau();
		}
	}
}