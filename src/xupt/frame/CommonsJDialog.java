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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import xupt.images.Images;
import xupt.mode.StudentModel;

public class CommonsJDialog extends JDialog {

	/**
	 * 子窗口公用设计
	 */
	private static final long serialVersionUID = 1L;
	private JButton insertBtn;
	private JButton updateBtn;
	private JButton deleteBtn;
	private JButton refreshBtn;
	
	/**
	 * 构造函数
	 */
	public CommonsJDialog(Dimension size) {
		// TODO Auto-generated constructor stub
		super();
		this.setSize(size);
		this.setIconImage(new Images().getSchoolLogo());
		this.setLocation(new Point( (Login.getScreenSize().width-size.width)/2 , (Login.getScreenSize().
				height-size.height)/2 ));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	public JPanel createBtnPanel() {
		JPanel btnPanel = new JPanel();
		FlowLayout flow = new FlowLayout(5);
		btnPanel.setLayout(flow);
		btnPanel.setPreferredSize(new Dimension(getWidth()-10,40));
		
		insertBtn = new JButton("添加");
		insertBtn.setPreferredSize(new Dimension(80,30));
		updateBtn = new JButton("修改");
		updateBtn.setPreferredSize(new Dimension(80,30));
		deleteBtn = new JButton("删除");
		deleteBtn.setPreferredSize(new Dimension(80,30));
		refreshBtn = new JButton("刷新");
		refreshBtn.setPreferredSize(new Dimension(80,30));
		
		btnPanel.add(insertBtn);
		btnPanel.add(updateBtn);
		btnPanel.add(deleteBtn);
		btnPanel.add(refreshBtn);
		
		return btnPanel;
	}
	
	public void addInsertAction(ActionListener action) {
		insertBtn.addActionListener(action);
	}
	
	public void addUpdateAction(ActionListener action) {
		updateBtn.addActionListener(action);
	}
	
	public void addDeleteAction(ActionListener action) {
		deleteBtn.addActionListener(action);
	}
	
	public void addRefreshAction(ActionListener action) {
		refreshBtn.addActionListener(action);
	}
	
	   
	class TextJDialog extends JDialog{
		/*
		 * 数据操作
		 * 添加和修改操作窗口
		 */
		private static final long serialVersionUID = 1L;
		
		public TextJDialog(Dimension size) {
			super();
			this.setSize(size);
			this.setIconImage(new Images().getSchoolLogo());
			this.setLocation(new Point( (Login.getScreenSize().width-size.width)/2 , (Login.getScreenSize()
					.height-size.height)/2 ));
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		}
	}
	
	class TablePane extends JPanel{
		
		/*
		 * 数据展示
		 * 创建表格并将从数据库得到的数据通过表格展示
		 */
		private static final long serialVersionUID = 1L;
		private JTable table;
		private JScrollPane js;
		private JPanel searchPane;
		private JTextField textFild;
		private JButton searchBtn;
		
		public TablePane() {
			/*
			 * 不含参量的构造函数，创建一个默认大小的JPanel对象
			 */
			super();
			FlowLayout flow = new FlowLayout(FlowLayout.LEFT,10,5);
			this.setLayout(flow);
			initPane();
		}
		
		public TablePane(Dimension size) {
			/*
			 * 含有参数size的构造函数，创建一个指定大小的JPane对象
			 */
			super();
			this.setSize(size);
			this.setPreferredSize(size);
			FlowLayout flow = new FlowLayout(FlowLayout.LEFT,5,5);
			this.setLayout(flow);
			initPane();
		}
		
		private void initPane() {
			/*
			 * 给新创建的JPanel对象添加组件
			 */
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
			/*
			 * 给表格组件指定TableModel
			 */
			table.setModel(model);
		}
		
		public int getSelectRowCount() {
			/*
			 * 获取表格中行的数量
			 */
			return table.getSelectedRowCount();
		}
		
		public int getSelectRow() {
			/*
			 * 获取表格中选中行的行号
			 */
			return table.getSelectedRow();
		}
		
		public Object getValueAt(int row,int cloumn) {
			/*
			 * 获取表格中指定位置单元格的数据
			 */
			return table.getValueAt(row, cloumn);
		}

		public void setColumnWidth(int width) {
			/*
			 * 设置表格列的宽度
			 */
			TableColumnModel tableColumnModel = table.getColumnModel();
			for(int i=0; i<tableColumnModel.getColumnCount(); i++) {
				TableColumn column = tableColumnModel.getColumn(i);
				column.setPreferredWidth(width);
				column.setMinWidth(width);
			}
		}
		
		public void setSelectedRow(int row) {
			/*
			 * 设置行号为row的行被选中
			 */
			table.setRowSelectionInterval(row, row);
		}
		
		private void addSearchBtnAction() {
			/*
			 * 为查询组件注册监听器
			 */
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
