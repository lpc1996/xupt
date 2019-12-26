package xupt.frame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import xupt.dao.CollegeDao;
import xupt.mode.CollegeModel;

public class CollegeInfo extends CommonsJDialog {

	/**
	 * ѧԱ��Ϣ������
	 * ����ʵ�֣�
	 * 1.��ѯ�����е������в�ѯ����
	 * 2.���ѧԺ��Ϣ
	 * 3.��ѡ�е�ѧԺ��Ϣ�����޸�
	 * 4.ɾ��ѡ�е�ѧԺ��Ϣ
	 * 5.ˢ�´���
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private TablePane tablePane;
	private JPanel btnPanel;
	private JTextField idText;
	private JTextField nameText;
	private JComboBox<String> presidentBox;
	private JComboBox<String> vicePresidentBox;

	public CollegeInfo() {
		super(new Dimension(400,500));
		// TODO Auto-generated constructor stub
		this.setTitle("ѧԺ��Ϣ����");
		InitContentPane();
		InitData();
	}
	
	private void InitContentPane() {
		contentPane = new JPanel();
		FlowLayout flow = new FlowLayout(FlowLayout.LEFT,5,5);
		contentPane.setLayout(flow);
		contentPane.setBorder(BorderFactory.createTitledBorder("����һ�廯"));
		
		tablePane = new TablePane(new Dimension(getWidth()-20,390));
		contentPane.add(tablePane);
		
		btnPanel = createBtnPanel();
		addInsertAction(insertBtnAction());
		addUpdateAction(updateBtnAction());
		addDeleteAction(deleteBtnAction());
		addRefreshAction(refreshBtnAction());
		
		contentPane.add(btnPanel);
		this.setContentPane(contentPane);
	}
	
	private void InitData() {
		CollegeDao collegeDao = new CollegeDao();
		List<CollegeModel> collegeList = collegeDao.getList();
		Vector<String> title = collegeDao.getComments();
		DefaultTableModel model = new DefaultTableModel(title, collegeList.size());
		int i=0;
		for(CollegeModel college:collegeList) {
			model.setValueAt(college.getId(), i, 0);
			model.setValueAt(college.getName(), i, 1);
			model.setValueAt(college.getPresidentId(), i, 2);
			model.setValueAt(college.getVicePresidentId(), i, 3);
			i++;
		}
		tablePane.setTableModel(model);
		tablePane.setColumnWidth(100);
	}
	
	private JPanel InitTextPane() {
		JPanel textPane = new JPanel();
		FlowLayout flow = new FlowLayout(FlowLayout.LEFT, 5, 5);
		textPane.setLayout(flow);
		textPane.setBorder(BorderFactory.createTitledBorder("����һ�廯"));
		
		JLabel idLab = new JLabel("ѧԺ��ţ�");
		idLab.setPreferredSize(jlabelSize);
		idText = new JTextField();
		idText.setPreferredSize(jtextSize);
		textPane.add(idLab);
		textPane.add(idText);
		JLabel nameLab = new JLabel("ѧԺ���ƣ�");
		nameLab.setPreferredSize(jlabelSize);
		nameText = new JTextField();
		nameText.setPreferredSize(jtextSize);
		textPane.add(nameLab);
		textPane.add(nameText);
		JLabel presidentLab = new JLabel("Ժ��ID��");
		presidentLab.setPreferredSize(jlabelSize);
		presidentBox = new JComboBox<String>();
		presidentBox.setPreferredSize(jtextSize);
		textPane.add(presidentLab);
		textPane.add(presidentBox);
		JLabel vicePresidentLab = new JLabel("��Ժ��ID��");
		vicePresidentLab.setPreferredSize(jlabelSize);
		vicePresidentBox = new JComboBox<String>();
		vicePresidentBox.setPreferredSize(jtextSize);
		textPane.add(vicePresidentLab);
		textPane.add(vicePresidentBox);
		return textPane;
	}

	private ActionListener refreshBtnAction() {
		// TODO Auto-generated method stub
		ActionListener refreshAction = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				InitData();
				repaint();
			}
		};
		return refreshAction;
	}

	private ActionListener deleteBtnAction() {
		// TODO Auto-generated method stub
		return null;
	}

	private ActionListener updateBtnAction() {
		// TODO Auto-generated method stub
		return null;
	}

	private ActionListener insertBtnAction() {
		// TODO Auto-generated method stub
		ActionListener insertAction = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TextJDialog insertJDialog = new TextJDialog(new Dimension(630, 450));
				insertJDialog.setTitle("���ѧԺ��Ϣ");
				insertJDialog.setContentPane(InitTextPane());
				insertJDialog.setVisible(true);
			}
		};
		return insertAction;
	}

}
