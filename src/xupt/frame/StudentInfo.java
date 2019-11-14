package xupt.frame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import xupt.dao.StudentDao;
import xupt.mode.StudentModel;

public class StudentInfo extends CommonsJDialog {

	private JPanel contentPane;
	private TablePane tablePane;

	public StudentInfo() {
		super(new Dimension(700, 550));
		// TODO Auto-generated constructor stub
		this.setTitle("学生信息管理");
		initContentPane();
		initData();
	}
	
	public void initContentPane() {
		contentPane = new JPanel();
		FlowLayout flow = new FlowLayout(5);
		contentPane.setLayout(flow);
		
		tablePane = new TablePane();
		tablePane.setPreferredSize(new Dimension(675, 450) );
//		tablePane.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		contentPane.add(tablePane);
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
			model.setValueAt(student.getType(), i, 16);
			model.setValueAt(student.getEducation(), i, 17);
			i++;
		}
		tablePane.setTableModel(model);
	}
	
}
