package xupt.frame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import xupt.dao.TeacherDao;
import xupt.mode.TeacherModel;
import xupt.util.Tools;

public class TeacherInfo extends CommonsJDialog {
	
	/**
	 * 教职工信息管理
	 */
	private static final long serialVersionUID = 1L;
	private Tools tool;
	private JPanel contentPane;
	private TablePane tablePane;
	private JPanel btnPanel;

	public TeacherInfo() {
		super(new Dimension(675,550));
		// TODO Auto-generated constructor stub
		 setTitle("教职工信息管理");
		 tool = new Tools();
		 initContentPane();
		 initData();
	}
	
	private void initContentPane() {
		contentPane = new JPanel();
		FlowLayout flow = new FlowLayout(FlowLayout.LEFT, 5, 5);
		contentPane.setLayout(flow);
		contentPane.setBorder(BorderFactory.createTitledBorder("操作一体化"));
		
		tablePane = new TablePane(new Dimension(getWidth()-10,440));
		contentPane.add(tablePane);
		
		btnPanel = createBtnPanel();
		
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
	
}

	