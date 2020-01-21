package xupt.frame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import xupt.dao.SchoolTremDao;
import xupt.dao.SchoolYearDao;
import xupt.images.Images;
import xupt.mode.SchoolTremModel;
import xupt.util.DateChooser;
import xupt.util.Tools;

public class SchoolTremInfo extends CommonsJDialog {
	
	/**
	 * @author ���F-�h��
	 */
	private static final long serialVersionUID = 1L;
	private JTextField idText;
	private JTextField nameText;
	private JComboBox<String> SYBox;
	private JTextField beginText;
	private JTextField endText;

	public SchoolTremInfo() {
		super(new Dimension(400, 500));
		this.setTitle("ѧ����Ϣ����");
		InitContentPane();
		addInsertAction(insertBtnAction());
		addUpdateAction(updateBtnAction());
		addDeleteAction(deleteBtnAction());
		addRefreshAction(refreshBtnAction());
		InitData();
	}
	@Override
	protected void InitData() {
		// TODO Auto-generated method stub
		SchoolTremDao schoolTremDao = new SchoolTremDao();
		List<SchoolTremModel> schoolTremList = schoolTremDao.getList();
		Vector<String> title = schoolTremDao.getComments();
		DefaultTableModel model = new DefaultTableModel(title, schoolTremList.size());
		int i=0;
		for(SchoolTremModel schoolTrem:schoolTremList) {
			model.setValueAt(schoolTrem.getId(), i, 0);
			model.setValueAt(schoolTrem.getName(), i, 1);
			model.setValueAt(schoolTrem.getSYId(), i, 2);
			model.setValueAt(schoolTrem.getBegin(), i, 3);
			model.setValueAt(schoolTrem.getEnd(), i, 4);
			i++;
		}
		tablePane.setTableModel(model);
	}

	@Override
	protected JPanel InitTextPane(Dimension size) {
		// TODO Auto-generated method stub
		JPanel textPane = new JPanel();
		FlowLayout flow = new FlowLayout(FlowLayout.LEFT, 5, 5);
		textPane.setLayout(flow);
		textPane.setBorder(BorderFactory.createTitledBorder("����һ�廯"));
		JLabel idLab = new JLabel("ѧ�ڱ�ţ�");
		idLab.setPreferredSize(jlabelSize);
		idText = new JTextField();
		idText.setPreferredSize(jtextSize);
		textPane.add(idLab);
		textPane.add(idText);
		JLabel nameLab = new JLabel("ѧ�����ƣ�");
		nameLab.setPreferredSize(jlabelSize);
		nameText = new JTextField();
		nameText.setPreferredSize(jtextSize);
		textPane.add(nameLab);
		textPane.add(nameText);
		JLabel SYLab = new JLabel("ѧ���ţ�");
		SYLab.setPreferredSize(jlabelSize);
		SYBox = new JComboBox<String>();
		SYBox.setPreferredSize(jtextSize);
		textPane.add(SYLab);
		textPane.add(SYBox);
		JLabel beginLab = new JLabel("��ʼʱ�䣺");
		beginLab.setPreferredSize(jlabelSize);
		beginText = new JTextField();
		DateChooser dc1 = DateChooser.getInstance("yyyy-MM-dd");
		dc1.register(beginText);
		beginText.setPreferredSize(jtextSize);
		textPane.add(beginLab);
		textPane.add(beginText);
		JLabel endLab = new JLabel("��ֹʱ�䣺");
		endLab.setPreferredSize(jlabelSize);
		endText = new JTextField();
		DateChooser dc2 = DateChooser.getInstance("yyyy-MM-dd");
		dc2.register(endText);
		endText.setPreferredSize(jtextSize);
		textPane.add(endLab);
		textPane.add(endText);
		textPane.add(createTextBtnPane(size));
		return textPane;
	}

	@Override
	protected void InitComboBox() {
		// TODO Auto-generated method stub
		SchoolYearDao schoolYearDao = new SchoolYearDao();
		List<String> list = schoolYearDao.getIdAndNameList();
		for(String str:list) {
			SYBox.addItem(str);
		}
		Tools tool = new Tools();
		String currentTime = tool.getNowTime();
		beginText.setText(currentTime);
	}
	
	protected void InitTimeText() {
		Tools tool = new Tools();
		String currentTime = tool.getNowTime();
		beginText.setText(currentTime);
		endText.setText(currentTime);
	}

