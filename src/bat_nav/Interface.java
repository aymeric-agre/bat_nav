package bat_nav;

import IOS;
import OC;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;

import javax.swing.*;

public class Interface {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IOS serveur;
		String result;
		OC client = new OC();
		try {
			String nomserveur = "//:2005/transformeur";
			serveur = (IOS)Naming.lookup(nomserveur);
		}
		catch(Exception ex) {
			System.out.println("Exception ; "+ ex.getMessage());
			ex. printStackTrace();
			return;
		}
		System.out.print("transformation de la chaine : bonjour --> ");
		result = client.demande("bonjour",serveur);
		System.out.println(result);
		
		
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
	private Joueur joueur;	
	
	public Fenetre(){
		f = this;
		f.setTitle("Bataille Navale");
		f.setSize(1400,800);		
		//getContentPane().setLayout(new FlowLayout());
		p = new JPanel();
		p.setLayout(new BorderLayout());
		f.setContentPane(p);
		
		infoJoueur = new JPanel();
		boutons = new JPanel();
		plateau = new Plateau(10);
	
		newGame = new JButton("Nouvelle partie");
		newGame.addActionListener(this);
		
		exit = new JButton("Quitter");
		exit.addActionListener(this);
		
		boutons.add("West", newGame);
		boutons.add("East", exit);
		
		p.add("Center",plateau);
		p.add("East", infoJoueur);
		p.add("South", boutons);
		
		f.setVisible(true);
		
		System.out.println(plateau.getX()+"  "+plateau.getY());
		System.out.println(plateau.getHeight()+"  "+plateau.getWidth());
	}

	public void newGame() {
		// TODO Auto-generated method stub
		
		Integer taille = Integer.parseInt(JOptionPane.showInputDialog(null, "Taille du plateau.", "Taille du plateau", JOptionPane.QUESTION_MESSAGE));
		
		plateau.changerTaille(taille);
	}

	public void newPlayer(){
		String name = JOptionPane.showInputDialog(null, "Nom du joueur.", "Nom du joueur", JOptionPane.QUESTION_MESSAGE);
		
		joueur = new Joueur(name);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object ae = e.getSource();
		if(ae == exit){
			System.out.println("Arrêt du jeu.");
			System.exit(0);
		}else if(ae == newGame){
			System.out.println("Nouvelle partie.");
			newGame();
		}
	}
}