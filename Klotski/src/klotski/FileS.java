package klotski;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileS
{
	protected List<String> contenuto = new ArrayList<>();
	private String nome;
	
	public FileS(String nome)
	{
		this.nome = nome;
		
		try {
				FileReader reader = new FileReader(nome);
				int data;
				data = reader.read();
				contenuto.add("");
				while(data != -1)
				{
					if((char)data == '\n')
						contenuto.add("");
					
					contenuto.set(contenuto.size()-1, contenuto.get(contenuto.size()-1) + (char)data); 
					
					data = reader.read();			
				}
				reader.close();
			}
			catch (FileNotFoundException e) {
				contenuto.add("");
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	
	public void print()
	{
		for(int i=0; i<contenuto.size(); i++)
			System.out.print(contenuto.get(i));
		System.out.print("\n");
		
	}
	
	public int getNumMove()
	{
		int n;
		int [] dove = index(1,0);
		String s = contenuto.get(0).substring(dove[0], dove[1]);
		n = 0;
		for(int i=0; i<s.length();i++)
		{
			if((int)s.charAt(i) - 48>=0 && (int)s.charAt(i) - 48 < 10)
			{
				n += (int)s.charAt(i) - 48;
				n *=10;

			}
		}
		n /= 10;
		return n;
	}
	
	public Mossa find_move(String s)
	{
		Coord c_in = new Coord(99,99);
		String str;
		for(int i=0;i<contenuto.size();i++)
		{
			if(contenuto.get(i).length() >19)
			{
				str = contenuto.get(i);
				if (str.charAt(0) == '\n')
					str = str.substring(1,str.length());

				if ((str.charAt(str.length()-1) == '\n') || (str.charAt(str.length()-1) == ' '))
					str = str.substring(0,str.length()-2);
							
				if(str.substring(0,20).equals(s))
				{
					return get_move(i+1);
				}
			}
		}
		
		return new Mossa(c_in,'N',0);
		
		
	}
	
	public void crea_file()
	{
		File f = new File(nome);
		try {
			f.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			FileWriter file = new FileWriter(nome);
			file.write(contenuto.get(0));
			for(int i=1; i<contenuto.size();i++)
				file.append(contenuto.get(i));
			file.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public void remove()
	{
		File f = new File(nome);
		f.delete();
		
	}
	public void add(Mossa m)
	{
		String s;
		s = "\n" + m.c_in.getX()+","+m.c_in.getY() + "," + m.dir + "," + m.num_pezzo;
		contenuto.add(s);
	
	}
	
	public void afdd(String s)
	{
		contenuto.add(s);
	}
	
	private int[] index(int el, int j)
	{
		int in[] = {0,contenuto.get(j).length()};
		for(int i=0; i<contenuto.get(j).length();i++)
		{
			if(contenuto.get(j).charAt(i) == ',')
			{
				if (el == 0)
				{
					in[1] = i;
					return in;
				}
				else {
					el -= 1;
					in[0] = i+1;
				}

			}
			
		}
		return in;
	}
	
	public int getLevel()
	{
		int lvl=0;
		int dove[] = index(0,0);
		
		String s = contenuto.get(0).substring(dove[0],dove[1]);
		for(int i=0; i<s.length();i++)
		{
			lvl += ((int)s.charAt(i)-48);
		}
		return lvl;
		
	}
	
	public void imposta_formazione(int f)
	{
		int []c;	
		c=index(0,0);
		contenuto.set(0, contenuto.get(0).substring(0,c[0]) + f + contenuto.get(0).substring(c[1], contenuto.get(0).length()));
	}
	
	public Mossa get_move(int ind)
	{
		Mossa m;
		int num_p = 0;
		Coord c = new Coord(0,0);
		char dir = 'N';
		int dove[] = index(0,ind); 
		String s = contenuto.get(ind).substring(dove[0],dove[1]);
		for(int i=0; i<s.length();i++)
		{
			c.setX(((int)s.charAt(i)-48));
		}
		
		dove = index(1,ind); 
		s = contenuto.get(ind).substring(dove[0],dove[1]);
		for(int i=0; i<s.length();i++)
		{
			c.setY(((int)s.charAt(i)-48));
		}
		
		dove = index(2,ind); 
		s = contenuto.get(ind).substring(dove[0],dove[1]);
		for(int i=0; i<s.length();i++)
		{
			dir = (char)s.charAt(i);
		}
		
		dove = index(3,ind); 
		s = contenuto.get(ind).substring(dove[0],dove[1]);
		num_p = 0;
		for(int i=0; i<s.length();i++)
		{
			if((int)s.charAt(i) - 48>=0 && (int)s.charAt(i) - 48 < 10)
			{
				num_p += (int)s.charAt(i) - 48;
				num_p *=10;

			}
		}
		num_p /= 10;
	
		
		
		m = new Mossa(c, dir ,num_p);
		
		return m;
	}
	
	public void add_mossa(int m)
	{
		int []c;	
		c=index(1,0);
		contenuto.set(0, contenuto.get(0).substring(0,c[0]) + m + contenuto.get(0).substring(c[1], contenuto.get(0).length()));
	}
}