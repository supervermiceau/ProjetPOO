import java.io.IOException;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class Tableau extends AbstractTableModel 
{
    private String[] entetes = {
    		"Categorie",
            "Sous categorie",
            "Nom de la recette",
            "Provenance"};
 
    public Tableau() {
        super();
        
		try 
		{
			ESDonnees.ChargementProvenance("RecettesData/index_livres.txt");
			ESDonnees.ChargementRecettes("RecettesData/sale.txt");
			ESDonnees.ChargementRecettes("RecettesData/sucre.txt");
			
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 
    public int getRowCount()
    {
        return ESDonnees.getRecettes().size();
    }
 
    public int getColumnCount() 
    {
        return entetes.length;
    }
 
    public String getColumnName(int columnIndex)
    {
        return entetes[columnIndex];
    }
 
    public Object getValueAt(int rowIndex, int columnIndex) 
    {
        switch(columnIndex)
        {
            case 0:
                return ESDonnees.getRecettes().get(rowIndex).getCategorie();
            case 1:
                return ESDonnees.getRecettes().get(rowIndex).getSousCategorie().getNom();
            case 2:
                return ESDonnees.getRecettes().get(rowIndex).getTitre();
            case 3:
            	if (ESDonnees.getRecettes().get(rowIndex).getProvenance() == null)
            		return null;
            	else
            		return ESDonnees.getRecettes().get(rowIndex).getProvenance().getNom();
            default:
                return null;
        }
    }
    
    public void addRecette(Recette recette) 
    {
    	ESDonnees.getRecettes().add(recette);
 
        fireTableRowsInserted(ESDonnees.getRecettes().size() -1, ESDonnees.getRecettes().size() -1);
    }
 
    public void removeRecette(int rowIndex) 
    {
    	ESDonnees.getRecettes().remove(rowIndex);
 
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) 
    {
        return (columnIndex != 0); 
    }
     
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) 
    {
        if(aValue != null)
        {
            Recette recette = ESDonnees.getRecettes().get(rowIndex);
     
            switch(columnIndex)
            {
                case 0:
                	recette.setCategorie((String)aValue);
                    break;
                case 1:
					recette.getSousCategorie().setNom((String)aValue);
                    break;
                case 2:
                	recette.setTitre((String)aValue);
                    break;
                case 3:
					// il faut regarder si la provenance existe
                	Provenance p = recette.getProvenance();
                	if (p != null)
                	{
	                	p.setNom((String)aValue);
	                	recette.setProvenance(p);
                	}
 
                    break;
            }
            fireTableRowsUpdated(0, getRowCount()-1);
        }
    }
    
    @Override
    public Class getColumnClass(int columnIndex)
    {
        switch(columnIndex)
        {
        	/*case 1:
        		return Categorie.class;*/
        	//case 3:
        		//return Provenance.class;
            default:
                return Object.class;
        }
    }

}
