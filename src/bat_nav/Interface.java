package bat_nav;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Interface {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame f = new JFrame("Bataille Navale");
		JPanel p = (JPanel)f.getContentPane();
		Plateau damier = new Plateau();
		
		JPanel infoJoueur = new JPanel();
				
		JPanel bouttons = new JPanel();
		JButton newGame = new JButton("Nouvelle partie");
		JButton exit = new JButton("Quitter");
		bouttons.add("West", newGame);
		bouttons.add("East", exit);
		
		p.add("West", damier);
		p.add("East", infoJoueur);
		p.add("South", bouttons);
		f.pack();
		f.setSize(1000, 600);
		f.setVisible(true);
	}
}