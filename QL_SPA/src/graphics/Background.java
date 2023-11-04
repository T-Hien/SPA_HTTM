package graphics;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JComponent;

public class Background extends JComponent 
{
	private static final long serialVersionUID = 9092677081786260602L;
	private Image image;
    
    public Background(Image image) 
    {
        this.image = image;
    }
    
    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}
