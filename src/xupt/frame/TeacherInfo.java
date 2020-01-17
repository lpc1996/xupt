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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import xupt.dao.CollegeDao;
import xupt.dao.Dao;
import xupt.dao.DepartmentDao;
import xupt.dao.TeacherDao;
import xupt.images.Images;
import xupt.mode.TeacherModel;
import xupt.util.DateChooser;
import xupt.util.Tools;

public class TeacherInfo extends CommonsJDialog {
	
	/**
	 * 教职工信息管理
	 */
	private static final long serialVersionUID = 1L;
	private JTextField idText;
	private JTextField nameText;
	private JTextField formarNameText;
	private JComboBox<String> sexComBox;
	private JTextField ageText;
	private JTextField nativePlaceText;
	private JComboBox<String> idCardTypeComBox;
	private JTextField idCardNumText;
	private JTextField telText;
	private JLabel yearLab;
	private JTextField yearText;
	private JLabel collegeLab;
	private JComboBox<String> collegeBox;
	private JLabel departmentLab;
	private JComboBox<String> departmentBox;
	private JLabel levelLab;
	private JComboBox<String> levelBox;
	private JLabel educationLab;
	private JComboBox<String> educationBox;
	private JButton submitBtn;

	public TeacherInfo() {
		super(new Dimension(675,550));
		// TODO Auto-generated constructor stub
		setTitle("教职工信息管理");
		InitContentPane();
		addInsertAction(insertBtnAction());
		addUpdateAction(updateBtnAction());
		addDeleteAction(deleteBtnAction());
		addRefreshAction(refreshBtnAction());
		InitData();
	}
	
	protected void InitData() {
		TeacherDao teacherDao = new TeacherDao();
		List<TeacherModel> teacherList = teacherDao.getList();
		Vector<String> comments = teacherDao.getComments();
		DefaultTableModel model = new DefaultTableModel(comments, teacherList.size());
		int i=0;
		for(TeacherModel teacher:teacherList) {
			model.setValueAt(teacher.getBaseInfo().getId(), i, 0);
			model.setValueAt(teacher.getBaseInfo().getName(), i, 1);
			model.setValueAt(teacher.getBaseInfo().getFormarName(), i, 2);
			model.setValueAt(teacher.getBaseInfo().getSex(), i, 3);
			model.setValueAt(teacher.getBaseInfo().getAge(), i, 4);
			model.setValueAt(teacher.getBaseInfo().getNativePlace(), i, 5);
			model.setValueAt(teacher.getBaseInfo().getIDCARDTYPE(), i, 6);
			model.setValueAt(teacher.getBaseInfo().getIDCARDNUM(), i, 7);
			model.setValueAt(teacher.getBaseInfo().getType(), i, 8);
			model.setValueAt(teacher.getBaseInfo().getTel(), i, 9);
			model.setValueAt(teacher.getCollege(), i, 10);
			model.setValueAt(teacher.getDepartment(), i, 11);
			model.setValueAt(teacher.getLevel(), i, 12);
			model.setValueAt(teacher.getEducation(), i, 13);
			model.setValueAt(teacher.getYear(), i, 14);
			i++;
		}
		tablePane.setTableModel(model);
		tablePane.setColumnWidth(80);
	}
	
