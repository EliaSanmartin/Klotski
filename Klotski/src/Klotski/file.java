package Klotski;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class file
{
	List<String> contenuto = new ArrayList<>();
	String dove;
	
	public file(String nome)
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
		
	}
	
	public void add(mossa m)
	{
		String s;
		s = m.c_in.x+","+m.c_in.x + "," + m.dir + "," + m.num_pezzo;
		
		contenuto.add(s);
	}
}