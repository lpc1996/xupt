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
import xupt.dao.SemesterDao;
import xupt.images.Images;
import xupt.mode.SemesterModel;

public class SemesterInfo extends CommonsJDialog {
	
	/**
	 * @author 濃霧-遠方
	 */
	private static final long serialVersionUID = 1L;
	private JTextField idText;
	private JTextField nameText;

	public SemesterInfo() {
		super(new Dimension(400,500));
		this.setTitle("年级信息管理");
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
		SemesterDao semesterDao = new SemesterDao();
		List<SemesterModel> semesterList = semesterDao.getList();
		Vector<String> title = semesterDao.getComments();
		DefaultTableModel model = new DefaultTableModel(title, semesterList.size());
		int i=0;
		for(SemesterModel semester:semesterList) {
			model.setValueAt(semester.getId(), i, 0);
			model.setValueAt(semester.getName(), i, 1);
			i++;
		}
		tablePane.setTableModel(model);
		tablePane.setColumnWidth(200);
	}

	@Override
	protected JPanel InitTextPane(Dimension size) {
		// TODO Auto-generated method stub
		JPanel textPane = new JPanel();
		FlowLayout flow = new FlowLayout(FlowLayout.LEFT, 5, 5);
		textPane.setLayout(flow);
		textPane.setBorder(BorderFactory.createTitledBorder("操作一体化"));
		
		JLabel idLab = new JLabel("年级编号：");
		idLab.setPreferredSize(jlabelSize);
		idText = new JTextField();
		idText.setPreferredSize(jtextSize);
		textPane.add(idLab);
		textPane.add(idText);
		JLabel nameLab = new JLabel("年级名称：");
		nameLab.setPreferredSize(jlabelSize);
		nameText = new JTextField();
		nameText.setPreferredSize(jtextSize);
		textPane.add(nameLab);
		textPane.add(nameText);
		textPane.add(createTextBtnPane(size));
		return textPane;
	}

	@Override
	protected void InitComboBox() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected SemesterModel getData() {
		// TODO Auto-generated method stub
		SemesterModel semester = new SemesterModel();
		if(idText.getText().length() > 0) {
			semester.setId(idText.getText());
		}else {
			JOptionPane.showMessageDialog(null, "请输入年级编号！", "警告", JOptionPane.ERROR_MESSAGE, 
					new ImageIcon(new Images().getError2()));
			return null;
		}
		if(nameText.getText().length() > 0) {
			semester.setName(nameText.getText());
		}else {
			JOptionPane.showMessageDialog(null, "请输入年级名称！", "警告", JOptionPane.ERROR_MESSAGE, 
					new ImageIcon(new Images().getError2()));
			return null;
		}
		return semester;
	}

	@Override
	protected void setData(Object data) {
		// TODO Auto-generated method stub
		SemesterModel semester = (SemesterModel)data;
		idText.setText(semester.getId());
		nameText.setText(semester.getName());
	}

	@Override
	protected SemesterModel getSelectData() {
		// TODO Auto-generated method stub
		SemesterModel semester = null;
		int row = tablePane.getSelectRow();
		if(row > -1) {
			semester = new SemesterModel();
			semester.setId(tablePane.getValueAt(row, 0)+"");
			semester.setName(tablePane.getValueAt(row, 1)+"");
		}
		return semester;
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
				+"的年级吗？", "温馨提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, 
				new ImageIcon(new Images().getWarring2())) == 0) {
					SemesterDao semesterDao = new SemesterDao();
					if(semesterDao.deleteData(tablePane.getValueAt(i, 0)+"")) {
						JOptionPane.showMessageDialog(null, "编号为："+tablePane.getValueAt(i, 0)+
								"的年级信息已删除！", "温馨提示", JOptionPane.INFORMATION_MESSAGE, 
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
				SemesterModel semester = getSelectData();
				if(semester == null) {
					JOptionPane.showMessageDialog(null, "你没有选中一行数据！", "错误", 
							JOptionPane.ERROR_MESSAGE, new ImageIcon(new Images().getError2()));
					return ;
				}
				TextJDialog updateJDialog = new TextJDialog(new Dimension(320, 250));
				updateJDialog.setTitle("年级信息添加");
				updateJDialog.setContentPane(InitTextPane(new Dimension(280, 40)));
				setData(semester);
				submitBtn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						SemesterModel newSemester = getData();
						if(newSemester != null) {
							SemesterDao semesterDao = new SemesterDao();
							if(semesterDao.updateData(newSemester, semester.getId())) {
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
				insertJDialog.setTitle("年级信息添加");
				insertJDialog.setContentPane(InitTextPane(new Dimension(280, 40)));
				submitBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						SemesterModel semester = getData();
						if(semester != null) {
							SemesterDao semesterDao = new SemesterDao();
							if(semesterDao.insertData(semester)) {
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