	protected JPanel InitTextPane(Dimension size) {
		JPanel textPane = new JPanel();
		FlowLayout flow = new FlowLayout(FlowLayout.LEFT, 5, 5);
		textPane.setLayout(flow);
		textPane.setBorder(BorderFactory.createTitledBorder("操作一体化"));
		Dimension jlabelSize = new Dimension(80, 30);
		Dimension jtextSize = new Dimension(200, 30);
		JLabel idLab = new JLabel("教职工号：");
		idLab.setPreferredSize(jlabelSize);
		textPane.add(idLab);
		idText = new JTextField();
		idText.setPreferredSize(jtextSize);
		textPane.add(idText);
		JLabel nameLab = new JLabel("姓名：");
		nameLab.setPreferredSize(jlabelSize);
		textPane.add(nameLab);
		nameText = new JTextField();
		nameText.setPreferredSize(jtextSize);
		textPane.add(nameText);
		JLabel formarNameLab = new JLabel("曾用名：");
		formarNameLab.setPreferredSize(jlabelSize);
		textPane.add(formarNameLab);
		formarNameText = new JTextField();
		formarNameText.setPreferredSize(jtextSize);
		textPane.add(formarNameText);
		JLabel sexLab = new JLabel("性别：");
		sexLab.setPreferredSize(jlabelSize);
		textPane.add(sexLab);
		sexComBox = new JComboBox<String>();
		sexComBox.setPreferredSize(jtextSize);
		textPane.add(sexComBox);
		JLabel ageLab = new JLabel("年龄：");
		ageLab.setPreferredSize(jlabelSize);
		textPane.add(ageLab);
		ageText = new JTextField();
		ageText.setPreferredSize(jtextSize);
		textPane.add(ageText);
		JLabel nativePlaceLab = new JLabel("籍贯：");
		nativePlaceLab.setPreferredSize(jlabelSize);
		textPane.add(nativePlaceLab);
		nativePlaceText = new JTextField();
		nativePlaceText.setPreferredSize(jtextSize);
		textPane.add(nativePlaceText);
		JLabel idCardTypeLab = new JLabel("证件类型：");
		idCardTypeLab.setPreferredSize(jlabelSize);
		textPane.add(idCardTypeLab);
		idCardTypeComBox = new JComboBox<String>();
		idCardTypeComBox.setPreferredSize(jtextSize);		
		textPane.add(idCardTypeComBox);
		JLabel idCardNumLab = new JLabel("证件号码：");
		idCardNumLab.setPreferredSize(jlabelSize);
		textPane.add(idCardNumLab);
		idCardNumText = new JTextField();
		idCardNumText.setPreferredSize(jtextSize);
		textPane.add(idCardNumText);
		JLabel telLab = new JLabel("联系方式：");
		telLab.setPreferredSize(jlabelSize);
		textPane.add(telLab);
		telText = new JTextField();
		telText.setPreferredSize(jtextSize);
		textPane.add(telText);
		
		yearLab = new JLabel("入职时间:");
		yearLab.setPreferredSize(jlabelSize);
		textPane.add(yearLab);
		yearText = new JTextField();
		DateChooser dc = DateChooser.getInstance("yyyy-MM-dd");
		dc.register(yearText);
		yearText.setPreferredSize(jtextSize);
		textPane.add(yearText);
		collegeLab = new JLabel("所属学院：");
		collegeLab.setPreferredSize(jlabelSize);
		textPane.add(collegeLab);
		collegeBox = new JComboBox<String>();
		collegeBox.setPreferredSize(jtextSize);
		textPane.add(collegeBox);
		departmentLab = new JLabel("所属系/部：");
		departmentLab.setPreferredSize(jlabelSize);
		textPane.add(departmentLab);
		departmentBox = new JComboBox<String>();
		departmentBox.setPreferredSize(jtextSize);
		textPane.add(departmentBox);
		levelLab = new JLabel("职称：");
		levelLab.setPreferredSize(jlabelSize);
		textPane.add(levelLab);
		levelBox = new JComboBox<String>();
		levelBox.setPreferredSize(jtextSize);
		textPane.add(levelBox);
		educationLab = new JLabel("学历");
		educationLab.setPreferredSize(jlabelSize);
		textPane.add(educationLab);
		educationBox = new JComboBox<String>();
		educationBox.setPreferredSize(jtextSize);
		textPane.add(educationBox);
		
		textPane.add(createTextBtnPane(size));
		return textPane;
	}
	
	protected void InitComboBox() {
			
		Dao dao = new Dao();
		String tableName = "base_teacher";
		List<String> value = dao.getEnumValue(tableName,"sex");
		for(int i=0; i < value.size(); i++) {
			sexComBox.addItem(value.get(i));
		}
		value = null;
		value = dao.getEnumValue(tableName,"IDCARD_type");
		for(int i=0; i < value.size(); i++) {
			idCardTypeComBox.addItem(value.get(i));
		}
		value = null;
		value = new CollegeDao().getIdAndNameList();
		for(int i=0; i < value.size(); i++) {
			collegeBox.addItem(value.get(i));
		}
		value = null;
		value = new DepartmentDao().getIdAndNameList();
		for(int i=0; i < value.size(); i++) {
			departmentBox.addItem(value.get(i));
		}
		value = null;
		value = dao.getEnumValue(tableName, "level");
		for(int i = 0; i<value.size(); i++) {
			levelBox.addItem(value.get(i));
		}
		value = null;
		value = dao.getEnumValue(tableName, "education");
		for(int i=0; i < value.size(); i++) {
			educationBox.addItem(value.get(i));
		}
	}
	
