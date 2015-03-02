package bat_nav;

public class Plateau {
	//Contient le placement des bateaux
	//Contient les coups joués précédemment

	int[][] plateau;
	int[][] viseur;
	int taille;
	int absShot;
	int ordShot;
	
	Plateau(int t)
	{
		taille=t;
		plateau = new int[taille][taille];
		for(int i=0; i<taille; i++)
		{
			for(int j=0; j<taille; j++)
			{
				plateau[i][j] = 0;
				viseur[i][j] = 0;
			}
		}
	}
	
	public int coupJoue(int a, int b)
	{
		return plateau[a][b];
	}
	
	public void resultatTir(int a)
	{
		viseur[absShot][ordShot] = a;
	}
	
	public void cible (int a, int b)
	{
		absShot = a;
		ordShot = b;
	}

}
