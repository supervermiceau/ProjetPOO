import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import java.util.Random;

public class Fenetre extends JFrame
{
	private JTable Tableau;
	private Tableau Donnees = new Tableau();
	TableRowSorter<TableModel> sorter;   
	Random randomGenerator = new Random();
	
	public Fenetre()
	{
       super();
       
        setTitle("Gestionnaire de recettes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        Tableau = new JTable(Donnees);
        
        sorter = new TableRowSorter<TableModel>(Tableau.getModel()); 
        
        //Tableau.setAutoCreateRowSorter(true);
        Tableau.setRowSorter(sorter);
        sorter.setSortsOnUpdates(true);
        

        
        getContentPane().add(new JScrollPane(Tableau), BorderLayout.CENTER);
        
        JPanel boutons = new JPanel();
        
        boutons.add(new JButton(new AddAction()));
        boutons.add(new JButton(new RemoveAction()));
        boutons.add(new JButton(new FilterAction()));
        boutons.add(new JButton(new RandAction()));
        boutons.add(new JButton(new CancelAction()));
        //boutons.add(new JButton(new ModifySousCatAction()));
        boutons.add(new JButton(new SaveAction()));
        
        
        getContentPane().add(boutons, BorderLayout.SOUTH);
        
 
        //pack();
	}
		
    private class AddAction extends AbstractAction 
    {
        private AddAction() 
        {
            super("Ajouter");
        }
 
        public void actionPerformed(ActionEvent e) 
        {
			/*Object[] cat = {"Salé", "Sucré"};
			
			String s = (String)JOptionPane.showInputDialog("Choisissez la catégorie de la nouvelle recette",
								"Customized Dialog",
								JOptionPane.PLAIN_MESSAGE,
								null,
								cat);
								

			
			if ((s != null) && (s.length() > 0))
			{
				Donnees.addRecette(new Recette("Lol", 
				"loll", 
				ESDonnees.getSousCategories().get(0),
				ESDonnees.getProvenances().get(0)));
			}*/
			
        	
        }
    }
 
    private class RemoveAction extends AbstractAction
    {
        private RemoveAction() {
            super("Supprimer");
        }
 
        public void actionPerformed(ActionEvent e) 
        {
            int[] selection = Tableau.getSelectedRows();
 
            for(int i = selection.length - 1; i >= 0; i--){
            	Donnees.removeRecette(selection[i]);
            }
        }
    }
	
    private class FilterAction extends AbstractAction 
    {
        private FilterAction() {
            super("Filtrer");
        }
     
        public void actionPerformed(ActionEvent e) {
            String regex = JOptionPane.showInputDialog("Parametres de la recherche");
     
            sorter.setRowFilter(RowFilter.regexFilter(regex, 0, 1, 2, 3));
        }
    }
    
    private class RandAction extends AbstractAction 
    {
        private RandAction() {
            super("Au hasard");
        }
     
        public void actionPerformed(ActionEvent e) 
        {
        	String regex = Donnees.getValueAt(randomGenerator.nextInt(Donnees.getRowCount()),2).toString();
            sorter.setRowFilter(RowFilter.regexFilter(regex, 2));
        }
    }
    
    private class CancelAction extends AbstractAction 
    {
        private CancelAction() {
            super("Annuler");
        }
     
        public void actionPerformed(ActionEvent e) 
        {
            sorter.setRowFilter(RowFilter.regexFilter("", 0, 1, 2, 3));
        }
    }
    
    private class SaveAction extends AbstractAction 
    {
        private SaveAction() {
            super("Sauvegarder");
        }
     
        public void actionPerformed(ActionEvent e) 
        {
        	ESDonnees.SauvegardeProvenances("RecettesData/index_livres.txt");
        	ESDonnees.SauvegardeRecettes("RecettesData/sale.txt");
        	ESDonnees.SauvegardeRecettes("RecettesData/sucre.txt");
        }
    }
    
    
    private class ModifySousCatAction extends AbstractAction 
    {
        private ModifySousCatAction() {
            super("Modifier sous categorie");
        }
     
        public void actionPerformed(ActionEvent e) 
        {
        	int[] selection = Tableau.getSelectedRows();
        	String nouveaunom = null;
        	
        	if (selection.length > 0)
        	{
        		nouveaunom = JOptionPane.showInputDialog("Nouveau nom",
								Donnees.getValueAt(selection[0], 1));
        		
        		Donnees.setValueAt(nouveaunom, selection[0], 1);
        	}
        }
    }
}
