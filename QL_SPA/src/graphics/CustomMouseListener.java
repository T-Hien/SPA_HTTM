package graphics;

import java.awt.Color;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CustomMouseListener implements MouseListener
{
	protected static ArrayList<CustomMouseListener> comps = new ArrayList<CustomMouseListener>();
	protected JComponent comp;
	protected JPasswordField pwd;
	protected GUI frame;
	protected Color pressedColor;
	protected Color beforeColor;
	protected Color afterColor;
	protected int originalClickType;
	protected int clickType;
	
	public CustomMouseListener(JComponent comp, JPasswordField pwd, GUI frame, Color pressedColor, Color beforeColor, Color afterColor, int originalClickType, int clickType)
	{
		this.comp = comp;
		this.pwd = pwd;
		this.frame = frame;
		this.beforeColor = beforeColor;
		this.afterColor = afterColor;
		this.originalClickType = originalClickType;
		this.clickType = clickType;
		CustomMouseListener.comps.add(this);
	}
	
	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {}
	
	@Override
	public void mousePressed(java.awt.event.MouseEvent e) 
	{
		for(CustomMouseListener customMouseListener : CustomMouseListener.comps)
		{
			if(e.getSource() == customMouseListener.comp && customMouseListener.originalClickType == 1) // text or password
			{
				if(customMouseListener.pwd != null)
				{
					customMouseListener.comp.setVisible(false);
					customMouseListener.pwd.setVisible(true);
					customMouseListener.pwd.setText("");
					customMouseListener.pwd.setForeground(Color.black);
					customMouseListener.pwd.requestFocus();
				}
				else
				{
					JTextField temp = (JTextField) customMouseListener.comp;
					if(customMouseListener.clickType != -1)
					{
						temp.setText("");
						temp.setForeground(Color.black);
						customMouseListener.clickType = -1;
					}
				}
			}
			else if(e.getSource() == customMouseListener.comp && customMouseListener.clickType == 2) // sign up
			{
				JTextField[] inputs = new JTextField[] {customMouseListener.frame.newUserName, customMouseListener.frame.surname, customMouseListener.frame.name, customMouseListener.frame.mail, customMouseListener.frame.phoneNumber};
				String[] components = new String[] {"Tên tài khoản", "Họ", "Tên", "Gmail", "Số điện thoại"};
				customMouseListener.comp.setForeground(customMouseListener.pressedColor);
				customMouseListener.frame.dispayLogin(false);
				for(int i=0; i<inputs.length; i++)
				{
						inputs[i].setText(components[i]);
						inputs[i].setForeground(Color.gray);
				}
				customMouseListener.frame.resetTemplate();
				customMouseListener.frame.moveScreen(new JPanel(), customMouseListener.frame.signupWindow, "LEFT");
				break;
			}
			else if(e.getSource() == customMouseListener.comp && customMouseListener.clickType == 3) // reset password
			{
				customMouseListener.comp.setForeground(customMouseListener.pressedColor);
				customMouseListener.frame.dispayLogin(false);
				customMouseListener.frame.restoreUserName.setText("Tên tài khoản");
				customMouseListener.frame.restoreUserName.setForeground(Color.gray);
				customMouseListener.frame.resetTemplate();
				customMouseListener.frame.moveScreen(new JPanel(), customMouseListener.frame.resetMailWindow, "LEFT");
				break;
			}
			else if(e.getSource() == customMouseListener.comp && customMouseListener.clickType == 4) // password
			{
				customMouseListener.comp.setVisible(false);
				customMouseListener.pwd.setVisible(true);
				customMouseListener.pwd.setText("");
				customMouseListener.pwd.setForeground(Color.black);
				customMouseListener.pwd.requestFocus();
				return;
			}
			else if(e.getSource() == customMouseListener.comp && customMouseListener.originalClickType == 5) // management menu bar
			{
				if(customMouseListener.clickType == 5)
				{
					customMouseListener.frame.managementSection.setVisible(true);
					customMouseListener.frame.managementPersonal.setVisible(false);
					customMouseListener.frame.line1.setVisible(false);
					customMouseListener.clickType = -1;
				}
				else if(customMouseListener.clickType == -1)
				{
					customMouseListener.frame.managementSection.setVisible(false);
					customMouseListener.frame.managementPersonal.setVisible(true);
					customMouseListener.frame.line1.setVisible(true);
					customMouseListener.clickType = 5;
				}
				break;
			}
		}
	}
	
	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {}
	
	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) 
	{
		for(CustomMouseListener customMouseListener : CustomMouseListener.comps)
		{
			if(e.getSource() == customMouseListener.comp)
			{
				if(e.getSource() == customMouseListener.comp && customMouseListener.clickType == 2 || customMouseListener.clickType == 3) 
				{
					customMouseListener.comp.setForeground(customMouseListener.afterColor);
					customMouseListener.comp.requestFocus();
				}
				else if(e.getSource() == customMouseListener.comp && customMouseListener.originalClickType == 5) 
				{
					if(customMouseListener.clickType == 5)
					{
						customMouseListener.frame.managementSection.setVisible(true);
						customMouseListener.frame.managementPersonal.setVisible(false);
						customMouseListener.frame.line1.setVisible(false);
						customMouseListener.clickType = -1;
					}
					else if(customMouseListener.clickType == -1)
					{
						customMouseListener.frame.managementSection.setVisible(false);
						customMouseListener.frame.managementPersonal.setVisible(true);
						customMouseListener.frame.line1.setVisible(true);
						customMouseListener.clickType = 5;
					}
				}
				break;
			}
		}
	}
	
	@Override
	public void mouseExited(java.awt.event.MouseEvent e) 
	{
		for(CustomMouseListener customMouseListener : CustomMouseListener.comps)
		{
			if(e.getSource() == customMouseListener.comp &&  customMouseListener.clickType != -1)
			{
				if(e.getSource() == customMouseListener.comp && customMouseListener.clickType == 2 || customMouseListener.clickType == 3)
				{
					customMouseListener.comp.setForeground(customMouseListener.beforeColor);
				}
				break;
			}
		}
	}
}