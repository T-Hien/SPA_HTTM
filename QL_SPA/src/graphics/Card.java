package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Card extends JPanel 
{
	private static final long serialVersionUID = 3882436530417694182L;
    private Color color1;
    private Color color2;
    private JLabel icon;
    private JLabel title;
    private JLabel value;
    private JLabel description;
    
    public Card(Color color1, Color color2) 
    {
        this.icon = new JLabel();
        this.title = new JLabel();
        this.value= new JLabel();
        this.description = new JLabel();
        this.setLayout(null);
        this.setOpaque(false);
		this.icon.setHorizontalAlignment(JLabel.LEFT);
		this.icon.setVerticalAlignment(JLabel.CENTER);
		this.icon.setBounds(10, 10, 40, 40);
        this.title.setFont(new Font("Consolas", Font.BOLD, 15)); 
		this.title.setHorizontalAlignment(JLabel.LEFT);
		this.title.setVerticalAlignment(JLabel.CENTER);
        this.title.setForeground(Color.white);
		this.title.setBounds(10, 50, 150, 30);
        this.value.setFont(new Font("Consolas", Font.BOLD, 25));
        this.value.setHorizontalAlignment(JLabel.RIGHT);
		this.value.setVerticalAlignment(JLabel.CENTER);
        this.value.setForeground(Color.white);
        this.value.setBounds(190, 15, 40, 50);
        this.description.setFont(new Font("Times New Roman", Font.PLAIN, 12)); 
        this.description.setHorizontalAlignment(JLabel.LEFT);
		this.description.setVerticalAlignment(JLabel.BOTTOM);
        this.description.setForeground(Color.white);
        this.description.setBounds(10, 80, 230, 20);
        this.color1 = color1;
        this.color2 = color2;
        this.add(this.icon);
        this.add(this.title);
        this.add(this.value);
        this.add(this.description);
    }

    public void setData(CardValue cv) 
    {
        this.icon.setIcon(cv.getIcon());
        this.title.setText(cv.getTitle());
        this.value.setText(cv.getValues());
        this.description.setText(cv.getDescription());
    }

    @Override
    protected void paintComponent(Graphics grphcs) 
    {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint g = new GradientPaint(0, 0, this.color1, 0, getHeight(), this.color2);
        g2.setPaint(g);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.setColor(new Color(255, 255, 255, 50));
        g2.fillOval(getWidth() - (getHeight() / 2), 10, getHeight(), getHeight());
        g2.fillOval(getWidth() - (getHeight() / 2) - 20, getHeight() / 2 + 20, getHeight(), getHeight());
        super.paintComponent(grphcs);
    }
}