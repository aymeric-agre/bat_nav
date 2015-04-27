package bat_nav;

import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Joueur extends JPanel {
	//Contient le score, historique, les bateaux qu'il reste
	private String name;
	private Integer score;
	private Integer partiesJouees;
	private DefaultTableModel model;
	private JLabel pseudo, played, won;
	
	Joueur(String newName){
		this.setLayout(new GridLayout(3,2));
		
		pseudo = new JLabel();
		played = new JLabel();
		won = new JLabel();
		
		changePlayer(newName);
		
		this.add(new JLabel("Pseudo"));
		this.add(pseudo);
		this.add(new JLabel("Parties jouées"));
		this.add(played);
		this.add(new JLabel("Parties gagnées"));
		this.add(won);
	}
	
	public void changePlayer(String newName){
		this.name = newName;
		this.score = 0;
		this.partiesJouees = 0;
		
		this.pseudo.setText(this.name);
		this.played.setText(this.score.toString());
		this.won.setText(this.partiesJouees.toString());
		
		System.out.println("Création d\'un nouveau joueur: " + newName);
	}
	
	public boolean isCellEditable(int row, int column){
		return false;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void paintComponent(Graphics g){
		this.getWidth();
		this.getHeight();
	}
}

