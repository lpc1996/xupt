package xupt.frame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import xupt.dao.StudentDao;
import xupt.mode.StudentModel;

public class StudentInfo extends CommonsJDialog {

	private JPanel contentPane;
	private TablePane tablePane;
	private JPanel btnPane;
	private JButton insertBtn;
	private JButton deleteBtn;
	private JButton updateBtn;
	private JButton refreshBtn;

	public StudentInfo() {
		super(new Dimension(700, 550));
		// TODO Auto-generated constructor stub
		this.setTitle("ѧ����Ϣ����");
		initContentPane();
		initData();
	}
	
	public void initContentPane() {
		contentPane = new JPanel();
		FlowLayout flow = new FlowLayout(5);
		contentPane.setLayout(flow);
		contentPane.setBorder(BorderFactory.createTitledBorder("����һ�廯"));
		
		tablePane = new TablePane(new Dimension(675, 440));
		contentPane.add(tablePane);
		
		btnPane = new JPanel();
		btnPane.setLayout(flow);
		btnPane.setPreferredSize(new Dimension(675,40));
		
		insertBtn = new JButton("���");
		insertBtn.setPreferredSize(new Dimension(80,30));
		insertBtnAction();
		updateBtn = new JButton("�޸�");
		updateBtn.setPreferredSize(new Dimension(80,30));
		deleteBtn = new JButton("ɾ��");
		deleteBtn.setPreferredSize(new Dimension(80,30));
		refreshBtn = new JButton("ˢ��");
		refreshBtn.setPreferredSize(new Dimension(80,30));
		btnPane.add(insertBtn);
		btnPane.add(updateBtn);
		btnPane.add(deleteBtn);
		btnPane.add(refreshBtn);
		contentPane.add(btnPane);
		
		this.setContentPane(contentPane);
	}
	
