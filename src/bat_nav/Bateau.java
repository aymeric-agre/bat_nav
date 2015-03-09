package bat_nav;

public class Bateau {
	int absStart;
	int ordStart;
	int taille;
	int absEnd;
	int ordEnd;
	
	Bateau(int a, int b, int c, int d, int t)
	{
		absStart = a;
		ordStart = b;
		
		if(a==c-1)
		{
			ordEnd = b;
			absEnd = a+t-1;
		}
		else if(a==c+1)
		{
			ordEnd = b;
			absEnd = a-t+1;
		}
		else if(b==d-1)
		{
			absEnd = a;
			ordEnd = b+t-1;
		}
		else
		{
			absEnd = a;
			ordEnd = b-t+1;
		}
	}

}
