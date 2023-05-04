package klotski;

public class Coord
{
	private int x;
	private int y;
	
	public Coord(int y, int x)
	{
		this.x = x; 
		this.y = y;	
	}
	
	public Coord copy()
	{
		Coord c = new Coord(y, x);
		return c;
	}
	
	public void print()
	{
		System.out.println("[" + x + " - " + y + "]");
	}
	
	public Coord up()
	{
		Coord c = new Coord(y,x);
		c.x -= 1;
		return c;
	}

	public Coord right()
	{
		Coord c = new Coord(y,x);
		c.y += 1;
		return c;
	}
	public Coord left()
	{
		Coord c = new Coord(y,x);
		c.y -= 1;
		return c;
	}
	public Coord down()
	{
		Coord c = new Coord(y,x);
		c.x += 1;
		return c;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public boolean compare(Coord c)
	{
		if((c.x == x) && (c.y == y))
		{
			return true;
		}
		return false;
	}
}