	private void initData() {
		StudentDao studentDao = new StudentDao();
		List studentList = studentDao.getList();
		Vector comments = studentDao.getComments();
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
	
	private void insertBtnAction() {
		insertBtn.addActionListener(new ActionListener() {
			private TextJDialog insertJDialog;
			private JTextField idText;
			private JTextField nameText;
			private JTextField formarNameText;
			private JComboBox sexComBox;
			private JTextField ageText;
			private JTextField nativePlaceText;
			private JComboBox idCardTypeComBox;
			private JTextField userTypeText;
			private JTextField telText;
			private JTextField yearText;
			private JComboBox collegeComBox;
			private JComboBox departmentBox;
			private JComboBox majorBox;
			private JComboBox gradeBox;
			private JComboBox classBox;
			private JComboBox cultureLevelBox;
			private JComboBox studentTypeBox;
			private JTextField educationText;
			private JButton submitBtn;
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				insertJDialog = new TextJDialog(new Dimension(630,300));
				insertJDialog.setTitle("���ѧ����Ϣ");
				initContentPane();
				insertJDialog.setVisible(true);
			}
			
			private void initContentPane() {
				contentPane = new JPanel();
				FlowLayout flow = new FlowLayout(FlowLayout.LEFT, 5, 5);
				contentPane.setLayout(flow);
				contentPane.setBorder(BorderFactory.createTitledBorder("����һ�廯"));
				Dimension jlabelSize = new Dimension(80, 30);
				Dimension jtextSize = new Dimension(200, 30);
				JLabel idLab = new JLabel("ѧ�ţ�");
				idLab.setPreferredSize(jlabelSize);
				contentPane.add(idLab);
				idText = new JTextField();
				idText.setPreferredSize(jtextSize);
				contentPane.add(idText);
				JLabel nameLab = new JLabel("������");
				nameLab.setPreferredSize(jlabelSize);
				contentPane.add(nameLab);
				nameText = new JTextField();
				nameText.setPreferredSize(jtextSize);
				contentPane.add(nameText);
				JLabel formarNameLab = new JLabel("��������");
				formarNameLab.setPreferredSize(jlabelSize);
				contentPane.add(formarNameLab);
				formarNameText = new JTextField();
				formarNameText.setPreferredSize(jtextSize);
				contentPane.add(formarNameText);
				JLabel sexLab = new JLabel("�Ա�");
				sexLab.setPreferredSize(jlabelSize);
				contentPane.add(sexLab);
				sexComBox = new JComboBox();
				sexComBox.addItem("��");
				sexComBox.addItem("Ů");
				sexComBox.addItem("����");
				sexComBox.setPreferredSize(jtextSize);
				contentPane.add(sexComBox);
				JLabel ageLab = new JLabel("���䣺");
				ageLab.setPreferredSize(jlabelSize);
				contentPane.add(ageLab);
				ageText = new JTextField();
				ageText.setPreferredSize(jtextSize);
				contentPane.add(ageText);
				JLabel nativePlaceLab = new JLabel("���᣺");
				nativePlaceLab.setPreferredSize(jlabelSize);
				contentPane.add(nativePlaceLab);
				nativePlaceText = new JTextField();
				nativePlaceText.setPreferredSize(jtextSize);
				contentPane.add(nativePlaceText);
				JLabel idCardTypeLab = new JLabel("֤�����ͣ�");
				idCardTypeLab.setPreferredSize(jlabelSize);
				contentPane.add(idCardTypeLab);
				idCardTypeComBox = new JComboBox();
				idCardTypeComBox.setPreferredSize(jtextSize);
				idCardTypeComBox.addItem("�������֤");
				contentPane.add(idCardTypeComBox);
				JLabel userTypeLab = new JLabel("�û����ͣ�");
				userTypeLab.setPreferredSize(jlabelSize);
				contentPane.add(userTypeLab);
				userTypeText = new JTextField();
				userTypeText.setPreferredSize(jtextSize);
				contentPane.add(userTypeText);
				JLabel telLab = new JLabel("��ϵ��ʽ��");
				telLab.setPreferredSize(jlabelSize);
				contentPane.add(telLab);
				telText = new JTextField();
				telText.setPreferredSize(jtextSize);
				contentPane.add(telText);
				JLabel yearLab = new JLabel("��ѧ���ڣ�");
				yearLab.setPreferredSize(jlabelSize);
				contentPane.add(yearLab);
				yearText = new JTextField();
				yearText.setPreferredSize(jtextSize);
				contentPane.add(yearText);
				JLabel collegeLab = new JLabel("����ѧԺ��");
				collegeLab.setPreferredSize(jlabelSize);
				contentPane.add(collegeLab);
				collegeComBox = new JComboBox();
				collegeComBox.setPreferredSize(jtextSize);
				contentPane.add(collegeComBox);
				JLabel departmentLab = new JLabel("ϵ����");
				departmentLab.setPreferredSize(jlabelSize);
				contentPane.add(departmentLab);
				departmentBox = new JComboBox();
				departmentBox.setPreferredSize(jtextSize);
				contentPane.add(departmentBox);
				JLabel majorLab = new JLabel("רҵ��");
				majorLab.setPreferredSize(jlabelSize);
				contentPane.add(majorLab);
				majorBox = new JComboBox();
				majorBox.setPreferredSize(jtextSize);
				contentPane.add(majorBox);
				JLabel gradeLab = new JLabel("�꼶��");
				gradeLab.setPreferredSize(jlabelSize);
				contentPane.add(gradeLab);
				gradeBox = new JComboBox();
				gradeBox.setPreferredSize(jtextSize);
				contentPane.add(gradeBox);
				JLabel classLab = new JLabel("�༶��");
				classLab.setPreferredSize(jlabelSize);
				contentPane.add(classLab);
				classBox = new JComboBox();
				classBox.setPreferredSize(jtextSize);
				contentPane.add(classBox);
				JLabel cultureLevelLab = new JLabel("������Σ�");
				cultureLevelLab.setPreferredSize(jlabelSize);
				contentPane.add(cultureLevelLab);
				cultureLevelBox = new JComboBox();
				cultureLevelBox.setPreferredSize(jtextSize);
				contentPane.add(cultureLevelBox);
				JLabel studentTypeLab = new JLabel("ѧ�����");
				studentTypeLab.setPreferredSize(jlabelSize);
				contentPane.add(studentTypeLab);
				studentTypeBox = new JComboBox();
				studentTypeBox.setPreferredSize(jtextSize);
				contentPane.add(studentTypeBox);
				JLabel educationLab = new JLabel("ѧ����");
				educationLab.setPreferredSize(jlabelSize);
				contentPane.add(educationLab);
				educationText = new JTextField();
				educationText.setPreferredSize(jtextSize);
				contentPane.add(educationText);
				submitBtn = new JButton("�ύ");
				submitBtn.setPreferredSize(new Dimension(80,30));
				contentPane.add(submitBtn);
				insertJDialog.setContentPane(contentPane);
			}
		});
	}
	
}
