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

import xupt.dao.CollegeDao;
import xupt.dao.Dao;
import xupt.dao.MajorDao;
import xupt.frame.CommonsJDialog.TextJDialog;
import xupt.images.Images;
import xupt.mode.MajorModel;
import xupt.util.Tools;

public class MajorInfo extends CommonsJDialog {
	
	/**
	 * @author 濃霧-遠方
	 */
	private static final long serialVersionUID = 1L;
	private JTextField idText;
	private JTextField nameText;
	private JComboBox<String> collegeBox;
	private JComboBox<String> departmentBox;

	public MajorInfo() {
		super(new Dimension(400, 500));
		this.setTitle("学院信息管理");
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
		MajorDao majorDao = new MajorDao();
		List<MajorModel> majorList = majorDao.getList();
		Vector<String> title = majorDao.getComments();
		DefaultTableModel model = new DefaultTableModel(title, majorList.size());
		int i=0;
		for(MajorModel major:majorList) {
			model.setValueAt(major.getId(), i, 0);
			model.setValueAt(major.getName(), i, 1);
			model.setValueAt(major.getCollegeId(), i, 2);
			model.setValueAt(major.getDepartmentId(), i, 3);
			i++;
		}
		tablePane.setTableModel(model);
		tablePane.setColumnWidth(80);
	}

	@Override
	protected JPanel InitTextPane(Dimension size) {
		// TODO Auto-generated method stub
		JPanel textPane = new JPanel();
		FlowLayout flow = new FlowLayout(FlowLayout.LEFT, 5, 5);
		textPane.setLayout(flow);
		textPane.setBorder(BorderFactory.createTitledBorder("操作一体化"));
		JLabel idLab = new JLabel("专业编号：");
		idLab.setPreferredSize(jlabelSize);
		idText = new JTextField();
		idText.setPreferredSize(jtextSize);
		textPane.add(idLab);
		textPane.add(idText);
		JLabel nameLab = new JLabel("专业名称：");
		nameLab.setPreferredSize(jlabelSize);
		nameText = new JTextField();
		nameText.setPreferredSize(jtextSize);
		textPane.add(nameLab);
		textPane.add(nameText);
		JLabel collegeLab = new JLabel("所属学院ID：");
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
		textPane.add(createTextBtnPane(size));
		return textPane;
	}

	@Override
	protected void InitComboBox() {
		// TODO Auto-generated method stub
		Dao dao = new Dao();
		List<String> list = dao.getIdAndNameList("college");
		for(String str:list) {
			collegeBox.addItem(str);
		}
		list = dao.getIdAndNameList("department");
		for(String str:list) {
			departmentBox.addItem(str);
		}
	}

	@Override
	protected MajorModel getData() {
		// TODO Auto-generated method stub
		MajorModel major = new MajorModel();
		if(idText.getText().length() > 0) {
			major.setId(idText.getText());
		}else {
			JOptionPane.showMessageDialog(null, "请输入专业编号！", "警告", JOptionPane.ERROR_MESSAGE, 
					new ImageIcon(new Images().getError2()));
			return null;
		}
		if(nameText.getText().length() > 0) {
			major.setName(nameText.getText());
		}else {
			JOptionPane.showMessageDialog(null, "请输入专业名称！", "警告", JOptionPane.ERROR_MESSAGE, 
					new ImageIcon(new Images().getError2()));
			return null;
		}
		Tools tool = new Tools();
		major.setCollegeId(tool.Split(collegeBox.getSelectedItem()+""));
		major.setDepartmentId(tool.Split(departmentBox.getSelectedItem()+""));
		return major;
	}

	@Override
	protected void setData(Object data) {
		// TODO Auto-generated method stub
		MajorModel major = (MajorModel)data;
		idText.setText(major.getId());
		nameText.setText(major.getName());
		Tools tool = new Tools();
		tool.setSelectedItem(collegeBox, major.getCollegeId());
		tool.setSelectedItem(departmentBox, major.getDepartmentId());
	}

	@Override
	protected MajorModel getSelectData() {
		// TODO Auto-generated method stub
		int row = tablePane.getSelectRow();
		MajorModel major = null;
		if(row > -1) {
			major = new MajorModel();
			major.setId(tablePane.getValueAt(row, 0)+"");
			major.setName(tablePane.getValueAt(row, 1)+"");
			major.setCollegeId(tablePane.getValueAt(row, 2)+"");
			major.setDepartmentId(tablePane.getValueAt(row, 2)+"");
		}
		return major;
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
				if( i < 0 ) {
					JOptionPane.showMessageDialog(null, "请选中一行数据！", "错误", 
							JOptionPane.ERROR_MESSAGE, new ImageIcon(new Images().getError2()));
					return ;
				}
				if(JOptionPane.showConfirmDialog(null, "确定要删除编号为："+tablePane.getValueAt(i, 0)
				+"的专业吗？", "温馨提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, 
				new ImageIcon(new Images().getWarring2())) == 0) {
					MajorDao majorDao = new MajorDao();
					if(majorDao.deleteData(tablePane.getValueAt(i, 0)+"")) {
						JOptionPane.showMessageDialog(null, "编号为："+tablePane.getValueAt(i, 0)+
								"的专业信息已删除！", "温馨提示", JOptionPane.INFORMATION_MESSAGE, 
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
				MajorModel major = getSelectData();
				if(major == null) {
					JOptionPane.showMessageDialog(null, "你没有选中一行数据！", "错误", 
							JOptionPane.ERROR_MESSAGE, new ImageIcon(new Images().getError2()));
					return ;
				}
				TextJDialog updateJDialog = new TextJDialog(new Dimension(630, 450));
				updateJDialog.setTitle("修改学院信息");
				updateJDialog.setContentPane(InitTextPane(new Dimension(590, 40)));
				InitComboBox();
				setData(major);
				submitBtn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						MajorModel newMajor = getData();
						if(newMajor != null) {
							MajorDao majorDao = new MajorDao();
							if(majorDao.updateData(newMajor, major.getId())) {
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
				TextJDialog insertJDialog = new TextJDialog(new Dimension(320, 250));
				insertJDialog.setTitle("专业信息添加");
				insertJDialog.setContentPane(InitTextPane(new Dimension(280, 40)));
				InitComboBox();
				submitBtn.addActionListener(new ActionListener() {
		
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						MajorModel major = getData();
						if(major != null) {
							MajorDao majorDao = new MajorDao();
							if(majorDao.insertData(major)) {
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
