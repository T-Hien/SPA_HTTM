package graphics;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

public class PanelAction extends javax.swing.JPanel 
{
	private static final long serialVersionUID = 8787178566771787737L;
    private ActionButton firstAction = new ActionButton();;
    private ActionButton secondAction = new ActionButton();;
    
	public PanelAction(String icon1, String icon2) 
	{
	    this.setLayout(new FlowLayout());
	    this.firstAction.setIcon(new ImageIcon(icon1)); 
	    this.secondAction.setIcon(new ImageIcon(icon2)); 
	    if(GUI.managementIcon1 == null)
	    {
	    	this.firstAction.setVisible(false);
	    }
	    if(GUI.managementIcon2 == null)
	    {
	    	this.secondAction.setVisible(false);
	    }
	    this.add(this.firstAction);
	    this.add(this.secondAction);
    }

    public void addEvent(TableActionEvent event, int row) 
    {
        this.firstAction.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                event.onFirstAction(row);
            }
        });
        this.secondAction.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                event.onSecondAction(row);
            }
        });
    }      
}
