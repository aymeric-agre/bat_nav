package bat_nav;

import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Joueur extends JPanel {
	//Contient le score, historique, les bateaux qu'il reste
	private String name;
	private Integer score;
	private Integer partiesJouees;
	private JTable stats;
	private DefaultTableModel model;
	
	Joueur(String newName){
		this.stats = new JTable();
		changePlayer(newName);	
		add("North", stats);
	}
	
	public void changePlayer(String newName){
		this.name = newName;
		this.score = 0;
		this.partiesJouees = 0;
		
		this.model = new DefaultTableModel();
		this.model.addColumn("1");
		this.model.addColumn("2");
		this.model.addRow(new Object[]{"Pseudo", this.name});
		this.model.addRow(new Object[]{"Parties jouées", this.partiesJouees});
		this.model.addRow(new Object[]{"Parties gagnées", this.score});
		this.stats.setModel(this.model);
		
		System.out.println("Création d\'un nouveau joueur: " + newName);
	}

	public void paintComponent(Graphics g){
	}
}