	protected TeacherModel getData() {
		Tools tool = new Tools();
		TeacherModel teacher = new TeacherModel();
		if(idText.getText().length() > 0) {
			teacher.getBaseInfo().setId(idText.getText());
		}else {
			JOptionPane.showMessageDialog(null, "请输入教职工号！", "警告", JOptionPane.ERROR_MESSAGE, 
					new ImageIcon(new Images().getError2()));
			return null;
		}
		if(nameText.getText().length() > 0) {
			teacher.getBaseInfo().setName(nameText.getText());
		}else {
			JOptionPane.showMessageDialog(null, "请输入姓名！", "警告", JOptionPane.ERROR_MESSAGE, 
					new ImageIcon(new Images().getError2()));
			return null;
		}
		if(formarNameText.getText().length() > 0) {
			teacher.getBaseInfo().setFormarName(formarNameText.getText());
		}else {
			JOptionPane.showMessageDialog(null, "请输入曾用名，如果没有请输入无！", "警告", 
					JOptionPane.ERROR_MESSAGE, new ImageIcon(new Images().getError2()));
			return null;
		}
		teacher.getBaseInfo().setSex(tool.Split( (String) sexComBox.getSelectedItem() ));
		try {
			teacher.getBaseInfo().setAge(Integer.parseInt( ageText.getText() ));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "请输入正确的年龄！", "警告", JOptionPane.ERROR_MESSAGE, 
					new ImageIcon(new Images().getError2()));
			return null;
		}
		if(nativePlaceText.getText().length() > 0) {
			teacher.getBaseInfo().setNativePlace(nativePlaceText.getText());
		}else {
			JOptionPane.showMessageDialog(null, "请输入籍贯！", "警告", JOptionPane.ERROR_MESSAGE, 
					new ImageIcon(new Images().getError2()));
			return null;
		}
		
		teacher.getBaseInfo().setIDCARDTYPE(tool.Split( (String) idCardTypeComBox.getSelectedItem() ));
		if(idCardNumText.getText().length() > 0) {
			teacher.getBaseInfo().setIDCARDNUM(idCardNumText.getText());
		}else {
			JOptionPane.showMessageDialog(null, "请输入证件号码！", "警告", JOptionPane.ERROR_MESSAGE,
					new ImageIcon(new Images().getError2()));
			return null;
		}
		
		teacher.getBaseInfo().setType("teacher");
		if(telText.getText().length() > 0) {
			teacher.getBaseInfo().setTel(telText.getText());
		}else {
			JOptionPane.showMessageDialog(null, "请输入电话号码！", "警告", JOptionPane.ERROR_MESSAGE, 
					new ImageIcon(new Images().getError2()));
			return null;
		}
		if(yearText.getText().length() > 0) {
			teacher.setYear(yearText.getText());
		}else {
			JOptionPane.showMessageDialog(null, "请选择入职时间！", "警告", JOptionPane.ERROR_MESSAGE,
					new ImageIcon(new Images().getError2()));
			return null;
		}
		teacher.setCollege(tool.Split(collegeBox.getSelectedItem()+""));
		teacher.setDepartment(tool.Split(departmentBox.getSelectedItem()+""));
		teacher.setLevel(levelBox.getSelectedItem()+"");
		teacher.setEducation(educationBox.getSelectedItem()+"");
		
