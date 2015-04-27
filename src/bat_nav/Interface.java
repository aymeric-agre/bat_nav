package bat_nav;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Interface {
	
	public static void main(String[] args) {
		
		Fenetre fenetre = new Fenetre();
		
	}
	
}

@SuppressWarnings("serial")
class Fenetre extends JFrame implements ActionListener{
	
	private JPanel imageFond, boutons, stats;
	private JButton newPlayer, newGame, exit;
	private Plateau plateau1, plateau2;
	private Joueur joueur1, joueur2;	
	private Reseau reseau;
	
	public Fenetre(){
		
		this.setTitle("Bataille Navale");
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);

	    this.setLayout(new BorderLayout());
	    
	    //Au centre
	    stats = new JPanel();
	    
	    newPlayer = new JButton("Changer de profil");
		newPlayer.addActionListener(this);
	    joueur1 = new Joueur("");	newPlayer();
	    joueur2 = new Joueur("");
	    stats.add(new JLabel("Vous jouez contre : " + joueur2.getName()));
	    stats.add(joueur1);
	    stats.add(newPlayer);
	    stats.setBounds(0, 0, 10, 10);
	    this.getContentPane().add(stats, BorderLayout.CENTER);

	    //Au sud
	    boutons = new JPanel();
	    
		newGame = new JButton("Nouvelle partie");
		newGame.addActionListener(this);
		
		exit = new JButton("Quitter");
		exit.addActionListener(this);
		
		boutons.add(newGame);
		boutons.add(exit);
	    
	    this.getContentPane().add(boutons, BorderLayout.SOUTH);
	    
	    //À l'ouest
	    plateau1 = new Plateau(10);
	    plateau1.setPreferredSize(new Dimension(500,450));
	    this.getContentPane().add(plateau1, BorderLayout.WEST);
	    //À l'est
	    plateau2 = new Plateau(10);
	    plateau2.setPreferredSize(new Dimension(500,450));
	    this.getContentPane().add(plateau2, BorderLayout.EAST);
	    
	    this.setSize(1200,600);
	    this.setResizable(false);
	    this.setVisible(true);
	}

	public void newGame() {
		// TODO Auto-generated method stub
		Integer taille = Integer.parseInt(JOptionPane.showInputDialog(null, "La taille du plateau doit être comprise entre 5 et 25.", "Taille du plateau.", JOptionPane.QUESTION_MESSAGE));
		if(taille > 5 && taille < 26){
			plateau1.changerTaille(taille);
			plateau2.changerTaille(taille);
		}else{
			newGame();
		}
	}

	public void newPlayer(){
		String name = JOptionPane.showInputDialog(null, "Nom du joueur.", "Nom du joueur", JOptionPane.QUESTION_MESSAGE);
		joueur1.changePlayer(name);
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