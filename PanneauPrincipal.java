import java.awt.Graphics;
import javax.swing.JPanel;
  
public class PanneauPrincipal extends JPanel 
{
	public void paintComponent(Graphics g)
  	{
		super.paintComponent(g);
		g.drawString("Recettes :", 10, 30);
		g.drawRect(10,50,500,500);
  	}               
}