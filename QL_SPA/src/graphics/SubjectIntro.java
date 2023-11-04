package graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JPanel;

public class SubjectIntro extends JPanel
{
	private static final long serialVersionUID = 1275684384854353519L;;
	private Image backgroundImage;
	
	public SubjectIntro(Image backgroundImage)
	{
		this.backgroundImage = backgroundImage;
	}
	
	public void paint(Graphics g)
	{
		/*Graphics2D g2d = (Graphics2D) g;
		super.paint(g);
		g2d.drawImage(this.backgroundImage, 0, 0, null);*/
	}
}