	@Override
	protected SchoolTremModel getData() {
		// TODO Auto-generated method stub
		SchoolTremModel schoolTrem = new SchoolTremModel();
		if(idText.getText().length() > 0) {
			schoolTrem.setId(idText.getText());
		}else {
			JOptionPane.showMessageDialog(null, "������ѧ�ڱ�ţ�", "����", JOptionPane.ERROR_MESSAGE, 
					new ImageIcon(new Images().getError2()));
			return null;
		}
		if(nameText.getText().length() > 0) {
			schoolTrem.setName(nameText.getText());
		}else {
			JOptionPane.showMessageDialog(null, "������ѧ�ڱ�ţ�", "����", JOptionPane.ERROR_MESSAGE, 
					new ImageIcon(new Images().getError2()));
			return null;
		}
		Tools tool = new Tools();
		schoolTrem.setSYId(tool.Split(SYBox.getSelectedItem()+""));
		schoolTrem.setBegin(beginText.getText());
		schoolTrem.setEnd(endText.getText());
		return schoolTrem;
	}

	@Override
	protected void setData(Object data) {
		// TODO Auto-generated method stub
		SchoolTremModel schoolTrem = (SchoolTremModel)data;
		idText.setText(schoolTrem.getId());
		nameText.setText(schoolTrem.getName());
		new Tools().setSelectedItem(SYBox, schoolTrem.getSYId());
		beginText.setText(schoolTrem.getBegin());
		endText.setText(schoolTrem.getEnd());
	}

	@Override
	protected SchoolTremModel getSelectData() {
		// TODO Auto-generated method stub
		SchoolTremModel schoolTrem = null;
		int row = tablePane.getSelectRow();
		if(row > -1) {
			schoolTrem = new SchoolTremModel();
			schoolTrem.setId(tablePane.getValueAt(row, 0)+"");
			schoolTrem.setName(tablePane.getValueAt(row, 1)+"");
			schoolTrem.setSYId(tablePane.getValueAt(row, 2)+"");
			schoolTrem.setBegin(tablePane.getValueAt(row, 3)+"");
			schoolTrem.setEnd(tablePane.getValueAt(row, 4)+"");
		}
		return schoolTrem;
	}

	@Override
	protected ActionListener refreshBtnAction() {
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

	@Override
	protected ActionListener deleteBtnAction() {
		// TODO Auto-generated method stub
		ActionListener deleteAction = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int i = tablePane.getSelectRow();
				if(i < 0) {
					JOptionPane.showMessageDialog(null, "��ѡ��һ�����ݣ�", "����", 
							JOptionPane.ERROR_MESSAGE, new ImageIcon(new Images().getError2()));
					return ;
				}
				SchoolTremDao schoolTremDao = new SchoolTremDao();
				if(JOptionPane.showConfirmDialog(null, "ȷ��Ҫɾ�����Ϊ��"+tablePane.getValueAt(i, 0)
				+"��ѧ����Ϣ��", "��ܰ��ʾ", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, 
				new ImageIcon(new Images().getWarring2())) == 0) {
					if(schoolTremDao.deleteData(tablePane.getValueAt(i, 0)+"")) {
						JOptionPane.showMessageDialog(null, "���Ϊ��"+tablePane.getValueAt(i, 0)+
								"��ѧ����Ϣ��ɾ����", "��ܰ��ʾ", JOptionPane.INFORMATION_MESSAGE, 
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

	@Override
	protected ActionListener updateBtnAction() {
		// TODO Auto-generated method stub
		ActionListener updateAction = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SchoolTremModel schoolTrem = getSelectData();
				if(schoolTrem == null) {
					JOptionPane.showMessageDialog(null, "��û��ѡ��һ�����ݣ�", "����", 
							JOptionPane.ERROR_MESSAGE, new ImageIcon(new Images().getError2()));
					return;
				}
				TextJDialog updateJDialog = new TextJDialog(new Dimension(320, 400));
				updateJDialog.setTitle("ѧ����Ϣ�޸�");
				updateJDialog.setContentPane(InitTextPane(new Dimension(280, 40)));
				InitComboBox();
				setData(schoolTrem);
				submitBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						SchoolTremModel newSchoolTrem = getData();
						if(newSchoolTrem != null) {
							SchoolTremDao schoolTremDao = new SchoolTremDao();
							if(schoolTremDao.updateData(newSchoolTrem, schoolTrem.getId())) {
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
					}
				});
				updateJDialog.setVisible(true);
			}
		};
		return updateAction;
	}

	@Override
	protected ActionListener insertBtnAction() {
		// TODO Auto-generated method stub
		ActionListener insertAction = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TextJDialog insertJDialog = new TextJDialog(new Dimension(320, 400));
				insertJDialog.setTitle("ѧ����Ϣ���");
				insertJDialog.setContentPane(InitTextPane(new Dimension(280, 40)));
				InitComboBox();
				InitTimeText();
				submitBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						SchoolTremModel schoolTrem = getData();
						if(schoolTrem != null) {
							SchoolTremDao schoolTremDao = new SchoolTremDao();
							if(schoolTremDao.insertData(schoolTrem)) {
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
