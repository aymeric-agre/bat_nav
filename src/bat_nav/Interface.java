package bat_nav;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Interface {
	public static void main(String[] args) {
		Fenetre fenetre = new Fenetre();
	}
}

@SuppressWarnings("serial")
class Fenetre extends JFrame implements ActionListener{
	
	private JPanel imageFond, boutons, stats;
	private JButton newPlayer, newGame, exit, ready;
	private Plateau plateau1, plateau2;
	private Joueur joueur1, joueur2;	
	Reseau reseau;
	
	Fenetre(){
		this.setContentPane(new panelFond());
		this.setTitle("Bataille Navale");
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLayout(new BorderLayout());
	    
	    //Au centre
	    stats = new JPanel();
	    
	    newPlayer = new JButton("Changer de profil");
		newPlayer.addActionListener(this);
		joueur1 = new Joueur("");
	    joueur2 = new Joueur("");
	    stats.add(new JLabel("Vous jouez contre : "));
	    stats.add(joueur2);
	    stats.add(joueur1);
	    stats.add(newPlayer);
	    stats.setOpaque(false);
	    this.getContentPane().add(stats, BorderLayout.CENTER);

	    //Au sud
	    boutons = new JPanel();
	    
		newGame = new JButton("Nouvelle partie");
		newGame.addActionListener(this);
		
		ready = new JButton("Ready");
		ready.addActionListener(this);
		
		exit = new JButton("Quitter");
		exit.addActionListener(this);
		
		boutons.add(newGame);
		boutons.add(ready);
		boutons.add(exit);
		boutons.setOpaque(false);
	    
	    this.getContentPane().add(boutons, BorderLayout.SOUTH);
	    
	    //� l'ouest
	    plateau1 = new Plateau(10, this, 0);
	    plateau1.setPreferredSize(new Dimension(500,450));
	    plateau1.setOpaque(false);
	    this.getContentPane().add(plateau1, BorderLayout.WEST);
	    //� l'est
	    plateau2 = new Plateau(10, this, 1);
	    plateau2.setPreferredSize(new Dimension(500,450));
	    plateau2.setOpaque(false);
	    this.getContentPane().add(plateau2, BorderLayout.EAST);
	    
    	// réseau
	    reseau = new Reseau(plateau1, plateau2);
	    
	    this.setVisible(true);
	    this.setSize(1200,600);
	    this.setResizable(false);
	    
	    newPlayer();
	}
	
	public void ready() throws RemoteException {
		if (plateau1.pret()) {
			reseau.jeSuisPret();
		} else {
			JOptionPane.showMessageDialog(null, "Placez 5 bateaux avant d'etre pret");
		}
	}
	
	public void perdu() {
		JOptionPane.showMessageDialog(null, "Vous avez perdu");
	}

	public void gagne() {
		JOptionPane.showMessageDialog(null,  "Vous avez gagne");
	}
	public void newGame() {
		// TODO Auto-generated method stub
		Integer taille = Integer.parseInt(JOptionPane.showInputDialog(null, "La taille du plateau doit �tre comprise entre 5 et 25.", "Taille du plateau.", JOptionPane.QUESTION_MESSAGE));
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
		this.repaint();
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
		}else if(ae == ready){
			System.out.println("Ready");
			try {
				ready();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}

class panelFond extends JPanel{
	private BufferedImage image;
	
	public panelFond(){
		try {
			image = ImageIO.read(new File("img\\fond.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g){	
		super.paintComponent(g);
		g.drawImage(image, 0, 0, 1200, 600, null);
	}
}