package graphics;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class TableActionCellEditor extends DefaultCellEditor 
{
	private static final long serialVersionUID = -4236284195386132543L;
	private TableActionEvent event;

    public TableActionCellEditor(TableActionEvent event) 
    {
        super(new JCheckBox());
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column) 
     {
        PanelAction action = new PanelAction(GUI.managementIcon1, GUI.managementIcon2);
        action.addEvent(event, row);
        action.setBackground(jtable.getSelectionBackground());
        return action;
    }
}
