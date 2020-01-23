package xupt.frame;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import xupt.dao.CollegeDao;
import xupt.dao.DepartmentDao;
import xupt.dao.MajorDao;
import xupt.dao.SemesterDao;
import xupt.dao.TeamDao;
import xupt.images.Images;
import xupt.mode.TeamModel;
import xupt.util.Tools;

public class TeamInfo extends CommonsJDialog {
	
	/**
	 * @author 濃霧-遠方
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField idText;
	private JTextField nameText;
	private JTextField numberText;
	private JComboBox<String> collegeBox;
	private JComboBox<String> departmentBox;
	private JComboBox<String> majorBox;
	private JComboBox<String> semesterBox;

	public TeamInfo() {
		super(new Dimension(400, 500));
		this.setTitle("班级信息管理");
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
		TeamDao teamDao = new TeamDao();
		List<TeamModel> teamList = teamDao.getList();
		Vector<String> title = teamDao.getComments();
		DefaultTableModel model = new DefaultTableModel(title, teamList.size());
		int i=0;
		for(TeamModel team:teamList) {
			model.setValueAt(team.getId(), i, 0);
			model.setValueAt(team.getName(), i, 1);
			model.setValueAt(team.getNumber(), i, 2);
			model.setValueAt(team.getCollegeId(), i, 3);
			model.setValueAt(team.getDepartmentId(), i, 4);
			model.setValueAt(team.getMajorId(), i, 5);
			model.setValueAt(team.getSemester(), i, 6);
			i++;
		}
		tablePane.setTableModel(model);
		
	}

	@Override
	protected JPanel InitTextPane(Dimension size) {
		// TODO Auto-generated method stub
		JPanel textPane = createTextJPanel();
		JLabel idLab = new JLabel("班级编号：");
		idLab.setPreferredSize(jlabelSize);
		idText = new JTextField();
		idText.setPreferredSize(jtextSize);
		textPane.add(idLab);
		textPane.add(idText);
		JLabel nameLab = new JLabel("班级名称：");
		nameLab.setPreferredSize(jlabelSize);
		nameText = new JTextField();
		nameText.setPreferredSize(jtextSize);
		textPane.add(nameLab);
		textPane.add(nameText);
		JLabel numberLab = new JLabel("班级人数：");
		numberLab.setPreferredSize(jlabelSize);
		numberText = new JTextField();
		numberText.setPreferredSize(jtextSize);
		textPane.add(numberLab);
		textPane.add(numberText);
		JLabel collegeLab = new JLabel("所属学院：");
		collegeLab.setPreferredSize(jlabelSize);
		collegeBox = new JComboBox<String>();
		collegeBox.setPreferredSize(jtextSize);
		textPane.add(collegeLab);
		textPane.add(collegeBox);
		JLabel departmentLab = new JLabel("所属系/部：");
		departmentLab.setPreferredSize(jlabelSize);
		departmentBox = new JComboBox<String>();
		departmentBox.setPreferredSize(jtextSize);
		textPane.add(departmentLab);
		textPane.add(departmentBox);
		JLabel majorLab = new JLabel("所属专业：");
		majorLab.setPreferredSize(jlabelSize);
		majorBox = new JComboBox<String>();
		majorBox.setPreferredSize(jtextSize);
		textPane.add(majorLab);
		textPane.add(majorBox);
		JLabel semesterLab = new JLabel("所属年级：");
		semesterLab.setPreferredSize(jlabelSize);
		semesterBox = new JComboBox<String>();
		semesterBox.setPreferredSize(jtextSize);
		textPane.add(semesterLab);
		textPane.add(semesterBox);
		textPane.add(createTextBtnPane(size));
		return textPane;
	}

	@Override
	protected void InitComboBox() {
		// TODO Auto-generated method stub
		List<String> list = null;
		list = new CollegeDao().getIdAndNameList();
		for(String item:list) {
			collegeBox.addItem(item);
		}
		list = new DepartmentDao().getIdAndNameList();
		for(String item:list) {
			departmentBox.addItem(item);
		}
		list = new MajorDao().getIdAndNameList();
		for(String item:list) {
			majorBox.addItem(item);
		}
		list = new SemesterDao().getIdAndNameList();
		for(String item:list) {
			semesterBox.addItem(item);
		}
	}

	@Override
	protected TeamModel getData() {
		// TODO Auto-generated method stub
		TeamModel team = new TeamModel();
		if(idText.getText().length() != 0) {
			team.setId(idText.getText());
		}else {
			JOptionPane.showMessageDialog(null, "请输入班级编号！", "警告", JOptionPane.ERROR_MESSAGE, 
					new ImageIcon(new Images().getError2()));
			return null;
		}
		if(nameText.getText().length() != 0) {
			team.setName(nameText.getText());
		}else {
			JOptionPane.showMessageDialog(null, "请输入班级名称！", "警告", JOptionPane.ERROR_MESSAGE, 
					new ImageIcon(new Images().getError2()));
			return null;
		}
		if(numberText.getText().length() != 0) {
			try {
				team.setNumber(Integer.parseInt(numberText.getText()));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				return null;
			}
		}else if(numberText.getText().length() == 0){
			JOptionPane.showMessageDialog(null, "请输入班级人数！", "警告", JOptionPane.ERROR_MESSAGE, 
					new ImageIcon(new Images().getError2()));
			return null;
		}else if(team.getNumber() <= 0) {
			JOptionPane.showMessageDialog(null, "请输入正确的人数！", "警告", JOptionPane.ERROR_MESSAGE, 
					new ImageIcon(new Images().getError2()));
			return null;
		}
		Tools tool = new Tools();
		team.setCollegeId(tool.Split(collegeBox.getSelectedItem()+""));
		team.setDepartmentId(tool.Split(departmentBox.getSelectedItem()+""));
		team.setMajorId(tool.Split(majorBox.getSelectedItem()+""));
		team.setSemester(tool.Split(semesterBox.getSelectedItem()+""));
		return team;
	}

	@Override
	protected void setData(Object data) {
		// TODO Auto-generated method stub
		TeamModel team = (TeamModel)data;
		idText.setText(team.getId());
		nameText.setText(team.getName());
		numberText.setText(team.getNumber()+"");
		Tools tool = new Tools();
		tool.setSelectedItem(collegeBox, team.getCollegeId());
		tool.setSelectedItem(departmentBox, team.getDepartmentId());
		tool.setSelectedItem(majorBox, team.getMajorId());
		tool.setSelectedItem(semesterBox, team.getSemester());
	}

	@Override
	protected TeamModel getSelectData() {
		// TODO Auto-generated method stub
		int row = tablePane.getSelectRow();
		TeamModel team = null;
		if(row > -1) {
			team = new TeamModel();
			team.setId(tablePane.getValueAt(row, 0)+"");
			team.setName(tablePane.getValueAt(row, 1)+"");
			team.setNumber(Integer.parseInt(tablePane.getValueAt(row, 2)+""));	
			team.setCollegeId(tablePane.getValueAt(row, 3)+"");
			team.setDepartmentId(tablePane.getValueAt(row, 4)+"");
			team.setMajorId(tablePane.getValueAt(row, 5)+"");
			team.setSemester(tablePane.getValueAt(row, 6)+"");
		}
		return team;
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
					JOptionPane.showMessageDialog(null, "你没有选中一行数据！", "错误", 
							JOptionPane.ERROR_MESSAGE, new ImageIcon(new Images().getError2()));
					return;
				}
				if(JOptionPane.showConfirmDialog(null, "确定要删除编号为："+tablePane.getValueAt(i, 0)
				+"的班级信息吗？", "温馨提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, 
				new ImageIcon(new Images().getWarring2())) == 0) {
				TeamDao teamDao = new TeamDao();
					if(teamDao.deleteData(tablePane.getValueAt(i, 0)+"")) {
						JOptionPane.showMessageDialog(null, "编号为："+tablePane.getValueAt(i, 0)+
								"的班级信息已删除！", "温馨提示", JOptionPane.INFORMATION_MESSAGE, 
								new ImageIcon(new Images().getYes2()));
						InitData();
						repaint();
					}else {
						JOptionPane.showMessageDialog(null, "删除失败！", "错误", JOptionPane.ERROR_MESSAGE,
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
				TeamModel team = getSelectData();
				if(team == null) {
					JOptionPane.showMessageDialog(null, "你没有选中一行数据！", "错误", 
							JOptionPane.ERROR_MESSAGE, new ImageIcon(new Images().getError2()));
					return;
				}
				TextJDialog updateJDialog = new TextJDialog(new Dimension(630, 400));
				updateJDialog.setTitle("班级信息修改");
				updateJDialog.setContentPane(InitTextPane(new Dimension(590, 40)));
				InitComboBox();
				setData(team);
				submitBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						TeamModel newTeam = getData();
						if(newTeam != null) {
							TeamDao teamDao = new TeamDao();
							if(teamDao.updateData(newTeam, team.getId())) {
								JOptionPane.showMessageDialog(null, "修改成功！", "温馨提示", 
										JOptionPane.OK_OPTION, new ImageIcon(new Images().getSuccessful()));
								InitData();
								repaint();
								updateJDialog.dispose();
							}else {
								JOptionPane.showMessageDialog(null, "修改失败！", "提示消息",
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
				TextJDialog insertJDialog = new TextJDialog(new Dimension(630, 400));
				insertJDialog.setTitle("班级信息添加");
				insertJDialog.setContentPane(InitTextPane(new Dimension(590, 40)));
				InitComboBox();
				submitBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						TeamModel team = getData();
						if(team != null) {
							TeamDao teamDao = new TeamDao();
							if(teamDao.insertData(team)) {
								JOptionPane.showMessageDialog(null, "添加成功！", "温馨提示",
										JOptionPane.OK_OPTION, new ImageIcon(new Images().getSuccessful()));
								InitData();
								repaint();
								insertJDialog.dispose();
							}else {
								JOptionPane.showMessageDialog(null, "添加失败！", "提示消息",
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
