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
	 * �Ӵ��ڹ������
	 */
	private static final long serialVersionUID = 1L;
	private JButton insertBtn;
	private JButton updateBtn;
	private JButton deleteBtn;
	private JButton refreshBtn;
	
	/**
	 * ���캯��
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
		
		insertBtn = new JButton("���");
		insertBtn.setPreferredSize(new Dimension(80,30));
		updateBtn = new JButton("�޸�");
		updateBtn.setPreferredSize(new Dimension(80,30));
		deleteBtn = new JButton("ɾ��");
		deleteBtn.setPreferredSize(new Dimension(80,30));
		refreshBtn = new JButton("ˢ��");
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
		 * ���ݲ���
		 * ��Ӻ��޸Ĳ�������
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
		 * ����չʾ
		 * ������񲢽������ݿ�õ�������ͨ�����չʾ
		 */
		private static final long serialVersionUID = 1L;
		private JTable table;
		private JScrollPane js;
		private JPanel searchPane;
		private JTextField textFild;
		private JButton searchBtn;
		
		public TablePane() {
			/*
			 * ���������Ĺ��캯��������һ��Ĭ�ϴ�С��JPanel����
			 */
			super();
			FlowLayout flow = new FlowLayout(FlowLayout.LEFT,10,5);
			this.setLayout(flow);
			initPane();
		}
		
		public TablePane(Dimension size) {
			/*
			 * ���в���size�Ĺ��캯��������һ��ָ����С��JPane����
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
			 * ���´�����JPanel����������
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
			searchBtn = new JButton("��ѯ");
			searchBtn.setPreferredSize(new Dimension(80,30));
			addSearchBtnAction();
			searchPane.add(searchBtn);
			this.add(searchPane);
		}
		
		public void setTableModel(DefaultTableModel model) {
			/*
			 * ��������ָ��TableModel
			 */
			table.setModel(model);
		}
		
		public int getSelectRowCount() {
			/*
			 * ��ȡ������е�����
			 */
			return table.getSelectedRowCount();
		}
		
		public int getSelectRow() {
			/*
			 * ��ȡ�����ѡ���е��к�
			 */
			return table.getSelectedRow();
		}
		
		public Object getValueAt(int row,int cloumn) {
			/*
			 * ��ȡ�����ָ��λ�õ�Ԫ�������
			 */
			return table.getValueAt(row, cloumn);
		}

		public void setColumnWidth(int width) {
			/*
			 * ���ñ���еĿ��
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
			 * �����к�Ϊrow���б�ѡ��
			 */
			table.setRowSelectionInterval(row, row);
		}
		
		private void addSearchBtnAction() {
			/*
			 * Ϊ��ѯ���ע�������
			 */
			searchBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					String str = textFild.getText();
					if(str.length() == 0) {
						JOptionPane.showMessageDialog(null, "������Ҫ��ѯ����Ϣ��", "����", JOptionPane.WARNING_MESSAGE, new ImageIcon(new Images().getWarring2()));
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
						JOptionPane.showMessageDialog(null, "�Ҳ�����"+str, "����", JOptionPane.WARNING_MESSAGE, new ImageIcon(new Images().getError2()));
					}
				}
			});
		}
	}
	
}
