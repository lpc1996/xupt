package xupt.frame;

import java.awt.Dimension;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import xupt.dao.DepartmentDao;
import xupt.mode.CollegeModel;
import xupt.mode.DepartmentModel;

public class DepartmentInfo extends CommonsJDialog {

	public DepartmentInfo() {
		super(new Dimension(400, 500));
		// TODO Auto-generated constructor stub
		this.setTitle("系部信息管理");
		InitContentPane();
//		addInsertAction(insertBtnAction());
//		addUpdateAction(updateBtnAction());
//		addDeleteAction(deleteBtnAction());
//		addRefreshAction(refreshBtnAction());
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

}
