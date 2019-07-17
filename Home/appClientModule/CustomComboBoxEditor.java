import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class CustomComboBoxEditor extends DefaultCellEditor {

	      private DefaultComboBoxModel model;

		  public CustomComboBoxEditor() {
		      super(new JComboBox());
		      this.model = (DefaultComboBoxModel)((JComboBox)getComponent()).getModel();
	}
		  
		  @Override
		  public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		      
		      model.addElement(1);
		      model.addElement(2);
		      model.addElement(3);
		      model.addElement(4);
		      model.addElement(5);
		      
		   
		      
		      return super.getTableCellEditorComponent(table, value, isSelected, row, column);
		  } 

}
