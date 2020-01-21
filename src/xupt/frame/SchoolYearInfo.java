package xupt.frame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import xupt.dao.SchoolYearDao;
import xupt.images.Images;
import xupt.mode.SchoolYearModel;
import xupt.util.DateChooser;
import xupt.util.Tools;

public class SchoolYearInfo extends CommonsJDialog {
	
	/**
	 * @author ���F-�h��
	 */
	private static final long serialVersionUID = 1L;
	private JTextField idText;
	private JTextField nameText;
	private JTextField beginText;
	private JTextField endText;

	public SchoolYearInfo() {
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
		SchoolYearDao schoolYearDao = new SchoolYearDao();
		List<SchoolYearModel> schoolYearList = schoolYearDao.getList();
		Vector<String> title = schoolYearDao.getComments();
		DefaultTableModel model = new DefaultTableModel(title, schoolYearList.size());
		int i=0;
		for(SchoolYearModel schoolYear:schoolYearList) {
			model.setValueAt(schoolYear.getId(), i, 0);
			model.setValueAt(schoolYear.getName(), i, 1);
			model.setValueAt(schoolYear.getBegin(), i, 2);
			model.setValueAt(schoolYear.getEnd(), i, 3);
			i++;
		}
		tablePane.setTableModel(model);
		tablePane.setColumnWidth(100);
	}

	@Override
	protected JPanel InitTextPane(Dimension size) {
		// TODO Auto-generated method stub
		JPanel textPane = new JPanel();
		FlowLayout flow = new FlowLayout(FlowLayout.LEFT, 5, 5);
		textPane.setLayout(flow);
		textPane.setBorder(BorderFactory.createTitledBorder("����һ�廯"));
		JLabel idLab = new JLabel("ѧ���ţ�");
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
		JLabel beginLab = new JLabel("��ʼʱ�䣺");
		DateChooser dc = DateChooser.getInstance("yyyy-MM-dd");
		beginLab.setPreferredSize(jlabelSize);
		beginText = new JTextField();
		dc.register(beginText);
		beginText.setPreferredSize(jtextSize);
		textPane.add(beginLab);
		textPane.add(beginText);
		JLabel endLab = new JLabel("����ʱ��");
		endLab.setPreferredSize(jlabelSize);
		endText = new JTextField();
		DateChooser dc1 = DateChooser.getInstance("yyyy-MM-dd");
		dc1.register(endText);
		endText.setPreferredSize(jtextSize);
		textPane.add(endLab);
		textPane.add(endText);
		textPane.add(createTextBtnPane(size));
		return textPane;
	}

	@Override
	protected void InitComboBox() {
		// TODO Auto-generated method stub
		
	}
	
	protected void InitTimeText() {
		Tools tool = new Tools();
		String currentTime = tool.getNowTime();
		beginText.setText(currentTime);
		endText.setText(currentTime);
	}

	@Override
	protected SchoolYearModel getData() {
		// TODO Auto-generated method stub
		SchoolYearModel schoolYear = new SchoolYearModel();
		if(idText.getText().length() != 0) {
			schoolYear.setId(idText.getText());
		}else {
			JOptionPane.showMessageDialog(null, "������ѧ���ţ�", "����", JOptionPane.ERROR_MESSAGE, 
					new ImageIcon(new Images().getError2()));
			return null;
		}
		if(nameText.getText().length() != 0) {
			schoolYear.setName(nameText.getText());
		}else {
			JOptionPane.showMessageDialog(null, "������ѧ�����ƣ�", "����", JOptionPane.ERROR_MESSAGE, 
					new ImageIcon(new Images().getError2()));
			return null;
		}
		schoolYear.setBegin(beginText.getText());
		schoolYear.setEnd(endText.getText());
		return schoolYear;
	}

	@Override
	protected void setData(Object data) {
		// TODO Auto-generated method stub
		SchoolYearModel schoolYear = (SchoolYearModel)data;
		idText.setText(schoolYear.getId());
		nameText.setText(schoolYear.getName());
		beginText.setText(schoolYear.getBegin());
		endText.setText(schoolYear.getEnd());
	}

	@Override
	protected SchoolYearModel getSelectData() {
		// TODO Auto-generated method stub
		int row = tablePane.getSelectRow();
		SchoolYearModel schoolYear = null;
		if(row >= 0) {
			schoolYear = new SchoolYearModel();
			schoolYear.setId(tablePane.getValueAt(row, 0)+"");
			schoolYear.setName(tablePane.getValueAt(row, 1)+"");
			schoolYear.setBegin(tablePane.getValueAt(row, 2)+"");
			schoolYear.setEnd(tablePane.getValueAt(row, 3)+"");
		}
		return schoolYear;
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
				int row = tablePane.getSelectRow();
				if(row < 0) {
					JOptionPane.showMessageDialog(null, "��ѡ��һ�����ݣ�", "����", 
							JOptionPane.ERROR_MESSAGE, new ImageIcon(new Images().getError2()));
					return ;
				}
				SchoolYearDao schoolYearDao = new SchoolYearDao();
				if(JOptionPane.showConfirmDialog(null, "ȷ��Ҫɾ�����Ϊ��"+tablePane.getValueAt(row, 0)
				+"��ѧ����Ϣ��", "��ܰ��ʾ", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, 
				new ImageIcon(new Images().getWarring2())) == 0) {
					if(schoolYearDao.deleteDate(tablePane.getValueAt(row, 0)+"")) {
						JOptionPane.showMessageDialog(null, "���Ϊ��"+tablePane.getValueAt(row, 0)+
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
				SchoolYearModel schoolYear = getSelectData();
				if(schoolYear == null) {
					JOptionPane.showMessageDialog(null, "��û��ѡ��һ�����ݣ�", "����", 
							JOptionPane.ERROR_MESSAGE, new ImageIcon(new Images().getError2()));
					return;
				}
				TextJDialog updateJDialog = new TextJDialog(new Dimension(320, 250));
				updateJDialog.setTitle("ѧ����Ϣ�޸�");
				updateJDialog.setContentPane(InitTextPane(new Dimension(280, 40)));
				setData(schoolYear);
				submitBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						SchoolYearModel newSchoolYear = getData();
						if(newSchoolYear != null) {
							SchoolYearDao schoolYearDao = new SchoolYearDao();
							if(schoolYearDao.updateData(newSchoolYear, schoolYear.getId())) {
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
				TextJDialog insertJDialog = new TextJDialog(new Dimension(320, 250));
				insertJDialog.setTitle("ѧ����Ϣ���");
				insertJDialog.setContentPane(InitTextPane(new Dimension(280, 40)));
				submitBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						SchoolYearModel schoolYear = getData();
						if(schoolYear != null) {
							SchoolYearDao schoolYearDao = new SchoolYearDao();
							if(schoolYearDao.insertData(schoolYear)) {
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
