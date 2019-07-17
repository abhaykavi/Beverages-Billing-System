import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class CellRenderer extends DefaultTableCellRenderer implements TableCellRenderer  {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
	
		Component c = super.getTableCellRendererComponent(table, value,
                isSelected, hasFocus, row, column);
          
		c.setBackground(Color.ORANGE);
		
		return c;
	}

}
