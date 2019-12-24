package xupt.frame;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import xupt.dao.BaseInfoDao;
import xupt.dao.CollegeDao;
import xupt.dao.Dao;
import xupt.dao.DepartmentDao;
import xupt.dao.StudentDao;
import xupt.dao.TeacherDao;
import xupt.mode.TeacherModel;
import xupt.util.DateChooser;
import xupt.util.Tools;

public class TeacherInfo extends CommonsJDialog {
	
	/**
	 * ��ְ����Ϣ����
	 */
	private static final long serialVersionUID = 1L;
	private Tools tool;
	private JPanel contentPane;
	private TablePane tablePane;
	private JPanel btnPanel;
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

	public TeacherInfo() {
		super(new Dimension(675,550));
		// TODO Auto-generated constructor stub
		 setTitle("��ְ����Ϣ����");
		 tool = new Tools();
		 initContentPane();
		 initData();
	}
	
	private void initContentPane() {
		contentPane = new JPanel();
		FlowLayout flow = new FlowLayout(FlowLayout.LEFT, 5, 5);
		contentPane.setLayout(flow);
		contentPane.setBorder(BorderFactory.createTitledBorder("����һ�廯"));
		
		tablePane = new TablePane(new Dimension(getWidth()-10,440));
		contentPane.add(tablePane);
		
		btnPanel = createBtnPanel();
		addInsertAction(insertBtnAction());
		
		contentPane.add(btnPanel);
		this.setContentPane(contentPane);
	}
	
	private void initData() {
		TeacherDao teacherDao = new TeacherDao();
		List<TeacherModel> teacherList = teacherDao.getList();
		Vector<String> comments = teacherDao.getComments();
		DefaultTableModel model = new DefaultTableModel(comments, teacherList.size());
		int i=0;
		for(TeacherModel teacher:teacherList) {
			model.setValueAt(teacher.getBase().getId(), i, 0);
			model.setValueAt(teacher.getBase().getName(), i, 1);
			model.setValueAt(teacher.getBase().getFormarName(), i, 2);
			model.setValueAt(teacher.getBase().getSex(), i, 3);
			model.setValueAt(teacher.getBase().getAge(), i, 4);
			model.setValueAt(teacher.getBase().getNativePlace(), i, 5);
			model.setValueAt(teacher.getBase().getIDCARDTYPE(), i, 6);
			model.setValueAt(teacher.getBase().getIDCARDNUM(), i, 7);
			model.setValueAt(teacher.getBase().getType(), i, 8);
			model.setValueAt(teacher.getBase().getTel(), i, 9);
			model.setValueAt(teacher.getCollege(), i, 10);
			model.setValueAt(teacher.getDepartment(), i, 11);
			model.setValueAt(teacher.getLevel(), i, 12);
			model.setValueAt(teacher.getEducation(), i, 13);
			model.setValueAt(teacher.getYear(), i, 14);
			i++;
		}
		tablePane.setTableModel(model);
		tablePane.setColumnWidth(60);
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
		
		yearLab = new JLabel("��ְʱ��:");
		yearLab.setPreferredSize(jlabelSize);
		JTextPane.add(yearLab);
		yearText = new JTextField();
		DateChooser dc = DateChooser.getInstance("yyyy-MM-dd");
		dc.register(yearText);
		yearText.setPreferredSize(jtextSize);
		JTextPane.add(yearText);
		collegeLab = new JLabel("����ѧԺ��");
		collegeLab.setPreferredSize(jlabelSize);
		JTextPane.add(collegeLab);
		collegeBox = new JComboBox<String>();
		collegeBox.setPreferredSize(jtextSize);
		JTextPane.add(collegeBox);
		departmentLab = new JLabel("����ϵ/����");
		departmentLab.setPreferredSize(jlabelSize);
		JTextPane.add(departmentLab);
		departmentBox = new JComboBox<String>();
		departmentBox.setPreferredSize(jtextSize);
		JTextPane.add(departmentBox);
		levelLab = new JLabel("ְ�ƣ�");
		levelLab.setPreferredSize(jlabelSize);
		JTextPane.add(levelLab);
		levelBox = new JComboBox<String>();
		levelBox.setPreferredSize(jtextSize);
		JTextPane.add(levelBox);
		educationLab = new JLabel("ѧ��");
		educationLab.setPreferredSize(jlabelSize);
		JTextPane.add(educationLab);
		educationBox = new JComboBox<String>();
		educationBox.setPreferredSize(jtextSize);
		JTextPane.add(educationBox);
		
		return JTextPane;
	}
	
	private void initComBox() {
			
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
	
	private ActionListener insertBtnAction() {
		ActionListener insertAction = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TextJDialog insertJDialog = new TextJDialog(new Dimension(630,450));
				insertJDialog.setTitle("��ӽ�ʦ��Ϣ");
				insertJDialog.setContentPane(initJTextPane());
				initComBox();
				insertJDialog.setVisible(true);
			}
		};
		return insertAction;
	}
	
	
	
}

	