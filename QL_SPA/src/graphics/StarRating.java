package graphics;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class StarRating implements MouseListener 
{
	protected static ArrayList<StarRating> stars = new ArrayList<StarRating>();
	protected JLabel star;
	protected boolean clicked;
	public StarRating(JLabel star)
	{
		this.star = star;
		this.star.setIcon(new ImageIcon("./icon_image/unrated.png"));
		this.clicked = false;
		StarRating.stars.add(this);
	}

	public static void setRated(int rated)
	{
		if(rated >= 0 && rated < 6)
		{
			int count = 0;
			for(StarRating sr : StarRating.stars)
			{
				if(count < rated)
				{
					sr.clicked = true;
					sr.star.setIcon(new ImageIcon("./icon_image/rated.png"));
					sr.star.updateUI();
				}
				else
				{
					sr.clicked = false;
					sr.star.setIcon(new ImageIcon("./icon_image/unrated.png"));
					sr.star.updateUI();
				}
				count++;
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		if(StarRating.stars.indexOf(this) == 0 && !StarRating.stars.get(1).clicked && this.clicked == true)
		{
			this.clicked = false;
			this.star.setIcon(new ImageIcon("./icon_image/unrated.png"));
			this.star.updateUI();
			return;
		}
		for(int i=0; i<StarRating.stars.size(); i++)
		{
			if(i <= StarRating.stars.indexOf(this))
			{
				StarRating.stars.get(i).clicked = true;
				StarRating.stars.get(i).star.setIcon(new ImageIcon("./icon_image/rated.png"));
				StarRating.stars.get(i).star.updateUI();
			}
			else
			{
				StarRating.stars.get(i).clicked = false;
				StarRating.stars.get(i).star.setIcon(new ImageIcon("./icon_image/unrated.png"));
				StarRating.stars.get(i).star.updateUI();
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		if(!this.clicked)
		{
			for(int i=0; i<=StarRating.stars.indexOf(this); i++)
			{
				StarRating.stars.get(i).star.setIcon(new ImageIcon("./icon_image/rated.png"));
				StarRating.stars.get(i).star.updateUI();
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
		if(!this.clicked)
		{
			for(int i=0; i<=StarRating.stars.indexOf(this); i++)
			{
				if(!StarRating.stars.get(i).clicked)
				{
					StarRating.stars.get(i).star.setIcon(new ImageIcon("./icon_image/unrated.png"));
					StarRating.stars.get(i).star.updateUI();
				}
			}
		}
	}
}
