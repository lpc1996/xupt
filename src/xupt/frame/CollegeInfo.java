package xupt.frame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import xupt.dao.BaseInfoDao;
import xupt.dao.CollegeDao;
import xupt.images.Images;
import xupt.mode.CollegeModel;
import xupt.util.Tools;

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
	private JTextField idText;
	private JTextField nameText;
	private JComboBox<String> presidentBox;
	private JComboBox<String> vicePresidentBox;
	private JTextArea informationArea;

	public CollegeInfo() {
		super(new Dimension(400,500));
		// TODO Auto-generated constructor stub
		this.setTitle("ѧԺ��Ϣ����");
		InitContentPane();
		addInsertAction(insertBtnAction());
		addUpdateAction(updateBtnAction());
		addDeleteAction(deleteBtnAction());
		addRefreshAction(refreshBtnAction());
		InitData();
	}
	
//	private void InitContentPane() {
//		contentPane = new JPanel();
//		FlowLayout flow = new FlowLayout(FlowLayout.LEFT,5,5);
//		contentPane.setLayout(flow);
//		contentPane.setBorder(BorderFactory.createTitledBorder("����һ�廯"));
//		
//		tablePane = new TablePane(new Dimension(getWidth()-20,390));
//		contentPane.add(tablePane);
//		
//		btnPanel = createBtnPanel();
//		addInsertAction(insertBtnAction());
//		addUpdateAction(updateBtnAction());
//		addDeleteAction(deleteBtnAction());
//		addRefreshAction(refreshBtnAction());
//		
//		contentPane.add(btnPanel);
//		this.setContentPane(contentPane);
//	}
	
	protected void InitData() {
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
			model.setValueAt(college.getInformation(), i, 4);
			i++;
		}
		tablePane.setTableModel(model);
		tablePane.setColumnWidth(100);
	}
	
	protected JPanel InitTextPane() {
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
		JLabel informationLab = new JLabel("ѧԺ��飺");
		informationLab.setPreferredSize(jlabelSize);
		informationArea = new JTextArea();
		informationArea.setPreferredSize(new Dimension(590, 90));
		textPane.add(informationLab);
		textPane.add(informationArea);
		JPanel btnPane = new JPanel();
		btnPane.setPreferredSize(new Dimension(590, 40));
		btnPane.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
		submitBtn = new JButton("�ύ");
		submitBtn.setPreferredSize(new Dimension(80,30));
		btnPane.add(submitBtn);
		textPane.add(btnPane);
		return textPane;
	}
	
	protected void initComboBox() {
		List<String> idAndNameList = new BaseInfoDao().getIdAndNameList("teacher");
		for(String name:idAndNameList) {
			presidentBox.addItem(name);
			vicePresidentBox.addItem(name);
		}
	}
	
	protected CollegeModel getData() {
		CollegeModel college = new CollegeModel();
		if(idText.getText().length() > 0) {
			college.setId(idText.getText());
		}else {
			JOptionPane.showMessageDialog(null, "������ѧԺ��ţ�", "����", JOptionPane.ERROR_MESSAGE, 
					new ImageIcon(new Images().getError2()));
			return null;
		}
		if(nameText.getText().length() > 0) {
			college.setName(nameText.getText());
		}else{
			JOptionPane.showMessageDialog(null, "������ѧԺ���ƣ�", "����", JOptionPane.ERROR_MESSAGE, 
					new ImageIcon(new Images().getError2()));
			return null;
		}
		Tools tool = new Tools();
		college.setPresidentId(tool.Split(presidentBox.getSelectedItem()+""));
		college.setVicePresidentId(tool.Split(vicePresidentBox.getSelectedItem()+""));
		college.setInformation(informationArea.getText());
		
		return college;
	}
	
	protected void setData(CollegeModel data) {
		idText.setText(data.getId());
		nameText.setText(data.getName());
		Tools tool = new Tools();
		tool.setSelectedItem(presidentBox, data.getPresidentId());
		tool.setSelectedItem(vicePresidentBox, data.getVicePresidentId());
		informationArea.setText(data.getInformation());
	}
	
	protected CollegeModel getSelectData() {
		CollegeModel college = null;
		int row = tablePane.getSelectRow();
		if(row > -1) {
			college = new CollegeModel();
			college.setId(tablePane.getValueAt(row, 0)+"");
			college.setName(tablePane.getValueAt(row, 1)+"");
			college.setPresidentId(tablePane.getValueAt(row, 2)+"");
			college.setVicePresidentId(tablePane.getValueAt(row, 3)+"");
			college.setInformation(tablePane.getValueAt(row, 4)+"");
		}
		return college;
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
		ActionListener deleteAction = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int i = tablePane.getSelectRow();
				if( i < 0 ) {
					JOptionPane.showMessageDialog(null, "��ѡ��һ�����ݣ�", "����", 
							JOptionPane.ERROR_MESSAGE, new ImageIcon(new Images().getError2()));
					return ;
				}
				if(JOptionPane.showConfirmDialog(null, "ȷ��Ҫɾ�����Ϊ��"+tablePane.getValueAt(i, 0)
				+"��ѧԺ��", "��ܰ��ʾ", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, 
				new ImageIcon(new Images().getWarring2())) == 0) {
					CollegeDao collegeDao = new CollegeDao();
					if(collegeDao.deleteData(tablePane.getValueAt(i, 0)+"")) {
						JOptionPane.showMessageDialog(null, "���Ϊ��"+tablePane.getValueAt(i, 0)+
								"��ѧԺ��Ϣ��ɾ����", "��ܰ��ʾ", JOptionPane.INFORMATION_MESSAGE, 
								new ImageIcon(new Images().getYes2()));
						InitData();
						repaint();
					}else {
						JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ�", "����", JOptionPane.ERROR_MESSAGE,
								new ImageIcon(new Images().getError2()));
						
					}
				}
			}
		};
		return deleteAction;
	}

	private ActionListener updateBtnAction() {
		// TODO Auto-generated method stub
		ActionListener updateAction = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CollegeModel college = getSelectData();
				if(college == null) {
					JOptionPane.showMessageDialog(null, "����û��ѡ��һ�����ݣ�", "����", 
							JOptionPane.ERROR_MESSAGE, new ImageIcon(new Images().getError2()));
					return ;
				}
				TextJDialog updateJDialog = new TextJDialog(new Dimension(630, 450));
				updateJDialog.setTitle("�޸�ѧԺ��Ϣ");
				updateJDialog.setContentPane(InitTextPane());
				initComboBox();
				setData(college);
				submitBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						CollegeDao collegeDao = new CollegeDao();
						CollegeModel newCollege = getData();
						if(collegeDao.updateData(newCollege, college.getId())) {
							JOptionPane.showMessageDialog(null, "�޸ĳɹ���", "��ܰ��ʾ", 
									JOptionPane.OK_OPTION, new ImageIcon(new Images().getSuccessful()));
							InitData();
							repaint();
							updateJDialog.dispose();
						}else {
							JOptionPane.showMessageDialog(null, "�޸�ʧ�ܣ�", "��ʾ��Ϣ",
									JOptionPane.ERROR_MESSAGE, new ImageIcon(new Images().getError2()));
							return;
						}
					}
				});
				updateJDialog.setVisible(true);
			}
		};
		return updateAction;
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
				initComboBox();
				submitBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						CollegeModel college = getData();
						if(college!= null) {
							CollegeDao collegeDao = new CollegeDao();
							if(collegeDao.insertData(college)) {
								JOptionPane.showMessageDialog(null, "��ӳɹ���", "��ܰ��ʾ",
										JOptionPane.OK_OPTION, new ImageIcon(new Images().getSuccessful()));
								InitData();
								repaint();
								insertJDialog.dispose();
							}else {
								JOptionPane.showMessageDialog(null, "���ʧ�ܣ�", "��ʾ��Ϣ",
										JOptionPane.ERROR_MESSAGE, new ImageIcon(new Images().getError2()));
								return;
							}
						}
					}
				});
				insertJDialog.setVisible(true);
			}
		};
		return insertAction;
	}

}
