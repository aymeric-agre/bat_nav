package bat_nav;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Interface {
	
	public static void main(String[] args) {		
		Fenetre fenetre = new Fenetre();

		fenetre.setVisible(true);
		
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//Ferme le programme �la fermeture de la fen黎re
	}
}

class Fenetre extends JFrame implements ActionListener{
	private JFrame f;
	private JPanel p;
	private JPanel boutons;
	private JButton newPlayer;
	private JButton newGame;
	private JButton exit;
	private Plateau plateau;
	private Joueur joueur;	
	private Reseau reseau;
	
	public Fenetre(){
		f = this;
		f.setTitle("Bataille Navale");
		f.setSize(800,600);
		p = new JPanel();
		p.setLayout(new BorderLayout());
		f.setContentPane(p);
		
		joueur = new Joueur("");
		newPlayer();
		boutons = new JPanel();
		plateau = new Plateau(10);
		reseau = new Reseau(plateau);
		
		newPlayer = new JButton("Changer de profil");
		newPlayer.addActionListener(this);
		
		joueur.add("South", newPlayer);
		
		newGame = new JButton("Nouvelle partie");
		newGame.addActionListener(this);
		
		exit = new JButton("Quitter");
		exit.addActionListener(this);
		
		boutons.add("West", newGame);
		boutons.add("East", exit);
		
		p.add("Center", plateau);
		p.add("East", joueur);
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
		joueur.changePlayer(name);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object ae = e.getSource();
		if(ae == exit){
			System.out.println("Arret du jeu.");
			System.exit(0);
		}else if(ae == newGame){
			System.out.println("Nouvelle partie.");
			newGame();
		}else if(ae == newPlayer){
			System.out.println("Changement de joueur.");
			newPlayer();
		}
	}
}