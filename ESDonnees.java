import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;


public class ESDonnees 
{
	static private List<Categorie> SousCategories = new ArrayList<Categorie>();
	static private List<Provenance> Provenances = new ArrayList<Provenance>();
	static private List<Recette> Recettes = new ArrayList<Recette>();
	
	public static List<Recette> getRecettes()
	 {
		return Recettes;
	}

	public static void setRecettes(List<Recette> recettes)
	 {
		Recettes = recettes;
	}

	public static List<Provenance> getProvenances()
	 {
		return Provenances;
	}

	public static void setProvenances(List<Provenance> provenances) 
	{
		Provenances = provenances;
	}

	
	public static void ChargementProvenance(String fileName)
	{	
		try
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF8"));
			String str;
			int i = 1;
			
			while ((str = br.readLine()) != null)
			{
				// sachant qu'une provenance est de cette forme:
				// 2.	les salades
				// il faut separer le numero du nom
				String[] splitter = str.split("[.]");
				
				// on ajoute la provenance
				Provenances.add(new Provenance(splitter[1].trim(), i));
				i++;
			}
			
			br.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void ChargementRecettes(String fileName) throws IOException
	{		
		try
		{
			
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF8"));
			String str;
			String souscat = null;
			
			while ((str = br.readLine()) != null)
			{
				// si la ligne contient au moins 2 caractères, alors ça semble valide
				if (str.length() > 2)
				{
					// on regarde si c'est une sous catégorie...
					if (str.charAt(0) == '*')
					{
						SousCategories.add(new Categorie(str.substring(1).trim().split(":")[0]));
					}
					// ou une recette
					else
					{
						// on split la ligne.
						// en effet, elle est de cette forme:
						// - mini-brochettes de porc et chorizo      (6)
						// il faut donc separer le nom du numero de provenance
						String[] splitter = str.substring(1).trim().split("[()]+");
						Provenance prov = null;
						
						// si split a reussi alors la provenance existe
						if (splitter.length > 1)
						{
							prov = Provenances.get(Integer.parseInt(splitter[1])-1);
						}
						
						// on ajoute la nouvelle recette
						Recettes.add(new Recette(splitter[0], 
												fileName.split("[./]")[1],
												SousCategories.get(SousCategories.size()-1),
												prov));
					}
				}
			}
			
			br.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static List<Categorie> getSousCategories() {
		return SousCategories;
	}

	public static void setSousCategories(List<Categorie> sousCategories) {
		SousCategories = sousCategories;
	}

	public static void SauvegardeRecettes(String fileName)
	{
		try
		{
			BufferedWriter br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF8"));
			int i = 0, j = 0;
			StringBuffer sb = new StringBuffer();
			String sous_cat = null;
			
			String[] splitter = fileName.split("[/.]+");
			
			for (i = 0; i < Recettes.size(); i++)
			{
				// si le nom de fichier contient la categorie de la recette
				// on ecrit la recette dans le fichier
				// cela sert à separer les diffèrentes categories et a ne pas
				// tout ecrire dans un seul fichier
				// les categories etant separes dans des fichiers differents
				// sale.txt  		sucre.txt ...
				if (splitter[1].contains(Recettes.get(i).getCategorie()) == true)
				{
					// Si la sous categorie a change
					// Alors on l'écrit dans le fichier
					// de la forme
					// * nom de la sous categorie
					if (sous_cat != Recettes.get(i).getSousCategorie().getNom()) 
					{
						sous_cat = Recettes.get(i).getSousCategorie().getNom();
						
						sb.setLength(0); // on vide le buffer
						sb.append("\n");
						sb.append("* ");
						sb.append(sous_cat);
						sb.append("\n");
						br.write(sb.toString());
					}
					
					// on ecrit la recette courante d'indice i
					sb.setLength(0); // on vide le buffer
					
					sb.append("- ");
					sb.append(Recettes.get(i).getTitre()); // on recupere le titre
					
					if (Recettes.get(i).getProvenance() != null) // puis la provenance si elle existe
					{
						sb.append(" (");
						sb.append(Recettes.get(i).getProvenance().getID()); // il faut le numero de la provenance
						sb.append(")");
					}
					
					// retour a la ligne, nouvelle recette
					sb.append("\n");
					
					br.write(sb.toString());
				}
			}
			
			br.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void SauvegardeProvenances(String fileName)
	{
		try
		{
			BufferedWriter br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF8"));
			int i = 0;
			StringBuffer sb = new StringBuffer();
			
			for (i = 0; i < Provenances.size(); i++)
			{
				sb.setLength(0); // on vide le buffer
				
				sb.append(i+1); // On commence par ajouter le numero de la provenance (1,2,3,...)
				sb.append(".\t"); // on ajoute une tabulation ->   1.	........
				sb.append(Provenances.get(i).getNom()); // on complete avec le nom  -> 	1.	Marie-Claire
				sb.append("\n"); // on finit avec un retour à la ligne \n
				
				br.write(sb.toString()); // on écrit dans le fichier
			}
			
			br.close(); // on ferme le fichier
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