		return teacher;
	}
	
	protected void setData(Object data) {
		TeacherModel teacher = (TeacherModel)data;
		Tools tool = new Tools();
		idText.setText(teacher.getBaseInfo().getId());
		nameText.setText(teacher.getBaseInfo().getName());
		nativePlaceText.setText(teacher.getBaseInfo().getNativePlace());
		formarNameText.setText(teacher.getBaseInfo().getFormarName());
		tool.setSelectedItem(sexComBox, teacher.getBaseInfo().getSex());
		ageText.setText(teacher.getBaseInfo().getAge()+"");
		tool.setSelectedItem(idCardTypeComBox, teacher.getBaseInfo().getIDCARDTYPE());
		idCardNumText.setText(teacher.getBaseInfo().getIDCARDNUM());
		telText.setText(teacher.getBaseInfo().getTel());
		yearText.setText(teacher.getYear());
		tool.setSelectedItem(collegeBox, teacher.getCollege());
		tool.setSelectedItem(departmentBox, teacher.getDepartment());
		levelBox.setSelectedItem(teacher.getLevel());
		educationBox.setSelectedItem(teacher.getEducation());
	}
	
	protected TeacherModel getSelectData() {
		// TODO Auto-generated method stub
		TeacherModel data = null;
		int row = tablePane.getSelectRow();
		if(row != -1) {
			data = new TeacherModel();
			data.getBaseInfo().setId( (String)tablePane.getValueAt(row, 0));
			data.getBaseInfo().setName((String)tablePane.getValueAt(row, 1));
			data.getBaseInfo().setFormarName((String)tablePane.getValueAt(row, 2));
			data.getBaseInfo().setSex((String)tablePane.getValueAt(row, 3));
			data.getBaseInfo().setAge(Integer.parseInt(tablePane.getValueAt(row, 4)+"") );
			data.getBaseInfo().setNativePlace((String)tablePane.getValueAt(row, 5));
			data.getBaseInfo().setIDCARDTYPE((String)tablePane.getValueAt(row, 6));
			data.getBaseInfo().setIDCARDNUM((String)tablePane.getValueAt(row, 7));
			data.getBaseInfo().setType((String)tablePane.getValueAt(row, 8));
			data.getBaseInfo().setTel((String)tablePane.getValueAt(row, 9));
			data.setCollege(tablePane.getValueAt(row, 10)+"");
			data.setDepartment(tablePane.getValueAt(row, 11)+"");
			data.setLevel(tablePane.getValueAt(row, 12)+"");
			data.setEducation(tablePane.getValueAt(row, 13)+"");
			data.setYear(tablePane.getValueAt(row, 14)+"");
		}
		return data;
	}
	
	protected ActionListener insertBtnAction() {
		ActionListener insertAction = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TextJDialog insertJDialog = new TextJDialog(new Dimension(630,450));
				insertJDialog.setTitle("添加教师信息");
				insertJDialog.setContentPane(InitTextPane(new Dimension(590, 40)));
				InitComboBox();
				submitBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						TeacherModel teacher = getData();
						if(teacher == null) {
							return;
						}
						TeacherDao teacherDao = new TeacherDao();
						if(teacherDao.insertData(teacher)) {
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
				});
				insertJDialog.setVisible(true);
			}
		};
		return insertAction;
	}
	
	protected ActionListener updateBtnAction() {
		ActionListener updateAction = new ActionListener() {
			
			private TextJDialog updateJDialog;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updateJDialog = new TextJDialog(new Dimension(630,450));
				updateJDialog.setTitle("修改教师信息");
				updateJDialog.setContentPane(InitTextPane(new Dimension(590, 40)));
				InitComboBox();
				TeacherModel data = getSelectData();
				if(data == null) {
					JOptionPane.showMessageDialog(null, "你没有选中一行数据！", "错误", 
							JOptionPane.ERROR_MESSAGE, new ImageIcon(new Images().getError2()));
					return ;
				}
				setData(data);
				submitBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						TeacherModel newdata = getData();
						TeacherDao teacherDao = new TeacherDao();
						if(teacherDao.updateData(newdata, data.getBaseInfo().getId())) {
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
				});
				updateJDialog.setVisible(true);
			}
		};
		return updateAction;
	}
	
	protected ActionListener deleteBtnAction() {
		ActionListener deleteAction = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int i = tablePane.getSelectRow();
				if( i == -1) {
					JOptionPane.showMessageDialog(null, "你没有选中一行数据！", "错误", 
							JOptionPane.ERROR_MESSAGE, new ImageIcon(new Images().getError2()));
					return ;
				}
				if(JOptionPane.showConfirmDialog(null, "确定要删除教职工号为："+tablePane.getValueAt(i, 0)
				+"的教师吗？", "温馨提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, 
				new ImageIcon(new Images().getWarring2())) == 0) {
					TeacherDao teacherDao = new TeacherDao();
					if(teacherDao.deleteData((String)tablePane.getValueAt(i, 0))) {
						JOptionPane.showMessageDialog(null, "教职工号为："+tablePane.getValueAt(i, 0)+
								"的教师信息已删除！", "温馨提示", JOptionPane.INFORMATION_MESSAGE, 
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
	
	protected ActionListener refreshBtnAction() {
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
	
}

	