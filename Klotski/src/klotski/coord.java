package klotski;

public class coord
{
	int x;
	int y;
	
	public coord(int Y, int X)
	{
		x = X; 
		y = Y;	
	}
	
	public coord copy()
	{
		coord c = new coord(y, x);
		return c;
	}
	
	public void print()
	{

		System.out.print("[");
		System.out.print(x);
		System.out.print(" - ");
		System.out.print(y);
		System.out.print("]\n");
	}
	
	public coord up()
	{
		coord c = new coord(y,x);
		c.x -= 1;
		return c;
	}

	public coord right()
	{
		coord c = new coord(y,x);
		c.y += 1;
		return c;
	}
	public coord left()
	{
		coord c = new coord(y,x);
		c.y -= 1;
		return c;
	}
	public coord down()
	{
		coord c = new coord(y,x);
		c.x += 1;
		return c;
	}
	
	public boolean compare(coord c)
	{
		if((c.x == x) && (c.y == y))
		{
			return true;
		}
		return false;
	}
}