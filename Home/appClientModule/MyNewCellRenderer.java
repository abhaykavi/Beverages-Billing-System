import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class MyNewCellRenderer extends DefaultTableCellRenderer {

	   int k =0;
	
		
	    MyNewCellRenderer(int clickedRow){
           k = clickedRow;
	    } 
		
	   @Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			
		
            Component cell= super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
             
            if(row == k) {
            setBackground(Color.BLUE) ;
            } 
             
            return cell;
		
		}    
		
	}


