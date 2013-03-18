import java.lang.*;
import java.io.*;

public class Recette
{
	String Titre;
	String Categorie;
	Categorie SousCategorie;
	Provenance Provenance;
	
	String Commentaires;
	
	public Recette()
	{ }
	
	public Recette(String titre, String cat, Categorie souscat, Provenance prov)
	{
		Titre = titre;
		Categorie = cat;
		SousCategorie = souscat;
		Provenance = prov;
	}
	
	public void AjouterCommentaire(String com)
	{
		Commentaires = com;
	}

	public String getTitre() {
		return Titre;
	}

	public void setTitre(String titre) {
		Titre = titre;
	}

	public String getCategorie() {
		return Categorie;
	}

	public void setCategorie(String categorie) {
		Categorie = categorie;
	}

	public Categorie getSousCategorie() {
		return SousCategorie;
	}

	public void setSousCategorie(Categorie sousCategorie) {
		SousCategorie = sousCategorie;
	}

	public Provenance getProvenance() {
		return Provenance;
	}

	public void setProvenance(Provenance provenance) {
		Provenance = provenance;
	}

	public String getCommentaires() {
		return Commentaires;
	}

	public void setCommentaires(String commentaires) {
		Commentaires = commentaires;
	}
	
}
	
