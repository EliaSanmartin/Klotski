package klotski;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileS
{
	List<String> contenuto = new ArrayList<>();
	String dove;
	
	public FileS(String nome)
	{
		dove = nome;
		File f = new File(nome);
		
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
				
			}
			catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	
	public void print()
	{
		for(int i=0; i<contenuto.size();i++)
			System.out.print(contenuto.get(i));
		System.out.print("\n");
		
	}
	
	public void add(Mossa m)
	{
		String s;
		s = "\n" + m.c_in.getX()+","+m.c_in.getY() + "," + m.dir + "," + m.num_pezzo;

		contenuto.add(s);
	}
	
	public void add(String s)
	{
		contenuto.add(s);
	}
	
	public int[] index(int el)
	{
		int in[] = {0,contenuto.get(0).length()};
		for(int i=0; i<contenuto.get(0).length();i++)
		{
			if(contenuto.get(0).charAt(i) == ',')
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
	
	public void imposta_formazione(int f)
	{
		int []c;	
		c=index(0);
		contenuto.set(0, contenuto.get(0).substring(0,c[0]) + f + contenuto.get(0).substring(c[1], contenuto.get(0).length()));
	}
	
	public void add_mossa(int m)
	{
		int []c;	
		c=index(1);
		contenuto.set(0, contenuto.get(0).substring(0,c[0]) + m + contenuto.get(0).substring(c[1], contenuto.get(0).length()));
	}
}