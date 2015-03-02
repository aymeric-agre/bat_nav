package bat_nav;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Interface {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame f = new JFrame("Bataille Navale");
		JPanel p = (JPanel)f.getContentPane();
		JPanel damier = new JPanel();
		JPanel infoJoueur = new JPanel(); 
		
		p.add("West", damier);
		p.add("East", infoJoueur);
		f.pack();
		f.setSize(1000, 600);
		f.setVisible(true);
	}
	
}
