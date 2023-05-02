package klotski_Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Klotski.coord;

public class TestCoord {
	
	@org.junit.Test
	public void testCopy() {
		coord c1 = new coord(2, 3);
		coord c2 = c1.copy();
		//assertEquals(c1.x, c2.x);
		//assertEquals(c1.y, c2.y);
	}
	
	@org.junit.Test
	public void testUp() {
		coord c1 = new coord(2, 3);
		coord c2 = c1.up();
		//assertEquals(c1.x - 1, c2.x);
		//assertEquals(c1.y, c2.y);
	}
	
	@org.junit.Test
	public void testRight() {
		coord c1 = new coord(2, 3);
		coord c2 = c1.right();
		//assertEquals(c1.x, c2.x);
		//assertEquals(c1.y + 1, c2.y);
	}
	
	@org.junit.Test
	public void testLeft() {
		coord c1 = new coord(2, 3);
		coord c2 = c1.left();
		//assertEquals(c1.x, c2.x);
		//assertEquals(c1.y - 1, c2.y);
	}
	
	@org.junit.Test
	public void testDown() {
		coord c1 = new coord(2, 3);
		coord c2 = c1.down();
		//assertEquals(c1.x + 1, c2.x);
		//assertEquals(c1.y, c2.y);
	}

}
