package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class ActionButton extends JButton 
{
	private static final long serialVersionUID = -6450823952631435685L;
	private boolean mousePress;

    public ActionButton() 
    {
    	setContentAreaFilled(false);
        setBorder(new EmptyBorder(3, 3, 3, 3));
        addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mousePressed(MouseEvent me) 
            {
                mousePress = true;
            }

            @Override
            public void mouseReleased(MouseEvent me) 
            {
                mousePress = false;
            }
        });
    }

    @Override
    protected void paintComponent(Graphics grphics) 
    {
        Graphics2D g2 = (Graphics2D) grphics.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = getWidth();
        int height = getHeight();
        int size = Math.min(width, height);
        int x = (width - size) / 2;
        int y = (height - size) / 2;
        if (mousePress) 
        {
            g2.setColor(new Color(0x9ba0a8));
        } 
        else 
        {
            g2.setColor(new Color(0xd0d4db));
        }
        g2.fill(new Ellipse2D.Double(x, y, size, size));
        g2.dispose();
        super.paintComponent(grphics);
    }
}