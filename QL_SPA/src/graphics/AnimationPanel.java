package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.Timer;

public class AnimationPanel extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 2741972619494358754L;
	private ArrayList<JComponent> comps = new ArrayList<JComponent>(); 
	private int endpoint;
	
	public AnimationPanel()
	{
		Timer timer = new Timer(100, this);
		timer.restart();
	}
	
	public void addComponents(JComponent[] comps, int endpoint)
	{
		for(JComponent comp : comps)
		{
			this.comps.add(comp);
		}
		this.endpoint = endpoint;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		for(JComponent comp : this.comps)
		{
			comp.setLocation(comp.getLocation().x+20, comp.getLocation().y);
			if(comp.getLocation().x > this.endpoint)
			{
				comp.setLocation(-this.endpoint, comp.getLocation().y);
			}
		}
		this.repaint();
	}

}
