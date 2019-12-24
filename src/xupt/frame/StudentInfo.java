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
import xupt.dao.ClassDao;
import xupt.dao.CollegeDao;
import xupt.dao.Dao;
import xupt.dao.DepartmentDao;
import xupt.dao.MajorDao;
import xupt.dao.SemesterDao;
import xupt.dao.StudentDao;
import xupt.images.Images;
import xupt.mode.StudentModel;
import xupt.util.DateChooser;
import xupt.util.Tools;

public class StudentInfo extends CommonsJDialog {

	/**
	 * ѧ����Ϣ����
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private TablePane tablePane;
	private JPanel btnPane;
	private JTextField idText;
	private JTextField nameText;
	private JTextField formarNameText;
	private JComboBox<String> sexComBox;
	private JTextField ageText;
	private JTextField nativePlaceText;
	private JComboBox<String> idCardTypeComBox;
	private JTextField telText;
	private JTextField yearText;
	private JComboBox<String> collegeComBox;
	private JComboBox<String> departmentBox;
	private JComboBox<String> majorBox;
	private JComboBox<String> gradeBox;
	private JComboBox<String> classBox;
	private JComboBox<String> cultureLevelBox;
	private JComboBox<String> studentTypeBox;
	private JTextField educationText;
	private JButton submitBtn;
	private JTextField idCardNumText;
	private Tools tool;

	public StudentInfo() {
		super(new Dimension(700, 550));
		// TODO Auto-generated constructor stub
		this.setTitle("ѧ����Ϣ����");
		tool = new Tools();
		initContentPane();
		initData();
	}
	
	public void initContentPane() {
		contentPane = new JPanel();
		FlowLayout flow = new FlowLayout(5);
		contentPane.setLayout(flow);
		contentPane.setBorder(BorderFactory.createTitledBorder("����һ�廯"));
		
		tablePane = new TablePane(new Dimension(getWidth()-10, 440));
		contentPane.add(tablePane);

		btnPane = createBtnPanel();
		addInsertAction(insertBtnAction());
		addUpdateAction(updateBtnAction());
		addDeleteAction(deleteBtnAction());
		addRefreshAction(refreshBtnAction());
		
		contentPane.add(btnPane);
		
		this.setContentPane(contentPane);
	}
	
	private void initData() {
		StudentDao studentDao = new StudentDao();
		List<StudentModel> studentList = studentDao.getList();
		Vector<String> comments = studentDao.getComments();
		DefaultTableModel model = new DefaultTableModel(comments,studentList.size());
		int i=0;
		for(Object data:studentList) {
			StudentModel student = (StudentModel)data;
			model.setValueAt(student.getBaseInfo().getId(), i, 0);
			model.setValueAt(student.getBaseInfo().getName(), i, 1);
			model.setValueAt(student.getBaseInfo().getFormarName(), i, 2);
			model.setValueAt(student.getBaseInfo().getSex(), i, 3);
			model.setValueAt(student.getBaseInfo().getAge(), i, 4);
			model.setValueAt(student.getBaseInfo().getNativePlace(), i, 5);
			model.setValueAt(student.getBaseInfo().getIDCARDTYPE(), i, 6);
			model.setValueAt(student.getBaseInfo().getIDCARDNUM(), i, 7);
			model.setValueAt(student.getBaseInfo().getType(), i, 8);
			model.setValueAt(student.getBaseInfo().getTel(), i, 9);
			model.setValueAt(student.getYear(), i, 10);
			model.setValueAt(student.getCollege(), i, 11);
			model.setValueAt(student.getDepartment(), i, 12);
			model.setValueAt(student.getMajor(), i, 13);
			model.setValueAt(student.getGrade(), i, 14);
			model.setValueAt(student.getClassId(), i, 15);
			model.setValueAt(student.getCulture_level(), i, 16);
			model.setValueAt(student.getType(), i, 17);
			model.setValueAt(student.getEducation(), i, 18);
			i++;
		}
		tablePane.setTableModel(model);
		tablePane.setColumnWidth(80);
	}
	
	private ActionListener insertBtnAction() {
		 ActionListener insertAction = new ActionListener() {
			
			private TextJDialog insertJDialog;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				insertJDialog = new TextJDialog(new Dimension(630,450));
				insertJDialog.setTitle("���ѧ����Ϣ");
				insertJDialog.setContentPane( initJTextPane() );
				initComBox();
				submitBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						StudentModel student = getData();
						if(student == null) {
							return;
						}
						StudentDao studentDao = new StudentDao();
						if(studentDao.insertData(student)) {
							JOptionPane.showMessageDialog(null, "��ӳɹ���", "��ܰ��ʾ",
									JOptionPane.OK_OPTION, new ImageIcon(new Images().getSuccessful()));
							initData();
							contentPane.repaint();
							insertJDialog.dispose();
						}else {
							JOptionPane.showMessageDialog(null, "���ʧ�ܣ�", "��ʾ��Ϣ",
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
	
	private ActionListener updateBtnAction() {
		ActionListener updateAction = new ActionListener() {
			
			private TextJDialog updateJDialog;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updateJDialog = new TextJDialog(new Dimension(630,450));
				updateJDialog.setTitle("�޸�ѧ����Ϣ");
				updateJDialog.setContentPane(initJTextPane());
				initComBox();
				StudentModel data = tablePane.getSelectData();
				if(data == null) {
					JOptionPane.showMessageDialog(null, "��û��ѡ��һ�����ݣ�", "����", 
							JOptionPane.ERROR_MESSAGE, new ImageIcon(new Images().getError2()));
					return ;
				}
				setData(data);
				submitBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						StudentModel data = getData();
						StudentDao studentDao = new StudentDao();
						if(studentDao.updateData(data, data.getBaseInfo().getId())) {
							JOptionPane.showMessageDialog(null, "�޸ĳɹ���", "��ܰ��ʾ", 
									JOptionPane.OK_OPTION, new ImageIcon(new Images().getSuccessful()));
							initData();
							contentPane.repaint();
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
	
	private ActionListener deleteBtnAction() {
		ActionListener deleteAction = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int i = tablePane.getSelectRow();
				if( i == -1) {
					JOptionPane.showMessageDialog(null, "��û��ѡ��һ�����ݣ�", "����", 
							JOptionPane.ERROR_MESSAGE, new ImageIcon(new Images().getError2()));
					return ;
				}
				if(JOptionPane.showConfirmDialog(null, "ȷ��Ҫɾ��ѧ��Ϊ��"+tablePane.getValueAt(i, 0)
				+"��ѧ����", "��ܰ��ʾ", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, 
				new ImageIcon(new Images().getWarring2())) == 0) {
					StudentDao studentDao = new StudentDao();
					if(studentDao.deleteData((String)tablePane.getValueAt(i, 0))) {
						JOptionPane.showMessageDialog(null, "ѧ��Ϊ��"+tablePane.getValueAt(i, 0)+
								"��ѧ����Ϣ��ɾ����", "��ܰ��ʾ", JOptionPane.INFORMATION_MESSAGE, 
								new ImageIcon(new Images().getYes2()));
						initData();
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
	
	private ActionListener refreshBtnAction() {
		ActionListener refreshAction = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				initData();
				repaint();
			}
		};
		return refreshAction;
	}
	
	private JPanel initJTextPane() {
		JPanel JTextPane = new JPanel();
		FlowLayout flow = new FlowLayout(FlowLayout.LEFT, 5, 5);
		JTextPane.setLayout(flow);
		JTextPane.setBorder(BorderFactory.createTitledBorder("����һ�廯"));
		Dimension jlabelSize = new Dimension(80, 30);
		Dimension jtextSize = new Dimension(200, 30);
		JLabel idLab = new JLabel("ѧ�ţ�");
		idLab.setPreferredSize(jlabelSize);
		JTextPane.add(idLab);
		idText = new JTextField();
		idText.setPreferredSize(jtextSize);
		JTextPane.add(idText);
		JLabel nameLab = new JLabel("������");
		nameLab.setPreferredSize(jlabelSize);
		JTextPane.add(nameLab);
		nameText = new JTextField();
		nameText.setPreferredSize(jtextSize);
		JTextPane.add(nameText);
		JLabel formarNameLab = new JLabel("��������");
		formarNameLab.setPreferredSize(jlabelSize);
		JTextPane.add(formarNameLab);
		formarNameText = new JTextField();
		formarNameText.setPreferredSize(jtextSize);
		JTextPane.add(formarNameText);
		JLabel sexLab = new JLabel("�Ա�");
		sexLab.setPreferredSize(jlabelSize);
		JTextPane.add(sexLab);
		sexComBox = new JComboBox<String>();
		sexComBox.setPreferredSize(jtextSize);
		JTextPane.add(sexComBox);
		JLabel ageLab = new JLabel("���䣺");
		ageLab.setPreferredSize(jlabelSize);
		JTextPane.add(ageLab);
		ageText = new JTextField();
		ageText.setPreferredSize(jtextSize);
		JTextPane.add(ageText);
		JLabel nativePlaceLab = new JLabel("���᣺");
		nativePlaceLab.setPreferredSize(jlabelSize);
		JTextPane.add(nativePlaceLab);
		nativePlaceText = new JTextField();
		nativePlaceText.setPreferredSize(jtextSize);
		JTextPane.add(nativePlaceText);
		JLabel idCardTypeLab = new JLabel("֤�����ͣ�");
		idCardTypeLab.setPreferredSize(jlabelSize);
		JTextPane.add(idCardTypeLab);
		idCardTypeComBox = new JComboBox<String>();
		idCardTypeComBox.setPreferredSize(jtextSize);		
		JTextPane.add(idCardTypeComBox);
		JLabel idCardNumLab = new JLabel("֤�����룺");
		idCardNumLab.setPreferredSize(jlabelSize);
		JTextPane.add(idCardNumLab);
		idCardNumText = new JTextField();
		idCardNumText.setPreferredSize(jtextSize);
		JTextPane.add(idCardNumText);
		JLabel telLab = new JLabel("��ϵ��ʽ��");
		telLab.setPreferredSize(jlabelSize);
		JTextPane.add(telLab);
		telText = new JTextField();
		telText.setPreferredSize(jtextSize);
		JTextPane.add(telText);
		JLabel yearLab = new JLabel("��ѧ���ڣ�");
		yearLab.setPreferredSize(jlabelSize);
		JTextPane.add(yearLab);
		yearText = new JTextField();
		DateChooser dateChooser = DateChooser.getInstance("yyyy-MM-dd");
		dateChooser.register(yearText);
		yearText.setPreferredSize(jtextSize);
		yearText.setText(tool.getNowTime());
		JTextPane.add(yearText);
		JLabel collegeLab = new JLabel("����ѧԺ��");
		collegeLab.setPreferredSize(jlabelSize);
		JTextPane.add(collegeLab);
		collegeComBox = new JComboBox<String>();
		collegeComBox.setPreferredSize(jtextSize);
		JTextPane.add(collegeComBox);
		JLabel departmentLab = new JLabel("ϵ����");
		departmentLab.setPreferredSize(jlabelSize);
		JTextPane.add(departmentLab);
		departmentBox = new JComboBox<String>();
		departmentBox.setPreferredSize(jtextSize);
		JTextPane.add(departmentBox);
		JLabel majorLab = new JLabel("רҵ��");
		majorLab.setPreferredSize(jlabelSize);
		JTextPane.add(majorLab);
		majorBox = new JComboBox<String>();
		majorBox.setPreferredSize(jtextSize);
		JTextPane.add(majorBox);
		JLabel gradeLab = new JLabel("�꼶��");
		gradeLab.setPreferredSize(jlabelSize);
		JTextPane.add(gradeLab);
		gradeBox = new JComboBox<String>();
		gradeBox.setPreferredSize(jtextSize);
		JTextPane.add(gradeBox);
		JLabel classLab = new JLabel("�༶��");
		classLab.setPreferredSize(jlabelSize);
		JTextPane.add(classLab);
		classBox = new JComboBox<String>();
		classBox.setPreferredSize(jtextSize);
		JTextPane.add(classBox);
		JLabel cultureLevelLab = new JLabel("������Σ�");
		cultureLevelLab.setPreferredSize(jlabelSize);
		JTextPane.add(cultureLevelLab);
		cultureLevelBox = new JComboBox<String>();
		cultureLevelBox.setPreferredSize(jtextSize);
		JTextPane.add(cultureLevelBox);
		JLabel studentTypeLab = new JLabel("ѧ�����");
		studentTypeLab.setPreferredSize(jlabelSize);
		JTextPane.add(studentTypeLab);
		
		studentTypeBox = new JComboBox<String>();
		studentTypeBox.setPreferredSize(jtextSize);
		JTextPane.add(studentTypeBox);
		JLabel educationLab = new JLabel("ѧ����");
		educationLab.setPreferredSize(jlabelSize);
		JTextPane.add(educationLab);
		educationText = new JTextField();
		educationText.setPreferredSize(jtextSize);
		JTextPane.add(educationText);
		JPanel btnPanel = new JPanel();
		btnPanel.setPreferredSize(new Dimension(620,40));
		btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		submitBtn = new JButton("�ύ");
		submitBtn.setPreferredSize(new Dimension(80,30));
		btnPanel.add(submitBtn);
		JTextPane.add(btnPanel);
		return JTextPane;
	}
	
	private void initComBox() {
		
		Dao dao = new Dao();
		String tableName = "base_student";
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
		for(int i=0; i<value.size(); i++) {
			collegeComBox.addItem(	value.get(i) );
		}
		value = null;
		value = new DepartmentDao().getIdAndNameList();
		for(int i=0; i<value.size(); i++) {
			departmentBox.addItem(value.get(i));
		}
		value = null;
		value = new MajorDao().getIdAndNameList();
		for(int i=0; i<value.size(); i++) {
			majorBox.addItem(value.get(i));
		}
		value = null;
		value = new SemesterDao().getIdAndNameList();
		for(int i=0; i<value.size(); i++) {
			gradeBox.addItem(value.get(i));
		}
		value = null;
		value = new ClassDao().getIdAndNameList();
		for(int i=0; i<value.size(); i++) {
			classBox.addItem(value.get(i));
		}
		
		value = null;
		value = dao.getEnumValue(tableName, "culture_level");
		for(int i=0; i< value.size(); i++) {
			cultureLevelBox.addItem(value.get(i));
		}
		value = null;
		value = dao.getEnumValue(tableName, "student_type");
		for(int i=0; i <value.size(); i++) {
			studentTypeBox.addItem(value.get(i));
		}
		
	}
	
	private StudentModel getData() {
		StudentModel student = new StudentModel();
		if(idText.getText().length() > 0) {
			student.getBaseInfo().setId(idText.getText());
		}else {
			JOptionPane.showMessageDialog(null, "������ѧ�ţ�", "����", JOptionPane.ERROR_MESSAGE, 
					new ImageIcon(new Images().getError2()));
			return null;
		}
		if(nameText.getText().length() > 0) {
			student.getBaseInfo().setName(nameText.getText());
		}else {
			JOptionPane.showMessageDialog(null, "������������", "����", JOptionPane.ERROR_MESSAGE, 
					new ImageIcon(new Images().getError2()));
			return null;
		}
		if(formarNameText.getText().length() > 0) {
			student.getBaseInfo().setFormarName(formarNameText.getText());
		}else {
			JOptionPane.showMessageDialog(null, "�����������������û���������ޣ�", "����", 
					JOptionPane.ERROR_MESSAGE, new ImageIcon(new Images().getError2()));
			return null;
		}
		student.getBaseInfo().setSex(tool.Split( (String) sexComBox.getSelectedItem() ));
		try {
			student.getBaseInfo().setAge(Integer.parseInt( ageText.getText() ));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "��������ȷ�����䣡", "����", JOptionPane.ERROR_MESSAGE, 
					new ImageIcon(new Images().getError2()));
			return null;
		}
		if(nativePlaceText.getText().length() > 0) {
			student.getBaseInfo().setNativePlace(nativePlaceText.getText());
		}else {
			JOptionPane.showMessageDialog(null, "�����뼮�ᣡ", "����", JOptionPane.ERROR_MESSAGE, 
					new ImageIcon(new Images().getError2()));
			return null;
		}
		
		student.getBaseInfo().setIDCARDTYPE(tool.Split( (String) idCardTypeComBox.getSelectedItem() ));
		if(idCardNumText.getText().length() > 0) {
			student.getBaseInfo().setIDCARDNUM(idCardNumText.getText());
		}else {
			JOptionPane.showMessageDialog(null, "������֤�����룡", "����", JOptionPane.ERROR_MESSAGE,
					new ImageIcon(new Images().getError2()));
			return null;
		}
		
		student.getBaseInfo().setType("student");
		if(telText.getText().length() > 0) {
			student.getBaseInfo().setTel(telText.getText());
		}else {
			JOptionPane.showMessageDialog(null, "������绰���룡", "����", JOptionPane.ERROR_MESSAGE, 
					new ImageIcon(new Images().getError2()));
			return null;
		}
		if(yearText.getText().length() > 0) {
			student.setYear(yearText.getText());
		}else {
			JOptionPane.showMessageDialog(null, "��ѡ����ѧʱ�䣡", "����", JOptionPane.ERROR_MESSAGE,
					new ImageIcon(new Images().getError2()));
			return null;
		}
		student.setCollege( tool.Split( (String)(collegeComBox.getSelectedItem() ) ));
		student.setDepartment(tool.Split( (String)departmentBox.getSelectedItem() ));
		student.setMajor( tool.Split( (String)majorBox.getSelectedItem() )  );
		student.setGrade(  tool.Split( (String)gradeBox.getSelectedItem() ) );
		student.setClassId( tool.Split( (String)classBox.getSelectedItem() ) );
		student.setCulture_level( tool.Split( (String)cultureLevelBox.getSelectedItem() ) );
		student.setType(tool.Split( (String)studentTypeBox.getSelectedItem() ));
		if(educationText.getText().length() > 0) {
			student.setEducation(educationText.getText());
		}else {
			JOptionPane.showMessageDialog(null, "������ѧ�������û���������ޣ�", "����", 
					JOptionPane.ERROR_MESSAGE, new ImageIcon(new Images().getError2()));
			return null;
		}
		
		return student;
	}
	
	private void setData(StudentModel data) {
		idText.setText(data.getBaseInfo().getId());
		nameText.setText(data.getBaseInfo().getName());
		nativePlaceText.setText(data.getBaseInfo().getNativePlace());
		formarNameText.setText(data.getBaseInfo().getFormarName());
		tool.setSelectedItem(sexComBox, data.getBaseInfo().getSex());
		ageText.setText(data.getBaseInfo().getAge()+"");
		tool.setSelectedItem(idCardTypeComBox, data.getBaseInfo().getIDCARDTYPE());
		idCardNumText.setText(data.getBaseInfo().getIDCARDNUM());
		telText.setText(data.getBaseInfo().getTel());
		yearText.setText(data.getYear());
		tool.setSelectedItem(collegeComBox, data.getCollege());
		tool.setSelectedItem(departmentBox, data.getDepartment());
		tool.setSelectedItem(majorBox, data.getMajor());
		tool.setSelectedItem(gradeBox, data.getGrade());
		tool.setSelectedItem(classBox, data.getClassId());
		tool.setSelectedItem(cultureLevelBox, data.getCulture_level());
		tool.setSelectedItem(studentTypeBox, data.getType());
		educationText.setText(data.getEducation());
	}
	
}
