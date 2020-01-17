package xupt.frame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Insets;
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
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import xupt.dao.Dao;
import xupt.dao.DepartmentDao;
import xupt.images.Images;
import xupt.mode.CollegeModel;
import xupt.mode.DepartmentModel;
import xupt.util.Tools;

public class DepartmentInfo extends CommonsJDialog {

	/**
	 * @author 濃霧-遠方
	 */
	private static final long serialVersionUID = 1L;
	private JTextField idText;
	private JTextField nameText;
	private JComboBox<String> collegeBox;

	public DepartmentInfo() {
		super(new Dimension(400, 500));
		// TODO Auto-generated constructor stub
		this.setTitle("系部信息管理");
		InitContentPane();
		addInsertAction(insertBtnAction());
		addUpdateAction(updateBtnAction());
		addDeleteAction(deleteBtnAction());
		addRefreshAction(refreshBtnAction());
		InitData();
	}
	

	protected void InitData() {
		DepartmentDao departmentDao = new DepartmentDao();
		List<DepartmentModel> departmentList = departmentDao.getList();
		Vector<String> title = departmentDao.getComments();
		DefaultTableModel model = new DefaultTableModel(title, departmentList.size());
		int i=0;
		for(DepartmentModel department:departmentList) {
			model.setValueAt(department.getId(), i, 0);
			model.setValueAt(department.getName(), i, 1);
			model.setValueAt(department.getCollegeId(), i, 2);
			i++;
		}
		tablePane.setTableModel(model);
		tablePane.setColumnWidth(100);
	}
	
	protected JPanel InitTextPane() {
		JPanel textPane = new JPanel();
		textPane.setBorder(BorderFactory.createTitledBorder("操作一体化"));
		textPane.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		JLabel idLab = new JLabel("系部编号：");
		idLab.setPreferredSize(jlabelSize);
		idText = new JTextField();
		idText.setPreferredSize(jtextSize);
		textPane.add(idLab);
		textPane.add(idText);
		JLabel nameLab = new JLabel("系部名称：");
		nameLab.setPreferredSize(jlabelSize);
		nameText = new JTextField();
		nameText.setPreferredSize(jtextSize);
		textPane.add(nameLab);
		textPane.add(nameText);
		JLabel collegeLab = new JLabel("所属学院：");
		collegeLab.setPreferredSize(jlabelSize);
		collegeBox = new JComboBox<String>();
		collegeBox.setPreferredSize(jtextSize);
		textPane.add(collegeLab);
		textPane.add(collegeBox);
		textPane.add(createTextBtnPane(new Dimension(280, 40)));
		return textPane;
	}
	
	@Override
	protected void InitComboBox() {
		// TODO Auto-generated method stub
		List<String> list = new Dao().getIdAndNameList("college");
		for(String item:list) {
			collegeBox.addItem(item);
		}
	}


	@Override
	protected DepartmentModel getData() {
		// TODO Auto-generated method stub
		DepartmentModel department = new DepartmentModel();
		if(idText.getText().length() > 0) {
			department.setId(idText.getText());
		}else {
			JOptionPane.showMessageDialog(null, "请输入系部编号！", "警告", JOptionPane.ERROR_MESSAGE, 
					new ImageIcon(new Images().getError2()));
			return null;
		}
		if(nameText.getText().length() > 0) {
			department.setName(nameText.getText());
		}else{
			JOptionPane.showMessageDialog(null, "请输入系部名称！", "警告", JOptionPane.ERROR_MESSAGE, 
					new ImageIcon(new Images().getError2()));
			return null;
		}
		Tools tool = new Tools();
		department.setCollegeId(tool.Split(collegeBox.getSelectedItem()+""));
		return department;
	}


	@Override
	protected void setData(Object data) {
		// TODO Auto-generated method stub
		DepartmentModel department = (DepartmentModel)data;
		idText.setText(department.getId());
		nameText.setText(department.getName());
		Tools tool = new Tools();
		tool.setSelectedItem(collegeBox, department.getCollegeId());
	}


	@Override
	protected DepartmentModel getSelectData() {
		// TODO Auto-generated method stub
		DepartmentModel department = null;
		int row = tablePane.getSelectRow();
		if(row > -1) {
			department = new DepartmentModel();
			department.setId(tablePane.getValueAt(row, 0)+"");
			department.setName(tablePane.getValueAt(row, 1)+"");
			department.setCollegeId(tablePane.getValueAt(row, 2)+"");
		}
		return department;
	}

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

	protected ActionListener deleteBtnAction() {
		// TODO Auto-generated method stub
		ActionListener deleteAction = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int i = tablePane.getSelectRow();
				if( i < 0 ) {
					JOptionPane.showMessageDialog(null, "请选中一行数据！", "错误", 
							JOptionPane.ERROR_MESSAGE, new ImageIcon(new Images().getError2()));
					return ;
				}
				if(JOptionPane.showConfirmDialog(null, "确定要删除编号为："+tablePane.getValueAt(i, 0)
				+"的系/部吗？", "温馨提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, 
				new ImageIcon(new Images().getWarring2())) == 0) {
					DepartmentDao departmentDao = new DepartmentDao();
					if(departmentDao.deleteData(tablePane.getValueAt(i, 0)+"")) {
						JOptionPane.showMessageDialog(null, "编号为："+tablePane.getValueAt(i, 0)+
								"的系/部信息已删除！", "温馨提示", JOptionPane.INFORMATION_MESSAGE, 
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

	protected ActionListener updateBtnAction() {
		// TODO Auto-generated method stub
		ActionListener updateAction = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DepartmentModel department = getSelectData();
				if(department == null) {
					JOptionPane.showMessageDialog(null, "请你没有选中一行数据！", "错误", 
							JOptionPane.ERROR_MESSAGE, new ImageIcon(new Images().getError2()));
					return ;
				}
				TextJDialog updateJDialog = new TextJDialog(new Dimension(320,250));
				updateJDialog.setTitle("系部信息修改");
				updateJDialog.setContentPane(InitTextPane());
				InitComboBox();
				setData(department);
				submitBtn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						DepartmentModel newDepartment = getData();
						DepartmentDao departmentDao = new DepartmentDao();
						if(newDepartment != null) {
							if(departmentDao.updateData(newDepartment, department.getId())) {
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

	protected ActionListener insertBtnAction() {
		// TODO Auto-generated method stub
		ActionListener insertAction = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TextJDialog insertJDialog = new TextJDialog(new Dimension(320,250));
				insertJDialog.setTitle("系部信息添加");
				insertJDialog.setContentPane(InitTextPane());
				InitComboBox();
				submitBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						DepartmentModel department = getData();
						if(department != null) {
							DepartmentDao departmentDao = new DepartmentDao();
							if(departmentDao.insertData(department)) {
								JOptionPane.showMessageDialog(insertJDialog, "添加成功！", "温馨提示",
										JOptionPane.OK_OPTION, new ImageIcon(new Images().getSuccessful()));
								InitData();
								repaint();
								insertJDialog.dispose();
							}else {
								JOptionPane.showMessageDialog(insertJDialog, "添加失败！", "提示消息",
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
