package xupt.frame;

import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import xupt.images.Images;

public class CommonsJDialog extends JDialog {

	public CommonsJDialog(Dimension size) {
		// TODO Auto-generated constructor stub
		super();
		this.setSize(size);
		this.setIconImage(new Images().getSchoolLogo());
		this.setLocation(new Point( (Login.getScreenSize().width-size.width)/2 , (Login.getScreenSize().height-size.height)/2 ));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	class TextJDialog extends JDialog{
		public TextJDialog(Dimension size) {
			super();
			this.setIconImage(new Images().getSchoolLogo());
			this.setLocation(new Point( (Login.getScreenSize().width-size.width)/2 , (Login.getScreenSize().height-size.height)/2 ));
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		}
	}
	
	class TablePane extends JScrollPane{
		
		private JTable table;

		public TablePane() {
			super();
			initPane();
		}
		
		private void initPane() {
			this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			table = new JTable();
			this.setViewportView(table);
		}
		
		public void setTableModel(DefaultTableModel model) {
			table.setModel(model);
		}
		
		public int getSelectRowCount() {
			return table.getSelectedRowCount();
		}
		
		public Object getValueAt(int row,int cloumn) {
			return table.getValueAt(row, cloumn);
		}

		public void setAutoResizeMode(int autoResizeAllColumns) {
			// TODO Auto-generated method stub
			this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		}
	}
	
}
