package xupt.frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

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
			this.setSize(size);
			this.setIconImage(new Images().getSchoolLogo());
			this.setLocation(new Point( (Login.getScreenSize().width-size.width)/2 , (Login.getScreenSize().height-size.height)/2 ));
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		}
	}
	
	class TablePane extends JPanel{
		
		private JTable table;
		private JScrollPane js;
		private JPanel searchPane;
		private JTextField textFild;
		private JButton searchBtn;

		public TablePane() {
			super();
			FlowLayout flow = new FlowLayout(FlowLayout.LEFT,10,5);
			this.setLayout(flow);
			initPane();
		}
		
		public TablePane(Dimension size) {
			super();
			this.setSize(size);
			this.setPreferredSize(size);
			FlowLayout flow = new FlowLayout(FlowLayout.LEFT,5,5);
			this.setLayout(flow);
			initPane();
		}
		
		private void initPane() {
			js = new JScrollPane();
			js.setPreferredSize(new Dimension(getWidth()-25,getHeight()-50));
			js.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			table = new JTable();
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.setRowSelectionAllowed(true);
			js.setViewportView(table);
			this.add(js);
			
			searchPane = new JPanel();
			FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT, 10, 5);
			searchPane.setLayout(flowLayout);
			searchPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			searchPane.setPreferredSize(new Dimension(getWidth()-25,40));
			textFild = new JTextField();
			textFild.setPreferredSize(new Dimension(getWidth()-140,30));
			searchPane.add(textFild);
			searchBtn = new JButton("查询");
			searchBtn.setPreferredSize(new Dimension(80,30));
			addSearchBtnAction();
			searchPane.add(searchBtn);
			this.add(searchPane);
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

		public void setColumnWidth(int width) {
			TableColumnModel tableColumnModel = table.getColumnModel();
			for(int i=0; i<tableColumnModel.getColumnCount(); i++) {
				TableColumn column = tableColumnModel.getColumn(i);
				column.setPreferredWidth(width);
				column.setMinWidth(width);
			}
		}
		
		public void setSelectedRow(int row) {
			table.setRowSelectionInterval(row, row);
		}
		
		private void addSearchBtnAction() {
			searchBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					String str = textFild.getText();
					if(str.length() == 0) {
						JOptionPane.showMessageDialog(null, "请输入要查询的信息！", "警告", JOptionPane.WARNING_MESSAGE, new ImageIcon(new Images().getWarring2()));
						return;
					}
					int row = -1;
					for(int i=0; i<table.getRowCount(); i++) {
						if(str.equals(table.getValueAt(i, 0)) || str.equals(table.getValueAt(i, 1))) {
							row = i;
							table.setRowSelectionInterval(i, i);
							table.scrollRectToVisible(table.getCellRect(i, 0, true));
							break;
						}
					}
					textFild.setText("");
					if(row == -1) {
						JOptionPane.showMessageDialog(null, "找不到："+str, "错误", JOptionPane.WARNING_MESSAGE, new ImageIcon(new Images().getError2()));
						
					}
				}
			});
		}
	}
	
}
