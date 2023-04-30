package Klotski;
import java.util.*;
public class board 
{
	int righe = 5;
	int colonne = 4;
	int num_mosse = 0;
	
	int [][] mat = new int[righe][colonne];
		
	List<pezzo> pezzi = new ArrayList<>();
	List<mossa> mosse = new ArrayList<>();
	
	file salvataggio = new file("salvataggi/prova.txt");
	
	public board()
	{
		salvataggio.contenuto.set(0, "X,0");//formazione e num_mosse
		
		imposta_formazione(1);
		
	}	
	
	
	public void imposta_formazione(int formazione)
	{
		salvataggio.imposta_formazione(formazione);
		pezzi.clear();
		//svuoto la matrice
		for(int i=0;i<mat.length;i++)
			for(int j=0;j<mat[0].length;j++)
				mat[i][j] = 0;

		num_mosse = 0;//resetto numero mosse
		//-----------reset di tutti i pezzi------------
		for(int i=0;i<pezzi.size();i++)
			pezzi.get(i).reset();
		//---------------------------------------------
		
		if (formazione == 1)
		{
			pezzi.add(new pezzo(2,2));
			pezzi.add(new pezzo(2,1));
			for(int i=0; i<4;i++)
				pezzi.add(new pezzo(1,2));
			for(int i=0; i<4;i++)
				pezzi.add(new pezzo(1,1));
			
			pezzi.get(0).imposta_coord(new mossa(new coord(1, 0),'N', 1), mat);
			pezzi.get(1).imposta_coord(new mossa(new coord(1, 2),'N', 2), mat);
			pezzi.get(2).imposta_coord(new mossa(new coord(0, 0),'N', 3), mat);
			pezzi.get(3).imposta_coord(new mossa(new coord(3, 0),'N', 4), mat);
			pezzi.get(4).imposta_coord(new mossa(new coord(3, 2),'N', 5), mat);
			pezzi.get(5).imposta_coord(new mossa(new coord(0, 2),'N', 6), mat);
			pezzi.get(6).imposta_coord(new mossa(new coord(0, 4),'N', 7), mat);
			pezzi.get(7).imposta_coord(new mossa(new coord(1, 3),'N', 8), mat);
			pezzi.get(8).imposta_coord(new mossa(new coord(2, 3),'N', 9), mat);
			pezzi.get(9).imposta_coord(new mossa(new coord(3, 4),'N', 10), mat);
			
		}
	}

	public int seleziona_pezzo()
	{
		int sel_pezzo =0;

		Scanner s = new Scanner(System.in);
		System.out.print("Seleziona pezzo da muovere:");
		try
			{
				sel_pezzo = Integer.parseInt(s.nextLine());
			}
		catch(NumberFormatException ex) {}
		if(sel_pezzo == 54)//indietro
		{
			undo();
			return 0;
		}
		while((sel_pezzo < 1) || (sel_pezzo > 10))
		{
			if(sel_pezzo == 54)//indietro
			{
				undo();
				return 0;
			}

			System.out.print("\nPezzo non valido\nSeleziona pezzo da muovere:");
			try
				{
					sel_pezzo = Integer.parseInt(s.nextLine());
				}
			catch(NumberFormatException ex) {}
		}
		return sel_pezzo;
	}
	
	public void pulisci_matrice()
	{
		for(int i=0;i<mat.length ;i++)
			for(int j=0;j<mat[0].length;j++)
				if((mat[i][j]>49) && (mat[i][j]<54))
					mat[i][j] = 0;
					
				
	}
	
	public void print()
	{
				
		for(int i=0;i<mat.length ;i++)
		{
			System.out.print("\n\n|\t");
			for(int j=0;j<mat[0].length;j++)
			{
				System.out.print(mat[i][j]);
				System.out.print("\t-\t");
			}	
		}
		System.out.print("\n\n");
	}
	
	public int seleziona_mossa()
	{
		int mossa = 0;
		
		Scanner s = new Scanner(System.in);
		System.out.print("Come lo vuoi muovere muovere:");
		try
		{
			mossa = Integer.parseInt(s.nextLine());
		}
		catch(NumberFormatException ex) {}
		
		while(true)
		{
			if(mossa == 54)//indietro
			{
				undo();
				return 0;
			}
			if(mossa != 0)
				for(int i=0;i<mat.length ;i++)
					for(int j=0;j<mat[0].length;j++)
						if(mat[i][j] == mossa)
							return mossa;
			System.out.print("scegli mossa valida:");
			try
			{
				mossa = Integer.parseInt(s.nextLine());
			}
			catch(NumberFormatException ex) {}
		}
		
	
	}
	public void undo()
	{
		if(mosse.size() != 0)
		{
			mossa m = mosse.get(mosse.size()-1).reverse();
	
			pezzi.get(m.num_pezzo-1).imposta_coord(m, mat);
			pulisci_matrice();		
			salvataggio.print();
			print();
			mosse.remove(mosse.size()-1);
			num_mosse += 1;	
			salvataggio.add_mossa(num_mosse);
			salvataggio.contenuto.remove(salvataggio.contenuto.size() - 1);
		}
	}
	
	public void new_partita()
	{
		int sel_pezzo = 0;
		int sel_mossa = 0;
		
		while(true)
		{
		
			pulisci_matrice();
			
 			salvataggio.print();

			print();
			
			//--------------SELEZIONE PEZZO DA MUOVERE----------------
			
			sel_pezzo = seleziona_pezzo();
			while(sel_pezzo == 0)
				sel_pezzo = seleziona_pezzo();
			//---------------CONTROLLO MOSSE POSSIBILI e le SEGNA----------------------
			while(pezzi.get(sel_pezzo - 1).controlla_mosse(mat) == true)
			{
				print();	
				sel_mossa = seleziona_mossa();
				pulisci_matrice();
				
				if(sel_mossa == 50)//mossa verso l'alto
				{
					mosse.add(new mossa(pezzi.get(sel_pezzo - 1).coord.copy(), 'U', sel_pezzo));
					
				}
				else if(sel_mossa == 51)//mossa verso destra
				{
					mosse.add(new mossa(pezzi.get(sel_pezzo - 1).coord.copy(), 'R', sel_pezzo));
				}
				else if(sel_mossa == 52)//mossa verso il basso
				{
					mosse.add(new mossa(pezzi.get(sel_pezzo - 1).coord.copy(), 'D', sel_pezzo));
				}
				else if(sel_mossa == 53)//mossa verso sinistra
				{
					mosse.add(new mossa(pezzi.get(sel_pezzo - 1).coord.copy(), 'L', sel_pezzo));
				}
				else
				{
					sel_pezzo = sel_mossa;
				}
				
				if((sel_mossa >= 50) && (sel_mossa <=53))
				{
					pezzi.get(sel_pezzo - 1).imposta_coord(mosse.get(mosse.size() - 1), mat);
					num_mosse += 1;	
					salvataggio.add_mossa(num_mosse);
					salvataggio.add(mosse.get(mosse.size()-1));
					break;
				}
				
			}	
			
		}
	}
	
	
}
